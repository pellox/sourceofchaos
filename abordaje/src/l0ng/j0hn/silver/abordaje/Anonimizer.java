/**
 * Anonimizer.java
 *
 */

package l0ng.j0hn.silver.abordaje;

import java.util.Random;

/**
 * Ofrece los métodos necesarios para que
 *
 * @author none
 * @version $Revision: 1.3 $ $Date: 2002/03/19 12:53:07 $
 */
public final class Anonimizer {

	//Nombre del fichero de propiedades
	private static final String proxies[] = {"Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.0.1) Gecko/2008070206","Opera/9.80 (Windows NT 5.1; U;) Presto/2.7.62 Version/11.01"};
    
    
	/**
	* getUserAgent
	* Devuelve un user agent aleatorio
	* @return String
	*/
	public static String getProxy() {
		Random aleatorio = new Random();
		String resultado = proxies[aleatorio.nextInt((proxies.length-1))];
		
		return resultado;
	}

}
