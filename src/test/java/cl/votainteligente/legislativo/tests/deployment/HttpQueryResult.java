package cl.votainteligente.legislativo.tests.deployment;

public class HttpQueryResult {
	private String httpQuery;
	private String httpResponse;
	private Integer httpStatusCode;

	public HttpQueryResult() {
	}

	public HttpQueryResult(String httpQuery) {
		this.httpQuery = httpQuery;
	}

	public String getHttpQuery() {
		return httpQuery;
	}

	public void setHttpQuery(String httpQuery) {
		this.httpQuery = httpQuery;
	}

	public String getHttpResponse() {
		return httpResponse;
	}

	public void setHttpResponse(String httpResponse) {
		this.httpResponse = httpResponse;
	}

	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(Integer httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
}
