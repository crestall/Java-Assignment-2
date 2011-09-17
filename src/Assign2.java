import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class Assign2 {
//testing
	//testing b

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int a;//
		try {				
			File dir = new File("files");

			String[] children = dir.list();
			// Compile a regex expression to match HTML or HTM files
			Pattern isHtml = Pattern.compile(".*[.]html?$");
			ArrayList<File> htmlFiles = new ArrayList<File>();
			
			// Fill the array with all valid HTML files
			for(String child: children)
			{
				if (isHtml.matcher(child).matches())
					htmlFiles.add(new File("files/" + child));				
			}

			HTMLEditorKit.Parser parser = new ParserDelegator();
			ArrayList<CakeRecipe> recipes = new ArrayList<CakeRecipe>();
			// Parse the HTML files
			for (File file: htmlFiles)
			{
				Reader reader = new FileReader(file);
				CakeRecipe recipe = new CakeRecipe();
	   			parser.parse(reader, new HTMLRecipeParser(recipe), true);
	   			reader.close();
	   			recipes.add(recipe);
	   			System.out.println(recipe);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exception: " + e);
			System.exit(0);
		}
	}
}
