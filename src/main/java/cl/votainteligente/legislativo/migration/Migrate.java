package cl.votainteligente.legislativo.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.AgrupationAffiliation;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.Commission;
import cl.votainteligente.legislativo.model.CommissionType;
import cl.votainteligente.legislativo.model.Commune;
import cl.votainteligente.legislativo.model.Debate;
import cl.votainteligente.legislativo.model.DebateInCommission;
import cl.votainteligente.legislativo.model.DiscussionType;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.LegislatorRole;
import cl.votainteligente.legislativo.model.Matter;
import cl.votainteligente.legislativo.model.Party;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.Region;
import cl.votainteligente.legislativo.model.Role;
import cl.votainteligente.legislativo.model.Stage;
import cl.votainteligente.legislativo.model.StageDescription;
import cl.votainteligente.legislativo.model.Substage;
import cl.votainteligente.legislativo.model.Tag;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;
import cl.votainteligente.legislativo.service.agrupation.CommissionService;
import cl.votainteligente.legislativo.service.agrupation.CommissionTypeService;
import cl.votainteligente.legislativo.service.agrupation.PartyService;
import cl.votainteligente.legislativo.service.bill.BillService;
import cl.votainteligente.legislativo.service.bill.StageDescriptionService;
import cl.votainteligente.legislativo.service.debate.DebateService;
import cl.votainteligente.legislativo.service.debate.DiscussionTypeService;
import cl.votainteligente.legislativo.service.debate.TagService;
import cl.votainteligente.legislativo.service.geo.CircunscriptionService;
import cl.votainteligente.legislativo.service.geo.DistrictService;
import cl.votainteligente.legislativo.service.geo.RegionService;
import cl.votainteligente.legislativo.service.matter.MatterService;
import cl.votainteligente.legislativo.service.person.PersonService;

@Controller
public class Migrate {

	private Chamber sChamber;
	private Chamber dChamber;

	private Connection c;
	private EntityManager em;
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
		password = "Puh7kaiF";
	}

	public Migrate(String url, String dbName, String driver, String userName,
			String password) {
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
				"CommissionType", "Committee", "Debate", "DebateInCommission",
				"DiscussionType", "GovernmentExecutive", "LegislatorRole",
				"Matter", "MergedBill", "Participant", "Party", "Person",
				"Role", "Session", "Stage", "Substage", "Tag", "Chamber",
				"Circunscription", "Commune", "District", "Region",
				"StageDescription" };

		for (String tableName : tableNames) {
			EntityTransaction tr = em.getTransaction();
			tr.begin();
			Query q = em.createQuery("Select p from " + tableName + " p");
			List<Object> list = q.getResultList();
			int n = 0;
			for (Object o : list) {
				em.remove(o);
				n++;
			}
			System.out.println("Deleted " + n + " rows from " + tableName);
			tr.commit();
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
			System.out.println("Migrated Successfully");
			return "Migrated Successfully";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Migration Failed");
		return "Migration Failed";
	}

	public boolean loadEntityManager() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceLegislativo");
		em = emf.createEntityManager();
		emf.close();
		return em != null;
	}

	private void loadParty() throws SQLException, ServiceException {
		ResultSet rs = getTableData("partido");
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		while (rs.next()) {
			Party p = new Party();
			p.setName(rs.getString("nombre"));
			p.setInitials(rs.getString("sigla"));
			p.setAddress(rs.getString("direccion"));
			p.setPhoneNumber(rs.getString("telefono"));
			p.setMailAddress(rs.getString("mail"));
			p.setHistory(rs.getString("historia"));
			p.setWebsite(rs.getString("web"));
			p.setPrinciples(rs.getString("principios"));
			p.setUpdatedAt(getSafeDate(rs, "updated_at"));
			p.setCreatedAt(getSafeDate(rs, "created_at"));
			em.persist(p);
			Long newId = p.getId();
			Long oldId = rs.getLong("id_partido");

			Long[] array = new Long[2];
			array[0] = oldId;
			array[1] = newId;
			partyIds.add(array);

		}
		rs.close();
		tr.commit();
	}

	private void loadGeo() throws SQLException, ServiceException {
		ResultSet rsC = getTableData("comuna");
		ResultSet rsR = getTableData("region");
		ResultSet rsCi = getTableData("circunscripcion");
		ResultSet rsDi = getTableData("distrito");

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		while (rsR.next()) {
			Region p = new Region();
			p.setName(rsR.getString("nombre"));
			p.setInitials(rsR.getString("sigla"));
			em.persist(p);
			Long newId = p.getId();
			Long oldId = rsR.getLong("id_region");

			Long[] array = new Long[2];
			array[0] = oldId;
			array[1] = newId;
			regionIds.add(array);
		}
		rsR.close();
		while (rsCi.next()) {
			Circunscription p = new Circunscription();
			p.setName(rsCi.getString("nombre"));
			em.persist(p);
			Long newId = p.getId();
			Long oldId = rsCi.getLong("id_circunscripcion");

			Long[] array = new Long[2];
			array[0] = oldId;
			array[1] = newId;
			circunscriptionIds.add(array);
		}
		rsCi.close();
		tr.commit();

		tr = em.getTransaction();
		tr.begin();

		while (rsDi.next()) {
			District p = new District();
			Long oldDistrictId = rsDi.getLong("id_distrito");
			Long oldCircunscriptionId = rsDi.getLong("id_circunscripcion");
			Long oldRegionId = rsDi.getLong("id_region");
			Long newCircunscriptionId = findNewId(circunscriptionIds,
					oldCircunscriptionId);
			Long newRegionId = findNewId(regionIds, oldRegionId);

			p.setCircunscription(circunscriptionService
					.getCircunscription(newCircunscriptionId));
			p.setRegion(regionService.getRegion(newRegionId));
			em.persist(p);

			Long[] array = new Long[2];
			array[0] = oldDistrictId;
			array[1] = p.getId();
			districtIds.add(array);

		}
		tr.commit();
		rsDi.close();

		tr = em.getTransaction();
		tr.begin();
		while (rsC.next()) {
			Commune p = new Commune();
			p.setName(rsC.getString("nombre"));
			Long oldDistrictId = rsC.getLong("id_distrito");
			Long newDistrictId = findNewId(districtIds, oldDistrictId);

			p.setDistrict(districtService.getDistrict(newDistrictId));
			em.persist(p);
			Long oldCommuneId = rsC.getLong("id_comuna");
			Long[] array = new Long[2];
			array[0] = oldCommuneId;
			array[1] = p.getId();
			communeIds.add(array);
		}
		rsC.close();
		tr.commit();
	}

	private void loadChamber() {
		Chamber s = new Chamber();
		s.setName("Senado");
		Chamber d = new Chamber();
		d.setName("C.Diputados");
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(s);
		em.persist(d);
		tr.commit();

		dChamber = d;
		sChamber = s;
	}

	private void loadMatter() throws SQLException {
		ResultSet rs = getTableData("materia");
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		while (rs.next()) {
			Matter m = new Matter();
			m.setCreatedAt(getSafeDate(rs, "created_at"));
			m.setUpdatedAt(getSafeDate(rs, "updated_at"));
			m.setName(rs.getString("nombre"));
			m.setSuperMatter(rs.getString("super_materia"));
			em.persist(m);

			Long oldId = rs.getLong("id_materia");
			Long[] array = new Long[2];
			array[0] = oldId;
			array[1] = m.getId();
			matterIds.add(array);
		}
		rs.close();
		tr.commit();
	}

	private void loadDiscussionType() throws SQLException {
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		String[] discussions = { "General", "Particular", "Ambas", "Unicas" };
		for (String s : discussions) {
			DiscussionType dt = new DiscussionType();
			dt.setName(s);
			em.persist(dt);
		}
		tr.commit();
	}

	private void loadPerson() throws SQLException, ServiceException {
		ResultSet rsau = getTableData("autor");
		EntityTransaction tr = em.getTransaction();
		while (rsau.next()) {
			Person p = new Person();
			p.setFirstName(rsau.getString("nombre"));
			p.setLastName(rsau.getString("apellidos"));
			Person exist = getPerson(p.getFirstName(), p.getLastName());
			if (exist == null) {
				p.setCreatedAt(getSafeDate(rsau, "created_at"));
				p.setUpdatedAt(getSafeDate(rsau, "updated_at"));
				tr.begin();
				em.persist(p);
				tr.commit();
			} else
				p = exist;
			Long oldId = rsau.getLong("id_autor");
			Long[] array = new Long[2];
			array[0] = oldId;
			array[1] = p.getId();
			authorsToPersonIds.add(array);
		}
		rsau.close();
		tr = em.getTransaction();
		ResultSet rspar = getTableData("parlamentario as p");

		while (rspar.next()) {
			Person p = new Person();
			p.setFirstName(rspar.getString("p.nombre"));
			p.setLastName(rspar.getString("p.apellidos"));
			Person exist = getPerson(p.getFirstName(), p.getLastName());
			if (exist != null)
				p = exist;
			p.setGender(rspar.getString("p.sexo"));
			p.setBirthday(getSafeDate(rspar, "p.fecha_nacimiento"));
			p.setProfession(rspar.getString("p.profesion"));
			p.setMailAddress(rspar.getString("p.mail"));
			p.setWebsite(rspar.getString("p.web"));
			p.setTwitterAccount(rspar.getString("p.twitter"));
			p.setFacebookAccount(rspar.getString("p.facebook"));
			p.setStatementOfInterest(rspar.getString("p.declaracion_interes"));
			p.setUniversityEducation(rspar
					.getString("p.educacion_universitaria"));

			tr.begin();
			if (p.getId() == null) {
				em.persist(p);
			} else
				em.merge(p);

			// Parliamentarian to person
			Long oldId = rspar.getLong("id_parlamentario");
			Long[] array = new Long[2];
			array[0] = oldId;
			array[1] = p.getId();
			parliamentarianToPersonIds.add(array);

			Matcher md = pattern.matcher(rspar
					.getString("p.periodos_diputado_desc"));
			Matcher ms = pattern.matcher(rspar
					.getString("p.periodos_senador_desc"));
			List<LegislatorRole> dip = setupLegislator(md, dChamber, rspar);
			List<LegislatorRole> sen = setupLegislator(ms, sChamber, rspar);

			LinkedList<Role> roles = new LinkedList<Role>();
			for (LegislatorRole d : dip) {
				d.setPerson(p);
				roles.add(d);
				em.persist(d);
			}
			for (LegislatorRole d : sen) {
				d.setPerson(p);
				roles.add(d);
				em.persist(d);
			}
			p.setRoles(roles);
			em.merge(p);

			Long oldPartyid = rspar.getLong("p.id_partido");
			Long newPartyId = findNewId(partyIds, oldPartyid);
			if (newPartyId != null) {
				boolean found = false;
				for (Long ids : personPartyAffiliation)
					if (p.getId().longValue() == ids.longValue()) {
						found = true;
						break;
					}
				if (!found) {
					Party party = partyService.getParty(newPartyId);
					AgrupationAffiliation agr = new AgrupationAffiliation();
					agr.setAgrupation(party);
					agr.setPerson(p);
					em.persist(agr);
					personPartyAffiliation.add(p.getId());
				}
			}
			tr.commit();

		}
		rspar.close();

	}

	private Person getPerson(String firstName, String lastname)
			throws ServiceException {
		Page<PersonDO> listName = personService.findPersonsByFirstName(
				firstName, 1, Integer.MAX_VALUE);
		Page<PersonDO> listLastName = personService.findPersonsByLastName(
				lastname, 1, Integer.MAX_VALUE);
		if (listName.getTotalElements().longValue() == 0L
				|| listLastName.getTotalElements().longValue() == 0L)
			return null;
		for (PersonDO pname : listName.getElements())
			for (PersonDO plastname : listLastName.getElements())
				if (pname.getId().longValue() == plastname.getId().longValue()) {
					System.out.println("Persona Encontrada,  id: "
							+ pname.getId() + ", name: " + pname.getFirstName()
							+ ", lastname: " + plastname.getLastName());
					return personService.getPerson(pname.getId());
				}

		return null;
	}

	private List<LegislatorRole> setupLegislator(Matcher m, Chamber c,
			ResultSet rspar) throws SQLException, ServiceException {
		LinkedList<LegislatorRole> list = new LinkedList<LegislatorRole>();
		while (m.find()) {
			LegislatorRole leg = new LegislatorRole();
			Date startDate;
			int startDateInt = -1;
			try {
				String tmp = m.group();
				startDate = dateFormat.parse(tmp);
				startDateInt = Integer.parseInt(tmp);
			} catch (ParseException e) {
				// e.printStackTrace();
				startDate = null;
			}
			if (m.find()) {
				try {
					Date endDate;
					endDate = dateFormat.parse(m.group());
					leg.setEndDate(endDate);
				} catch (ParseException e) {
					// e.printStackTrace();
				}
			}
			leg.setStartDate(startDate);
			if (leg.getStartDate() != null) {
				if (startDateInt == date2005int) {
					leg.setCampaignFinance(rspar
							.getLong("p.financiamiento_electoral2005"));
					leg.setCampaignSpending(rspar
							.getLong("p.gasto_electoral2005"));
				}
				if (startDateInt == date2009int) {
					leg.setCampaignFinance(rspar
							.getLong("p.financiamiento_electoral2005"));
					leg.setCampaignSpending(rspar
							.getLong("p.gasto_electoral2005"));
				}
			}
			leg.setAllowance(rspar.getInt("p.dietas"));
			leg.setChamber(c);
			leg.setComiteEnvoy(rspar.getString("p.comite_parlamentario"));
			leg.setCurrentComissions(rspar.getString("p.comisiones_actuales"));
			leg.setPastComissions(rspar.getString("p.comisiones_anteriores"));
			leg.setElectionCharges(rspar.getString("p.cargos_eleccion"));
			leg.setGovernmentCharges(rspar.getString("p.cargos_gobierno"));
			leg.setParliamentSiteId(rspar.getInt("p.id_parlamento"));
			leg.setVoteNumber(rspar.getLong("p.voto_nro"));
			leg.setVotePercentage(rspar.getDouble("p.voto_porcentaje"));

			try {
				Long id_district = rspar.getLong("p.id_distrito");
				Long newDistrictId = findNewId(districtIds, id_district);
				District d = districtService.getDistrict(newDistrictId);
				leg.setDistrict(d);
			} catch (Exception e) {
				// e.printStackTrace();
			}

			try {
				Long oldCircunscritionId = rspar
						.getLong("p.id_circunscripcion");
				Long newCircunscriptionId = findNewId(circunscriptionIds,
						oldCircunscritionId);
				Circunscription cir = circunscriptionService
						.getCircunscription(newCircunscriptionId);
				leg.setCircunscription(cir);
			} catch (Exception e) {
				// System.out.println("Falle en agregar Circunscripcion.");
				// e.printStackTrace();
			}
			list.add(leg);
		}
		return list;
	}

	private void loadBills() throws ServiceException, SQLException {
		ResultSet bills = getTableData("proyectoley as p");
		EntityTransaction tr = em.getTransaction();

		tr.begin();
		while (bills.next()) {
			Bill b = new Bill();

			b.setDecree(bills.getLong("p.decreto"));
			b.setCreatedAt(getSafeDate(bills, "p.created_at"));
			b.setUpdatedAt(getSafeDate(bills, "p.updated_at"));
			b.setBulletinNumber(bills.getString("p.nro_boletin"));
			b.setEntryDate(getSafeDate(bills, "p.fecha_ingreso"));
			b.setBcnLawId(bills.getLong("p.ley"));
			b.setBcnLawURL(bills.getString("p.ley_bcn"));
			b.setInitiative(bills.getString("p.iniciativa"));
			b.setSummary(bills.getString("p.resumen"));
			b.setTitle(bills.getString("p.titulo"));
			b.setUrgency(bills.getString("p.urgencia"));
			String stageDescriptionString = bills.getString("p.etapa");

			try {
				HashSet<Stage> hs = new HashSet<Stage>();
				StageDescription sd = stageDescriptionService
						.getByName(stageDescriptionString, 1, Integer.MAX_VALUE)
						.getElements().get(0);
				Stage s = new Stage();
				s.setBill(b);
				s.setStageDescription(sd);
				HashSet<Substage> sub = new HashSet<Substage>();
				Substage subs = new Substage();
				subs.setDescription(bills.getString("p.sub_etapa"));
				subs.setStage(s);
				sub.add(subs);
				s.setSubStages(sub);
				hs.add(s);
				b.setStages(hs);

			} catch (Exception e) {
				System.out.println("No Encontre etapa: |"
						+ stageDescriptionString + "|");
			}

			try {
				Long oldMatterId = bills.getLong("id_materia");
				if (oldMatterId != null) {
					Long newMatterId = findNewId(matterIds, oldMatterId);
					if (newMatterId != null) {
						Matter m = matterService.getById(newMatterId);
						b.setMatter(m);
					}
				}
			} catch (Exception e) {
				System.out
						.println("Error agregando materia: " + e.getMessage());
				// e.printStackTrace();
			}
			b.setPublished(false);
			Date m = getSafeDate(bills, "p.fecha_publicacion");
			if (m != null) {
				b.setPublicationDate(m);
				b.setPublished(true);
			}
			b.setOriginChamber((bills.getString("camara_origen").toLowerCase()
					.indexOf("senado") != -1) ? sChamber : dChamber);

			em.persist(b);

			Long oldBillId = bills.getLong("p.id_proyecto_ley");
			Long newBillId = b.getId();
			Long[] array = new Long[2];
			array[0] = oldBillId;
			array[1] = newBillId;
			billIds.add(array);

			setupDebates(oldBillId, b);

			if (b.getId().longValue() % 100 == 0) {
				System.out.println("Added bill, Old id: " + oldBillId
						+ ", new id: " + b.getId());

				tr.commit();
				tr.begin();
			}
		}
		tr.commit();
		bills.close();

	}

	private void setupDebates(long old_bill_id, Bill b) throws SQLException,
			ServiceException {
		ResultSet debates = getTableData("debate where id_proyecto_ley ="
				+ old_bill_id);

		while (debates.next()) {
			Debate d;
			if (debates.getInt("comision_sala") == 1)
				d = new DebateInCommission();
			else
				d = new Debate();
			d.setAbstractText(debates.getString("destacado_texto"));
			d.setAbstractText(debates.getString("destacado_titulo"));
			d.setCreatedAt(getSafeDate(debates, "created_at"));
			d.setUpdatedAt(getSafeDate(debates, "updated_at"));

			Date m = getSafeDate(debates, "fecha");
			if (m != null) {
				d.setDate(m);
			}

			d.setDebate(debates.getString("debate"));
			d.setDocUrl(debates.getString("docs"));
			d.setTopic(debates.getString("tema"));
			d.setChamber((debates.getString("camara").toLowerCase()
					.indexOf("senado") != -1) ? sChamber : dChamber);
			d.setDiscussionType(discussionTypeService
					.getDisscussionType(new Long(debates.getInt("discusion"))));
			d.setBill(b);

			em.persist(d);

			Long oldDebateId = debates.getLong("id_debate");
			Long newDebateId = d.getId();
			Long[] array = new Long[2];
			array[0] = oldDebateId;
			array[1] = newDebateId;
			debateIds.add(array);
		}
		debates.close();
	}

	private void loadCommissionTypes() {
		CommissionType special = new CommissionType();
		special.setName("Especial");
		CommissionType permanent = new CommissionType();
		permanent.setName("Permanente");

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(special);
		em.persist(permanent);
		tr.commit();

	}

	private void loadCommission() throws ServiceException, SQLException {
		ResultSet rs = getTableData("comision");
		CommissionType special = commissionTypeService.getAll().get(0);
		CommissionType permanent = commissionTypeService.getAll().get(1);

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		while (rs.next()) {
			Commission c = new Commission();

			c.setName(rs.getString("nombre"));
			c.setChamber((rs.getString("camara").toLowerCase()
					.indexOf("senado") != -1) ? sChamber : dChamber);
			c.setCommissionType((rs.getString("tipo").toLowerCase()
					.indexOf("especial") != -1) ? special : permanent);

			c.setChamber((rs.getString("camara").toLowerCase()
					.indexOf("senado") != -1) ? sChamber : dChamber);
			c.setMailAddress(rs.getString("contacto_mail"));
			c.setPhoneNumber(rs.getString("contacto_tel"));
			c.setForm(rs.getString("contacto_form"));
			c.setSecretaryLawyer(rs.getString("abogado_secretario"));
			c.setAssistantLawyer(rs.getString("abogado_ayudante"));
			c.setExecutive_lawyer(rs.getString("secretario_ejecutivo"));
			c.setCreatedAt(getSafeDate(rs, "created_at"));
			c.setUpdatedAt(getSafeDate(rs, "updated_at"));

			em.persist(c);

			Long oldCommissionId = rs.getLong("id_comision");
			Long newCommissionId = c.getId();
			Long[] array = new Long[2];
			array[0] = oldCommissionId;
			array[1] = newCommissionId;
			commissionIds.add(array);

		}
		tr.commit();
		rs.close();
	}

	private void loadDebateCommission() throws SQLException, ServiceException {
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		for (Long[] debate : debateIds) {
			Debate d = debateService.getDebate(debate[1]);
			if (d instanceof DebateInCommission) {
				ResultSet rs = getTableData("debatecomision where id_debate="
						+ debate[0]);
				DebateInCommission dic = (DebateInCommission) d;
				HashSet<Commission> participants = new HashSet<Commission>();
				while (rs.next()) {
					Long oldCommissionId = rs.getLong("id_comision");
					Long newCommissionId = findNewId(commissionIds,
							oldCommissionId);
					Commission c = commissionService
							.getCommissionById(newCommissionId);

					participants.add(c);
				}
				dic.setParticipantCommissions(participants);
				em.merge(dic);
				rs.close();
			}
		}
		tr.commit();
	}

	private void loadDebateTags() throws SQLException, ServiceException {
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		for (Long[] debate : debateIds) {
			Debate d = debateService.getDebate(debate[1]);

			ResultSet rs = completeQueryDate("Select tags from debate where id_debate="
					+ debate[0]);
			try {
				rs.next();
				String tags[] = rs.getString("tags").split(",");
				HashSet<Tag> hstags = new HashSet<Tag>();
				for (int i = 0; i < tags.length; i++) {
					String currentTag = tags[i].trim();
					List<Tag> tagList = tagService.findByName(currentTag, 1,
							Integer.MAX_VALUE).getElements();
					for (Tag t : tagList)
						hstags.add(t);
				}
				d.setTags(hstags);
			} catch (Exception e) {
				e.printStackTrace();
			}
			em.merge(d);
			rs.close();
		}
		tr.commit();
	}

	private void loadBillAuthors() throws SQLException, ServiceException {
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		for (Long[] bill : billIds) {
			Bill d = billService.getBill(bill[1]);
			ResultSet rs = getTableData("autorproyectoley where id_proyecto_ley="
					+ bill[0]);
			HashSet<Person> authors = new HashSet<Person>();
			while (rs.next()) {
				Long oldPersonId = rs.getLong("id_autor");
				Long newPersonId = findNewId(authorsToPersonIds, oldPersonId);
				Person c = personService.getPerson(newPersonId);

				authors.add(c);
			}
			d.setAuthors(authors);
			em.merge(d);
			rs.close();
			if (bill[1] % 300 == 0) {
				System.out.println("Authors set for bill: " + bill[1]);
				tr.commit();
				tr.begin();
			}
		}
		tr.commit();
	}

	private void loadCommissionAffiliation() throws SQLException,
			ServiceException {
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		ResultSet rs = getTableData("parlamentarioencomision");
		while (rs.next()) {
			AgrupationAffiliation agr = new AgrupationAffiliation();
			Long oldParliamentarianId = rs.getLong("id_parlamentario");
			Long oldCommissionId = rs.getLong("id_comision");

			Long newPersonId = findNewId(parliamentarianToPersonIds,
					oldParliamentarianId);
			Long newCommissionId = findNewId(commissionIds, oldCommissionId);

			agr.setAgrupation(commissionService
					.getCommissionById(newCommissionId));
			agr.setPerson(personService.getPerson(newPersonId));
			em.persist(agr);
		}
		tr.commit();
	}

	private void loadTags() throws SQLException {
		ResultSet stages = completeQueryDate("Select distinct(tags) from debate");
		EntityTransaction tr = em.getTransaction();
		String tags = ",";
		while (stages.next()) {
			try {
				String tagsArray[] = stages.getString("tags").split(",");
				for (String tag : tagsArray) {
					String tagTrim = tag.trim();
					if (tags.indexOf("," + tagTrim + ",") == -1)
						tags = tags + tagTrim + ",";
				}
			} catch (Exception e) {

			}
		}
		String[] realTags = tags.split(",");
		tr.begin();
		for (String tag : realTags) {
			if (!tag.equals("")) {
				Tag t = new Tag();
				t.setName(tag);
				em.persist(t);
			}
		}
		tr.commit();
		stages.close();
	}

	private void loadStageDescriptions() throws SQLException {
		ResultSet stages = completeQueryDate("Select distinct(etapa) from proyectoley");
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		while (stages.next()) {
			StageDescription sd = new StageDescription();
			sd.setDescription(stages.getString("etapa"));
			em.persist(sd);
		}
		tr.commit();
		stages.close();
	}

	private ResultSet completeQueryDate(String query) {
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = c.createStatement();
			rs = statement.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ResultSet getTableData(String tablename) {
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = c.createStatement();
			rs = statement.executeQuery("SELECT * FROM " + tablename);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean connect() {
		try {
			Class.forName(driver).newInstance();
			c = DriverManager.getConnection(url + dbName, userName, password);
			if (c != null) {
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

	private Date getSafeDate(ResultSet r, String fieldName) {
		try {
			return r.getDate(fieldName);
		} catch (Exception e) {
			// e.printStackTrace();
			// System.out.println("Fail Parsing date");
			return null;
		}

	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setCircunscriptionService(
			CircunscriptionService circunscriptionService) {
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

	public void setDiscussionTypeService(
			DiscussionTypeService discussionTypeService) {
		this.discussionTypeService = discussionTypeService;
	}

	public void setCommissionTypeService(
			CommissionTypeService commissionTypeService) {
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

	public void setStageDescriptionService(
			StageDescriptionService stageDescriptionService) {
		this.stageDescriptionService = stageDescriptionService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
