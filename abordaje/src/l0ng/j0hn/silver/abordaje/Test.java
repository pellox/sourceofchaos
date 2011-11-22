
package l0ng.j0hn.silver.abordaje;

public class Test {
	public static void main(String args[]) {
		DataService ds = new DataService("");
		ds.connect("");
		Log.write("Esto funciona: " + Propiedades.getPropiedad("saludo"));
	}
}