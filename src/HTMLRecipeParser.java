import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

class HTMLRecipeParser extends HTMLEditorKit.ParserCallback
{
	private CakeRecipe recipe;
	
	public HTMLRecipeParser(CakeRecipe recipe) {
		this.recipe = recipe;
	}
	
    private boolean foundName = false;
    private boolean foundPrepTime = false;
    private boolean foundCookTime = false;
    private int searchIngredients = 0;
    private boolean foundIngredientList = false;
    private boolean foundIngredient = false;
    private int searchMethod = 0;
    private boolean foundMethod = false;
    private boolean foundMethodStep = false;

    public void handleText(char[] data, int pos)
    {
        if(foundName) recipe.setName(new String(data));
        if(foundPrepTime) recipe.prepTime = Integer.parseInt(new String(data).split(" ")[0]);        
        if(foundCookTime) recipe.cookTime = Integer.parseInt(new String(data).split(" ")[0]);        
        if(foundIngredient) recipe.addIngredient(new String(data));
        if(foundMethodStep) recipe.addMethodStep(new String(data));
    }

    public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos)
    {
    	if(t == HTML.Tag.IMG && a.containsAttribute("itemprop", "photo"))
    		recipe.setImgSrc(((String) a.getAttribute(HTML.Attribute.SRC)).replace('\\', '/'));
    }
    
    public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos)
    {
        if(t == HTML.Tag.STRONG && a.containsAttribute("itemprop", "name"))
        	foundName = true;

    	if(t == HTML.Tag.P && a.containsAttribute("itemprop", "prepTime"))
    		foundPrepTime = true;

    	if(t == HTML.Tag.P && a.containsAttribute("itemprop", "cookTime"))
    		foundCookTime = true;
    	
    	if(t == HTML.Tag.DIV)
    	{
        	if (searchIngredients > 0) searchIngredients++;
        	if (searchMethod > 0) searchMethod++;
    		if (a.containsAttribute(HTML.Attribute.CLASS, "module recipe-ingredients"))
    			searchIngredients = 1; 
    		if (a.containsAttribute(HTML.Attribute.CLASS, "module recipe-method"))
    			searchMethod = 1; 
    	}

		if(t == HTML.Tag.UL && searchIngredients > 0)
    		foundIngredientList = true;

		if(t == HTML.Tag.LI && foundIngredientList)
			foundIngredient = true;

		if(t == HTML.Tag.OL && searchMethod > 0)
    		foundMethod = true;

		if(t == HTML.Tag.P && foundMethod)
			foundMethodStep = true;
    }
    
    public void handleEndTag(HTML.Tag t, int pos)
    {
        if(t == HTML.Tag.STRONG) foundName = false;
    	if(t == HTML.Tag.P)
    	{
    		foundPrepTime = false;
    		foundCookTime = false;
    	}
    	if(t == HTML.Tag.DIV)
    		searchIngredients--;

		if(t == HTML.Tag.UL)
    		foundIngredientList = false;
		
		if(t == HTML.Tag.LI)
    		foundIngredient = false;
		
    	if(t == HTML.Tag.DIV)
    		searchMethod--;

		if(t == HTML.Tag.OL)
    		foundMethod = false;
		
		if(t == HTML.Tag.P)
    		foundMethodStep = false;
    }
}
