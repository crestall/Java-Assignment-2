import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;


public class CakeRecipeUtil
{

	public static ArrayList<CakeRecipe> parseDirectory(String directory) throws RecipeViewerException
	{
		File dir = new File(directory);
		if (!dir.exists())
			throw new RecipeViewerException("directory not found");
		
		String[] children = dir.list();
		// Compile a regex expression to match HTML or HTM files
		Pattern isHtml = Pattern.compile(".*[.]html?$");
		ArrayList<File> htmlFiles = new ArrayList<File>();
		
		// Fill the array with all valid HTML files
		for(String child: children)
		{
			if (isHtml.matcher(child).matches())
				htmlFiles.add(new File(directory + "/" + child));				
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
				Util.warning("file could not be read, missing: "+file.toString());
				continue;
			}
			CakeRecipe recipe = new CakeRecipe();
			recipe.setImgDir(directory+"/");
   			try
			{
				parser.parse(reader, new HTMLRecipeParser(recipe), true);
				reader.close();
			}
			catch (IOException e)
			{
				throw new RecipeViewerException(e.toString());
			}
   			try
			{
				if (recipe.isValid())
					recipes.add(recipe);
			}
			catch (InvalidRecipeException e)
			{
				Util.warning(e.toString());
			}
		}
		return recipes;
	}

	public static ArrayList<SearchResult<CakeRecipe>> search(ArrayList<CakeRecipe> recipes, String[] tokens, boolean searchName, boolean searchMethod, boolean searchIngredients)
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
			if (searchIngredients) hits += recipe.countInIngredients(tokensLower);
			if (hits != 0) results.add(new SearchResult<CakeRecipe>(hits ,recipe));
		}
		return results;
	}
	
	public static void orderResultsBy(ArrayList<SearchResult<CakeRecipe>> results, SearchResultOrder order, int ascendingDescending) {
		switch (order) {
			case RELEVANCE: Collections.sort(results, new CakeRecipeResultByRelevance(ascendingDescending));break;
			case PREP_TIME: Collections.sort(results, new CakeRecipeResultByPrepTime(ascendingDescending));break;
			case COOK_TIME: Collections.sort(results, new CakeRecipeResultByCookTime(ascendingDescending));break;
			case REQUIRED_TIME: Collections.sort(results, new CakeRecipeResultByRequiredTime(ascendingDescending));break;
			default: Collections.sort(results, new CakeRecipeResultByRelevance(ascendingDescending));break;
		}
	}
	
}
