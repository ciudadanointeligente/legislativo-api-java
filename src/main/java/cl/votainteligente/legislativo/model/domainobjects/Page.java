package cl.votainteligente.legislativo.model.domainobjects;

import java.util.List;

public class Page<T> {

	private int pageNumber;
	private int totalPages;
	private int totalElements;
	private List<T> content;

	public int getPageNumber() {
		return pageNumber;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public List<T> getContent() {
		return content;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public static <T> Page<T> listToPage(List<T> list, int pageNumber, int resultsPerPage) {
		Page<T> resultPage = new Page<T>();
		int totalResults = list.size();
		int totalPages = (totalResults >= resultsPerPage) ? (totalResults / resultsPerPage) : 1;
		resultPage.setPageNumber(pageNumber);
		resultPage.setTotalElements(totalResults);
		resultPage.setTotalPages(totalPages);
		int start = (pageNumber - 1) * resultsPerPage;
		int end = (start + resultsPerPage) > totalResults ? totalResults : start + resultsPerPage;
		if(pageNumber > totalPages){
			resultPage.setContent(null);
		}
		else {
			resultPage.setContent(list.subList(start, end));
		}
		return resultPage;
	}
}