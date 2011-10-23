import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**    
 * Assign2 is the driver class that views recipes.
 *    
 * @version 1.0, 23/10/2011, Initial version
 * @author Adrian Cowan (6515258), Chris Restall (651____)
 */
public class Assign2
{
	/**
	 * The entry point for the program, it handles the command line inputs.
	 * 
	 * @param args The arguments passed to the program.
	 */
	public static void main(String[] args)
	{
		try {
			// The first argument passed should be the directory to find the files in
			String directory = args[0];
			
			// Get a list of all of the recipes
			ArrayList<CakeRecipe> recipes = CakeRecipeUtil.parseDirectory(directory);
			if (recipes.size() == 0)
			{
				throw new RecipeViewerException("No files found in directory: "+directory);
			}
			
			// Print the recipe details back to files
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
			
			// Startup the GUI
   			Gui theGui = new Gui();
   			theGui.launchFrame();
		} catch (RecipeViewerException rve) {
			// Known exception protection
			Util.warning(rve.toString());
			System.exit(-2);
		} catch (Exception e) {
			// Global exception protection
			e.printStackTrace();
			System.out.println("Exception: " + e);
			System.exit(-1);
		}
	}
}
