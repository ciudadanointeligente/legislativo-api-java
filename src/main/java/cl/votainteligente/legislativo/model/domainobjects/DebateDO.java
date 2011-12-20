package cl.votainteligente.legislativo.model.domainobjects;

import cl.votainteligente.legislativo.model.Debate;

public class DebateDO {
	private Long id;
	private Long billId;
	private String chamber;
	private String discussionType;
	private String abstractText;
	private String abstractTitle;
	private String topic;

	public DebateDO(Debate d) {
		this.id = d.getId();
		this.billId = d.getBill().getId();
		this.chamber = d.getChamber().getName();
		this.discussionType = d.getDiscussionType().getName();
		this.abstractText = d.getAbstractText();
		this.abstractTitle = d.getAbstractTitle();
		this.topic = d.getTopic();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getChamber() {
		return chamber;
	}

	public void setChamber(String chamber) {
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
