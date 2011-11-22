/**
 * Proxy.java
 *
 */

package l0ng.j0hn.silver.abordaje;

import java.util.Random;

/**
 * Ofrece los métodos necesarios para proveer proxies aleatorios
 *
 * @author none
 * @version $Revision: 1.3 $ $Date: 2002/03/19 12:53:07 $
 */
public class Proxy {

	//Nombre del fichero de propiedades
	private static final String proxyes[] = {"127.0.0.1","127.0.0.1","127.0.0.1","127.0.0.1","127.0.0.1"};
	private static final int ports[] = {9666,9666,9666,9666,9666};
    private String _currentHost;
    private int _currentPort;
    
	/**
	* init
	* Inicializa los proxies a utilizar
	*/
	public Proxy () {
	}
	
	/**
	* getProxy
	* Devuelve un proxy aleatorio
	* @return String
	*/
	public void getProxy() {
		Random aleatorio = new Random();
		int selected = aleatorio.nextInt((proxyes.length-1));
		_currentHost = proxyes[selected];
		_currentPort = ports[selected];
		
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

}
