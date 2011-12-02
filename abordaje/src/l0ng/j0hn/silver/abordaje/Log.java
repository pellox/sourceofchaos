/**
 * Log.java
 *
 */
 
package l0ng.j0hn.silver.abordaje;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;


/**
 * Log de la aplicacion
 * Guarda cada mensaje en formato XML
 * para facilitar su posterior analisis automatizado
 * @author  n0n3
 * @version 1.0 , date 13/2/02
 */
public class Log {

	private static String logfile = "output.log.xml";


	/**
	* write
	* Metodo que escribe el log en el fichero que se haya especificado
	* en el properties
	* @param String msg el mensaje de log
	*/
  public static boolean write (String msg) {
		
  
      PrintWriter pw = null;
      String message =  "<Log date=\"" + new Date().toString() +"\">"+msg+"</Log>";
	 	
      System.out.println(message);
      /*
      try {
      	// Escribimos (append) en el fichero log
        pw = new PrintWriter(new FileOutputStream(logfile, true));
        pw.println(message);
        pw.close();
      } catch (IOException ioe) {
        System.err.println("Error al escrbir en log: "+ioe.getMessage());
        return false;
      }*/
	return true;
    

  }


  
}//end class