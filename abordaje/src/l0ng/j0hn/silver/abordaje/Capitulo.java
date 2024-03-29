/**
* Capitulo
* Capítulo de una serie
*
*/
package l0ng.j0hn.silver.abordaje;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Enumeration;
import java.io.IOException;

/**
* clase Capitulo
* Pone en marcha el cotarro
*
* @author An0nymous from Iruñea
* @version 1.0
*/
public class Capitulo extends Recurso {

	private CapituloDB capituloDB = new CapituloDB();
	private int _idtemporada;
	private String _numero;
	private String _nombre;
	private String _fecha;
	private String _url;
	private int _idcapitulo;

	/**
	* Capitulo
	* Constructor sin parametros
	*/
	public Capitulo () {
		super();
	}
	
	/**
	* Capitulo
	* @param String numero
	* @param String nombre
	* @param String fecha
	* @param String url
	*/
	public Capitulo (int idtemporada, String numero, String nombre, String fecha ,String url) {
		_numero = numero;
		_nombre = nombre;
		_fecha = fecha;
		_url = url;
		_idtemporada = idtemporada;

		_idcapitulo = capituloDB.existe(url);
		if (_idcapitulo==0 ) {
			_idcapitulo = insertar();
		}		
		//Log.write(_numero+"> "+_nombre+"> "+_fecha+"> "+_url);
	}

	/**
	* insertar
	* @return int idtemporada
	*
	*/
	public int insertar () {
			return capituloDB.insert(_idtemporada, _numero, _nombre, _fecha, _url);
	}
	
	/**
	* getFicheros
	* Saca los ficheros de determinado capitulo
	* @param String index
	* @return Vector
	*/
	public Vector getFicheros () {
	
		Vector resultado = new Vector();
		List<Fichero> ficheros = null;
		
		String site = Propiedades.getPropiedad("urlsy")+_url;
		try {
	    		ficheros = HTMLUtils.extractChapterFiles(site,_idcapitulo);
	    		resultado.addAll(ficheros);
			
    	} catch (IOException ioe) {
    		Log.write("Parece que no hay más links: " + ioe.getMessage());
    	
	    	} catch (Exception e) {
    		Log.write("Error al extraer datos: " + e.getMessage());
	    }
    	
		
		return resultado;
	}
	
		/**
	* initCapituloSerie
	* inicia una Capitulo de 0
	* @param int
	*/
	public void initCapituloSerie (int idTemporada) {
		capituloDB.initCapituloSerie(idTemporada);
	}
}