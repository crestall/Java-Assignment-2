import java.util.Comparator;

/**    
 * CakeRecipeResultByRelevance implements a comparator used
 * to compare the search results containing cake recipes by their relevance
 *    
 * @version 1.0, 23/10/2011, Initial version
 * @author Adrian Cowan (6515258), Chris Restall (651____)
 */
public class CakeRecipeResultByRelevance implements Comparator<SearchResult<CakeRecipe>>
{
	private int factor;
	
	/**
	 * Creates a comparator that sorts in the specified direction according to relevance
	 * 
	 * @param ascendingFactor The direction the comparisons should take
	 */
	CakeRecipeResultByRelevance(int ascendingFactor){
		factor = ascendingFactor;
	}
	
	/**
	 * Compares two search results (containing cake recipes) based of their relevance
	 * 
	 * @param o1 The first search result to be compared
	 * @param o2 The second search result to be compared
	 */
	public int compare(SearchResult<CakeRecipe> o1, SearchResult<CakeRecipe> o2)
	{
		return Integer.compare(o1.hits, o2.hits) * factor;
	}
}
