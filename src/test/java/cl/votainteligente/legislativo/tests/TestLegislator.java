package cl.votainteligente.legislativo.tests;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.LegislatorRole;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.Region;

public class TestLegislator extends TestCase {

	private EntityManager em;
	private LegislatorRole legislator;
	private Circunscription circunscription;
	private District district;
	private Chamber chamber;
	private Date startDate;
	private Date endDate;
	private Region region;
	private Set<Region> regions;
	private Person person;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = LocalEntityManager.factory();
		em = emf.createEntityManager();
		emf.close();

		chamber = new Chamber();
		circunscription = new Circunscription();
		district = new District();
		region = new Region();
		regions = new HashSet<Region>();
		person = new Person();
		person.setFirstName("Carlos");
		person.setLastName("Cantero");
		regions.add(region);
		circunscription.setRegions(regions);
		district.setRegion(region);
		district.setCircunscription(circunscription);
		startDate = new Date(1234124);
		endDate = new Date(1231223);
		legislator = new LegislatorRole();
		legislator.setAllowance(15000000);
		legislator.setChamber(chamber);
		legislator.setCircunscription(circunscription);
		legislator.setComiteEnvoy("IND. Representante del comité");
		legislator
				.setCurrentComissions("De Educación, Cultura, Ciencia y Tecnología");
		legislator.setDistrict(district);
		legislator
				.setElectionCharges("1990-1998: Diputado por Distrito n°3 (Tocopilla,Ca...");
		legislator.setStartDate(startDate);
		legislator.setEndDate(endDate);
		legislator.setGovernmentCharges("No tiene");
		legislator.setParliamentSiteId(8);
		legislator
				.setPastComissions("De Educación, Cultura, Ciencia y Tecnología");
		legislator.setPeriodsDetails("No hay detalles");
		legislator.setCampaignFinance(100000000L);
		legislator.setCampaignSpending(97000000L);
		legislator.setVoteNumber(453282L);
		legislator.setVotePercentage(56.7);
		legislator.setPerson(person);
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(person);
		em.persist(chamber);
		em.persist(region);
		em.persist(circunscription);
		em.persist(district);
		em.persist(legislator);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(legislator);
		em.remove(district);
		em.remove(circunscription);
		em.remove(region);
		em.remove(chamber);
		em.remove(person);
		tr.commit();
		em.clear();
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assert (em != null);
	}

	public void testNewLegislator() {
		assert (legislator.getId() != null);
	}

	public void testGetAllowance() {
		assert (legislator.getAllowance().equals(new Integer(15000000)));
	}

	public void testGetChamber() {
		assert (legislator.getChamber().equals(chamber));
	}

	public void testGetCircunscription() {
		assert (legislator.getCircunscription().equals(circunscription));
	}

	public void testGetComiteEnvoy() {
		assert (legislator.getComiteEnvoy()
				.equals("IND. Representante del comité"));
	}

	public void testGetCurrentComissions() {
		assert (legislator.getCurrentComissions()
				.equals("De Educación, Cultura, Ciencia y Tecnología"));
	}

	public void testGetDistrict() {
		assert (legislator.getDistrict().equals(district));
	}

	public void testGetElectionCharges() {
		assert (legislator.getElectionCharges()
				.equals("1990-1998: Diputado por Distrito n°3 (Tocopilla,Ca..."));
	}

	public void testGetStartDate() {
		assert (legislator.getStartDate().equals(startDate));
	}

	public void testGetEndDate() {
		assert (legislator.getEndDate().equals(endDate));
	}

	public void testGetGovernmentCharges() {
		assert (legislator.getGovernmentCharges().equals("No tiene"));
	}

	public void testGetParliamentSiteId() {
		assert (legislator.getParliamentSiteId().equals(new Integer(8)));
	}

	public void testGetPastComissions() {
		assert (legislator.getPastComissions()
				.equals("De Educación, Cultura, Ciencia y Tecnología"));
	}

	public void testGetPeriodDetails() {
		assert (legislator.getPeriodsDetails().equals("No hay detalles"));
	}

	public void testGetCampaignFinance() {
		assert (legislator.getCampaignFinance().equals(new Long(100000000)));
	}

	public void testGetCampaignSpending() {
		assert (legislator.getCampaignSpending().equals(new Long(97000000)));
	}

	public void testGetVoteNumber() {
		assert (legislator.getVoteNumber().equals(new Long(453282)));
	}

	public void testGetVotePercentage() {
		assert (legislator.getVotePercentage().equals(new Double(56.7)));
	}
}