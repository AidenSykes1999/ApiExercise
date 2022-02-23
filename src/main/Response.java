public class Response{

	@JsonProperty("count")
	private int count;

	@JsonProperty("results")
	private List<ResultsItem> results;

	public int getCount(){
		return count;
	}

	public List<ResultsItem> getResults(){
		return results;
	}
}
