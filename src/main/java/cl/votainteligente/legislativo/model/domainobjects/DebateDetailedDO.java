package cl.votainteligente.legislativo.model.domainobjects;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Debate;
import cl.votainteligente.legislativo.model.Tag;

@XmlRootElement
public class DebateDetailedDO {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Long getChamber() {
		return chamber;
	}

	public void setChamber(Long chamber) {
		this.chamber = chamber;
	}

	public String getDiscussionType() {
		return discussionType;
	}

	public void setDiscussionType(String discussionType) {
		this.discussionType = discussionType;
	}

	public String getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}

	public String getAbstractTitle() {
		return abstractTitle;
	}

	public void setAbstractTitle(String abstractTitle) {
		this.abstractTitle = abstractTitle;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public URL getDocUrl() {
		return docUrl;
	}

	public void setDocUrl(URL docUrl) {
		this.docUrl = docUrl;
	}

	private Long id;
	private Long billId;
	private Long chamber;
	private String discussionType;
	private String abstractText;
	private String abstractTitle;
	private String topic;
	private String date;
	private Set<String> tags;
	private URL docUrl;

	public DebateDetailedDO() {

	}

	public DebateDetailedDO(Debate d) {
		this.id = d.getId();
		this.billId = d.getBill().getId();
		this.chamber = d.getChamber().getId();
		this.discussionType = d.getDiscussionType().getName();
		this.abstractText = d.getAbstractText();
		this.abstractTitle = d.getAbstractTitle();
		this.topic = d.getTopic();
		this.date = DOUtil.getDateFormat().format(d.getDate());
		this.tags = new HashSet<String>();
		for (Tag tag : d.getTags())
			this.tags.add(tag.getName());
		this.docUrl = d.getDocUrl();
	}
}
