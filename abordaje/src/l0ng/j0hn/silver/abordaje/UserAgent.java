/**
 * UserAgent.java
 *
 */

package l0ng.j0hn.silver.abordaje;

import java.util.Random;

/**
 * Ofrece los métodos necesarios para generar navegadores aleatorios
 *
 * @author none
 * @version $Revision: 1.3 $ $Date: 2002/03/19 12:53:07 $
 */
public final class UserAgent {

	//Nombre del fichero de propiedades
	private static final String navegadores[] = {"Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.0.1) Gecko/2008070206","Opera/9.80 (Windows NT 5.1; U;) Presto/2.7.62 Version/11.01"};
    
    
	/**
	* getUserAgent
	* Devuelve un user agent aleatorio
	* @return String
	*/
	public static String getUserAgent() {
		Random aleatorio = new Random();
		String resultado = navegadores[aleatorio.nextInt((navegadores.length-1))];
		
		return resultado;
	}

}
