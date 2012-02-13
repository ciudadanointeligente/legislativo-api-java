package cl.votainteligente.legislativo.tests.deployment;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.DO.CommissionDO;

import org.apache.http.HttpStatus;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class TestCommissionAPI extends TestAPI {

	Type pageType = new TypeToken<Page<CommissionDO>>() {}.getType();

	public void testAll() throws Exception {
		/*
		 * First, get through the "all" service a list of commissions.
		 */
		String httpQuery = DeploymentTestConfiguration.apiRoute("commission/all");
		HttpQueryResult httpQueryResult = execQuery(httpQuery);

		assertEquals(httpQueryResult.getHttpStatusCode(), (Integer) HttpStatus.SC_OK);

		/*
		 * Parse it. It should be a JSON from Page<CommissionDO>
		 */
		Page<CommissionDO> commissionDOPage = getGson().fromJson(httpQueryResult.getHttpResponse(), pageType);
		assertNotNull("Null GSON at " + httpQuery, commissionDOPage);

		/*
		 * Now, we can check other services since we have real data (ids and names)
		 */

		// TODO: Uncomment this when model and model.domainObject has been
		// updated at main server.
		for (CommissionDO commission : commissionDOPage.getElements()) {
			executeQueries(commission);
		}
	}

	public void executeQueries(CommissionDO commission) throws Exception {
		anyId(commission.getId());
		anyName(commission.getName());
	}

	public void anyId(long id) throws Exception {
		String httpQuery = DeploymentTestConfiguration.apiRoute("commission/any?id=" + id);
		HttpQueryResult httpQueryResult = execQuery(httpQuery);

		if (httpQueryResult.getHttpStatusCode().equals((Integer)HttpStatus.SC_OK)) {
			assertNotNull("Null GSON at " + httpQuery, getGson().fromJson(httpQueryResult.getHttpResponse(), pageType));
		} else {
			assertEquals(httpQueryResult.getHttpStatusCode(), (Integer) HttpStatus.SC_NOT_FOUND);
		}
	}

	public void anyName(String name) throws Exception {
		String httpQuery = DeploymentTestConfiguration.apiRoute("commission/any?name=" + name);
		HttpQueryResult httpQueryResult = execQuery(httpQuery);

		if (httpQueryResult.getHttpStatusCode().equals((Integer)HttpStatus.SC_OK)) {
			assertNotNull("Null GSON at " + httpQuery, getGson().fromJson(httpQueryResult.getHttpResponse(), pageType));
		} else {
			assertEquals(httpQueryResult.getHttpStatusCode(), (Integer) HttpStatus.SC_NOT_FOUND);
		}
	}
}
