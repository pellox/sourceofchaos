/**
 * Propiedades.java
 *

 */

package l0ng.j0hn.silver.abordaje;

			 
import java.io.*;
import java.util.*;


/**
 * Ofrece los métodos necesarios para leer ficheros de propiedades.
 * El fichero properties debera establecerse en un lugar determinado.
 *
 * @author none
 * @version $Revision: 1.3 $ $Date: 2002/03/19 12:53:07 $
 */
public final class Propiedades {

	//Nombre del fichero de propiedades
	private static final String sFile = "abordaje.properties";
    
    
    /**
	 * Obtiene una propiededad de fichero de configuracion
	 * @param String sPropiedad
	 * @return String Valor de propiedad
	 */

    public static String getPropiedad(String sPropiedad){
		String sTemp = "";
	    Properties pr = new Properties();
        try{
	
	    	pr.load(new FileInputStream(sFile)); // /ServerRootDir/numeracionip.properties
	    	// Obtenemos el valor de la propiedad solicitada y lo devolvemos
    	    sTemp = pr.getProperty(sPropiedad);
			
			// Comprobamos si la propiedad esta vacia para avisar.
    	    if (sTemp == null || sTemp.compareTo("")==0)
				Log.write("<Propiedades/>Atencion propiedad "+sPropiedad+" nula");    
	    }catch(Exception e){
			Log.write("<Propiedades/>Error al leer el fichero de propiedades "+e.getMessage());
        }
    
        return sTemp;
    }
    
    /**
	 * Establece una propiededad en el fichero de configuracion
     *
	 * @param String sNombre
     * @param String sPropiedad
	 */

    public static void setPropiedad(String sNombre, String sPropiedad){

	    Properties pr = new Properties();

        try{
	
			// Comprobamos si la propiedad esta vacia para avisar.
    	    if (sNombre == null || sNombre.compareTo("")==0 || sPropiedad == null || sPropiedad.compareTo("")==0)
				Log.write("<Propiedades/>Atencion propiedad "+sPropiedad+" nula");
            else {    
	    	    pr.load(new FileInputStream(sFile)); // /ServerRootDir/numeracionip.properties	    	
    	        pr.put(sNombre,sPropiedad);// Establecemos el valor de la propiedad
                Log.write("<Propiedades/>Propiedad "+sNombre+" establecida correctamente");
            }

	    }catch(Exception e){
			Log.write("<Propiedades/>Error al leer el fichero de propiedades "+e.getMessage());
        }
    
    }

     /**
	 * Obtiene una propiededad dada 
	 * de fichero de configuracion dado
	 * @param String sPropiedad
	 * @param String sPropiedadFile
	 * @return String Valor de propiedad
	 */

    public static String getPropiedad(String sPropiedad, String sPropiedadFile){
	
		String sTemp = "";
	    Properties pr = new Properties();
        try{
	
	    	pr.load(new FileInputStream(sPropiedadFile)); // /ServerRootDir/numeracionip.properties
	    	// Obtenemos el valor de la propiedad solicitada y lo devolvemos
    	    sTemp = pr.getProperty(sPropiedad);
    	    
			// Comprobamos si la propiedad esta vacia para avisar.
    	    if (sTemp == null || sTemp.compareTo("")==0)
				Log.write("<Propiedades/>Atencion propiedad "+sPropiedad+" nula en fichero: "+sPropiedadFile);
    
	    }catch(Exception e){
			Log.write("<Propiedades/>Error al leer el fichero de propiedades "+e.getMessage());
        }
         
        return sTemp; 
    }


    /**
    * Establece una propiededad dada en un fichero de configuracion dado
    *
    * @param String sNombre
    * @param String sPropiedad
    * @param String sPropiedadFile	
    */

    public static void setPropiedad(String sNombre, String sPropiedad, String sPropiedadFile){
	
	    Properties pr = new Properties();

        try{
	   
			// Comprobamos si la propiedad esta vacia para avisar.
    	    if (sNombre == null || sNombre.compareTo("")==0 || sPropiedad == null || sPropiedad.compareTo("")==0)           
				Log.write("Atencion propiedad "+sPropiedad+" nula en fichero: "+sPropiedadFile);
            else {
	    	    pr.load(new FileInputStream(sPropiedadFile)); // /ServerRootDir/sPropiedadFile.properties 
    		    pr.put(sNombre,sPropiedad); // Establecemos el valor de la propiedad
                Log.write("<Propiedades/>Propiedad "+sNombre+" establecida correctamente");
            }

    
	    }catch(Exception e){
			Log.write("<Propiedades/>Error al leer el fichero de propiedades "+e.getMessage());
        }
        
    }

}
