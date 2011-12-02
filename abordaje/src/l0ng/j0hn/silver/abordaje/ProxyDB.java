/**
 * Proxy.java
 *
 */

package l0ng.j0hn.silver.abordaje;

import java.util.Random;
import java.util.Vector;
import java.util.List;
import java.io.IOException;
/**
 * Ofrece los métodos necesarios para proveer proxies aleatorios
 *
 * @author none
 * @version $Revision: 1.3 $ $Date: 2002/03/19 12:53:07 $
 */
public class ProxyDB {


	/**
	* insert
	* @return int
	*/
	public int insert(String host, int port) {

		DataService ds = new DataService("");
		ds.connect("");
		
		return ds.setData("insert_proxy", new Object[]{host, port});
	}
	
	/**
	* cleanList
	* cleans list of proxies
	*/
	public void cleanList() {
		DataService ds = new DataService("");
		ds.connect("");
		
		Log.write("Borrando Datos de proxies: ");
		ds.setData("delete_proxies", null);
	}

	/**
	* markBad
	* Mark proxy as not working
	* @param int id
	* @param int used
	*/
	public void markBad(int idProxy) {

		
		DataService ds = new DataService("");
		ds.connect("");
		
		ds.setData("bad_proxy", new Object[]{idProxy});
	}	
	/**
	* markUsed
	* Mark proxy as used or not
	* @param int id
	* @param int used
	*/
	public void markUsed(int idProxy, int used) {
		String sql = (used==1)?"proxy_used":"proxy_free";
		
		DataService ds = new DataService("");
		ds.connect("");
		
		ds.setData(sql, new Object[]{idProxy});
	}
	
	/**
	* getProxies
	* get proxies from DB
	* @return Vector
	*/
	public Vector getProxies () {
		Data datos = new Data();
		DataService ds = new DataService("");
		ds.connect("");
		
		ds.getData("select_proxies", null, datos);
		
		return datos.data2Vector();
	}
	
}
