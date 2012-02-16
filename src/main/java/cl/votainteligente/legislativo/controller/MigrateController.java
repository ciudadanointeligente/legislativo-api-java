package cl.votainteligente.legislativo.controller;

import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.migration.Migrate;
import cl.votainteligente.legislativo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
public class MigrateController {
	@Autowired
	private PartyService partyService;
	@Autowired
	private TagService tagService;
	@Autowired
	private PersonService personService;
	@Autowired
	private CircunscriptionService circunscriptionService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private StageDescriptionService stageDescriptionService;
	@Autowired
	private MatterService matterService;
	@Autowired
	private DiscussionTypeService discussionTypeService;
	@Autowired
	private CommissionTypeService commissionTypeService;
	@Autowired
	private CommissionService commissionService;
	@Autowired
	private DebateService debateService;
	@Autowired
	private BillService billService;
	@Autowired
	private SessionChamberService sessionChamberService;
	@Autowired
	private VoteService voteService;

	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@RequestMapping(value = "migrate", method = RequestMethod.GET)
	@ResponseBody
	public final String migrate(
			@RequestParam(value = "dbname", required = true) final String dbname,
			@RequestParam(value = "dbpass", required = true) final String dbpass,
			@RequestParam(value = "dbuser", required = true) final String dbuser) {
		try {
			System.out.println("Trying to migrate from: {Database: " + dbname
					+ ", User: " + dbuser + ", passlength: " + dbpass.length());
			Migrate m = new Migrate("jdbc:mysql://localhost:3306/", dbname,
					"com.mysql.jdbc.Driver", dbuser, dbpass);

			if (!m.connect())
				throw new ResourceNotFoundException();

			m.setBillService(billService);
			m.setCircunscriptionService(circunscriptionService);
			m.setCommissionService(commissionService);
			m.setCommissionTypeService(commissionTypeService);
			m.setDiscussionTypeService(discussionTypeService);
			m.setDistrictService(districtService);
			m.setMatterService(matterService);
			m.setPersonService(personService);
			m.setRegionService(regionService);
			m.setPartyService(partyService);
			m.setDebateService(debateService);
			m.setStageDescriptionService(stageDescriptionService);
			m.setTagService(tagService);
			m.setSessionChamberService(sessionChamberService);
			m.setVoteService(voteService);
			m.loadEntityManager();
			System.out.println("Trying to Clean database");
			m.clean();
			System.out.println("\nDatabase Cleaned Successfully");
			return m.run();

		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ResourceNotFoundException();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to Clean database");
			throw new ResourceNotFoundException();
		}
	}
}
