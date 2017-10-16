package week2.ft.web;

import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import week2.ft.business.TeamBean;
import week2.ft.model.Team;

@RequestScoped
@Named
public class TeamQueryView {

	@EJB private TeamBean teamBean;

	private String queryId;
	private Team team;

	public String getQueryId() {
		return queryId;
	}
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}

	public void query() {
		System.out.println(">> team id = " + queryId);
		team = teamBean
				.findById(queryId)
				.orElse(null);
	}
	
}
