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
		recipe.setImgDir("./");
		recipe.setImgName("thisFileDoesNotExist.jpg");
		
		assertEquals(expectedValidity, recipe.isValid());
		assertEquals(expectedImageSrc, recipe.getImageDir());
		assertEquals(expectedImageName, recipe.getImageName());
	}

	@Test
	public void testIsValid4()
	{
		CakeRecipe recipe = new CakeRecipe();
		boolean expectedValidity = false;
		
		recipe.setName("My Recipe");
		recipe.addIngredient("Stuff");
		recipe.addIngredient("Other Stuff");
		
		assertEquals(expectedValidity, recipe.isValid());
	}
	
	@Test
	public void testIsValid5()
	{
		CakeRecipe recipe = new CakeRecipe();		
		boolean expected = false;
		
		recipe.setName("My Recipe");
		recipe.addMethodStep("Step 1");
		recipe.addMethodStep("Step 2");
		recipe.addMethodStep("Step 3");
		
		assertEquals(expected, recipe.isValid());
	}
	
	@Test
	public void testIsValid6()
	{
		CakeRecipe recipe = new CakeRecipe();		
		boolean expected = false;
		
		recipe.addIngredient("Stuff");
		recipe.addIngredient("Other Stuff");
		recipe.addMethodStep("Step 1");
		recipe.addMethodStep("Step 2");
		recipe.addMethodStep("Step 3");
		
		assertEquals(expected, recipe.isValid());
	}
}
