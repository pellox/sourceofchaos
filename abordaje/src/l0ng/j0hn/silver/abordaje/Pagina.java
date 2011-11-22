/**
* Pagina
* Clase que representa una página web. Dispone de métodos para su descarga
*
* Para compilar:
*   javac Pagina.java
*
* Para ejecutarlo:
*   java Pagina
*/
package l0ng.j0hn.silver.abordaje;

// Librería necesaria para trabajar con la entrada/salida
import java.io.*;
import java.net.*;

/**
* clase Pagina
* Representa una página web.
*
* @author An0nymous from Iruñea
* @version 1.0
*/
public final class Pagina {
	
	
	public static String descargarHtml (String url)
	{
		URLConnection yc;
		BufferedReader in;
		String contenidoHtml = "";
		try {
		       URL oracle = new URL("http","192.168.16.2",80,url);
		       //URL oracle = new URL(url);
        yc = oracle.openConnection();
        in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
            contenidoHtml += inputLine;
            Log.write("-Pagina> OK " + url);
        in.close();
    } catch (Exception e)
    {
	    System.out.println("No se pudo conectar: " + e.getMessage());
	    return "";
	}
		return contenidoHtml;

	}
	


}