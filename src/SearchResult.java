
public class SearchResult<T>
{
	int hits;
	T item;
	
	public SearchResult(int hits, T item) {
		this.hits = hits;
		this.item = item;
	}
}
