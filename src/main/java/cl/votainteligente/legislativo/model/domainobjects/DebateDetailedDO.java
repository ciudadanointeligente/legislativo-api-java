package cl.votainteligente.legislativo.model.domainobjects;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import cl.votainteligente.legislativo.model.Debate;
import cl.votainteligente.legislativo.model.Tag;

@XmlRootElement
public class DebateDetailedDO {
	private Long id;
	private Long billId;
	private Long chamber;
	private Date date;
	private String discussionType;
	private String topic;
	private String debate;
	private Set<String> tags;
	private String docUrl;

	private String abstractTitle;
	private String abstractText;

	public DebateDetailedDO() {
	};

	public DebateDetailedDO(Debate d) {
		id = d.getId();
		billId = d.getBill().getId();
		chamber = d.getChamber().getId();
		date = d.getDate();
		discussionType = d.getDiscussionType().getName();
		topic = d.getTopic();
		debate = d.getDebate();
		tags = new HashSet<String>();
		if (d.getTags() != null)
			for (Tag t : d.getTags())
				tags.add(t.getName());
		abstractText = d.getAbstractText();
		abstractTitle = d.getAbstractTitle();
	}

	public URL getDocUrl() {
		try {
			return new URL(docUrl);
		} catch (MalformedURLException e) {
			return null;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChamber() {
		return chamber;
	}

	public void setChamber(Long chamber) {
		this.chamber = chamber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDiscussionType() {
		return discussionType;
	}

	public void setDiscussionType(String discussionType) {
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

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public String getAbstractTitle() {
		return abstractTitle;
	}

	public void setAbstractTitle(String abstractTitle) {
		this.abstractTitle = abstractTitle;
	}

	public String getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

}
