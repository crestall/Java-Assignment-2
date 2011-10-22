import java.io.FileWriter;
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
				//TODO: Create custom exception type
				throw new Exception("No files found in directory: "+directory);
			}
			for (CakeRecipe recipe:recipes)
			{
				//TODO: Add try catch for file io errors
				FileWriter file = new FileWriter(recipe.getName()+".txt");
				recipe.writeToFile(file);
				file.close();
			}
			
   			Gui theGui = new Gui();
   			theGui.launchFrame();
		} catch (Exception e) {
			//TODO: Auto-generated catch block
			e.printStackTrace();
			System.out.println("exception: " + e);
			System.exit(0);
		}
	}
}
