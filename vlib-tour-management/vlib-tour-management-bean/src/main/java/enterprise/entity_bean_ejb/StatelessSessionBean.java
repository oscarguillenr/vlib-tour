package enterprise.entity_bean_ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import enterprise.entity_bean_entity.POI;
import enterprise.entity_bean_entity.Tour;
import enterprise.entity_bean_entity.GPSPosition;

import enterprise.entity_bean_api.StatelessSession;

/**
 * The stateless session bean.
 */
@Stateless
public class StatelessSessionBean implements StatelessSession {

	/**
	 * the reference to the entity manager, which persistence context is "pu1".
	 */
	@PersistenceContext(unitName = "pu1")
	private EntityManager em;

	@Override
	public String listTours() {
		Query q = em.createQuery("select t from Tour t");
		List  result = q.getResultList();
		String out = "\n#####################################\nTours Availables:\n";
		for (int i = 0; i < result.size(); i++) {
			Tour tour = (Tour) result.get(i);
			out = out + " - " + tour.toString() + "\n";
		}
		out += "#####################################\n";
		return out;
	}

	@Override
	public String selectTour(final String tid) {
		Query q = em.createQuery("select o from Tour o where o.tid = :tid");
		q.setParameter("tid", tid);
		List results = q.getResultList();
		if (results == null || results.size() == 0) {
			throw new RuntimeException("PID doesn't exist.");
		}

		return "OK";
	}

	@Override
	public String createTour(final String tid, final String name, final String description) {
		Query q = em.createQuery("select o from Tour o where o.tid = :tid");
		q.setParameter("tid", tid);
		List results = q.getResultList();
		if (results != null && results.size() != 0) {
			throw new RuntimeException("TID has already been created.");
		}
		Tour tour = new Tour();
		tour.setId(tid);
		tour.setName(name);
		tour.setDescription(description);
		em.persist(tour);
		return "OK";
	}

	@Override
	public String addPoiToTour(final String tid, final String pid) {
		Query q = em.createQuery("select o from Poi o where o.pid = :pid");
		q.setParameter("pid", pid);
		List results = q.getResultList();
		if (results == null || results.size() == 0) {
			throw new RuntimeException("PID doesn't exist.");
		}
		POI poi = (POI) q.getSingleResult();

		q = em.createQuery("select o from Tour o where o.tid = :tid");
		q.setParameter("tid", tid);
		results = q.getResultList();
		if (results == null || results.size() == 0) {
			throw new RuntimeException("TID has already been created.");
		}
		Tour tour = (Tour) q.getSingleResult();
		tour.getPOIs().add(poi);
		poi.getTours().add(tour);
		return "OK";
	}

	@Override
	public String removePoiFromTour(final String tid, final String pid) {
		return "OK";
	}

	@Override
	public String createPoi(final String pid, final String name, final String description, final double latitude, final double longitude) {
		Query q = em.createQuery("select o from Poi o where o.pid = :pid");
		q.setParameter("pid", pid);
		List results = q.getResultList();
		if (results != null || results.size() != 0) {
			throw new RuntimeException("PID has already been created.");
		}
		POI poi = new POI();
		poi.setId(pid);
		poi.setName(name);
		poi.setDescription(description);
		GPSPosition position = new GPSPosition(latitude, longitude);
		poi.setGpslocation(position);
		em.persist(poi);
		return "OK";
	}

	@Override
	public String removePoi(final String pid) {
		Query q = em.createQuery("select o from Poi o where o.pid = :pid");
		q.setParameter("pid", pid);
		List results = q.getResultList();
		if (results == null || results.size() == 0) {
			throw new RuntimeException("PID doesn't exist.");
		}

		return "OK";
	}

	@Override
	public String modifyPoiDescription(final String pid, final String newDescription) {
		Query q = em.createQuery("select o from Poi o where o.pid = :pid");
		q.setParameter("pid", pid);
		List results = q.getResultList();
		if (results == null || results.size() == 0) {
			throw new RuntimeException("PID doesn't exist.");
		}



		return "OK";
	}
}
