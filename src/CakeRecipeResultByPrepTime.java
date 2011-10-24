import java.util.Comparator;

/**    
 * CakeRecipeResultByPrepTime implements a comparator used
 * to compare the search results containing cake recipes by the amount of preparation time
 *    
 * @version 1.0, 23/10/2011, Initial version
 * @author Adrian Cowan (6515258), Chris Restall (651____)
 */
public class CakeRecipeResultByPrepTime implements Comparator<SearchResult<CakeRecipe>>
{
	
	private int factor;
	
	/**
	 * Creates a comparator that sorts in the specified direction according to preparation time
	 * 
	 * @param ascendingFactor The direction the comparisons should take
	 */
	CakeRecipeResultByPrepTime(int ascendingFactor){
		factor = ascendingFactor;
	}
	
	/**
	 * Compares two search results (containing cake recipes) based on the time required to prepare them
	 * 
	 * @param o1 The first search result to be compared
	 * @param o2 The second search result to be compared
	 */
	public int compare(SearchResult<CakeRecipe> o1, SearchResult<CakeRecipe> o2)
	{
		return Integer.compare(o2.item.getPrepTime(), o2.item.getPrepTime()) * factor;
	}
}
