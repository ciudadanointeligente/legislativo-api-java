package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Debate;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DebateDO {

	private Long id;
	private Long billId;
	private Long chamber;
	private String discussionType;
	private String abstractText;
	private String abstractTitle;
	private String topic;

	public DebateDO() {
	}

	public DebateDO(Debate debate) {
		this.id = debate.getId();
		this.billId = debate.getBill().getId();
		this.chamber = debate.getChamber().getId();
		this.discussionType = (debate.getDiscussionType() != null) ? debate.getDiscussionType().getName() : null;
		this.abstractText = debate.getAbstractText();
		this.abstractTitle = debate.getAbstractTitle();
		this.topic = debate.getTopic();
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
}
