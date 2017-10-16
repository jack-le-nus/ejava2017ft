package week2.ft.business;

import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import week2.ft.model.Team;

@Stateless
public class TeamBean {

	@PersistenceContext private EntityManager em;
	
	public Optional<Team> findById(String tid) {
		//tx.begin()
		return Optional.ofNullable(em.find(Team.class, tid));
		//tx.commit()
	}

	//@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void addTeam(Team team) {
		//Insert 
		em.persist(team);
	}
}
