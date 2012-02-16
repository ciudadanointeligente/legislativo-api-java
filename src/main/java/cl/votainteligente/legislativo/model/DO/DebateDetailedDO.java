package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Debate;
import cl.votainteligente.legislativo.model.Tag;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

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
	}

	public DebateDetailedDO(Debate debateDetailed) {
		id = debateDetailed.getId();
		billId = debateDetailed.getBill().getId();
		chamber = debateDetailed.getChamber().getId();
		date = debateDetailed.getDate();
		discussionType = debateDetailed.getDiscussionType().getName();
		topic = debateDetailed.getTopic();
		debate = debateDetailed.getDebate();
		tags = new HashSet<String>();
		if (debateDetailed.getTags() != null) {
			for (Tag t : debateDetailed.getTags()) {
				tags.add(t.getName());
			}
		}
		abstractText = debateDetailed.getAbstractText();
		abstractTitle = debateDetailed.getAbstractTitle();
	}

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

	public URL getDocUrl() {
		try {
			return new URL(docUrl);
		} catch (MalformedURLException e) {
			return null;
		}
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
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
}
