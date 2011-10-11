import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;


public class CakeRecipeUtil
{

	public static ArrayList<CakeRecipe> parseDirectory(String string)
	{			
		File dir = new File(string);

		String[] children = dir.list();
		// Compile a regex expression to match HTML or HTM files
		Pattern isHtml = Pattern.compile(".*[.]html?$");
		ArrayList<File> htmlFiles = new ArrayList<File>();
		
		// Fill the array with all valid HTML files
		for(String child: children)
		{
			if (isHtml.matcher(child).matches())
				htmlFiles.add(new File(string + "/" + child));				
		}

		HTMLEditorKit.Parser parser = new ParserDelegator();
		ArrayList<CakeRecipe> recipes = new ArrayList<CakeRecipe>();
		// Parse the HTML files
		for (File file: htmlFiles)
		{
			Reader reader;
			try
			{
				reader = new FileReader(file);
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			CakeRecipe recipe = new CakeRecipe();
   			try
			{
				parser.parse(reader, new HTMLRecipeParser(recipe), true);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   			try
			{
				reader.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   			recipes.add(recipe);
		}
		return recipes;
	}

	public static ArrayList<SearchResult<CakeRecipe>> search(ArrayList<CakeRecipe> recipes, String[] tokens, boolean searchName, boolean searchMethod)
	{
		ArrayList<SearchResult<CakeRecipe>> results = new ArrayList<SearchResult<CakeRecipe>>();
		String[] tokensLower = new String[tokens.length];
		for (int i = 0; i<tokens.length; i++) {
			tokensLower[i] = tokens[i].toLowerCase();
		}
		for (CakeRecipe recipe : recipes) {
			int hits = 0;
			if (searchName) hits += recipe.countInName(tokensLower);
			if (searchMethod) hits += recipe.countInMethod(tokensLower);
			if (hits != 0) results.add(new SearchResult<CakeRecipe>(hits ,recipe));
		}
		return results;
	}
	
}
