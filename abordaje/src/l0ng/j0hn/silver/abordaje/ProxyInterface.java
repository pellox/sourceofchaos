/**
 * ProxyInterface.java
 *
 */

package l0ng.j0hn.silver.abordaje;

import java.util.Random;

/**
 * Interfaz que define los métodos que se deben usar al poner cualquier tipo de proxy
 *
 * @author none
 * @version $Revision: 1.3 $ $Date: 2002/03/19 12:53:07 $
 */
public interface ProxyInterface {


	/**
	* getPage
	* Devuelve un proxy aleatorio
	* @param String url
	* @return String
	*/
	public String getPage(String url);
	
	/**
	* checkOut
	* Checks out if a proxy is ok or not
	* @return boolean
	*/
	public boolean checkOut ();

}
