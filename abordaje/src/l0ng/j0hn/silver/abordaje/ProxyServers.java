/**
 * Proxy.java
 *
 */

package l0ng.j0hn.silver.abordaje;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.io.IOException;
/**
 * Ofrece los métodos necesarios para proveer proxies aleatorios
 *
 * @author none
 * @version $Revision: 1.3 $ $Date: 2002/03/19 12:53:07 $
 */
public class ProxyServers {

	//Nombre del fichero de propiedades
	private static ArrayList<Proxy> proxyServers;
	private static int _index = 0;   private ProxyDB proxyDB = new ProxyDB();
    
	/**
	* init
	* Inicializa los proxies a utilizar
	*/
	public ProxyServers () {

	}
	
	/**
	* getProxy
	* Devuelve un proxy aleatorio
	* @return String
	*/
	public void getProxy() {
	}
	
	
	/**
	* cleanProxyServers
	* Inicializa los proxies a utilizar
	*/
	public void cleanProxyServers () {
		proxyDB.cleanList();
	}
	
	/**
	* getProxies
	* Saca los proxies
	*/
	public void getProxies () {
	
		proxyServers = new ArrayList<Proxy>();
		List<Proxy> links = null;
		
		int pagina = 0;		
		String site = Propiedades.getPropiedad("urlproxies");
		try {
			do {

	    		links = HTMLUtils.extractProxies(site+(pagina));
	    		proxyServers.addAll(links);
   	
	    		pagina++;
			}	while (!links.isEmpty() && pagina<10);

    	} catch (IOException ioe) {
    		Log.write("Parece que no hay más links: " + ioe.getMessage());
    	}
    	
	}
	
	
	/**
	* getNext
	* Devuelve uno de los servidores proxy
	* @return Proxy
	*/	
	public static Proxy getNext() {
		if (_index>=proxyServers.size()) {
			_index = 0;
		} else {
			_index++;
		}
		return proxyServers.get(_index);	
	}
	
	/**
	* loadServers
	* Saca los proxies de la BBDD
	*/
	public void loadServers () {
		Vector proxies = null;
		Vector p = null;
		proxyServers = new ArrayList<Proxy>();
		_index = 0;
		
		proxies = proxyDB.getProxies();
		
		for (int i=0;i<proxies.size();i++) {
			p = (Vector)proxies.elementAt(i);
			proxyServers.add(new Proxy((String)p.elementAt(1),Integer.parseInt(p.elementAt(0).toString()),Integer.parseInt(p.elementAt(2).toString()) ));
		}
	}

}
