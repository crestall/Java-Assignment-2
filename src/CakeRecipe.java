import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class CakeRecipe
{
	private String name;
	private String imgName;
	private String imgDir;
	private int prepTime;
	private int cookTime;
	private ArrayList<String> ingredients;
	private ArrayList<String> method;
	
	public CakeRecipe()
	{
		ingredients = new ArrayList<String>();
		method = new ArrayList<String>();
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}

	public void setImgDir(String imgDir)
	{
		this.imgDir = imgDir;
	}	

	public void setImgName(String imgName)
	{
		this.imgName = imgName;
	}
	
	public String getImgName()
	{
		return imgName;
	}

	public String getImageName()
	{
		return imgName;
	}
	
	public String getImageDir()
	{
		return imgDir;
	}
	
	public int getPrepTime()
	{
		return prepTime;
	}

	public void setPrepTime(int prepTime)
	{
		this.prepTime = prepTime;
	}

	public int getCookTime()
	{
		return cookTime;
	}
	
	public int getRequiredTime()
	{
		return prepTime+cookTime;
	}

	public void setCookTime(int cookTime)
	{
		this.cookTime = cookTime;
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
