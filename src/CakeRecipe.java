import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**    
 * CakeRecipe represents an the recipe for a cake
 *    
 * @version 1.0, 23/10/2011, Initial version
 * @author Adrian Cowan (6515258), Chris Restall (651____)
 */
public class CakeRecipe
{
	private String name;
	private String imgName;
	private String imgDir;
	private int prepTime;
	private int cookTime;
	private ArrayList<String> ingredients;
	private ArrayList<String> method;
	
	/**
	 * Creates an empty recipe,
	 * at this point it will still be invalid until it is filled with data
	 */
	public CakeRecipe()
	{
		ingredients = new ArrayList<String>();
		method = new ArrayList<String>();
	}
	
	/**
	 * Set the name of the cake
	 * 
	 * @param name The new name of the recipe.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get the name of the cake
	 * 
	 * @return The name of the recipe.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name of the cake
	 * 
	 * @param name The new name of the recipe.
	 */
	public void setImgDir(String imgDir)
	{
		this.imgDir = imgDir;
	}	

	/**
	 * Set the filename of the image of the cake
	 * 
	 * @param imgName The filename image of the cake.
	 */
	public void setImgName(String imgName)
	{
		this.imgName = imgName;
	}
	
	/**
	 * Get the filename of the image of the cake
	 * 
	 * @return The filename of the image of the cake
	 */
	public String getImgName()
	{
		return imgName;
	}
	
	/**
	 * Get the filename of the image of the cake
	 * 
	 * @return The filename of the image of the cake
	 */
	public String getImageName()
	{
		return imgName;
	}
	
	/**
	 * Get the directory that the ckae's image resides in
	 * 
	 * @return The directory that the ckae's image resides in
	 */
	public String getImageDir()
	{
		return imgDir;
	}

	/**
	 * Get the expected preparation time for the recipe
	 * 
	 * @return The expected preparation time for the recipe
	 */
	public int getPrepTime()
	{
		return prepTime;
	}

	/**
	 * Set the expected preparation time for the recipe
	 * 
	 * @param prepTime The expected preparation time for the recipe
	 */
	public void setPrepTime(int prepTime)
	{
		this.prepTime = prepTime;
	}

	/**
	 * Get the expected cooking time for the recipe
	 * 
	 * @return The expected cooking time for the recipe
	 */
	public int getCookTime()
	{
		return cookTime;
	}

	/**
	 * Get the expected total time for the recipe
	 * 
	 * @return The expected total time for the recipe
	 */
	public int getRequiredTime()
	{
		return prepTime+cookTime;
	}

	/**
	 * Set the expected cooking time for the recipe
	 * 
	 * @param cookTime The expected cooking time for the recipe
	 */
	public void setCookTime(int cookTime)
	{
		this.cookTime = cookTime;
	}

	/**
	 * Add an ingredient to the recipe
	 * 
	 * @param ingredientName The ingredient line for the recipe
	 */
	public void addIngredient(String ingredientName)
	{
		ingredients.add(ingredientName);
	}

	/**
	 * Get the list of ingredients
	 * 
	 * @return The list of ingredients
	 */
	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	/**
	 * Add an step the the recipes method
	 * 
	 * @param step The new step in the method for the recipe
	 */
	public void addMethodStep(String step)
	{
		method.add(step);
	}

	/**
	 * Get the list of steps that make up the method for the recipe
	 * 
	 * @return The list of steps that make up the method for the recipe
	 */
	public ArrayList<String> getMethod() {
		return method;
	}

	/**
	 * Write the recipe to a file
	 * 
	 * @param file A FileWriter to use to write the file
	 */
	public void writeToFile(FileWriter file) throws IOException {
		StringBuffer out = new StringBuffer();
		out.append("********************\n");
		out.append("Name of the cake\n");
		out.append(name+"\n");
		out.append("********************\n");
		out.append("Name of the cake’s image\n");
		out.append(imgName+"\n");
		out.append("********************\n");
		out.append("Preparation time\n");
		out.append(prepTime+" minutes\n");
		out.append("********************\n");
		out.append("Cooking time\n");
		out.append(cookTime+" minutes\n");
		out.append("********************\n");
		out.append("Ingredients\n");
		for (String ingredient: ingredients)
			out.append(" * "+ingredient+"\n");
		out.append("********************\n");
		out.append("Method\n");
		int i = 0;
		for (String step: method)
			out.append(" "+(++i)+". "+step+"\n");
		out.append("********************\n");
		
		int idx = -1;
		do {
			int next = out.indexOf("\n",idx+1);
			if (next-idx > 60) {
				int nl = out.lastIndexOf(" ",idx+61);
				out.setCharAt(nl, '\n');
				next = idx+61;
			}
			idx = next;
		} while (idx>0);
		file.write(out.toString().replace("\n", "\r\n"));
	}

	/**
	 * Create a string representation of the recipe
	 * 
	 * @return A string representation of the recipe
	 */
	public String toString() {
		String out = "";
		out += "Recipe for '"+name+"' Prep Time: "+prepTime+", Cook Time: "+cookTime+"\n";
		out += "Image <"+imgName+">\n";
		out += "Ingredients:\n";
		for (String ingredient: ingredients)
			out += " * "+ingredient+"\n";
		out += "Method:\n";
		int i = 0;
		for (String step: method)
			out += " "+(++i)+". "+step+"\n";
		return out;
	}

	/**
	 * Count the number of occurrences of a any of the provided tokens in the name of the recipe
	 * 
	 * @param tokens A list of tokens to find
	 * @return The number of occurrences in the name of the recipe
	 */
	public int countInName(String[] tokens)
	{
		String nameLower = name.toLowerCase();
		int hits = 0;
		for (String token:tokens) {
			int idx = -1;
			do {
				idx = nameLower.indexOf(token, idx+1);
				hits++;
			} while (idx != -1);
			hits--;
		}
		return hits;
	}

	/**
	 * Count the number of occurrences of a any of the provided tokens in the recipes method
	 * 
	 * @param tokens A list of tokens to find
	 * @return The number of occurrences in the recipes method
	 */
	public int countInMethod(String[] tokens)
	{
		int hits = 0;
		for (String step:method) {
			String stepLower = step.toLowerCase();
			for (String token:tokens) {
				int idx = -1;
				do {
					idx = stepLower.indexOf(token, idx+1);
					hits++;
				} while (idx != -1);
				hits--;
			}
		}
		return hits;
	}

	/**
	 * Count the number of occurrences of a any of the provided tokens in the recipes ingredients
	 * 
	 * @param tokens A list of tokens to find
	 * @return The number of occurrences in the recipes ingredients
	 */
	public int countInIngredients(String[] tokens)
	{
		int hits = 0;
		for (String step:ingredients) {
			String stepLower = step.toLowerCase();
			for (String token:tokens) {
				int idx = -1;
				do {
					idx = stepLower.indexOf(token, idx+1);
					hits++;
				} while (idx != -1);
				hits--;
			}
		}
		return hits;
	}

	/**
	 * Checks whether the recipe is valid,
	 * that is has a name, list of ingredients, steps in the method
	 * and ensures that the image can be accessed 
	 * 
	 * @return The validity of the recipe
	 */
	public boolean isValid() throws InvalidRecipeException
	{
		if (name == null)
		{
			throw new InvalidRecipeException("no name found");
		}
		if (ingredients.size() == 0)
		{
			throw new InvalidRecipeException("no ingredients found");
		}
		if (method.size() == 0)
		{
			throw new InvalidRecipeException("no method found");
		}
		if (imgName != null)
		{
			if (!(new File(imgDir+imgName)).exists())
			{
				imgName = null;
				imgDir = null;
				Util.warning("image specified by recipe \""+name+"\" not found");
			}
		}
		return true;
	}
}
