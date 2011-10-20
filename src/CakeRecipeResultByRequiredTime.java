import java.util.Comparator;

public class CakeRecipeResultByRequiredTime implements Comparator<SearchResult<CakeRecipe>>
{
	private static int COOK_TIME_UNKNOWN = 1000;
	private static int PREP_TIME_UNKNOWN = 600;
	
	private int factor;
	
	CakeRecipeResultByRequiredTime(int ascendingFactor){
		factor = ascendingFactor;
	}
	
	public int compare(SearchResult<CakeRecipe> o1, SearchResult<CakeRecipe> o2)
	{
		int totalTime1 = 0;
		int totalTime2 = 0;
		if (o1.item.getCookTime() == 0)
		{
			totalTime1 += COOK_TIME_UNKNOWN;
		} else {
			totalTime1 += o1.item.getCookTime();
		}
		if (o2.item.getCookTime() == 0)
		{
			totalTime2 += COOK_TIME_UNKNOWN;
		} else {
			totalTime2 += o2.item.getCookTime();
		}
		if (o1.item.getPrepTime() == 0)
		{
			totalTime1 += PREP_TIME_UNKNOWN;
		} else {
			totalTime1 += o1.item.getPrepTime();
		}
		if (o2.item.getPrepTime() == 0)
		{
			totalTime2 += PREP_TIME_UNKNOWN;
		} else {
			totalTime2 += o2.item.getPrepTime();
		}
		return Integer.compare(totalTime1, totalTime2) * factor;
	}
}
