package week2.ft.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import week2.ft.business.TeamBean;
import week2.ft.model.Team;

@RequestScoped
@Path("/team") //Root resource
public class TeamResource {

	@EJB private TeamBean teamBean;

	@POST // /team
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON) //setContentType
	public Response post(MultivaluedMap<String, String> formData) {

		final String teamId = UUID.randomUUID().toString().substring(0, 8);

		final String name = formData.getFirst("teamName");
		final String gitRepo = formData.getFirst("gitRepo");

		System.out.println(String.format("teamId = %s, name = %s\n\tgit:%s"
				, teamId, name, gitRepo));

		Team t = new Team();
		t.setTeamId(teamId);
		t.setName(name);
		t.setGitRepo(gitRepo);
		teamBean.addTeam(t);

		JsonObject result = Json.createObjectBuilder()
				.add("teadId", teamId)
				.build();

		return (Response
				.status(Status.CREATED) //setStatus
				.entity(result) //PrintWriter
				.build());
	}

	@GET
	@Path("{tid}") // /team/1234 - sub resource
	public Response get(@PathParam("tid") String teamId) {

		//Optional<Team> opt = teamBean.findById(teamId);
		Team team = teamBean.findById(teamId);

		//if (!opt.isPresent())
		if (null == team)
			return (Response.status(Status.NOT_FOUND).build());

		//return (Response.ok(opt.get().toJson()).build());
		return (Response.ok(team.toJson()).build());
	}

	@GET // /team
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(
			@DefaultValue("5") @QueryParam("size")Integer size) {

		List<Team> teams = teamBean.getAllTeams();

		JsonArrayBuilder builder = Json.createArrayBuilder();

		//Using lambda expression - functional
		/*
		teams.stream()
				.forEach(t -> builder.add(t.toJson()));
		*/

		for (int i = 0; i < size; i++)
			builder.add(teams.get(i).toJson());

		//Old way - not functional
		/*
		for (Team t: teams)
			builder.add(t.toJson());
		*/

		return (Response.ok(builder.build()).build());
	}
	
}
