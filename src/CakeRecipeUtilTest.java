import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class CakeRecipeUtilTest
{

	@Test
	public void searchTest()
	{
		
		ArrayList<CakeRecipe> recipes = CakeRecipeUtil.parseDirectory("files");
		String[] tokens = {"cake", "butter", "pan", "stir", "blueberry"};
		ArrayList<CakeRecipe> matchingRecipes = CakeRecipeUtil.search(recipes, tokens, true, true);
		for (CakeRecipe recipe:matchingRecipes) {
			System.out.println(recipe.name);
			if (recipe.name.equals("Blueberry cake")) return;
		}
		fail("Failed to return the blue berry cake");
	}

}
