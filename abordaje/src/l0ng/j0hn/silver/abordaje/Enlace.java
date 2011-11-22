package l0ng.j0hn.silver.abordaje;


public class Enlace {
	private String _url;
	private String _text;
	private String _page;
	
	/**
	* Enlace
	* Constructor
	* @param String url
	* @param String text
	*/
	public Enlace (String url, String text) {
		_url = url;
		_text = text;
		String[] result = _url.split("/");
		_page = result[(result.length-1)];

	}
	
	/**
	* getText
	*
	* @return String _text
	*/
	public String getUrl() {
		return _url;
	}

	/**
	* getPage
	*
	* @return String _page
	*/
	public String getPage() {
		return _page;
	}

	/**
	* getText
	*
	* @return String _text
	*/
	public String getText() {
		return _text;
	}
} 