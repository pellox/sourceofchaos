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

/**
* clase Abordaje
* Pone en marcha el cotarro
*
* @author An0nymous from Iruñea
* @version 1.0
*/
public class Abordaje {
	

	/**
	* main
	* Función principal
	* esta función es la que se inicia directamente al ejecutar el programa
	* ATENCIÓN: debemos incluir una sentencia en la declaración de la función
	* para "avisar" de que puede ocurrir un error. Lo exige el compilador
	*/
	public static void main (String args[])
	{
		Log.write("Al abordaje!!");

		Indice indices = new Indice();
		Serie serie;
		Temporada temporada;
		Capitulo capitulo;
		Fichero fichero;
		String indiceActual = "";
		String serieActual = "";

		Enumeration indice = indices.getIndices();
		Enumeration series = null;
		Enumeration temporadas = null;
		Enumeration capitulos = null;
		Enumeration ficheros = null;
		
	//	Pagina p = new Pagina("http://www.oracle.com/");
		
	//	System.out.println(p.contenidoHtml);
		DataService ds = new DataService("");
		ds.connect("");
		Log.write("Esto funciona: " + Propiedades.getPropiedad("saludo"));
		Log.write("Agente: " + UserAgent.getUserAgent());
		
		// while not finished

		
			// Recorrer la estructura con un for;
		while(indice.hasMoreElements()){
			indiceActual = indice.nextElement().toString();
			
			Log.write(indiceActual);
			series = indices.getSeries(indiceActual);

			while(series.hasMoreElements()){
				serie = new Serie((Enlace)series.nextElement());
				temporadas = serie.getTemporadas();
	
				while (temporadas.hasMoreElements()) {
					temporada = (Temporada)temporadas.nextElement();
					capitulos = temporada.getCapitulos();		
					
					while (capitulos.hasMoreElements()) {
						capitulo = (Capitulo)capitulos.nextElement();
						capitulo.insertar(temporada.getId());
						ficheros = capitulo.getFicheros();
						
						while (ficheros.hasMoreElements()) {
							fichero = (Fichero)ficheros.nextElement();
							Log.write("---------------------GET "+fichero.getFinalUrl()+"--------------------");
							
						}
					}			
				}
			}	
						//indice.finished(new Object[]{indices.elementAt(i).toString()});
		}


		// get_lista_series
		//   while_paginas_sigue
		
		// foreach serie
		 //	get_temporadas
		   // foreach temporada
		      // get capitulos
		         // foreach capitulo
		            // get ficheros
		            	// foreach fichero
		            	    // insertar_enlace 
	}
}