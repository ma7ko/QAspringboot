package mk.ukim.finki.finkiqa.model.dto.search;

import java.util.List;

public class PagedResponse<T> {
	
	private List<T> content;
	private Long count;
	private Long totalCount;
	
	public PagedResponse() {
		super();
	}

	public PagedResponse(List<T> content, Long count, Long totalCount) {
		super();
		this.content = content;
		this.count = count;
		this.totalCount = totalCount;
	}
	
	public List<T> getContent() {
		return content;
	}
	
	public Long getCount() {
		return count;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	
	public void setContent(List<T> content) {
		this.content = content;
	}
	
	public void setCount(Long count) {
		this.count = count;
	}
	
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

}
