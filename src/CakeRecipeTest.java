import static org.junit.Assert.*;
import org.junit.Test;


public class CakeRecipeTest
{

	@Test
	public void testIsValid1()
	{
		CakeRecipe recipe = new CakeRecipe();
		boolean expected = false;
		
		assertEquals(expected, recipe.isValid());
	}
	
	@Test
	public void testIsValid2()
	{
		CakeRecipe recipe = new CakeRecipe();		
		boolean expected = true;
		
		recipe.setName("My Recipe");
		recipe.addIngredient("Stuff");
		recipe.addIngredient("Other Stuff");
		recipe.addMethodStep("Step 1");
		recipe.addMethodStep("Step 2");
		recipe.addMethodStep("Step 3");
		
		assertEquals(expected, recipe.isValid());
	}

	@Test
	public void testIsValid3()
	{
		CakeRecipe recipe = new CakeRecipe();
		boolean expectedValidity = true;
		String expectedImageSrc = null;
		String expectedImageName = null;
		
		recipe.setName("My Recipe");
		recipe.addIngredient("Stuff");
		recipe.addIngredient("Other Stuff");
		recipe.addMethodStep("Step 1");
		recipe.addMethodStep("Step 2");
		recipe.addMethodStep("Step 3");
		recipe.setImgSrc("./thisFileDoesNotExist.jpg");
		
		assertEquals(expectedValidity, recipe.isValid());
		assertEquals(expectedImageSrc, recipe.getImageSrc());
		assertEquals(expectedImageName, recipe.getImageName());
	}
}
