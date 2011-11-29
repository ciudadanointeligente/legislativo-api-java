package cl.votainteligente.legislativo.model;

import java.net.URL;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "debate")
public class Debate {
	@Id
	@GeneratedValue
	@Column
	private Long id;

	@ManyToOne
	@JoinColumn(name = "bill_id", nullable = false)
	private Bill bill;

	@Column
	private Boolean in_commission;

	@Column
	// TODO: averiguar si este boolean tiene sentido y que es.
	private Boolean united_commissions;

	@ManyToOne
	@JoinColumn(name = "chamber_id", nullable = false)
	private Chamber chamber;

	@Column
	private Date date;

	@ManyToOne
	@JoinColumn(name = "discussion_type_id", nullable = false)
	private DiscussionType discussionType;

	@Column
	private String topic;

	@Column
	private String debate;

	@ManyToMany
	@JoinColumn(name = "debate_id", nullable = true)
	private Set<Tag> tags;

	@Column
	private URL docUrl;

	@Column
	private String abstract_title;

	@Column
	private String abstract_text;

	@Column
	private Date created_at;

	@Column
	private Date updated_at;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Boolean getIn_commission() {
		return in_commission;
	}

	public void setIn_commission(Boolean in_commission) {
		this.in_commission = in_commission;
	}

	public Boolean getUnited_commissions() {
		return united_commissions;
	}

	public void setUnited_commissions(Boolean united_commissions) {
		this.united_commissions = united_commissions;
	}

	public Chamber getChamber() {
		return chamber;
	}

	public void setChamber(Chamber chamber) {
		this.chamber = chamber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public DiscussionType getDiscussionType() {
		return discussionType;
	}

	public void setDiscussionType(DiscussionType discussionType) {
		this.discussionType = discussionType;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDebate() {
		return debate;
	}

	public void setDebate(String debate) {
		this.debate = debate;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public URL getDocUrl() {
		return docUrl;
	}

	public void setDocUrl(URL docUrl) {
		this.docUrl = docUrl;
	}

	public String getAbstract_title() {
		return abstract_title;
	}

	public void setAbstract_title(String abstract_title) {
		this.abstract_title = abstract_title;
	}

	public String getAbstract_text() {
		return abstract_text;
	}

	public void setAbstract_text(String abstract_text) {
		this.abstract_text = abstract_text;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
}
