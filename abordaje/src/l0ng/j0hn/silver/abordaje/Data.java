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
 */
package l0ng.j0hn.silver.abordaje;


import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;





/**
 * Data
 *
 * Objeto en el que se mapea el resultado de una consulta (ResultSet)
 * Contiene algunas utilidades mas, se puede volcar el ResultSet a un
 * fichero XML, o a un vector de vectores.
 *
 * @author none
 * @version $Revision: 1.2 $ $Date: 2002/01/15 12:53:07 $
 */
public class Data extends Hashtable {

	private String _NAME = "REGISTRO";
	// Separador de lineas del sistema
	private String sep = System.getProperty("line.separator");
	// Puntero del Data, para recorrerlo
	private int pointer = 0;
	// RecordCount nos da el numero de registros del resultset
	private long recordCount = 0;
	
  	/*Controla que se escriba el valor de las sql en el fichero de log
	  ¡¡¡ precaución ralentiza mucho la carga de las paginas activarlo!!!*/
	private int _DEBUG = 0;	

	/**
	* constructor
	*/
	public Data() {
		super();
		// Se carga la propiedad debug. Si no existe, se asigna 0 -desactivado-		
		//_DEBUG = (Propiedades.getPropiedad("debug").compareTo("0")!=0)?1:0;
		_DEBUG = 1;
	}

	/**
	* constructor
	*/
	public Data(String name) {
		super();
		this._NAME = name;
	}

	/**
	* load
	* @param Result
	*/
	protected void load (ResultSet rs) {
		Vector vtemp = null;
		try {
			// Se el atributo inicia a 0
			recordCount = 0;
	
			if (rs == null) {//ResultSet NULO
				Log.write("<Data/> ResultSet nulo!");
			}
		
			//inicio
			for (int i = 1; i<=rs.getMetaData().getColumnCount();i++) {
				this.put(rs.getMetaData().getColumnLabel(i).toLowerCase(),new Vector());
			}
			//carga
			while (rs.next()) {
				recordCount++;
				for (int i = 1; i<=rs.getMetaData().getColumnCount();i++) {
				vtemp = (Vector)this.get(rs.getMetaData().getColumnLabel(i).toLowerCase());
				vtemp.addElement(rs.getObject(rs.getMetaData().getColumnName(i)));
				this.put(rs.getMetaData().getColumnLabel(i).toLowerCase(),vtemp);
				}//for
			}//while

			// Iniciamos el puntero
			pointer = 0;
			if (_DEBUG != 0) //Si está a false no se traza	  	
			Log.write("<Data/> Volcado de Hash: "+this.toString()+" (" + getRecordCount() + "reg)");

			// cerramos el resultset
			rs.close();
		} catch (SQLException sqle) {
			Log.write("<Data/> Error al recorrer ResultSet: "+sqle.getMessage());
		} catch (Exception e) {
			Log.write("<Data/> Error general al recorrer ResultSet: "+e.getMessage());
		}

	}

	/**
	* getRecordCount
	* Nos dice el numero de registros que tenemos
	* @return long
	*/
	public long getRecordCount () {
		return recordCount;
	}
	
	/**
	* reset
	* reset del Data, poner el puntero a 0.
	*/
	public void reset () {
		pointer = 0;
	}

	/**
	* nextElement
	* Pasa al siguiente elemento.
	*/
	public void next () {
		pointer++;
	}

	/**
	* hasMoreElements
	* Nos dice si quedan mas registros
	* @return boolean
	*/
	public boolean hasMoreElements () {
		return (pointer < this.getRecordCount());
	}

	/**
	* getValue
	* Toma un valor del Data
	* @param String column
	* @return Object
	*/	
	public Object getValue (String column) {
		try {

			return ((Vector)this.get(column.toLowerCase())).elementAt(pointer);
		} catch (Exception e) {
			Log.write("<Data/> Error al tratar de recuperar un valor de la columna "+column+ ". Chequea el nombre. Msg: "+e.getMessage());
			return null;
		}
	}
	
	
	/**
	* data2Vector
	* Mapea el resultset en un Vector de vectores
	* @return Vector
	*/
	public Vector data2Vector () {
		// resultado: empezamos por la cabecera.
		Vector result = new Vector(); // Vector con el resultado
		Vector tvector = null; // vector temporal

		Enumeration keys = this.keys();
		String temp = "";

		// Sacamos el numero regitros
		long len = this.getRecordCount();
		try {
			for (int i = 0;i < len;i++) {
				tvector = new Vector(); // reiniciamos vector temporal
				keys = this.keys();
				while (keys.hasMoreElements()) {
					temp = (String)keys.nextElement();
					tvector.addElement( (((Vector)this.get(temp)).elementAt(i)).toString() );				
				}//while
				result.addElement(tvector); //añadimos el vector (equivale a un registro)
			}//for

			return result;
		} catch (Exception e) {
			Log.write("<Data/> Error al convertir ResultSet en Vector de vectores: "+result+" "+e.getMessage());
			return null;
		}

	}

	/**
	* data2XML
	* Mapea el resultset en un fichero XML.
	* Puede ser util
	* @return String
	*/
	public String data2XML () {
		// resultado: empezamos por la cabecera.
		String result = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+sep+"<!-- ResultSet representado en XML Desarrollado por OSS Dani,Sara,Julio,Pello,Ataulfo-->"+sep; 
		Enumeration keys = this.keys();
		String temp = "";

		// Sacamos el numero regitros
		long len = this.getRecordCount();
		result +="<Data/>"+sep;
		try {
			for (int i = 0;i < len;i++) {
				result +="<" + _NAME + ">"+sep;
				keys = this.keys();
				while (keys.hasMoreElements()) {
					temp = (String)keys.nextElement();
					result +="\t<" + temp + ">";
					result += (((Vector)this.get(temp)).elementAt(i) != null)?(((Vector)this.get(temp)).elementAt(i)).toString():"";				
					result +="</" + temp + ">"+sep;
				}
				result +="</" + _NAME + ">"+sep;
			}
		result +=""+sep;

			return result;
		} catch (Exception e) {
	      Log.write("<Data/> Error al convertir ResultSet en XML: "+result+" "+e.getMessage());
			return "Error al pasar de RS a XML";
		}

	}
	
	
	/**
	* data2Entity
	* Mapea el data a un objeto dado. Muy util si trabajamos con entidades.
	* Retorna un Vector de entidades
	* @param String class nombre de la clase.
	* @param String[] vector con los campos con los que haremos set
	* @return Hashtable
	*/
	public Vector data2Entity (String nombreclass, String[] campos) {
		// resultado: empezamos por la cabecera.
		String temp = "";
		Class[] claseParametro = null;
		Object o = null;
		Object to = null;
		Class clase = null;
		Class tclase = null;
		Method metodo = null;
		Vector result = new Vector();
		Method[] metodos = null;
		Vector vmetodos = new Vector();
		Constructor constructor = null;

		try {
						
			o = Class.forName(nombreclass).newInstance();
			clase = o.getClass();
			
			// Lo pasamos a un array y luego a un vector
 			metodos = clase.getMethods();
 			for (int j = 0;j < metodos.length;j++) 
 				vmetodos.add(metodos[j].getName());
 			
 			while (this.hasMoreElements()) {
				 o = Class.forName(nombreclass).newInstance();
				for (int i = 0;i < campos.length;i++) {
					 //clase = o.getClass();
					 // Recuperamos un atributo
					 temp = "set" + campos[i].substring(0,1).toUpperCase() +campos[i].substring(1,campos[i].length());
	 				 // Recuperamos el metodo a traves del nombre del vector de metodos
	 				 metodo = metodos[vmetodos.indexOf(temp)];
	 				 claseParametro = metodos[vmetodos.indexOf(temp)].getParameterTypes();
	 				 tclase = claseParametro[0];
					 // ahora creamos un nuevo ojbeto del tipo que necesitamos
					 // y le pasamos como parametro
	 				 if (this.getValue(campos[i]) != null) {
	 				 	constructor = tclase.getConstructor(new Class[]{String.class});
	 				 	to = constructor.newInstance(new Object[]{this.getValue(campos[i]).toString()});
	 				 } else {
	 					to = null;
	 				 }
	 				 // Invocamos el setter (guau!) del bean
	 				 // y asi asignamos el valor
					 metodo.invoke(o,new Object[]{to});
				}
				
					this.next();
  			    result.addElement(o);
			}
			
			return result;
		} catch (Exception e) {
			e.printStackTrace(System.err);
	      Log.write("<Data/> Error al convertir ResultSet en una entidad: "+nombreclass+" "+e.getMessage());
			return null;
		}

	}

}//end class