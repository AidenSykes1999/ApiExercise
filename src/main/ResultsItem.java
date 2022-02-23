public class ResultsItem{

	@JsonProperty("name")
	private String name;

	@JsonProperty("index")
	private String index;

	@JsonProperty("url")
	private String url;

	public String getName(){
		return name;
	}

	public String getIndex(){
		return index;
	}

	public String getUrl(){
		return url;
	}
}
