import java.util.Comparator;


public class CakeRecipeResultByRelevance implements Comparator<SearchResult<CakeRecipe>>
{
	private int factor;
	CakeRecipeResultByRelevance(int ascendingFactor){
		factor = ascendingFactor;
	}
	
	
	public int compare(SearchResult<CakeRecipe> o1, SearchResult<CakeRecipe> o2)
	{
		return Integer.compare(o1.hits, o2.hits) * factor;
	}
}
