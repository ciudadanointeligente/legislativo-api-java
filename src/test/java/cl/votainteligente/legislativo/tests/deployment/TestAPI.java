package cl.votainteligente.legislativo.tests.deployment;

import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

import com.google.gson.Gson;

import junit.framework.TestCase;

public abstract class TestAPI extends TestCase {
	private Gson gson;
	private AbstractHttpClient client;

	protected void setUp() throws Exception {
		gson = new Gson();
		client = new DefaultHttpClient(new ThreadSafeClientConnManager());
	}

	public Gson getGson() {
		return gson;
	}

	public AbstractHttpClient getClient() {
		return client;
	}
}
