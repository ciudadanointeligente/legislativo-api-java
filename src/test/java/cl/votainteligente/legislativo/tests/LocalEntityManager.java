package cl.votainteligente.legislativo.tests;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class LocalEntityManager {
	/*
	 * Singleton Pattern. Creation of EntityManagerFactory is costly and creates too many connections.
	 */
	private static EntityManagerFactory factory=Persistence
	.createEntityManagerFactory("PersistenceLegislativo");
	public static  EntityManagerFactory factory(){
		return factory;
	}
}
