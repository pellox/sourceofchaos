/**
 * ProxyInterface.java
 *
 */

package l0ng.j0hn.silver.abordaje;


import java.io.*;
import java.net.*;

/**
 * Interfaz que define los métodos que se deben usar al poner cualquier tipo de proxy
 *
 * @author none
 * @version $Revision: 1.3 $ $Date: 2002/03/19 12:53:07 $
 */
public class ProxyProxify implements  ProxyInterface {


	/**
	* getPage
	* Devuelve un proxy aleatorio
	* @param String url
	* @return String
	*/
	public String getPage(String url) 	{
		URLConnection yc;
		BufferedReader in;
		String contenidoHtml = "";
		String postURL = "http://proxify.org/p/011010A0000110/x/start";
 // Construct data
 // Petición a proxify:
/*
fa	on
hb	1
hc	1
if	1
mc	0
rs	on
rt	0
so	00
url	http://www.seriesyonkis.com 
*/	
 	try {
    	String data = URLEncoder.encode("fa", "UTF-8") + "=" + URLEncoder.encode("on", "UTF-8");
   	 data += "&" + URLEncoder.encode("hb", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
   	 data += "&" + URLEncoder.encode("hc", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
   	 data += "&" + URLEncoder.encode("if", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
   	 data += "&" + URLEncoder.encode("mc", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
   	 data += "&" + URLEncoder.encode("rs", "UTF-8") + "=" + URLEncoder.encode("on", "UTF-8");
   	 data += "&" + URLEncoder.encode("rt", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
   	 data += "&" + URLEncoder.encode("so", "UTF-8") + "=" + URLEncoder.encode("00", "UTF-8");
   	 data += "&" + URLEncoder.encode("url", "UTF-8") + "=" + URLEncoder.encode(url, "UTF-8");


    // Send data
    	URL urlcon = new URL(postURL);
    	URLConnection conn = urlcon.openConnection();
      ((HttpURLConnection)conn).setRequestMethod("POST");
      conn.setDoInput(true);
      conn.setDoOutput(true);

    	OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
    	wr.write(data);
    	wr.flush();

    // Get the response
    	BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    	String line;
    /*	while ((line = rd.readLine()) != null) {
        // Process line...
    	}*/
    	wr.close();
    	rd.close();
	} catch (Exception e) {
		Log.write("Error al consultar por proxify: " + e.getMessage());
	}

		return contenidoHtml;

	}
	
	/**
	* checkOut
	* Checks out if a proxy is ok or not
	* @return boolean
	*/
	public boolean checkOut () {
		return true;
	}

}
