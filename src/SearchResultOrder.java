
public enum SearchResultOrder
{
	RELEVANCE("Relevance"),
	PREP_TIME("Preparation Time"),
	COOK_TIME("Cooking Time"),
	REQUIRED_TIME("Required Time");
	private final String desc;
	private SearchResultOrder(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return desc;
	}
}
