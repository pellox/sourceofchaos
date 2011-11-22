/**
 * ConexionBD.java
 *
 * Aplicacion Mecano. Enseñanza de mecanografia por lecciones
 * para entorno web, multiusuario.
 * Aplicacion web desarrollada segun el paradigam MVC
 * con Struts (http://jakarta.apache.org/struts)
 *
 * Libre distribucion  (ver licencia: license.txt)

 *
 */
 
package l0ng.j0hn.silver.abordaje;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.DriverManager;
import com.mysql.jdbc.Driver;
/**
 * Ofrece los métodos necesarios para obtener una conexión a la BD
 *
 * Bean abstracto de la aplicacion Mecano
 *
 * @author  none
 * @version 1.0 , date 13/2/02
*/
public class ConexionBD  {	
	/**
	* @field Propiedad autoCommit
	*/
	private boolean autoCommit=false;
	
	/**
	* Devuelve una conexión con la base de datos.
	*
	* @param sNombreDs Nombre del Data Source del que se 
	* quiere obtener una conexión
	* @return Connection istancia de conexion a la base de datos
	*/
	public Connection getConexion(String sNombreDs) {
		// Declaracion de variables
		Context 	ctxContexto;		// Contexto de conexión
		DataSource 	dtsDataSource;		// Conexión jndi
		Connection 	conConexion;		// Conexión con la base de datos    
		
		// Inicializaciones del contexto	
		ctxContexto = null;

		// Obtiene la conexión								
		try {
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName(Propiedades.getPropiedad("driver"));
			//ctxContexto = new InitialContext();
			//dtsDataSource = (DataSource) ctxContexto.lookup ("java:comp/env/jdbc/" + sNombreDs);
			//conConexion = dtsDataSource.getConnection();
			conConexion = DriverManager.getConnection( Propiedades.getPropiedad("dburl"),Propiedades.getPropiedad("dbuser"), Propiedades.getPropiedad("dbpass"));
			
			Log.write("<ConexionBD/>Conectado a BD, DS: "+sNombreDs);
		}catch (Exception e) {
			e.printStackTrace();
			conConexion = null;			
			Log.write("<ConexionBD/>No se pudo conectar a DS "+sNombreDs+ " error: "+e.getMessage());

		}finally {		
			// Si el contexto es distinto de null lo cierra
			if ( ctxContexto != null ){
				try	{
					ctxContexto.close();
				}catch( Exception e ){					
					e.printStackTrace();
				
				}
			}
		}
				
		// Devuelve la conexión obtenida		
		return conConexion;
	}	
	
	
	/**
	* Cierra una conexión con la base de datos.
	*
	* @param Connection Conexión a cerrar.
	*/	
	public void close(Connection conConexion) {	
	
		String catalog = getCatalog(conConexion);
		try {
			conConexion.close();
			Log.write("<ConexionBD/>Conexion a BD "+catalog+" cerrada.");
		} catch (SQLException e) {
			e.printStackTrace();			
			Log.write("<ConexionBD/>No se pudo CERRAR conexion, error: "+e.getMessage());
		}
	}

	/**
	* Realiza un rollback
	*
	* @param Connection Conexión sobre la que se hace rollback.
	*/	
	public void rollBack(Connection conConexion) {
		try 
		{
			conConexion.rollback();
			Log.write("<ConexionBD/>RollBack de operacion");			
		} catch (SQLException e) {
			e.printStackTrace();
			Log.write("<ConexionBD/>Error en rollback: "+e.getMessage());			
		}
	}
	
	/**
	* Realiza un commit
	*
	* @param Connection Conexión sobre la que se hace commit.
	*/
	public void commit(Connection conConexion) {
		try {
			conConexion.commit();				
		} catch (SQLException e) {
			e.printStackTrace();	
			Log.write("<ConexionBD/>Error en commit: "+e.getMessage());
		}
	}
	
	/**
	* Establece el valor de la propiedad
	*
	* @param boolean Valor a asignar a la propiedad.
	*/
	public void setCommit(Connection conConexion, boolean autoCommit) {
		try {
			conConexion.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();	
			Log.write("<ConexionBD/>Error al poner AutoCommit a : "+autoCommit+" "+e.getMessage());
		}
	}	
	
	/**
	* Retorna el nombre de catalogo
	* 
	* @param  Connection Conexión sobre la que se obtiene el catalogo.
	* @return String Nombre del catalogo y null en caso de error.
	*/
	private String getCatalog (Connection con) {
		try {
			return con.getCatalog();
		} catch (SQLException e) {
			e.printStackTrace();	
			Log.write("<ConexionBD/>Error en getCatalog: "+e.getMessage());
			return null;
		}
	}
	
}