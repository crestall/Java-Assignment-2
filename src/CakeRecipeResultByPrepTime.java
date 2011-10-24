import java.util.Comparator;

public class CakeRecipeResultByPrepTime implements Comparator<SearchResult<CakeRecipe>>
{
	
	private int factor;
	CakeRecipeResultByPrepTime(int ascendingFactor){
		factor = ascendingFactor;
	}
	
	public int compare(SearchResult<CakeRecipe> o1, SearchResult<CakeRecipe> o2)
	{
		return Integer.compare(o2.item.getPrepTime(), o2.item.getPrepTime()) * factor;
	}
}