package mk.ukim.finki.finkiqa.model.dto.search.util;

import mk.ukim.finki.finkiqa.model.dto.search.PageRequest;
import mk.ukim.finki.finkiqa.model.dto.search.SearchRequest;

public final class SearchRequestUtilClass {

	private static final int DEFAULT_PAGE_SIZE = 2;
	
	public static PageRequest toPageRequest(final SearchRequest request, String sortBy) {
		
		if (request == null) {
			return new PageRequest(0, DEFAULT_PAGE_SIZE, sortBy);
		}
		
		final Integer requestedSize = request.getSize();
		return new PageRequest(request.getPage(), (requestedSize <= 0) ? DEFAULT_PAGE_SIZE : requestedSize, sortBy);
	}
	
	
}
