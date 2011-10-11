import java.util.Comparator;

public class CakeRecipeResultByPrepTime implements Comparator<SearchResult<CakeRecipe>>
{
	public int compare(SearchResult<CakeRecipe> o1, SearchResult<CakeRecipe> o2)
	{
		return Integer.compare(o2.item.prepTime, o2.item.prepTime);
	}
}
