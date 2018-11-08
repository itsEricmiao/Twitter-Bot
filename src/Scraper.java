
import com.jaunt.*;


//This class uses the jaunt API to scrape webcontent and return the resulting text. 
//https://jaunt-api.com/download.htm
public class Scraper {
	
	UserAgent userAgent; //( an internal 'headless' browser)
	static String GOOGLE_URL = "http://google.com";
	static String HTTP = "=http"; 

	Scraper() {    

	}
	
	void scrape(String url, String searchTerm) throws JauntException{
	{
	    userAgent = new UserAgent();      //create new userAgent (headless browser)
	    userAgent.settings.autoSaveAsHTML = true;
	    userAgent.visit(url);       //visit 
	    userAgent.doc.apply(searchTerm).submit();     //apply form input (starting at first editable field & submit) 
	    
	    Elements links = userAgent.doc.findEvery("<h3 class=r>").findEvery("<a>");   //find search result links 
	    for(Element link : links) System.out.println(link.getAt("href"));            //print results -- expand this class here in order to use
	  }		
		
	}
	
	//prints the text content of google results -- can modify to save this text
	void scrapeGoogleResults(String searchTerm) throws JauntException{
	{
	    userAgent = new UserAgent();      //create new userAgent (headless browser)
	    userAgent.settings.autoSaveAsHTML = true;
	    userAgent.visit(GOOGLE_URL);       //visit 
	    userAgent.doc.apply(searchTerm).submit();     //apply form input (starting at first editable field & submit) 
	    
	    Elements links = userAgent.doc.findEvery("<h3 class=r>").findEvery("<a>");   //find search result links 
	    for(Element link : links) 
	    {
	    	String strLink = link.getAt("href"); 
	    	int startIndex = strLink.indexOf(HTTP); 
	    	if(startIndex != -1)
	    	{
	    		int endIndex = strLink.indexOf("&amp;sa=");
	    		UserAgent userAgent2 = new UserAgent();
	    		userAgent2.visit(strLink.substring(startIndex+1, endIndex));
	    		String body = userAgent2.doc.findFirst("<body>").getTextContent();
	    		System.out.println(body);            //print results -- expand this class here in order to use
	    		System.out.println("-------");
	    	}
	    }
	  }		
		
	}
}