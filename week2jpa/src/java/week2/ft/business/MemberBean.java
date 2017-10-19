package week2.ft.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import week2.ft.model.Member;
import week2.ft.model.Team;

@Stateless
public class MemberBean {

	@PersistenceContext private EntityManager em;

	public void addMember(Member member, Team team) {
		member.setTeam(team);
		em.persist(member);
	}
	
}
