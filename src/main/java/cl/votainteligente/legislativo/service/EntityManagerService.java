package cl.votainteligente.legislativo.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class EntityManagerService {

	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
