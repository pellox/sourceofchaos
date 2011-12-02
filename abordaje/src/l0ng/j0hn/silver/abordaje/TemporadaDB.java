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
public class TemporadaDB extends RecursoDB {

	protected String _finish;
	
	public TemporadaDB () {
		_finish = "finish_temporadas";
	}
	
	
	public int insert (int serie, String nombre, String numero) {
		Data datos = new Data();
		DataService ds = new DataService("");
		ds.connect("");

		return ds.setData("insert_temporada",new Object[]{serie, nombre, numero});
	}

	
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
	* initTemporadaSerie
	* inicia una temporada de 0
	* @param int
	*/
	public void initTemporadaSerie (int idSerie) {
		/*DataService ds = new DataService("");
		ds.connect("");
		
		ds.setData(_finish, args);
		*/
		Data datos = new Data();
		DataService ds = new DataService("");
		ds.connect("");
		
		Log.write("Borrando temporada anterior de serie: " + idSerie);
		ds.setData("init_temporadas_serie", new Object[]{idSerie});
	}
	
	/**
	* existe
	* comprueba si la temporada ya existe
	* @return boolean
	*/
	public int existe (int serie, String numero) {
		/*DataService ds = new DataService("");
		ds.connect("");
		
		ds.setData(_finish, args);
		*/
		Data datos = new Data();
		DataService ds = new DataService("");
		ds.connect("");
		
		ds.getData("existe_temporada",new Object[]{serie, numero},datos);
		if (datos.getRecordCount() > 0)
			return Integer.parseInt(datos.getValue("id").toString());
		else
			return 0;
	}
}