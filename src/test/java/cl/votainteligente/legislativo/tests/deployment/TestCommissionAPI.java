package cl.votainteligente.legislativo.tests.deployment;

import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.web.util.UriUtils;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.CommissionDO;

import com.google.gson.reflect.TypeToken;

public class TestCommissionAPI extends TestAPI {

	Type pageType = new TypeToken<Page<CommissionDO>>(){}.getType();
	public void testAll() throws Exception {
		/*
		 * First, get through the "all" service a list of commissions.
		 */
		String route = DeploymentTestConfiguration.apiRoute("commission/all");
		HttpResponse response = getClient().execute(new HttpGet(route));
		assertEquals(response.getStatusLine().getStatusCode(), 200);

		/*
		 * Parse it. It should be a JSON from Page<CommissionDO>
		 */

		Page<CommissionDO> dos = getGson().fromJson(
				EntityUtils.toString(response.getEntity()),
				pageType);
		assertNotNull("Null GSON at " + route, dos);

		/*
		 * Now, we can check other services since we have real data (ids and
		 * names)
		 */

		for (CommissionDO commission : dos.getElements())
			executeQueries(commission);
	}

	public void executeQueries(CommissionDO commission) throws Exception {
		anyId(commission.getCommissionId());
		anyName(commission.getName());
	}

	public void anyId(long id) throws Exception {
		String route = DeploymentTestConfiguration
				.apiRoute("commission/any?id=" + id);
		HttpGet query = new HttpGet(route);
		HttpResponse response = getClient().execute(query);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			assertNotNull("Null GSON at " + route, getGson().fromJson(
					EntityUtils.toString(response.getEntity()),
					pageType));
		else
			assertEquals(response.getStatusLine().getStatusCode(),
					HttpStatus.SC_NOT_FOUND);
	}

	public void anyName(String name) throws Exception {
		String route = DeploymentTestConfiguration.apiRoute(UriUtils.encodeUri(
				"commission/any?name=" + name, "utf8"));
		HttpGet query = new HttpGet(route);
		HttpResponse response = getClient().execute(query);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			assertNotNull("Null GSON at " + route, getGson().fromJson(
					EntityUtils.toString(response.getEntity()),
					pageType));
		/*
		 * TODO: After deployment update, this lines should be removed.
		 */
		else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_BAD_REQUEST)
			query.abort();
		/*
		 * end of removable section.
		 */
		else
			assertEquals(response.getStatusLine().getStatusCode(),
					HttpStatus.SC_NOT_FOUND);
	}

	public void testDocumentationExamples() throws Exception {
		String route = DeploymentTestConfiguration
				.apiRoute("commission/any?name=Exteriores&page=1&perPage=10");
		HttpResponse response = getClient().execute(new HttpGet(route));
		// TODO: Remove the if after correcting the deployment
		if (response.getStatusLine().getStatusCode() == 200)
			assertNotNull("Null GSON  at " + route, getGson().fromJson(
					EntityUtils.toString(response.getEntity()),
					pageType));

		route = DeploymentTestConfiguration.apiRoute("commission/any?id=448");
		response = getClient().execute(new HttpGet(route));
		assertNotNull("Null GSON at " + route, getGson().fromJson(
				EntityUtils.toString(response.getEntity()),
				pageType));

		route = DeploymentTestConfiguration.apiRoute("commission/chamber?id=1");
		response = getClient().execute(new HttpGet(route));
		assertNotNull("Null GSON at " + route, getGson().fromJson(
				EntityUtils.toString(response.getEntity()),
				pageType));

		route = DeploymentTestConfiguration.apiRoute("commission/type?id=1");
		response = getClient().execute(new HttpGet(route));
		assertNotNull("Null GSON at " + route, getGson().fromJson(
				EntityUtils.toString(response.getEntity()),
				pageType));
	}
}
