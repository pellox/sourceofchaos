/**
* FicheroDB
* Los indices de las series
*
*/
package l0ng.j0hn.silver.abordaje;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Enumeration;
import java.util.List;
import java.io.IOException;

/**
* clase Serie
* Pone en marcha el cotarro
*
* @author An0nymous from Iruñea
* @version 1.0
*/
public class FicheroDB extends RecursoDB {

	protected String _finish;
	
	public FicheroDB () {
		_finish = "finish_capitulos";
	}
	
	/**
	* insert
	*
	* @param idtemporada
	* @param numero
	* @param String titulo
	* @param String fecha
	* @param String url
	* @return int
	*/
	public int insert (int idcapitulo, String url, String servidor, String audio ,String subtitulos, String calidad, String formato, String autor) {
		Data datos = new Data();
		DataService ds = new DataService("");
		ds.connect("");

		
		
		return ds.setData("insert_fichero",new Object[]{idcapitulo, url, servidor, audio, subtitulos, calidad, formato, autor});
	}

	/**
	* existe
	* @return boolean
	*/
	public int existe (String url) {
		Data datos = new Data();
		DataService ds = new DataService("");
		ds.connect("");
		
		ds.getData("existe_fichero",new Object[]{url}, datos);
		if (datos.getRecordCount() > 0)
			return Integer.parseInt(datos.getValue("id").toString());
		else
			return 0;
	}
	
	/**
	* finished
	*
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
	
	/**
	* updateFichero
	*
	*/
	public void updateFichero (String url, int id) {

		Data datos = new Data();
		DataService ds = new DataService("");
		ds.connect("");

		ds.setData("update_fichero",new Object[]{url,id});
	}
}