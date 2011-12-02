/**
 * Data.java
 *
 * Aplicacion Mecano. Enseñanza de mecanografia por lecciones
 * para entorno web, multiusuario.
 * Aplicacion web desarrollada segun el paradigam MVC
 * con Struts (http://jakarta.apache.org/struts)
 *
 * Libre distribucion  (ver licencia: license.txt)
 *
 * Autor: none
 *
 */

package l0ng.j0hn.silver.abordaje;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.util.StringTokenizer;


/**
 * DataService
 *
 * Objeto para realizar todas las operaciones de BD
 * Cada sentencia es leida en un fichero externo: ese fichero tiene
 * el formato de un fichero properties
 *
 * @author none
 * @version $Revision: 1.3 $ $Date: 2002/03/19 12:53:07 $
 */
 
public class DataService {

	// Objeto que contiene la conexion al DS
  private Connection _DATA_CONNECTION = null;
  	// Objeto ConexionBD para manipular ese DS.
  private ConexionBD conexionbd = new ConexionBD();
  	// Ultimo comando dinamico generado
  private String lastCommand = null;

  //Constante para convertir al juego de caracteres español
//  private final static String _CONVERSION="set char_convert 'utf-8";
  private final static String _CONVERSION="";
  
  	/**
	* constructor
	*/
	public DataService() {
  	}

	/**
	* constructor
	* @param String
	* Si le pasamos el datasource, abre la conexion directamente.
	*/
  public DataService(String datasource) {
  	this.connect(datasource);
  }

    /**
   * connect
   * @String datasource
   * @return boolean
   */
   public boolean connect (String datasource) {
   	if (_DATA_CONNECTION == null)
    // Si conexionBD devuelve null este metodo devuelve false. A la vez que se asigna se comprueba eso.
    	return ((_DATA_CONNECTION = conexionbd.getConexion(Propiedades.getPropiedad(datasource))) == null) ? false : true;
 	  return true;
	}
	
   /**
   * close
   * @String datasource
   */
   public void close () {
   	if (_DATA_CONNECTION != null)
	    conexionbd.close(_DATA_CONNECTION);
  }


   /**
   * start
   * comienzo de transaccion
   */
   public void setCommit (boolean autoCommit) {
   	if (_DATA_CONNECTION != null)
	    conexionbd.setCommit(_DATA_CONNECTION, autoCommit);
   }

   /**
   * commit
   * confirmacion de transaccion
   */
   public void commit () {
   	if (_DATA_CONNECTION != null)
	    conexionbd.commit(_DATA_CONNECTION);
  }
  
  
   /**
   * rollback
   * Desechar cambios
   */
   public void rollback () {
   	if (_DATA_CONNECTION != null)
	    conexionbd.rollBack(_DATA_CONNECTION);
  }
  
  /**
   * public boolean getData (String command, Object[] args, Data data)
   * Realiza una consulta en la BBDD. En caso de error devuelve
   * un objeto ActionErrors cargado con mensajes. Si todo va bien,
   * la funcion devuelve null
   * Los resultados los guarda en el objeto Data
   * @param String command
   * @param DataArguments args
   * @param Data data (out)
   * @return ActionErrors
   */
   public boolean getData (String command, Object[] args, Data data) {
	
	

    try {
	
		//Traduce al juego de caracteres español.
		// Lo que hace es meter una sentencia en la conexion SQL
		// para soportar caracteres españoles.
		//Statement stm=_DATA_CONNECTION.createStatement();
		//stm.executeUpdate(_CONVERSION);
		//stm.close();
	
	  PreparedStatement pStmSQL= createCommand(command,args);	
	  ResultSet rsSQL= pStmSQL.executeQuery();
	  data.load(rsSQL);
	  //rsSQL.close();
	  //pStmSQL.close();
      Log.write("<DataService/>getData ejecutado con exito");
	  
      return true;
    } catch (SQLException sqle) {
      Log.write("<DataService/> Error SQL en consulta: "+sqle.getMessage());
      return false;
      //errors.add("sql",new ActionError("getdata.sqlerror"));
    } catch (Exception e) {
      Log.write("<DataService/> Error general en consulta: "+e.getMessage());
      return false;
      //errors.add("sql",new ActionError("getdata.error"));
    }
   }

  /**
   * public boolean getData (String command, String fields, Object[] args, Data data)
   * Realiza consultas dinamicas segun pasen parametros vacios o no.
   * Necesita un parametro nuevo que es el que trae los campos implicados en la consulta.
   * En caso de error devuelve
   * un objeto ActionErrors cargado con mensajes. Si todo va bien,
   * la funcion devuelve null
   * Los resultados los guardaen el objeto Data
   * 
   * @param String command
   * @param String fields
   * @param DataArguments args
   * @param Data data (out)
   * @return ActionErrors
   */
   public boolean getData (String command, String fields, Object[] args, Data data) {
	

	String campos[];
	StringTokenizer stcomando = null;
	StringTokenizer stcampos = null;

	String sTemp = "";
	String commandTemp = "";
	String finalCommand = "";
	String and = "";
	int ind = 0;
	
    try {
   	    
		//Traduce al juego de caracteres español.
		// Lo que hace es meter una sentencia en la conexion SQL
		// para soportar caracteres españoles.
		//Statement stm=_DATA_CONNECTION.createStatement();
		//stm.executeUpdate(_CONVERSION);
		//stm.close();

		finalCommand = createCommand(command,fields,args,null);
		Log.write("Command: " + finalCommand);

   	  // Llamamos a createDinamicCommand pasando el comando creado!  
	  PreparedStatement pStmSQL= createDinamicCommand(finalCommand,args);	
	  ResultSet rsSQL= pStmSQL.executeQuery();
	  data.load(rsSQL);
	  //rsSQL.close();
	  //pStmSQL.close();
      Log.write("<DataService/>getData ejecutado con exito");
	  
      return true;
    } catch (SQLException sqle) {
      Log.write("<DataService/> Error SQL en consulta: "+sqle.getMessage());
      return false;
      //errors.add("sql",new ActionError("getdata.sqlerror"));
    } catch (Exception e) {
      Log.write("<DataService/> Error general en consulta: "+e.getMessage());
    //errors.add("sql",new ActionError("getdata.error"));
      e.printStackTrace();
        return false;    
    }
   }
   
   /**
   * public boolean getData (String command, String fields, String[] criteria, Object[] args, Data data)
   * Realiza consultas dinamicas segun pasen parametros vacios o no.
   * Necesita un parametro nuevo que es el que trae los campos implicados en la consulta.
   * En caso de error devuelve
   * un objeto ActionErrors cargado con mensajes. Si todo va bien,
   * la funcion devuelve null
   * Los resultados los guardaen el objeto Data
   * 
   * @param String command
   * @param String fields
   * @param String[] criteria
   * @param DataArguments args
   * @param Data data (out)
   * @return ActionErrors
   */
   public boolean getData (String command, String fields,String[] criteria, Object[] args, Data data) {
	

	String campos[];
	StringTokenizer stcomando = null;
	StringTokenizer stcampos = null;

	String sTemp = "";
	String commandTemp = "";
	String finalCommand = "";
	String and = "";
	int ind = 0;
	
    try {
   	    
		//Traduce al juego de caracteres español.
		// Lo que hace es meter una sentencia en la conexion SQL
		// para soportar caracteres españoles.
		//Statement stm=_DATA_CONNECTION.createStatement();
		//stm.executeUpdate(_CONVERSION);
		//stm.close();

		finalCommand = createCommand(command,fields, args, criteria);
		Log.write("Command: " + finalCommand);

   	  // Llamamos a createDinamicCommand pasando el comando creado!  
	  PreparedStatement pStmSQL= createDinamicCommand(finalCommand,args);	
	  ResultSet rsSQL= pStmSQL.executeQuery();
	  data.load(rsSQL);
	  //rsSQL.close();
	  //pStmSQL.close();
      Log.write("<DataService/>getData ejecutado con exito");
	  
      return true;
    } catch (SQLException sqle) {
      Log.write("<DataService/> Error SQL en consulta: "+sqle.getMessage());
		return false;
    } catch (Exception e) {
      Log.write("<DataService/> Error general en consulta: "+e.getMessage());
      e.printStackTrace();
      return false;
    }

  }
   
   /**
   * public boolean setData (String command, Object[] args)
   * Efectua la llamada para la creacion del comando SQL
   * En caso de error devuelve
   * un objeto ActionErrors cargado con mensajes. Si todo va bien,
   * la funcion devuelve null
   * @param String command SQL 
   * @param Object[] args Vector con los datos del servicio
   * @return ActionErrors
   */
   public int setData (String command, Object[] args) {
	int result = 0;

    try {

		//Traduce al juego de caracteres español.
		// Lo que hace es meter una sentencia en la conexion SQL
		// para soportar caracteres españoles.
		//Statement stm=_DATA_CONNECTION.createStatement();
		//stm.executeUpdate(_CONVERSION);
		//stm.close();
		
     	PreparedStatement pStmSQL= createCommand(command,args);
     	result = pStmSQL.executeUpdate();
		//pStmSQL.close(); //Cierra el PreparedStatement
      	Log.write("<DataService/>setData ejecutado con exito");     	

		return result;
    } catch (SQLException sqle) {
      Log.write("<DataService/><setData/>Error en  comando: "+command+ " Msg: "+sqle.getMessage());
      return result;
    } catch (Exception e) {
      Log.write("<DataService/><setData/>Error general en Insert: comando: "+command+ " Msg: "+e.getMessage());
      return result;
    }
   }



	
   /**
    * private PreparedStatement createCommand (String command, Object[] args)
    * @param String command Sentencia SQL
    * @param Object[] args Vector con los objetos para el prepared statement
    * @return PreparedStatement listo para ejecutar
    */
  private PreparedStatement createCommand (String command, Object[] args) {
			PreparedStatement pstmn = null;
			Object o = null;
			int param = 0;
			int i = 0;
			boolean flagprepared = false;
			StringTokenizer st = new StringTokenizer(command,"#");

	try {
	// Si se pasa un comando que empieza por "numeracionip", se trata de una query montada!!	
	if (command.startsWith("numeracionip")) {
		st.nextToken();
		flagprepared = true;
		command = st.nextToken();
	} else {
    	command = Propiedades.getPropiedad(command,Propiedades.getPropiedad("sqlfile"));
	}
				
   		if (command==null || command.compareTo("")==0) 
	    	Log.write("<DataService/> Error al crear comando: "+command+" .Probablemente no se encontro la sentencia en el fichero SQL o el propio fichero SQL");
	
		    Log.write("<DataService/> Command: "+command.toString());

			// Preparamos a
			pstmn = _DATA_CONNECTION.prepareStatement(command,Statement.RETURN_GENERATED_KEYS); //

			// En caso de pasar argumentos, hay que cargarlos.
			if (!(args == null || args.length == 0)) {
			// Asignamos cada elemento del vector de objetos al statement
				if (flagprepared) {
		    		for ( i = 0;i<args.length;i++) {
		    			if (args[i] != null) {
	    	 			 	pstmn.setObject(++param,args[i]);
	    	 			}
	    	 		}
		    	} else {
		    		for ( i = 0;i<args.length;i++) {
			    		if (args[i] == null)
		    	 			pstmn.setNull(i+1,1);
	    		 		else 
       	 			 		pstmn.setObject(i+1,args[i]);
					}
				}
			}
     // Se devulve un objeto prepared statement
    	return pstmn;
   } catch (SQLException sqle) {
	    Log.write("<DataService/> Error SQL al crear comando: "+sqle.getMessage());
   } catch (Exception e) {
	    Log.write("<DataService/> Error al crear comando: "+e.getMessage()+" "+command);
   }
   return pstmn;
  }

   /**
    * private PreparedStatement createDinamicCommand (String command, Object[] args)
    * Crea una sentencia pero dinamicamente.
    * @param String command Sentencia SQL
    * @param Object[] args Vector con los objetos para el prepared statement
    * @return PreparedStatement listo para ejecutar
    */
  private PreparedStatement createDinamicCommand (String command,Object[] args) {
			PreparedStatement pstmn = null;
			Object o = null;
			int type = -1;
			int i = 0;
			int param = 0;

	try {
	
		    Log.write("<DataService> Command: "+command.toString());

			// Preparamos el objeto Statement
			pstmn = _DATA_CONNECTION.prepareStatement(command);

			// En caso de pasar argumentos, hay que cargarlos.
			if (!(args == null || args.length == 0))
			// Asignamos cada elemento del vector de objetos al statement
		    	for (i = 0;i<args.length;i++) 
		    		if (args[i] != null)	
		     			pstmn.setObject(++param,args[i]);
		     		
		    

     // Se devulve un objeto prepared statement
    	return pstmn;
   } catch (SQLException sqle) {
	    Log.write("<DataService/> Error SQL al crear comando: ("+i+") "+sqle.getMessage());
   } catch (Exception e) {
	    Log.write("<DataService/> Error al crear comando: ("+i+") "+e.getMessage()+" "+command);
   }
   return pstmn;
  }

	/**
   * public String createCommand (String command, String fields, Object[] args)
   * GENERA consultas dinamicas segun pasen parametros vacios o no.
   * Necesita un parametro nuevo que es el que trae los campos implicados en la consulta.
   * En caso de error devuelve 	
   * la funcion devuelve null
   * 
   * @param String command
   * @param String fields
   * @param Object[] args
   * @param String[] criteria
   * @return String
	*/
	public String createCommand (String command, String fields, Object[] args, String[] criteria) {

	String campos[];
	StringTokenizer stcomando = null;
	StringTokenizer stcampos = null;

	String sTemp = "";
	String commandTemp = "";
	String finalCommand = "";
	String and = "";
	String crit = "";
	int ind = 0;
	
    try {

    	stcomando = new StringTokenizer(Propiedades.getPropiedad(command,Propiedades.getPropiedad("sqlfile")),";");	
		stcampos = new StringTokenizer(Propiedades.getPropiedad(fields,Propiedades.getPropiedad("sqlfile")),",");
    	
    	finalCommand = stcomando.nextToken() + " ";

		// Dos casos para añadir o no and. hay un where al final
		// o hay un where en alguna parte
		and = (finalCommand.trim().toLowerCase().endsWith("where"))?"":" AND ";
		and = (finalCommand.trim().indexOf("where") < 0)?"":" AND ";
		
		
		if (args != null) {
	  		crit = (criteria == null)?"=":criteria[0];
  			sTemp = stcampos.nextToken();
    		if (stcampos.hasMoreTokens() && args[ind++] != null) 
	    		commandTemp += and + sTemp + " "+ crit +" ?  ";
    		
			// Añadimos los campos de restriccion al comando a piñon
	  		while (stcampos.hasMoreTokens() && (args.length-1) >= ind) {
	  			sTemp = stcampos.nextToken();
	  			crit = (criteria == null)?"=":criteria[ind];
	  			if (args[ind] != null && args[ind].toString().compareTo("")!=0) {//Si el correspondiente parametro es nulo
	  				// Cuidadin, si el criterior empieza por like, no se añade el simbolo ?
 	    			commandTemp += and + sTemp + " "+crit + ((crit.startsWith("like"))?" ":" ?  ");   	
 	    		}
 	    		ind++;
				and = (finalCommand.trim().toLowerCase().endsWith("where"))?"":" AND ";
				and = (finalCommand.trim().indexOf("where") < 0)?"":" AND ";
				and = (finalCommand.trim().toLowerCase().endsWith("and") )?"":" AND ";
 	  		}
 	  	
		}
		
 	  	this.lastCommand = commandTemp;

 	  	// Se le añade la restriccion de consulta: order by,etc...
		commandTemp += (stcomando.countTokens() > 0) ? stcomando.nextToken() : "";
   	    
		finalCommand += commandTemp;
    } catch (Exception e) {
      Log.write("<DataService/> Error general al crear comando: "+e.getMessage());
      e.printStackTrace();
      return "";
    }
      return finalCommand;
		
	}
   
   /**
   * fetchLastCommand
   * @return String
   * Metodo para recuperar la cola (a partir del where) del ultimo comando SQL generado
   */
   public String fetchLastCommand () {
      return this.lastCommand;
   }

}//class