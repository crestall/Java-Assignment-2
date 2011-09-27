import java.util.ArrayList;

public class Assign2 {


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {	
			ArrayList<CakeRecipe> recipes = CakeRecipeUtil.parseDirectory("files");
			for (CakeRecipe recipe:recipes)
				System.out.println(recipe);

   			theGui test_this = new theGui();
   			test_this.launchFrame();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exception: " + e);
			System.exit(0);
		}
	}
}
