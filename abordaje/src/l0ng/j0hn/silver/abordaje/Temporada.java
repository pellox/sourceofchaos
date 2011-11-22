/**
* Temporada
* Temporada de una serie
*
*/
package l0ng.j0hn.silver.abordaje;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Enumeration;
import java.io.IOException;

/**
* clase Temporada
* Pone en marcha el cotarro
*
* @author An0nymous from Iruñea
* @version 1.0
*/
public class Temporada extends Recurso {
	private TemporadaDB temporadaDB = new TemporadaDB();
	private String _nombre;
	private String _numero;
	private int _serie;
	private ArrayList _capitulos;
	private int _idtemporada;
	/**
	* Temporada
	* Constructor sin parametros
	*/
	public Temporada () {
		super();
	}
	
	/**
	* Temporada
	* @param String nombre
	* @param String numero
	* @param int capitulos
	*/
	public Temporada (String nombre, String numero, int serie, ArrayList capitulos) {
		_nombre = nombre;
		_numero = numero;
		_serie = serie;
		_capitulos = capitulos;
		
		_idtemporada = temporadaDB.existe(_serie, _numero);
		if (_idtemporada==0 ) {
			_idtemporada = temporadaDB.insert(serie, nombre, numero);
		}		
		
	}


	public Enumeration getCapitulos () {
		return Collections.enumeration(_capitulos);
	}
	
	/**
	* getId
	* @return int
	*/
	public int getId () {
		return _idtemporada;
	}
	
	
	
}