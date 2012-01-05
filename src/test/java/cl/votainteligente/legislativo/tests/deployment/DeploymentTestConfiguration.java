package cl.votainteligente.legislativo.tests.deployment;

public class DeploymentTestConfiguration {
	public static final String webroot = "http://demo.ciudadanointeligente.cl/Legislativo/api/";

	public static final String apiRoute(String api) {
		return webroot + api;
	}
}
