package week2.ft.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import week2.ft.business.CreateTeam;
import week2.ft.model.Member;
import week2.ft.model.Team;

@ViewScoped
@Named
public class CreateTeamView implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB private CreateTeam createTeam;

	private Team team = new Team();
	private List<Member> members = new LinkedList<>();

	private Member newMember = new Member();

	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}

	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Member getNewMember() {
		return newMember;
	}
	public void setNewMember(Member newMember) {
		this.newMember = newMember;
	}

	public void addMember() {
		members.add(newMember);
		newMember = new Member();
	}

	public String createTeam() {
		team.setTeamId(UUID.randomUUID().toString().substring(0, 8));
		createTeam.create(members, team);
		return ("teamcreated2");
	}
	
}
