/**
* Indice
* Los índices de las series
*
*/
package l0ng.j0hn.silver.abordaje;

import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import java.util.Enumeration;
import java.io.IOException;

/**
* clase Serie
* Pone en marcha el cotarro
*
* @author An0nymous from Iruñea
* @version 1.0
*/
public class Indice extends Recurso {

	private IndiceDB indiceDB = new IndiceDB();
	protected String _finish = "finish_indices";
	
	/**
	* getIndices
	* Toma los índices pendientes de la BBDD
	* @return Enumeration
	*/
	public Enumeration getIndices () {	
		return indiceDB.getIndices();
	}
	
	/**
	* getSeries
	* Saca las series dado un índice
	* @param String index
	* @return Vector
	*/
	public Enumeration getSeries (String index) {
	
		Vector resultado = new Vector();
		List<Enlace> links = null;
		List<Enlace> tmpLinks = null;
		
		int pagina = 100;		
		String site = Propiedades.getPropiedad("urlseries")+index.toUpperCase();
		try {
	    		links = HTMLUtils.extractLinksFiltered(site,Propiedades.getPropiedad("filterseries")+index);
	    		resultado.addAll(links);
	    		//Log.write(links.toString());
			do {
	    		links = HTMLUtils.extractLinksFiltered(site+"/"+pagina,Propiedades.getPropiedad("filterseries")+index);
	    		resultado.addAll(links);
	    		//Log.write(links.toString());
	    		pagina += 100;
			}	while (!links.isEmpty());
			
    	} catch (IOException ioe) {
    		Log.write("Parece que no hay más links: " + ioe.getMessage());
    	}
    	
		
		return resultado.elements();
	}
	
	/**
	* finished
	* Marca como tarea finalizada
	* @param Object[] args
	*/
	public void finished (Object[] args) {
		/*DataService ds = new DataService("");
		ds.connect("");
		
		ds.setData(_finish, args);
		*/
		indiceDB.finished(args);
	}
}