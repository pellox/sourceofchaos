/**
* IndiceDB
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
public class IndiceDB extends RecursoDB {

	protected String _finish;
	
	public IndiceDB () {
		_finish = "finish_indices";
	}
	
	/**
	* getIndices
	* Toma los indices pendientes de la BBDD
	* @return Enumeration
	*/
	public Enumeration getIndices () {
	
		Vector resultado = new Vector();
		Data datos = new Data();
		DataService ds = new DataService("");
		ds.connect("");
		
		ds.getData("getindices",null,datos);
		
		while (datos.hasMoreElements()) {
			resultado.add(datos.getValue("id"));		
			datos.next();
		}
		
		return resultado.elements();
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
}