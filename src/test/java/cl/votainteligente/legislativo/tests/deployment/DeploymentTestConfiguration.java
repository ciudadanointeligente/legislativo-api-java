package cl.votainteligente.legislativo.tests.deployment;

public class DeploymentTestConfiguration {
	public static final String webroot = "http://localhost:8983/legislativo/api/";

	public static final String apiRoute(String api) {
		return webroot + api;
	}
}
