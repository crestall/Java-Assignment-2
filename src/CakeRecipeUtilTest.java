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
		if (matchingRecipes.size() != 1)
			fail("Wrong number of results");
		if (matchingRecipes.get(0).name != "Blueberry cake")
			fail("Returned wrong cake");
	}

}
