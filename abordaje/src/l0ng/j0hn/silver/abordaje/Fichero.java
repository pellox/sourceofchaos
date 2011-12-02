/**
* Fichero
* Capítulo de una serie
*
*/
package l0ng.j0hn.silver.abordaje;

import java.util.ArrayList;
import java.util.List;
import java.util.Enumeration;
import java.io.IOException;

/**
* clase Fichero
* Pone en marcha el cotarro
*
* @author An0nymous from Iruñea
* @version 1.0
*/
public class Fichero extends Recurso {

	private FicheroDB ficheroDB = new FicheroDB();
	private int _idcapitulo;
	private String _url;
	private String _servidor;
	private String _audio;
	private String _subtitulos;
	private String _calidad;
	private String _formato;
	private String _autor;
	private int _idFichero;
	private String _urlFinal;

	/**
	* Fichero
	* Constructor sin parametros
	*/
	public Fichero () {
		super();
	}
	
	/**
	* Fichero
	* @param int idcapitulo
	* @param String url
	* @param String servidor
	* @param String audio
	* @param String subtitulos
	* @param String calidad
	* @param String formato
	* @para String autor
	*/
	public Fichero (int idcapitulo, String url, String servidor, String audio ,String subtitulos, String calidad, String formato, String autor) {
	 	_idcapitulo = idcapitulo;
		_url = url;
		_servidor = servidor;
	 	_audio = audio;
		_subtitulos = subtitulos;
	 	_calidad = calidad;
	 	_formato = formato;
	 	_autor = autor;
		
	 	insertar();
	}

	/**
	* insertar
	* @param int idtemporada
	*
	*/
	public void insertar () {
		_idFichero = ficheroDB.existe(_url);
		
		if (_idFichero == 0) {
			_idFichero = ficheroDB.insert(_idcapitulo, _url, _servidor, _audio, _subtitulos, _calidad, _formato, _autor);
		}
	}
	
	/**
	* getFinalUrl
	* Saca los ficheros de determinado capitulo
	* @param String index
	* @return String
	*/
	public String getFinalUrl () {
	
		String resultado = "";

		
		String site = Propiedades.getPropiedad("urlsy")+_url;
		try {
	    		resultado = HTMLUtils.extractFinalLink(Propiedades.getPropiedad("urlsy"),site,_idFichero);
				ficheroDB.updateFichero(resultado, _idFichero);
    	} catch (IOException ioe) {
    		Log.write("Parece que no hay más links: " + ioe.getMessage());
    	
	    	} catch (Exception e) {
    		Log.write("Error al extraer datos: " + e.getMessage());
	    }
    	
		
		return resultado;
	}
	
		/**
	* initFicheroSerie
	* inicia una Fichero de 0
	* @param int
	*/
	public void initFicheroSerie (int idCapitulo) {
		ficheroDB.initFicheroSerie(idCapitulo);
	}

}