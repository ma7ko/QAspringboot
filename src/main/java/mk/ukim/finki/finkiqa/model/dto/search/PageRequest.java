package mk.ukim.finki.finkiqa.model.dto.search;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageRequest extends AbstractPageRequest {

	private String sortBy;
	
	public PageRequest(int page, int size, String sortBy) {
		super(page, size);
		this.sortBy = sortBy;
	}

	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return Sort.by(this.sortBy);
	}

	@Override
	public Pageable next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable previous() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable first() {
		// TODO Auto-generated method stub
		return null;
	}

}
