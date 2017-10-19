package week2.ft.business;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import week2.ft.model.Member;
import week2.ft.model.Team;

@Stateless
public class CreateTeam {

	@EJB private TeamBean teamBean;
	@EJB private MemberBean memberBean;

	public void create(List<Member> members, Team team) {

		teamBean.addTeam(team);
		for (Member m: members)
			memberBean.addMember(m, team);

	}
	
}
