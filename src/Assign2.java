import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Assign2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {				
			File dir = new File("files");

			String[] children = dir.list();
			
			for(String child: children)
			{
				File test = new File("files/" + child);							
			}
			
			Document doc = (Document) Jsoup.parse(new File("files/Caramel & apple upside-down cake.html"), "UTF-8");
									
			String title = doc.select("#content-1 h1.heading").first().text();					
			System.out.println(title);
			
			
			 	
			String imgSrc = doc.select("img[itemprop=photo]").first().attr("src");
			
			String preparation_time = doc.select("#content-1 p[itemprop=prepTime]").first().text();			
			System.out.println(preparation_time);
			
			String cooking_time = doc.select("#content-1 p[itemprop=cookTime]").first().text();			
			System.out.println(cooking_time);
			
			Elements ingredients = doc.select("#content-1 div.recipe-ingredients .module-content li");
			for (Element elm : ingredients)
			{
				System.out.println("* " + elm.text());
			}

			
			 Elements method = doc.select("ol#method li > p");
			 ArrayList<String> steps = new ArrayList<String>();
			 for (Element step: method)
				steps.add(step.text());
			 
			Pattern isHtml = Pattern.compile(".*[.]html?$");
			 System.out.println(isHtml.matcher("Test String").matches());
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exception: " + e);
			System.exit(0);
		}
		
		
		               
		
	}

}
