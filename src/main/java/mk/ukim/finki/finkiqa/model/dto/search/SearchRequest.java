package mk.ukim.finki.finkiqa.model.dto.search;

public class SearchRequest {

	private Integer size;
	private Integer page;
	
	public SearchRequest(Integer page, Integer size) {
		super();
		this.size = size;
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public Integer getPage() {
		return page;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
