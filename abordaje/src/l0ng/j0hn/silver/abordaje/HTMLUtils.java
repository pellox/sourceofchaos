/**
*
* javac -cp jsoup-1.6.1.jar HTMLUtils.java
* java -cp jsoup-1.6.1.jar:. HTMLUtils
*  
*/
package l0ng.j0hn.silver.abordaje;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import org.jsoup.helper.HttpConnection;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLUtils {
  private HTMLUtils() {}

  public static List<String>extractLinks(String url) throws IOException {
    final ArrayList<String> result = new ArrayList<String>();

    Document doc = Jsoup.connect(url).userAgent(UserAgent.getUserAgent()).get();

    Elements links = doc.select("a[href]");
    Elements media = doc.select("[src]");
    Elements imports = doc.select("link[href]");

    // href ...
    for (Element link : links) {
      result.add(link.attr("abs:href"));
    }

    // img ...
   /* for (Element src : media) {
      result.add(src.attr("abs:src"));
    }*/

    // js, css, ...
  /*  for (Element link : imports) {
      result.add(link.attr("abs:href"));
    }*/
    return result;
  }


	/**
	* extractLinksFiltered
	* extrae los enlaces que cumplan un filtro.
	* @param String url
	* @param String filter
	*
	* @return List<Enlace>
	*/
  public static List<Enlace>extractLinksFiltered(String url, String filter) throws IOException {
    final ArrayList<Enlace> result = new ArrayList<Enlace>();

    Document doc = Jsoup.parse(Pagina.descargarHtml(url));

   //Document doc = Jsoup.connect(url).request(new HttpConnection().url(new URL(url))).get();
   // Document doc = Jsoup.parse(new URL("http","75.69.19.96",56883,url),50000);
    //Document doc = Jsoup.connect(url).userAgent(UserAgent.getUserAgent()).get();
    
    Elements links = doc.select("a[href]");
    Elements media = doc.select("[src]");
    Elements imports = doc.select("link[href]");

    // href ...
    for (Element link : links) {
    	if (link.attr("href").startsWith(filter)) {
    		System.out.println("OK: " + link.attr("href"));
      	result.add(new Enlace(link.attr("href"),link.text()));
      }
//     System.out.println("va va  va ok " + link.attr("href") + ": " + result.isEmpty());
    }
    return result;
  }
  
  
	/**
	* extractSeasons
	* extrae los enlaces que cumplan un filtro.
	* @param String url
	* @param int idserie
	*
	* @return List<Enlace>
	*/
  public static List<Temporada> extractSeasons(String url, int idserie) throws IOException {
    final ArrayList<Temporada> result = new ArrayList<Temporada>();
    Temporada tmpTemporada = null;
	 ArrayList<Capitulo> capitulosTemporada = null;
	 
    //Document doc = Jsoup.parse(Pagina.descargarHtml(url));
    //Document doc = Jsoup.connect(url).userAgent(UserAgent.getUserAgent()).get();
    Document doc = Jsoup.parse(Pagina.descargarHtml(url));
	// Patrón a buscar temporada <h3 class="season" data-number="1" id="season-1">
	// capitulo /capitulo/the-vampire-diaries/capitulo-03/44810
	// <table class="episodes"> 
	// Descarga de serie: <a href="/s/ngo/2/0/2/8/230" title="Descargar Comportamiento Perturbado
	// Descarga de serie: /s/y/2004228/0/s/1763
    Elements headers = doc.select("h3.season");
    Element nameElement = null;
    Element table = null; 
    Elements capitulos = null;
    Element numeroCapitulo = null;
    Element nombreCapitulo = null;
    Element fechaCapitulo = null;
    Element urlCapitulo = null;
    String season = null;

    // href ...
    for (Element seasons : headers) {
      	season = seasons.attr("data-number");

      	nameElement = doc.select("h3#"+seasons.attr("id")+" > strong").first();
      	capitulos = doc.select("h3#"+seasons.attr("id")+" + table > tbody > tr");
      	tmpTemporada = new Temporada(nameElement.text(),season,idserie);

      	capitulosTemporada = new ArrayList<Capitulo>();
      	      		
      	for (Element capitulo : capitulos) {
      		nombreCapitulo = capitulo.select("td.episode-title").first();
       		numeroCapitulo = capitulo.select("td.episode-title  strong").first();
      		fechaCapitulo = capitulo.select("td.episode-title + td").first();
      		urlCapitulo = capitulo.select("td.episode-title > a").first();
      		capitulosTemporada.add(new Capitulo(tmpTemporada.getId(),numeroCapitulo.text(), nombreCapitulo.text(), fechaCapitulo.text(), urlCapitulo.attr("href")));
      	}
      	
      	tmpTemporada.setCapitulos(capitulosTemporada);
      	result.add(tmpTemporada);     	
    }

    return result;
  }
  
  
  	/**
	* extractChapterFiles
	* extrae los enlaces de ficheros de determinado capítulo
	* @param String url
	* @param int idcapitulo
	*
	* @return List<Enlace>
	*/
  public static List<Fichero> extractChapterFiles(String url, int idcapitulo) throws IOException {
    final ArrayList<Fichero> result = new ArrayList<Fichero>();
	 ArrayList<Fichero> capitulosTemporada = null;
	     Document doc = Jsoup.parse(Pagina.descargarHtml(url));
    //Document doc = Jsoup.connect(url).userAgent(UserAgent.getUserAgent()).get();

	// Patrón a buscar temporada <h2 class="header-subtitle descargadirecta">
	// capitulo /capitulo/the-vampire-diaries/capitulo-03/44810
	// <table class="episodes"> 
	// Descarga de serie: <a href="/s/ngo/2/0/2/8/230" title="Descargar Comportamiento Perturbado
	// Descarga de serie: /s/y/2004228/0/s/1763
    Elements ficheros = doc.select("h2.header-subtitle.descargadirecta + table > tbody > tr");

    Element urlFichero = null;
    Element servidorFichero = null;
    Element audioFichero = null;
    Element subtitulosFichero = null;
    Element calidadFichero = null;
    Element formatoFichero = null;
    Element autorFichero = null;

    if (ficheros.size() < 2) {return result;}
    // href ...
    for (Element fichero : ficheros) {
	    
	    urlFichero = fichero.select("td.episode-server > a").first();
	    Log.write("----------------------" + urlFichero.attr("href"));
    	servidorFichero = fichero.select("td.episode-server-img  span").first();
        Log.write("|---------" + servidorFichero.attr("class"));
   		audioFichero = fichero.select("td.episode-lang > span").first();
    Log.write("|---------------" + audioFichero.text());
        	subtitulosFichero = fichero.select("td.episode-lang + td > span").first();
    Log.write("|-------------" + subtitulosFichero.text());
        	calidadFichero = fichero.select("td.episode-lang + td + td > span").first();
        	 Log.write("|-------------" + calidadFichero.attr("title"));
   		formatoFichero = fichero.select("td.episode-notes  li").first();
     Log.write("|-----------" + formatoFichero.text());
        	autorFichero = fichero.select("td.episode-uploader").first();
		  Log.write("|-----------" + autorFichero.text());

      	result.add(new Fichero(idcapitulo,urlFichero.attr("href"),servidorFichero.attr("class"),audioFichero.text(),subtitulosFichero.text(),calidadFichero.attr("title"), formatoFichero.text(), autorFichero.text()));
      	      	
    }

    return result;
  }
  
  
  	/**
	* extractChapterFiles
	* extrae los enlaces de ficheros de determinado capítulo
	* @param String url
	* @param int idfichero
	*
	* @return String
	*/
  public static String extractFinalLink(String pref, String url, int idfichero) throws IOException {
   String result = "";
	 ProxyFreeSurfProxy pp = new ProxyFreeSurfProxy();
    //Document doc = Jsoup.connect(url).userAgent(UserAgent.getUserAgent()).get();
    //OK: 
    Document doc = Jsoup.parse(Pagina.descargarHtmlPorProxy(url));
    //Document doc = Jsoup.parse(Pagina.descargarHtml(url));

    //Document doc = Jsoup.parse(pp.getPage(url));
    Element enlace = doc.select("table.episodes.full-width  a").first();
	
    result =  enlace.attr("href");
    
    result = Jsoup.connect(pref+result).userAgent(UserAgent.getUserAgent()).execute().url().toString();
    
    
    
    return result;
  }
  
  	/**
	* extractChapterFiles
	* extrae los enlaces de ficheros de determinado capítulo
	* @param String url
	* @param int idcapitulo
	*
	* @return List<Enlace>
	*/
  public static List<Proxy> extractProxies(String url) throws IOException {
    final ArrayList<Proxy> result = new ArrayList<Proxy>();
    Element proxyHost = null;
    Element proxyPort = null;
	try {
	     Document doc = Jsoup.parse(Pagina.descargarHtml(url));
	

    Elements ficheros = doc.select("tr.row0,tr.row1");

    // href ...
    for (Element fichero : ficheros) {
	            	Log.write("oh yes");

        	proxyHost = fichero.select("td + td").first();
        	proxyPort = fichero.select("td + td + td").first();
        	Log.write("|[nuevo Proxy]--" + proxyHost.text() +":"+proxyPort.text());

      	result.add(new Proxy(proxyHost.text(),Integer.parseInt(proxyPort.text())));
      	      	
    }

	} catch (Exception ioex) {
		Log.write("Error al sacar datos: " + ioex.getMessage());
	}
    return result;
  }
}
