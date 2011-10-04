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
		ArrayList<SearchResult<CakeRecipe>> matchingRecipes = CakeRecipeUtil.search(recipes, tokens, true, true);
		for (SearchResult<CakeRecipe> result:matchingRecipes) {
			System.out.println(result.item.getName()+" > "+result.hits);
			if (result.item.getName().equals("Blueberry cake")) return;
		}
		fail("Failed to return the blueberry cake");
	}
}
