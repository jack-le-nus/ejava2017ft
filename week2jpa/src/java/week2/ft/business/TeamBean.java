package week2.ft.business;

import java.util.List;
import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheResult;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import week2.ft.model.Team;

@CacheDefaults(cacheName = "teamBean")
@Stateless
public class TeamBean {

	@PersistenceContext private EntityManager em;

	public List<Team> getAllTeams() {

		TypedQuery<Team> query = em.createQuery("select t from Team t", Team.class);
		return (query.getResultList());
	}
	
	//public Optional<Team> findById(String tid) {
	@CacheResult
	public Team findById(@CacheKey String tid) {
		System.out.println("--- finding team: " + tid);
		//tx.begin()
		//return Optional.ofNullable(em.find(Team.class, tid));
		Team t = em.find(Team.class, tid);
		if (t != null)
			t.getMembers().size();
		return (t);
		//tx.commit()
	}

	@CacheRemove
	public void update(@CacheKey String tid, Team t) {
		Team managedTeam = em.merge(t);
	}

	@CacheRemove
	public void clear(@CacheKey String tid) { }

	//@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void addTeam(Team team) {
		//Insert 
		em.persist(team);
	}
}
