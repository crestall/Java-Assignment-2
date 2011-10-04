import java.util.ArrayList;


public class CakeRecipe
{
	private String name;
	private String imgName;
	private String imgSrc;
	public int prepTime;
	public int cookTime;
	private ArrayList<String> ingredients;
	private ArrayList<String> method;
	
	public CakeRecipe()
	{
		ingredients = new ArrayList<String>();
		method = new ArrayList<String>();
	}
	
	
	
	public void setName(String name) {
		this.name = name;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
		this.imgName = imgSrc.substring(imgSrc.lastIndexOf('/')+1);
	}	
	
	
	public String getImgName() {
		return imgName;
	}



	public void setImgName(String imgName) {
		this.imgName = imgName;
	}



	public String getImageName()
	{
		return imgName;
	}
	
	public String getImageSrc()
	{
		return imgSrc;
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

	public int countInName(String[] tokens)
	{
		int hits = 0;
		for (String token:tokens) {
			int idx = -1;
			do {
				idx = name.indexOf(token, idx+1);
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
			for (String token:tokens) {
				int idx = -1;
				do {
					idx = step.indexOf(token, idx+1);
					hits++;
				} while (idx != -1);
				hits--;
			}
		}
		return hits;
	}
}
