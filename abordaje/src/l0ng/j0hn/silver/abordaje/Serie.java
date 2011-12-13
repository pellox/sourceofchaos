/**
* Serie
* Serie es una generalización de algo descargable: serie, temporada, fichero, etc..
*
*/
package l0ng.j0hn.silver.abordaje;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Enumeration;
import java.io.IOException;

/**
* clase Serie
* Pone en marcha el cotarro
*
* @author An0nymous from Iruñea
* @version 1.0
*/
public class Serie extends Recurso {

	private SerieDB serieDB = new SerieDB();
	public String nombre;
	public String url;
	public String pagina;
	public int idserie;
	
	/**
	* Serie
	* Constructor sin parametros
	*/
	public Serie () {
		super();
	}
	
	/**
	* Serie
	* @param String url
	* @param String filtro
	*/
	public Serie (Enlace enlace) {
		url = enlace.getUrl();
		nombre = enlace.getText();
		pagina = enlace.getPage();
		
		idserie = serieDB.existe(pagina);
		if (idserie==0 ) {
			idserie = serieDB.insert(url, nombre, pagina);
		}		
	}


	/**
	* Serie
	* @param String nombre
	*/
	public Serie (String nombre) {
		
		idserie = serieDB.existe(nombre);
		if (idserie!=0 ) {
			pagina = nombre;
		} else {
			idserie = serieDB.insert(nombre, nombre, nombre);
			pagina = nombre;
		}
	}
	/**
	* getTemporadas
	* Saca las temporadas dado una serie
	* @param String index
	* @return Vector
	*/
	public Vector getTemporadas () {
	
		Vector resultado = new Vector();
		List<Temporada> temporadas = null;
				String site = Propiedades.getPropiedad("urlserie")+this.pagina;
		try {
	    		temporadas = HTMLUtils.extractSeasons(site,idserie);
	    		resultado.addAll(temporadas);
			
    	} catch (IOException ioe) {
    		Log.write("Parece que no hay más links: " + ioe.getMessage());
    	}
    	
		
		return resultado;
	}
	

	
}