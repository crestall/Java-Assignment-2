import java.util.Comparator;

/**    
 * CakeRecipeResultByCookTime implements a comparator used
 * to compare the search results containing cake recipes by the amount of cooking time
 *    
 * @version 1.0, 23/10/2011, Initial version
 * @author Adrian Cowan (6515258), Chris Restall (651____)
 */
public class CakeRecipeResultByCookTime implements Comparator<SearchResult<CakeRecipe>>
{
	private int factor;
	
	/**
	 * Creates a comparator that sorts in the specified direction according to cooking time
	 * 
	 * @param ascendingFactor The direction the comparisons should take
	 */
	CakeRecipeResultByCookTime(int ascendingFactor){
		factor = ascendingFactor;
	}
	
	/**
	 * Compares two search results (containing cake recipes) based on the time required to cook them
	 * 
	 * @param o1 The first search result to be compared
	 * @param o2 The second search result to be compared
	 */
	public int compare(SearchResult<CakeRecipe> o1, SearchResult<CakeRecipe> o2)
	{
		return Integer.compare(o1.item.getCookTime(), o2.item.getCookTime()) * factor;
	}
}
