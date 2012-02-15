package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.SingleVoteDO;
import cl.votainteligente.legislativo.model.DO.SingleVoteDetailedDO;

import javax.persistence.*;

@Entity
@Table(name = "single_vote")
public class SingleVote {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	@ManyToOne
	@JoinColumn(name = "vote_id")
	private Vote vote;

	@Column(name = "vote_detail")
	private String voteDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public String getVoteDetail() {
		return voteDetail;
	}

	public void setVoteDetail(String voteDetail) {
		this.voteDetail = voteDetail;
	}

	public SingleVoteDetailedDO asDetailedDomainObject() {
		return new SingleVoteDetailedDO(this);
	}

	public SingleVoteDO asDomainObject() {
		return new SingleVoteDO(this);
	}
}
