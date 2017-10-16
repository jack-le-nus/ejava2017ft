package week2.ft.web;

import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import week2.ft.business.TeamBean;
import week2.ft.model.Team;

@RequestScoped
@Named
public class TeamInsertView {

	@EJB private TeamBean teamBean;

	private String name;
	private String gitRepo;
	private String teamId;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getGitRepo() {
		return gitRepo;
	}
	public void setGitRepo(String gitRepo) {
		this.gitRepo = gitRepo;
	}

	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String addNewTeam() {
		teamId = UUID.randomUUID().toString().substring(0, 8);
		Team team = new Team();
		team.setGitRepo(gitRepo);
		team.setName(name);
		team.setTeamId(teamId);

		teamBean.addTeam(team);

		return ("teamcreated");
	}

}
