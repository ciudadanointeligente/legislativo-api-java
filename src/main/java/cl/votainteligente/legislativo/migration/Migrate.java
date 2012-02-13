package cl.votainteligente.legislativo.migration;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.*;
import cl.votainteligente.legislativo.model.DO.PersonDO;
import cl.votainteligente.legislativo.service.*;

import org.springframework.stereotype.Controller;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.*;

@Controller
public class Migrate {

	private Chamber sChamber;
	private Chamber dChamber;

	private Connection connection;
	private EntityManager entityManager;
	private PartyService partyService;
	private PersonService personService;
	private CircunscriptionService circunscriptionService;
	private RegionService regionService;
	private DistrictService districtService;
	private MatterService matterService;
	private DiscussionTypeService discussionTypeService;
	private CommissionTypeService commissionTypeService;
	private TagService tagService;
	private CommissionService commissionService;
	private DebateService debateService;
	private StageDescriptionService stageDescriptionService;
	private BillService billService;
	private SessionChamberService sessionChamberService;
	private VoteService voteService;

	private Pattern pattern = Pattern.compile("\\d+");

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
	private int date2009int = 2010;
	private int date2005int = 2006;

	private LinkedList<Long[]> billIds = new LinkedList<Long[]>();
	private LinkedList<Long[]> districtIds = new LinkedList<Long[]>();
	private LinkedList<Long[]> circunscriptionIds = new LinkedList<Long[]>();
	private LinkedList<Long[]> regionIds = new LinkedList<Long[]>();
	private LinkedList<Long[]> authorsToPersonIds = new LinkedList<Long[]>();
	private LinkedList<Long[]> parliamentarianToPersonIds = new LinkedList<Long[]>();
	private LinkedList<Long[]> partyIds = new LinkedList<Long[]>();
	private LinkedList<Long[]> communeIds = new LinkedList<Long[]>();
	private LinkedList<Long[]> debateIds = new LinkedList<Long[]>();
	private LinkedList<Long[]> matterIds = new LinkedList<Long[]>();
	private LinkedList<Long[]> commissionIds = new LinkedList<Long[]>();
	private LinkedList<Long[]> sessionChamberIds = new LinkedList<Long[]>();
	private LinkedList<Long[]> voteIds = new LinkedList<Long[]>();
	// no info of session-commission, just the reference to vote-commission
	// vote and vote-commission has different ids

	private LinkedList<Long> personPartyAffiliation = new LinkedList<Long>();

	// connection data
	private String url;
	private String dbName;
	private String driver;
	private String userName;
	private String password;

	public Migrate() {
		url = "jdbc:mysql://localhost:3306/";
		dbName = "votainteligente_proyectos";
		driver = "com.mysql.jdbc.Driver";
		userName = "legislativo-api";
		password = "eop2Thah";
	}

	public Migrate(String url, String dbName, String driver, String userName, String password) {
		this.url = url;
		this.dbName = dbName;
		this.driver = driver;
		this.userName = userName;
		this.password = password;
	}

	@SuppressWarnings("unchecked")
	public boolean clean() {
		String tableNames[] = { "Agrupation", "AgrupationAffiliation", "Bill",
								"Coalition", "CoalitionAffiliation", "Commission",
								"CommissionType", "Committee", "Debate",
								"DebateInCommission", "DiscussionType", "GovernmentExecutive",
								"LegislatorRole", "Matter", "MergedBillContainer",
								"Participant", "Party", "Person", "Role",
								"Session", "Stage", "Substage", "Tag", "Chamber",
								"Circunscription", "Commune", "District", "Region",
								"StageDescription", "Session", "SingleVote", "Vote" };

		for (String tableName : tableNames) {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createQuery("Select p from " + tableName + " p");
			List<Object> list = query.getResultList();
			int deletedRows = 0;
			for (Object object : list) {
				entityManager.remove(object);
				deletedRows++;
			}
			System.out.println("Deleted " + deletedRows + " rows from " + tableName);
			entityTransaction.commit();
		}
		return true;

	}

	public String run() throws ServiceException {

		try {
			System.out.println("Migrating...");
			this.loadChamber();
			System.out.println("Loaded Chambers Successfully...");
			this.loadMatter();
			System.out.println("Loaded Matters Successfully...");

			this.loadParty();
			System.out.println("Loaded Parties Successfully...");
			this.loadGeo();
			System.out.println("Loaded Geos Successfully...");
			this.loadPerson();
			System.out.println("Loaded Persons Successfully...");
			this.loadCommissionTypes();
			System.out.println("Loaded CommissionTypes Successfully...");
			this.loadCommission();
			System.out.println("Loaded Commission Successfully...");

			this.loadDiscussionType();
			System.out.println("Loaded DiscussionType Successfully...");
			this.loadStageDescriptions();
			System.out.println("Loaded StageDescriptions Successfully...");
			this.loadBills();
			System.out.println("Loaded Bills Successfully...");

			this.loadDebateCommission();
			System.out.println("Loaded DebateCommission Successfully...");
			this.loadBillAuthors();
			System.out.println("Loaded BillAuthors Successfully...");
			this.loadCommissionAffiliation();
			System.out.println("Loaded CommissionAffiliation Successfully...");
			this.loadTags();
			System.out.println("Loaded Tags Successfully...");
			this.loadDebateTags();
			System.out.println("Loaded Debates-Tags relation Successfully...");

			this.loadSessionChamber();
			System.out.println("Loaded SessionChambers Succesfully...");
			this.loadVotes();
			System.out.println("Loaded Votes Successfully...");
			this.loadSingleVotes();
			System.out.println("Loaded SingleVotes Successfully...");

			System.out.println("Migrated Successfully");
			return "Migrated Successfully";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Migration Failed");
		return "Migration Failed";
	}

	public boolean loadEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceLegislativo");
		entityManager = entityManagerFactory.createEntityManager();
		entityManagerFactory.close();
		return entityManager != null;
	}

	private void loadParty() throws SQLException, ServiceException {
		ResultSet resultSetParty = getTableData("partido");
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		while (resultSetParty.next()) {
			Party party = new Party();
			party.setName(resultSetParty.getString("nombre"));
			party.setInitials(resultSetParty.getString("sigla"));
			party.setAddress(resultSetParty.getString("direccion"));
			party.setPhoneNumber(resultSetParty.getString("telefono"));
			party.setMailAddress(resultSetParty.getString("mail"));
			party.setHistory(resultSetParty.getString("historia"));
			party.setWebsite(resultSetParty.getString("web"));
			party.setPrinciples(resultSetParty.getString("principios"));
			party.setUpdatedAt(getSafeDate(resultSetParty, "updated_at"));
			party.setCreatedAt(getSafeDate(resultSetParty, "created_at"));
			entityManager.persist(party);
			Long newId = party.getId();
			Long oldId = resultSetParty.getLong("id_partido");

			Long[] array = new Long[2];
			array[0] = oldId;
			array[1] = newId;
			partyIds.add(array);

		}
		resultSetParty.close();
		entityTransaction.commit();
	}

	private void loadGeo() throws SQLException, ServiceException {
		ResultSet resultSetCommune = getTableData("comuna");
		ResultSet resultSetRegion = getTableData("region");
		ResultSet resultSerCircunscription = getTableData("circunscripcion");
		ResultSet resultSetDistrict = getTableData("distrito");

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		while (resultSetRegion.next()) {
			Region region = new Region();
			region.setName(resultSetRegion.getString("nombre"));
			region.setInitials(resultSetRegion.getString("sigla"));
			entityManager.persist(region);
			Long newId = region.getId();
			Long oldId = resultSetRegion.getLong("id_region");

			Long[] array = new Long[2];
			array[0] = oldId;
			array[1] = newId;
			regionIds.add(array);
		}
		resultSetRegion.close();
		while (resultSerCircunscription.next()) {
			Circunscription circunscription = new Circunscription();
			circunscription.setName(resultSerCircunscription.getString("nombre"));
			entityManager.persist(circunscription);
			Long newId = circunscription.getId();
			Long oldId = resultSerCircunscription.getLong("id_circunscripcion");

			Long[] array = new Long[2];
			array[0] = oldId;
			array[1] = newId;
			circunscriptionIds.add(array);
		}
		resultSerCircunscription.close();
		entityTransaction.commit();

		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		while (resultSetDistrict.next()) {
			District district = new District();
			Long oldDistrictId = resultSetDistrict.getLong("id_distrito");
			Long oldCircunscriptionId = resultSetDistrict.getLong("id_circunscripcion");
			Long oldRegionId = resultSetDistrict.getLong("id_region");
			Long newCircunscriptionId = findNewId(circunscriptionIds, oldCircunscriptionId);
			Long newRegionId = findNewId(regionIds, oldRegionId);

			district.setCircunscription(circunscriptionService.getCircunscription(newCircunscriptionId));
			district.setRegion(regionService.getRegion(newRegionId));
			entityManager.persist(district);

			Long[] array = new Long[2];
			array[0] = oldDistrictId;
			array[1] = district.getId();
			districtIds.add(array);

		}
		entityTransaction.commit();
		resultSetDistrict.close();

		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		while (resultSetCommune.next()) {
			Commune commune = new Commune();
			commune.setName(resultSetCommune.getString("nombre"));
			Long oldDistrictId = resultSetCommune.getLong("id_distrito");
			Long newDistrictId = findNewId(districtIds, oldDistrictId);

			commune.setDistrict(districtService.getDistrict(newDistrictId));
			entityManager.persist(commune);
			Long oldCommuneId = resultSetCommune.getLong("id_comuna");
			Long[] array = new Long[2];
			array[0] = oldCommuneId;
			array[1] = commune.getId();
			communeIds.add(array);
		}
		resultSetCommune.close();
		entityTransaction.commit();
	}

	private void loadChamber() {
		Chamber senateChamber = new Chamber();
		senateChamber.setName("Senado");
		Chamber deputyChamber = new Chamber();
		deputyChamber.setName("C.Diputados");
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(senateChamber);
		entityManager.persist(deputyChamber);
		entityTransaction.commit();

		dChamber = deputyChamber;
		sChamber = senateChamber;
	}

	private void loadMatter() throws SQLException {
		ResultSet resultSetMatter = getTableData("materia");
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		while (resultSetMatter.next()) {
			Matter matter = new Matter();
			matter.setCreatedAt(getSafeDate(resultSetMatter, "created_at"));
			matter.setUpdatedAt(getSafeDate(resultSetMatter, "updated_at"));
			matter.setName(resultSetMatter.getString("nombre"));
			matter.setSuperMatter(resultSetMatter.getString("super_materia"));
			entityManager.persist(matter);

			Long oldId = resultSetMatter.getLong("id_materia");
			Long[] array = new Long[2];
			array[0] = oldId;
			array[1] = matter.getId();
			matterIds.add(array);
		}
		resultSetMatter.close();
		entityTransaction.commit();
	}

	private void loadDiscussionType() throws SQLException {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String[] discussions = { "General", "Particular", "Ambas", "Unicas" };
		for (String discussion : discussions) {
			DiscussionType discussionType = new DiscussionType();
			discussionType.setName(discussion);
			entityManager.persist(discussionType);
		}
		entityTransaction.commit();
	}

	private void loadPerson() throws SQLException, ServiceException {
		ResultSet resultSetAuthor = getTableData("autor");
		EntityTransaction entityTransaction = entityManager.getTransaction();
		while (resultSetAuthor.next()) {
			Person person = new Person();
			person.setFirstName(resultSetAuthor.getString("nombre"));
			person.setLastName(resultSetAuthor.getString("apellidos"));
			Person exist = getPerson(person.getFirstName(), person.getLastName());
			if (exist == null) {
				person.setCreatedAt(getSafeDate(resultSetAuthor, "created_at"));
				person.setUpdatedAt(getSafeDate(resultSetAuthor, "updated_at"));
				entityTransaction.begin();
				entityManager.persist(person);
				entityTransaction.commit();
			} else
				person = exist;
			Long oldId = resultSetAuthor.getLong("id_autor");
			Long[] array = new Long[2];
			array[0] = oldId;
			array[1] = person.getId();
			authorsToPersonIds.add(array);
		}
		resultSetAuthor.close();
		entityTransaction = entityManager.getTransaction();
		ResultSet resultSetParliamentarian = getTableData("parlamentario as p");

		while (resultSetParliamentarian.next()) {
			Person person = new Person();
			person.setFirstName(resultSetParliamentarian.getString("p.nombre"));
			person.setLastName(resultSetParliamentarian.getString("p.apellidos"));
			Person exist = getPerson(person.getFirstName(), person.getLastName());
			if (exist != null)
				person = exist;
			person.setGender(resultSetParliamentarian.getString("p.sexo"));
			person.setBirthday(getSafeDate(resultSetParliamentarian, "p.fecha_nacimiento"));
			person.setProfession(resultSetParliamentarian.getString("p.profesion"));
			person.setMailAddress(resultSetParliamentarian.getString("p.mail"));
			person.setWebsite(resultSetParliamentarian.getString("p.web"));
			person.setTwitterAccount(resultSetParliamentarian.getString("p.twitter"));
			person.setFacebookAccount(resultSetParliamentarian.getString("p.facebook"));
			person.setStatementOfInterest(resultSetParliamentarian.getString("p.declaracion_interes"));
			person.setUniversityEducation(resultSetParliamentarian.getString("p.educacion_universitaria"));

			entityTransaction.begin();
			if (person.getId() == null) {
				entityManager.persist(person);
			} else
				entityManager.merge(person);

			// Parliamentarian to person
			Long oldId = resultSetParliamentarian.getLong("id_parlamentario");
			Long[] array = new Long[2];
			array[0] = oldId;
			array[1] = person.getId();
			parliamentarianToPersonIds.add(array);

			Matcher matcherDeputy = pattern.matcher(resultSetParliamentarian.getString("p.periodos_diputado_desc"));
			Matcher matcherSenator = pattern.matcher(resultSetParliamentarian.getString("p.periodos_senador_desc"));
			List<LegislatorRole> deputyRoleList = setupLegislator(matcherDeputy, dChamber, resultSetParliamentarian);
			List<LegislatorRole> senatorRoleList = setupLegislator(matcherSenator, sChamber, resultSetParliamentarian);

			LinkedList<Role> roles = new LinkedList<Role>();
			for (LegislatorRole legislatorRole : deputyRoleList) {
				legislatorRole.setPerson(person);
				roles.add(legislatorRole);
				entityManager.persist(legislatorRole);
			}

			for (LegislatorRole legislatorRole : senatorRoleList) {
				legislatorRole.setPerson(person);
				roles.add(legislatorRole);
				entityManager.persist(legislatorRole);
			}
			person.setRoles(roles);
			entityManager.merge(person);

			Long oldPartyid = resultSetParliamentarian.getLong("p.id_partido");
			Long newPartyId = findNewId(partyIds, oldPartyid);
			if (newPartyId != null) {
				boolean found = false;
				for (Long ids : personPartyAffiliation) {
					if (person.getId().longValue() == ids.longValue()) {
						found = true;
						break;
					}
				}

				if (!found) {
					Party party = partyService.getParty(newPartyId);
					AgrupationAffiliation agr = new AgrupationAffiliation();
					agr.setAgrupation(party);
					agr.setPerson(person);
					entityManager.persist(agr);
					personPartyAffiliation.add(person.getId());
				}
			}
			entityTransaction.commit();

		}
		resultSetParliamentarian.close();

	}

	private Person getPerson(String firstName, String lastname) throws ServiceException {
		Page<PersonDO> listName = personService.findPersonsByFirstName(firstName, 1, Integer.MAX_VALUE);
		Page<PersonDO> listLastName = personService.findPersonsByLastName(lastname, 1, Integer.MAX_VALUE);
		if (listName.getTotalElements().longValue() == 0L || listLastName.getTotalElements().longValue() == 0L)
			return null;
		for (PersonDO pname : listName.getElements())
			for (PersonDO plastname : listLastName.getElements())
				if (pname.getId().longValue() == plastname.getId().longValue()) {
					return personService.getPerson(pname.getId());
				}

		return null;
	}

	private List<LegislatorRole> setupLegislator(Matcher matcher, Chamber chamber, ResultSet resultSet) throws SQLException, ServiceException {
		LinkedList<LegislatorRole> list = new LinkedList<LegislatorRole>();
		while (matcher.find()) {
			LegislatorRole legislatorRole = new LegislatorRole();
			Date startDate;
			int startDateInt = -1;
			try {
				String tmp = matcher.group();
				startDate = dateFormat.parse(tmp);
				startDateInt = Integer.parseInt(tmp);
			} catch (ParseException e) {
				startDate = null;
			}
			if (matcher.find()) {
				try {
					Date endDate;
					endDate = dateFormat.parse(matcher.group());
					legislatorRole.setEndDate(endDate);
				} catch (ParseException e) {
				}
			}
			legislatorRole.setStartDate(startDate);
			if (legislatorRole.getStartDate() != null) {
				if (startDateInt == date2005int) {
					legislatorRole.setCampaignFinance(resultSet.getLong("p.financiamiento_electoral2005"));
					legislatorRole.setCampaignSpending(resultSet.getLong("p.gasto_electoral2005"));
				}
				if (startDateInt == date2009int) {
					legislatorRole.setCampaignFinance(resultSet.getLong("p.financiamiento_electoral2005"));
					legislatorRole.setCampaignSpending(resultSet.getLong("p.gasto_electoral2005"));
				}
			}
			legislatorRole.setAllowance(resultSet.getInt("p.dietas"));
			legislatorRole.setChamber(chamber);
			legislatorRole.setComiteEnvoy(resultSet.getString("p.comite_parlamentario"));
			legislatorRole.setCurrentComissions(resultSet.getString("p.comisiones_actuales"));
			legislatorRole.setPastComissions(resultSet.getString("p.comisiones_anteriores"));
			legislatorRole.setElectionCharges(resultSet.getString("p.cargos_eleccion"));
			legislatorRole.setGovernmentCharges(resultSet.getString("p.cargos_gobierno"));
			legislatorRole.setParliamentSiteId(resultSet.getInt("p.id_parlamento"));
			legislatorRole.setVoteNumber(resultSet.getLong("p.voto_nro"));
			legislatorRole.setVotePercentage(resultSet.getDouble("p.voto_porcentaje"));

			try {
				Long id_district = resultSet.getLong("p.id_distrito");
				Long newDistrictId = findNewId(districtIds, id_district);
				District d = districtService.getDistrict(newDistrictId);
				legislatorRole.setDistrict(d);
			} catch (Exception e) {
			}

			try {
				Long oldCircunscritionId = resultSet.getLong("p.id_circunscripcion");
				Long newCircunscriptionId = findNewId(circunscriptionIds, oldCircunscritionId);
				Circunscription cir = circunscriptionService.getCircunscription(newCircunscriptionId);
				legislatorRole.setCircunscription(cir);
			} catch (Exception e) {
			}
			list.add(legislatorRole);
		}
		return list;
	}

	private void loadBills() throws ServiceException, SQLException {
		ResultSet bills = getTableData("proyectoley as p");
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		while (bills.next()) {
			Bill bill = new Bill();

			bill.setDecree(bills.getLong("p.decreto"));
			bill.setCreatedAt(getSafeDate(bills, "p.created_at"));
			bill.setUpdatedAt(getSafeDate(bills, "p.updated_at"));
			bill.setBulletinNumber(bills.getString("p.nro_boletin"));
			bill.setEntryDate(getSafeDate(bills, "p.fecha_ingreso"));
			bill.setBcnLawId(bills.getLong("p.ley"));
			bill.setBcnLawURL(bills.getString("p.ley_bcn"));
			bill.setInitiative(bills.getString("p.iniciativa"));
			bill.setSummary(bills.getString("p.resumen"));
			bill.setTitle(bills.getString("p.titulo"));
			bill.setUrgency(bills.getString("p.urgencia"));
			String stageDescriptionString = bills.getString("p.etapa");

			try {
				Set<Stage> stageSet = new HashSet<Stage>();
				StageDescription stageDescription = stageDescriptionService.getByName(stageDescriptionString, 1, Integer.MAX_VALUE).getElements().get(0);
				Stage stage = new Stage();
				stage.setBill(bill);
				stage.setStageDescription(stageDescription);
				Set<Substage> substageSet = new HashSet<Substage>();
				Substage substage = new Substage();
				substage.setDescription(bills.getString("p.sub_etapa"));
				substage.setStage(stage);
				substageSet.add(substage);
				stage.setSubStages(substageSet);
				stageSet.add(stage);
				bill.setStages(stageSet);

			} catch (Exception e) {
				System.out.println("Stage not found: |" + stageDescriptionString + "|");
			}

			try {
				Long oldMatterId = bills.getLong("id_materia");
				if (oldMatterId != null) {
					Long newMatterId = findNewId(matterIds, oldMatterId);
					if (newMatterId != null) {
						Matter matter = matterService.getById(newMatterId);
						bill.setMatter(matter);
					}
				}
			} catch (Exception e) {
				System.out.println("Error while adding matter: " + e.getMessage());
			}
			bill.setPublished(false);
			Date publishDate = getSafeDate(bills, "p.fecha_publicacion");
			if (publishDate != null) {
				bill.setPublicationDate(publishDate);
				bill.setPublished(true);
			}
			bill.setOriginChamber((bills.getString("camara_origen").toLowerCase().indexOf("senado") != -1) ? sChamber : dChamber);

			entityManager.persist(bill);

			Long oldBillId = bills.getLong("p.id_proyecto_ley");
			Long newBillId = bill.getId();
			Long[] array = new Long[2];
			array[0] = oldBillId;
			array[1] = newBillId;
			billIds.add(array);

			setupDebates(oldBillId, bill);

			if (bill.getId().longValue() % 100 == 0) {
				entityTransaction.commit();
				entityTransaction.begin();
			}
		}
		entityTransaction.commit();
		bills.close();

	}

	private void setupDebates(long oldBillId, Bill bill) throws SQLException, ServiceException {
		ResultSet resultSetDebate = getTableData("debate where id_proyecto_ley =" + oldBillId);

		while (resultSetDebate.next()) {
			Debate debate;
			if (resultSetDebate.getInt("comision_sala") == 1) {
				debate = new DebateInCommission();
			} else {
				debate = new Debate();
			}
			debate.setAbstractText(resultSetDebate.getString("destacado_texto"));
			debate.setAbstractText(resultSetDebate.getString("destacado_titulo"));
			debate.setCreatedAt(getSafeDate(resultSetDebate, "created_at"));
			debate.setUpdatedAt(getSafeDate(resultSetDebate, "updated_at"));

			Date date = getSafeDate(resultSetDebate, "fecha");
			if (date != null) {
				debate.setDate(date);
			}

			debate.setDebate(resultSetDebate.getString("debate"));
			debate.setDocUrl(resultSetDebate.getString("docs"));
			debate.setTopic(resultSetDebate.getString("tema"));
			debate.setChamber((resultSetDebate.getString("camara").toLowerCase().indexOf("senado") != -1) ? sChamber : dChamber);
			debate.setDiscussionType(discussionTypeService.getDisscussionType(new Long(resultSetDebate.getInt("discusion"))));
			debate.setBill(bill);

			entityManager.persist(debate);

			Long oldDebateId = resultSetDebate.getLong("id_debate");
			Long newDebateId = debate.getId();
			Long[] array = new Long[2];
			array[0] = oldDebateId;
			array[1] = newDebateId;
			debateIds.add(array);
		}
		resultSetDebate.close();
	}

	private void loadCommissionTypes() {
		CommissionType specialCommission = new CommissionType();
		specialCommission.setName("Especial");
		CommissionType permanentCommission = new CommissionType();
		permanentCommission.setName("Permanente");

		EntityTransaction entitytransaction = entityManager.getTransaction();
		entitytransaction.begin();
		entityManager.persist(specialCommission);
		entityManager.persist(permanentCommission);
		entitytransaction.commit();
	}

	private void loadCommission() throws ServiceException, SQLException {
		ResultSet resultSetCommission = getTableData("comision");
		CommissionType specialCommission = commissionTypeService.getAll().get(0);
		CommissionType permanentCommission = commissionTypeService.getAll().get(1);

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		while (resultSetCommission.next()) {
			Commission commission = new Commission();

			commission.setName(resultSetCommission.getString("nombre"));
			commission.setChamber((resultSetCommission.getString("camara").toLowerCase().indexOf("senado") != -1) ? sChamber : dChamber);
			commission.setCommissionType((resultSetCommission.getString("tipo").toLowerCase().indexOf("especial") != -1) ? specialCommission : permanentCommission);

			commission.setChamber((resultSetCommission.getString("camara").toLowerCase().indexOf("senado") != -1) ? sChamber : dChamber);
			commission.setMailAddress(resultSetCommission.getString("contacto_mail"));
			commission.setPhoneNumber(resultSetCommission.getString("contacto_tel"));
			commission.setForm(resultSetCommission.getString("contacto_form"));
			commission.setSecretaryLawyer(resultSetCommission.getString("abogado_secretario"));
			commission.setAssistantLawyer(resultSetCommission.getString("abogado_ayudante"));
			commission.setExecutive_lawyer(resultSetCommission.getString("secretario_ejecutivo"));
			commission.setCreatedAt(getSafeDate(resultSetCommission, "created_at"));
			commission.setUpdatedAt(getSafeDate(resultSetCommission, "updated_at"));

			entityManager.persist(commission);

			Long oldCommissionId = resultSetCommission.getLong("id_comision");
			Long newCommissionId = commission.getId();
			Long[] array = new Long[2];
			array[0] = oldCommissionId;
			array[1] = newCommissionId;
			commissionIds.add(array);

		}
		entityTransaction.commit();
		resultSetCommission.close();
	}

	private void loadDebateCommission() throws SQLException, ServiceException {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		for (Long[] debate : debateIds) {
			Debate debateTemp = debateService.getDebate(debate[1]);
			if (debateTemp instanceof DebateInCommission) {
				ResultSet resultSetDebateCommission = getTableData("debatecomision where id_debate=" + debate[0]);
				DebateInCommission debateInCommission = (DebateInCommission) debateTemp;
				HashSet<Commission> participants = new HashSet<Commission>();
				while (resultSetDebateCommission.next()) {
					Long oldCommissionId = resultSetDebateCommission.getLong("id_comision");
					Long newCommissionId = findNewId(commissionIds, oldCommissionId);
					Commission commission = commissionService.getCommissionById(newCommissionId);

					participants.add(commission);
				}
				debateInCommission.setParticipantCommissions(participants);
				entityManager.merge(debateInCommission);
				resultSetDebateCommission.close();
			}
		}
		entityTransaction.commit();
	}

	private void loadDebateTags() throws SQLException, ServiceException {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		for (Long[] debate : debateIds) {
			Debate debateTemp = debateService.getDebate(debate[1]);

			ResultSet resultSetTags = completeQueryDate("Select tags from debate where id_debate=" + debate[0]);
			try {
				resultSetTags.next();
				String tags[] = resultSetTags.getString("tags").split(",");
				Set<Tag> tagSet = new HashSet<Tag>();
				for (int i = 0; i < tags.length; i++) {
					String currentTag = tags[i].trim();
					List<Tag> tagList = tagService.findByName(currentTag, 1, Integer.MAX_VALUE).getElements();
					for (Tag t : tagList)
						tagSet.add(t);
				}
				debateTemp.setTags(tagSet);
			} catch (Exception e) {
			}
			entityManager.merge(debateTemp);
			resultSetTags.close();
		}
		entityTransaction.commit();
	}

	private void loadBillAuthors() throws SQLException, ServiceException {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		for (Long[] bill : billIds) {
			Bill billTemp = billService.getBill(bill[1]);
			ResultSet resultSetAuthor = getTableData("autorproyectoley where id_proyecto_ley=" + bill[0]);
			Set<Person> authors = new HashSet<Person>();
			while (resultSetAuthor.next()) {
				Long oldPersonId = resultSetAuthor.getLong("id_autor");
				Long newPersonId = findNewId(authorsToPersonIds, oldPersonId);
				Person person = personService.getPerson(newPersonId);

				authors.add(person);
			}
			billTemp.setAuthors(authors);
			entityManager.merge(billTemp);
			resultSetAuthor.close();
			if (bill[1] % 300 == 0) {
				entityTransaction.commit();
				entityTransaction.begin();
			}
		}
		entityTransaction.commit();
	}

	private void loadCommissionAffiliation() throws SQLException, ServiceException {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ResultSet resultSetParliamentarianCommission = getTableData("parlamentarioencomision");
		while (resultSetParliamentarianCommission.next()) {
			AgrupationAffiliation agrupationAffiliation = new AgrupationAffiliation();
			Long oldParliamentarianId = resultSetParliamentarianCommission.getLong("id_parlamentario");
			Long oldCommissionId = resultSetParliamentarianCommission.getLong("id_comision");

			Long newPersonId = findNewId(parliamentarianToPersonIds, oldParliamentarianId);
			Long newCommissionId = findNewId(commissionIds, oldCommissionId);

			agrupationAffiliation.setAgrupation(commissionService.getCommissionById(newCommissionId));
			agrupationAffiliation.setPerson(personService.getPerson(newPersonId));
			entityManager.persist(agrupationAffiliation);
		}
		entityTransaction.commit();
		resultSetParliamentarianCommission.close();
	}

	private void loadSessionChamber() throws SQLException, ServiceException {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ResultSet resultSetSession = getTableData("sesion");
		while (resultSetSession.next()) {
			SessionChamber session = new SessionChamber();
			session.setCreatedAt(getSafeDate(resultSetSession, "created_at"));
			session.setUpdatedAt(getSafeDate(resultSetSession, "updated_at"));
			session.setDate(getSafeDate(resultSetSession, "fecha"));
			session.setChamber((resultSetSession.getString("camara").toLowerCase().indexOf("senado") != -1) ? sChamber : dChamber);

			entityManager.persist(session);

			Long oldSessionId = resultSetSession.getLong("id_sesion");
			Long newSessionId = session.getId();
			Long[] array = new Long[2];
			array[0] = oldSessionId;
			array[1] = newSessionId;
			sessionChamberIds.add(array);
		}
		entityTransaction.commit();
		resultSetSession.close();
	}

	private void loadVotes() throws SQLException, ServiceException {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ResultSet resultSetVote = getTableData("votacion");
		while (resultSetVote.next()) {
			Vote vote = new Vote();
			vote.setName(resultSetVote.getString("name"));
			vote.setType(resultSetVote.getString("tipo"));
			vote.setCreatedAt(getSafeDate(resultSetVote, "created_at"));
			vote.setUpdatedAt(getSafeDate(resultSetVote, "updated_at"));
			vote.setYesVotes(resultSetVote.getInt("voto_si"));
			vote.setNoVotes(resultSetVote.getInt("voto_no"));
			vote.setAbsentVotes(resultSetVote.getInt("voto_aus"));
			vote.setAbstentionVotes(resultSetVote.getInt("voto_abs"));
			vote.setMatchingVotes(resultSetVote.getInt("voto_pareos"));
			vote.setQuorum(resultSetVote.getString("quorum"));
			String resultStr = resultSetVote.getString("resultado").toLowerCase();

			Long resultL = 0L;
			if (resultStr.equals("rechazado"))
				resultL = 1L;
			else if (resultStr.equals("empate"))
				resultL = 2L;
			else if (resultStr.equals("no quorum"))
				resultL = 3L;
			vote.setResult(resultL);

			Long newBillId = findNewId(billIds, resultSetVote.getLong("id_proyecto_ley"));
			if (newBillId == null)
				continue;
			Bill bill = billService.getBill(newBillId);
			vote.setBill(bill);

			Long oldSessionId = resultSetVote.getLong("id_sesion");
			Long newSessionId = findNewId(sessionChamberIds, oldSessionId);
			SessionChamber sessionChamber = null;

			if (newSessionId == null) {
				continue;
			}

			try {
				sessionChamber = sessionChamberService.getSessionChamber(newSessionId);
			} catch (Exception e) {
				System.out.println("Sesión no encontrada para el id antiguo " + oldSessionId + " y id nuevo " + newSessionId);
			}

			if (sessionChamber == null) {
				continue;
			}

			vote.setSession(sessionChamber);
			vote.setTime(getSafeDate(resultSetVote, "hora"));

			entityManager.persist(vote);

			Long oldVoteId = resultSetVote.getLong("id_votacion");
			Long newVoteId = vote.getId();
			Long[] array = new Long[2];
			array[0] = oldVoteId;
			array[1] = newVoteId;
			voteIds.add(array);
		}
		entityTransaction.commit();
		entityTransaction.begin();
		resultSetVote.close();

		resultSetVote = getTableData("votacioncomision");
		while (resultSetVote.next()) {
			Vote vote = new Vote();
			vote.setType(resultSetVote.getString("tipo"));
			vote.setCreatedAt(getSafeDate(resultSetVote, "created_at"));
			vote.setUpdatedAt(getSafeDate(resultSetVote, "updated_at"));
			vote.setYesVotes(resultSetVote.getInt("voto_si"));
			vote.setNoVotes(resultSetVote.getInt("voto_no"));
			vote.setAbsentVotes(resultSetVote.getInt("voto_aus"));
			vote.setAbstentionVotes(resultSetVote.getInt("voto_abs"));
			vote.setMatchingVotes(resultSetVote.getInt("voto_pareos"));
			vote.setQuorum(resultSetVote.getString("quorum"));
			String resultStr = resultSetVote.getString("resultado").toLowerCase();

			Long resultL = 0L;
			if (resultStr.equals("rechazado"))
				resultL = 1L;
			else if (resultStr.equals("empate"))
				resultL = 2L;
			else if (resultStr.equals("no quorum"))
				resultL = 3L;
			vote.setResult(resultL);

			Long newBillId = findNewId(billIds, resultSetVote.getLong("id_proyecto_ley"));

			if (newBillId == null) {
				continue;
			}

			Bill bill = billService.getBill(newBillId);
			vote.setBill(bill);

			// creation of sessionComission
			SessionCommission sessionCommission = new SessionCommission();
			Long oldCommissionId = resultSetVote.getLong("id_comision");
			Long newCommissionId = findNewId(commissionIds, oldCommissionId);

			if (newCommissionId == null) {
				continue;
			}

			try {
				sessionCommission.setCommission(commissionService.getCommissionById(newCommissionId));
			} catch (Exception e) {
				System.out.println("Comisión no encontrada para el id antiguo " + oldCommissionId + " y id nuevo " + newCommissionId);
			}
			sessionCommission.addDiscussedBill(bill);
			vote.setSession(sessionCommission);

			entityManager.persist(sessionCommission);
			entityManager.persist(vote);

			Long oldVoteId = resultSetVote.getLong("id_votacion");
			Long newVoteId = vote.getId();
			Long[] array = new Long[2];
			array[0] = oldVoteId;
			array[1] = newVoteId;
			voteIds.add(array);
		}
		entityTransaction.commit();
		resultSetVote.close();
	}

	private void loadSingleVotes() throws SQLException, ServiceException {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		ResultSet resultSetParliamentarianVote = getTableData("votacionparlamentario");
		while (resultSetParliamentarianVote.next()) {
			SingleVote singleVote = new SingleVote();
			singleVote.setVoteDetail(resultSetParliamentarianVote.getString("voto"));
			Long oldLegislatorId = resultSetParliamentarianVote.getLong("id_parlamentario");
			Long newPersonId = findNewId(parliamentarianToPersonIds, oldLegislatorId);
			Long oldVoteId = resultSetParliamentarianVote.getLong("id_votacion");
			Long newVoteId = findNewId(voteIds, oldVoteId);
			singleVote.setPerson(personService.getPerson(newPersonId));

			if (newVoteId == null) {
				continue;
			}

			try {
				singleVote.setVote(voteService.getVote(newVoteId));
			} catch (Exception e) {
				System.out.println("Votación no encontrada para el id antiguo " + oldVoteId + " y id nuevo " + newVoteId);
			}
			entityManager.persist(singleVote);
		}
		resultSetParliamentarianVote.close();
		entityTransaction.commit();

		resultSetParliamentarianVote = getTableData("votacioncomisionparlamentario");
		entityTransaction.begin();
		while (resultSetParliamentarianVote.next()) {
			SingleVote singleVote = new SingleVote();
			singleVote.setVoteDetail(resultSetParliamentarianVote.getString("voto"));
			Long oldLegislatorId = resultSetParliamentarianVote.getLong("id_parlamentario");
			Long newPersonId = findNewId(parliamentarianToPersonIds, oldLegislatorId);

			if (newPersonId == null) {
				continue;
			}

			Long oldVoteId = resultSetParliamentarianVote.getLong("id_votacion");
			Long newVoteId = findNewId(voteIds, oldVoteId);

			if (newVoteId == null) {
				continue;
			}

			singleVote.setPerson(personService.getPerson(newPersonId));
			singleVote.setVote(voteService.getVote(newVoteId));
			entityManager.persist(singleVote);
		}
		entityTransaction.commit();
		resultSetParliamentarianVote.close();
	}

	private void loadTags() throws SQLException {
		ResultSet resultSetDebateTags = completeQueryDate("Select distinct(tags) from debate");
		EntityTransaction entityTransaction = entityManager.getTransaction();
		String tags = ",";
		while (resultSetDebateTags.next()) {
			try {
				String tagsArray[] = resultSetDebateTags.getString("tags").split(",");
				for (String tag : tagsArray) {
					String tagTrim = tag.trim();
					if (tags.indexOf("," + tagTrim + ",") == -1)
						tags = tags + tagTrim + ",";
				}
			} catch (Exception e) {

			}
		}
		String[] realTags = tags.split(",");
		entityTransaction.begin();
		for (String tag : realTags) {
			if (!tag.equals("")) {
				Tag t = new Tag();
				t.setName(tag);
				entityManager.persist(t);
			}
		}
		entityTransaction.commit();
		resultSetDebateTags.close();
	}

	private void loadStageDescriptions() throws SQLException {
		ResultSet resultSetBillStages = completeQueryDate("Select distinct(etapa) from proyectoley");
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		while (resultSetBillStages.next()) {
			StageDescription stageDescription = new StageDescription();
			stageDescription.setDescription(resultSetBillStages.getString("etapa"));
			entityManager.persist(stageDescription);
		}
		entityTransaction.commit();
		resultSetBillStages.close();
	}

	private ResultSet completeQueryDate(String query) {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ResultSet getTableData(String tablename) {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM " + tablename);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean connect() {
		try {
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url + dbName, userName, password);
			if (connection != null) {
				System.out.println("Connected to the database");
				return true;
			}
		} catch (Exception e) {
			System.out.println("Failed connection to database");
			e.printStackTrace();
		}
		return false;
	}

	private Long findNewId(List<Long[]> ids, Long id) {
		for (Long[] idmatch : ids)
			if (idmatch[0].longValue() == id.longValue())
				return idmatch[1];
		return null;
	}

	private Date getSafeDate(ResultSet resultSet, String fieldName) {
		try {
			return resultSet.getDate(fieldName);
		} catch (Exception e) {
			return null;
		}

	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setCircunscriptionService(CircunscriptionService circunscriptionService) {
		this.circunscriptionService = circunscriptionService;
	}

	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}

	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}

	public void setMatterService(MatterService matterService) {
		this.matterService = matterService;
	}

	public void setDiscussionTypeService(DiscussionTypeService discussionTypeService) {
		this.discussionTypeService = discussionTypeService;
	}

	public void setCommissionTypeService(CommissionTypeService commissionTypeService) {
		this.commissionTypeService = commissionTypeService;
	}

	public void setCommissionService(CommissionService commissionService) {
		this.commissionService = commissionService;
	}

	public void setBillService(BillService billService) {
		this.billService = billService;
	}

	public void setPartyService(PartyService partyService) {
		this.partyService = partyService;
	}

	public void setDebateService(DebateService debateService) {
		this.debateService = debateService;
	}

	public void setStageDescriptionService(StageDescriptionService stageDescriptionService) {
		this.stageDescriptionService = stageDescriptionService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public void setEm(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void setSessionChamberService(SessionChamberService sessionChamberService) {
		this.sessionChamberService = sessionChamberService;
	}

	public void setVoteService(VoteService voteService) {
		this.voteService = voteService;
	}

}
