/**
* Recurso
* Recurso es una generalización de algo descargable: serie, temporada, fichero, etc..
*
*/
package l0ng.j0hn.silver.abordaje;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
* clase Recurso
* Pone en marcha el cotarro
*
* @author An0nymous from Iruñea
* @version 1.0
*/
public class Recurso {
	private String _url;
	private String _filtro;
	private ArrayList<String> _resultados;	
	protected RecursoDB recursoDB = new RecursoDB();

	/**
	* Recurso
	* Constructor sin parametros
	*/
	public Recurso () {
		_resultados = new ArrayList<String>();
	}
	
	/**
	* Recurso
	* @param String url
	* @param String filtro
	*/
	public Recurso (String url, String filtro){
		_filtro = filtro;
		_resultados = new ArrayList<String>();
	}

	/**
	* download
	* descarga el HTML y rellena los resultados
	* @throws IOException
	*/
	public void download() {
		try {
			List<String> listaTemporal = HTMLUtils.extractLinks(_url);
			for (String link : listaTemporal) {
      			_resultados.add(link);
			}
		} catch (IOException ioe) {
			Log.write("Error al descargar: " + ioe.getMessage());
		}
	}
	
	/**
	* dump
	* Vuelca todo el contenido de los resultados
	* @return String dump
	*/
	public String dump() {
		StringBuffer resultado = new StringBuffer("");
		int i = 0;
		
			for (String link : _resultados) {
      			resultado.append(i+".- "+ link);
			}
			
			return resultado.toString();
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
		recursoDB.finished(args);
	}
	
}