import java.util.Comparator;

public class CakeRecipeResultByRequiredTime implements Comparator<SearchResult<CakeRecipe>>
{
	public int compare(SearchResult<CakeRecipe> o1, SearchResult<CakeRecipe> o2)
	{
		int totalTime1 = 0;
		int totalTime2 = 0;
		if (o1.item.cookTime == 0)
		{
			totalTime1 += 1000;
		} else {
			totalTime1 += o1.item.cookTime;
		}
		if (o2.item.cookTime == 0)
		{
			totalTime2 += 1000;
		} else {
			totalTime2 += o2.item.cookTime;
		}
		if (o1.item.prepTime == 0)
		{
			totalTime1 += 600;
		} else {
			totalTime1 += o1.item.prepTime;
		}
		if (o2.item.prepTime == 0)
		{
			totalTime2 += 600;
		} else {
			totalTime2 += o2.item.prepTime;
		}
		return Integer.compare(totalTime1, totalTime2);
	}
}
