import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Assign2 {


	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try {	
			String directory = args[0];
			ArrayList<CakeRecipe> recipes = CakeRecipeUtil.parseDirectory(directory);
			if (recipes.size() == 0)
			{
				throw new RecipeViewerException("No files found in directory: "+directory);
			}
			for (CakeRecipe recipe:recipes)
			{
				try {
					FileWriter file = new FileWriter(recipe.getName()+".txt");
					recipe.writeToFile(file);
					file.close();
				} catch (IOException ioe) {
					throw new RecipeViewerException("unable to open output file for writing");
				}
			}
			
   			Gui theGui = new Gui();
   			theGui.launchFrame();
		} catch (RecipeViewerException rve) {
			Util.warning(rve.toString());
			System.exit(-2);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception: " + e);
			System.exit(-1);
		}
	}
}
