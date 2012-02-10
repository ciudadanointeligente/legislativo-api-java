package cl.votainteligente.legislativo.tests.deployment;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import junit.framework.TestCase;

public abstract class TestAPI extends TestCase {
	private Gson gson;

	protected void setUp() throws Exception {
		gson = new Gson();
	}

	public Gson getGson() {
		return gson;
	}

	static public HttpQueryResult execQuery(String httpQuery) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HtmlCleaner cleaner = new HtmlCleaner();
		HttpGet get;
		HttpResponse response;
		TagNode document;
		Integer httpStatusCode = null;
		String query = null;
		String httpResponse = null;
		URL url = null;
		URI uri = null;
		HttpQueryResult httpQueryResult = null;

		url = new URL(httpQuery);
		uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
		url = uri.toURL();
		query = url.toString();
		get = new HttpGet(query);
		response = client.execute(get);
		httpStatusCode = (Integer) response.getStatusLine().getStatusCode();
		document = cleaner.clean(new InputStreamReader(response.getEntity().getContent()));
		httpResponse = new String(document.getText().toString().toCharArray());
		httpQueryResult = new HttpQueryResult();
		httpQueryResult.setHttpQuery(httpQuery);
		httpQueryResult.setHttpResponse(httpResponse);
		httpQueryResult.setHttpStatusCode(httpStatusCode);

		return httpQueryResult;
	}
}
