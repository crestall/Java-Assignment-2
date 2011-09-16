import java.util.ArrayList;


public class CakeRecipe
{
	public String name;
	public String imgSrc;
	public String prepTime;
	public String cookTime;
	private ArrayList<String> ingredients;
	private ArrayList<String> method;
	
	public CakeRecipe()
	{
		ingredients = new ArrayList<String>();
		method = new ArrayList<String>();
	}
	
	public void addIngredient(String ingredientName)
	{
		ingredients.add(ingredientName);
	}
	
	public ArrayList<String> getIngredients() {
		return ingredients;
	}	

	public void addMethodStep(String step)
	{
		method.add(step);
	}
	
	public ArrayList<String> getMethod() {
		return method;
	}	
	
	public String toString() {
		String out = "";
		out += "Recipe for '"+name+"' Prep Time: "+prepTime+", Cook Time: "+cookTime+"\n";
		out += "Image <"+imgSrc+">\n";
		out += "Ingredients:\n";
		for (String ingredient: ingredients)
			out += " * "+ingredient+"\n";
		out += "Method:\n";
		int i = 0;
		for (String step: method)
			out += " "+(++i)+". "+step+"\n";
		return out;
	}
}
