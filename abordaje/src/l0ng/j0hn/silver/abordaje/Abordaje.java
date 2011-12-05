/**
* Abordaje
* Proyecto abordaje. Navega por una web, sigue los vínculos y trata
* de extraer información interesante.
*
* Para compilar:
*   javac Abordaje.java
*
* Para ejecutarlo:
*   java Abordaje
*/
package l0ng.j0hn.silver.abordaje;

// Librería necesaria para trabajar con la entrada/salida
import java.io.*;
import java.util.Enumeration;
import java.util.Vector;
import java.util.ArrayList;
import java.util.regex.*;

/**
* clase Abordaje
* Pone en marcha el cotarro
*
* @author An0nymous from Iruñea
* @version 1.0
*/
public class Abordaje {
	
	private int _mode;
	private int _proxy = 0;
	private ProxyServers proxyServers;
	
	public Abordaje(int mode, int proxy) {
		_proxy = proxy;
		_mode = mode;
		proxyServers = new ProxyServers();
		initProxies();
	}


	/**
	* initProxies
	*
	*/
	private void initProxies () {
		if (_proxy==1) {
			proxyServers.cleanProxyServers();
			proxyServers.getProxies();
		} else {
			proxyServers.loadServers();
		}
		
	}
	
	/**
	* crawl
	* Se sumergue en el arbol hasta el fondo. 
	* Por cada serie mira cada temporada cada capitulo cada fichero... 
	* Opción lenta con varios while anindados
	*/	
	public void crawl() {
		Indice indices = new Indice();
		Serie serie;
		Temporada temporada;
		//Capitulo capitulo;
		Fichero fichero;
		String indiceActual = "";
		String serieActual = "";
		int i = 0;
		
		Enumeration indice = indices.getIndices();
		Enumeration series = null;
		Enumeration temporadas = null;
		ArrayList<Capitulo> capitulos = null;
		Enumeration ficheros = null;
		
			// Recorrer la estructura con un for;
		while(indice.hasMoreElements()){
			indiceActual = indice.nextElement().toString();
			
			Log.write(indiceActual);
			series = indices.getSeries(indiceActual).elements();

			while(series.hasMoreElements()){
				serie = new Serie((Enlace)series.nextElement());
				temporadas = serie.getTemporadas().elements();
	
				while (temporadas.hasMoreElements()) {
					temporada = (Temporada)temporadas.nextElement();
					capitulos = temporada.getCapitulos();		
					
					for  (Capitulo capitulo : capitulos) {
						//capitulo.insertar(temporada.getId());
						ficheros = capitulo.getFicheros().elements();
						
						while (ficheros.hasMoreElements()) {
							fichero = (Fichero)ficheros.nextElement();
							Log.write("---------------------GET "+fichero.getFinalUrl()+"--------------------");
							
						}
					}			
				}
			}	
				//		indice.finished(new Object[]{indices.elementAt(i).toString()});
		}


	}
	
	
	/**
	* secuencia
	* Va por partes: primero las series
	* Luego por cada serie, sus temporadas.
	* Luego por cada temporada, sus capítulos.
	*/
	public void secuencia () {
		Indice indices = new Indice();
		Serie serie;
		Temporada temporada;
		Capitulo capitulo;
		Fichero fichero;
		String indiceActual = "";
		String serieActual = "";
		int i = 0;

		Enumeration indice = indices.getIndices();
		Vector series = new Vector();
		Enumeration eSeries = null;
		Vector temporadas = new Vector();
		Enumeration capitulos = null;
		Enumeration ficheros = null;
		
		indices.init();
		
			// Recorrer la estructura con un for;
	while(indice.hasMoreElements()){
			indiceActual = indice.nextElement().toString();
			Log.write(indiceActual);
			series.addAll(indices.getSeries(indiceActual));
			indices.finished(new Object[]{indiceActual});
	}
		
		eSeries = series.elements();
		
	while(eSeries.hasMoreElements()){
				serie = new Serie((Enlace)eSeries.nextElement());
				temporadas.addAll(serie.getTemporadas());
		//	while (temporadas.hasMoreElements()) {
		//				temporada = (Temporada)temporadas.nextElement();
		//				capitulos = temporada.getCapitulos();		
		//	}
	}	
				

					
		/*while (capitulos.hasMoreElements()) {
						capitulo = (Capitulo)capitulos.nextElement();
						capitulo.insertar(temporada.getId());
						ficheros = capitulo.getFicheros();
		}				
		
		while (ficheros.hasMoreElements()) {
							fichero = (Fichero)ficheros.nextElement();
							Log.write("---------------------GET "+fichero.getFinalUrl()+"--------------------");
							
		}	*/
						//indice.finished(new Object[]{indices.elementAt(i).toString()});
		
	}	
	
	/**
	* serie
	* Va directa a por una serie concreta
	* @param String nombreSerie
	*/
	public void serie (String nombreSerie) {
		Indice indices = new Indice();
		Serie serie;
		Temporada temporada;
		Capitulo capitulo;
		Fichero fichero;
		String indiceActual = "";
		String serieActual = "";
		int i = 0;

		Vector series = new Vector();
		Enumeration eSeries = null;
		Vector temporadas = new Vector();
		Enumeration eTemporadas = null;
		Vector capitulos = new Vector();
		Enumeration eCapitulos = null;
		Vector ficheros = new Vector();
		Enumeration eFicheros = null;
		
		indices.init();
		
		Log.write("Serie: " + nombreSerie);
		

				serie = new Serie(nombreSerie);
				if (serie.idserie != 0) {
					temporadas.addAll(serie.getTemporadas());
					eTemporadas = temporadas.elements();
					
					while (eTemporadas.hasMoreElements()) {
						temporada = (Temporada)eTemporadas.nextElement();
						Log.write("temporada: " + temporada.getId());
						capitulos.addAll(temporada.getCapitulos());	
						eCapitulos = capitulos.elements();
				
						while (eCapitulos.hasMoreElements()) {
							capitulo = (Capitulo)eCapitulos.nextElement();
							ficheros.addAll(capitulo.getFicheros());
						}
										
				 }
					
	
						
					eFicheros = ficheros.elements();
					
					while (eFicheros.hasMoreElements()) {
							fichero = (Fichero)eFicheros.nextElement();
							Log.write("---------------------GET "+fichero.getFinalUrl()+"--------------------");
							
					}	
						//indice.finished(new Object[]{indices.elementAt(i).toString()});			
				}	else {
					Log.write("Serie no encontrada...");
				}
				

					

		
	}	
	
	public static boolean isNumeric(String aStringValue) {
		Pattern pattern = Pattern.compile( "\\d+" );

		Matcher matcher = pattern.matcher(aStringValue);
		return matcher.matches();
	} 

	/**
	* main
	* Función principal
	* esta función es la que se inicia directamente al ejecutar el programa
	* ATENCIÓN: debemos incluir una sentencia en la declaración de la función
	* para "avisar" de que puede ocurrir un error. Lo exige el compilador
	*/
	public static void main (String args[])
	{
		int opcion = 0;
		int proxy = 0;
		
		if (args.length==0 || !isNumeric(args[0])) {
			Log.write("Tomando opción por defecto: 0, secuencial");
			opcion = 0;
		} else {
			opcion = Integer.parseInt(args[0]);
		}

		// Cargamos segundo argumento, si lo hay
		if (args.length>2 && args[2]!= "") {
			proxy = Integer.parseInt(args[2]);
		}
		
				
		Abordaje abordaje = new Abordaje(opcion, proxy);
		Log.write("Al abordaje!! : " + opcion);
		





		switch (opcion) {
			case 0:   abordaje.secuencia();
							  break;
			case 1: abordaje.crawl();
							  break;
			case 2: 		if (args.length>1 && args[1]!="")
							  abordaje.serie(args[1]);
							  break;
			default :	  abordaje.secuencia();
							  break;
		}

	}// main
}// class
