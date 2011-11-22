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
public class RecursoDB {
	protected String _finish;
	
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
		Data datos = new Data();
		DataService ds = new DataService("");
		ds.connect("");
		
		Log.write(_finish + " ," + args[0].toString());
		ds.setData(_finish,args);
	}
	
}