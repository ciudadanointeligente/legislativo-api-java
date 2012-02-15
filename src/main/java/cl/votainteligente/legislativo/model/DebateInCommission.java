package cl.votainteligente.legislativo.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "debate_in_commission")
public class DebateInCommission extends Debate {

	@ManyToMany
	@JoinColumn(name = "participant_commissions")
	private Set<Commission> participantCommissions;

	public Set<Commission> getParticipantCommissions() {
		return participantCommissions;
	}

	public void setParticipantCommissions(Set<Commission> participantCommissions) {
		this.participantCommissions = participantCommissions;
	}
}
