package cl.votainteligente.legislativo.common;

import java.util.List;

public class Page<T> {

	private Long pageNumber;
	private Long totalPages;
	private Long totalElements;
	private List<T> elements;

	public Page() {
	};

	public Page(List<T> content, long pageNumber, long resultsPerPage,
			long totalElements) {
		this.elements = content;
		this.pageNumber = pageNumber;
		this.totalElements = totalElements;
		this.totalPages = ((long) Math.ceil(1.0 * totalElements
				/ resultsPerPage));
	}

	public Long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Long page) {
		this.pageNumber = page;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public List<T> getElements() {
		return elements;
	}

	public void setElements(List<T> elements) {
		this.elements = elements;
	}
}
