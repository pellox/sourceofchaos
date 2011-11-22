/**
* SerieDB
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
public class SerieDB extends RecursoDB {

	protected String _finish;
	
	public SerieDB () {
		_finish = "finish_series";
	}
	
	
	public int insert (String url, String nombre, String pagina) {
		Data datos = new Data();
		DataService ds = new DataService("");
		ds.connect("");

		
		
		return ds.setData("insert_serie",new Object[]{nombre,url,pagina});
	}

	/**
	* existe
	* @return boolean
	*/
	public int existe (String pagina) {
		/*DataService ds = new DataService("");
		ds.connect("");
		
		ds.setData(_finish, args);
		*/
		Data datos = new Data();
		DataService ds = new DataService("");
		ds.connect("");
		
		ds.getData("existe_serie",new Object[]{pagina},datos);
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
}