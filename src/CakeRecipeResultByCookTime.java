import java.util.Comparator;


public class CakeRecipeResultByCookTime implements Comparator<SearchResult<CakeRecipe>>
{
	public int compare(SearchResult<CakeRecipe> o1, SearchResult<CakeRecipe> o2)
	{
		return Integer.compare(o1.item.cookTime, o2.item.cookTime);
	}
}
