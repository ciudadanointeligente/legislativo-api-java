package cl.votainteligente.legislativo.tests;

import java.net.URL;

import junit.framework.TestCase;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.CommissionDO;

import com.google.gson.Gson;

public class TestCommissionController extends TestCase {
	Gson gson;
	HttpClient client;
	protected void setUp() throws Exception {
		gson= new Gson();
		client = new DefaultHttpClient();
	}
	public void testAll() throws Exception{
		String route=ControllerTestConfiguration.apiRoute("commission/all");
		HttpResponse response=client.execute(new HttpGet(route));
		assertEquals(response.getStatusLine().getStatusCode(),200);
		assertNotNull("Null GSON at "+route, gson.fromJson(EntityUtils.toString(response.getEntity()), new Page<CommissionDO>().getClass()));
	}
	public void testAnyName() throws Exception{
		String route=ControllerTestConfiguration.apiRoute("commission/any?name=Exteriores&page=1&perPage=10");
		HttpResponse response=client.execute(new HttpGet(route));
		if(response.getStatusLine().getStatusCode()==200)
			assertNotNull("Null GSON at "+route, gson.fromJson(EntityUtils.toString(response.getEntity()), new Page<CommissionDO>().getClass()));
	}
	public void testAnyId() throws Exception{
		String route=ControllerTestConfiguration.apiRoute("commission/any?id=448");
		HttpResponse response=client.execute(new HttpGet(route));
		assertNotNull("Null GSON at "+route, gson.fromJson(EntityUtils.toString(response.getEntity()), new Page<CommissionDO>().getClass()));
	}
	public void testChamberId() throws Exception{
		String route=ControllerTestConfiguration.apiRoute("commission/chamber?id=1");
		HttpResponse response=client.execute(new HttpGet(route));
		assertNotNull("Null GSON at "+route, gson.fromJson(EntityUtils.toString(response.getEntity()), new Page<CommissionDO>().getClass()));
	}
	public void testTypeId() throws Exception{
		String route=ControllerTestConfiguration.apiRoute("commission/type?id=1");
		HttpResponse response=client.execute(new HttpGet(route));
		assertNotNull("Null GSON at "+route, gson.fromJson(EntityUtils.toString(response.getEntity()), new Page<CommissionDO>().getClass()));
	}
}
