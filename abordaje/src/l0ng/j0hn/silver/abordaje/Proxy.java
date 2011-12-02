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
public class Proxy {

   private String _currentHost;
   private int _currentPort;
   private int _inUse;
   private int _id;
   private ProxyDB proxyDB = new ProxyDB();
    
	/**
	* init
	* Inicializa los proxies a utilizar
	*/
	public Proxy (String host, int port) {
		_currentHost = host;
		_currentPort = port;
		_inUse = 0;
		_id = proxyDB.insert(host, port);
	}
	
	/**
	* init
	* Inicializa los proxies a utilizar
	*/
	public Proxy (String host, int port, int id) {
		_currentHost = host;
		_currentPort = port;
		_inUse = 0;
		_id = id;
	}

	/**
	* markAsBad
	* 
	*/
	public void markAsBad() {
		proxyDB.markBad(_id);
	}
		
	/**
	* markAsUsed
	* 
	*/
	public void markAsUsed() {
		_inUse = 1;
		proxyDB.markUsed(_id, 1);
	}

	/**
	* freeProxy
	* 
	*/
	public void freeProxy() {
		_inUse = 0;
		proxyDB.markUsed(_id, 0);
	}
	
	/**
	* isInUse
	* @return boolean
	*/
	public boolean isInUse() {
		return (_inUse == 1);
	}
	
	/**
	* getHost
	* @return String
	*/
	public String getHost () {
		return _currentHost;
	}

	/**
	* getPort
	* @return String
	*/
	public int getPort () {
		return _currentPort;
	}
	
	/**
	* getId
	* @return id
	*/
	public int getId () {
		return _id;
	}

}
