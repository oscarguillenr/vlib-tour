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

	// @Override
	// public int makeReservation(String room, String show, String client, int seats) {
	// 	// Create a new show
	// 	Show show1 = new Show();
	// 	show1.setId(1);
	// 	show1.setName(show);
	// 	show1.setRoom(room);
	// 	show1.setSeats(10);
	// 	// Persist the show
	// 	//em.persist(show1);
	// 	// Create a new reservation
	// 	Reservation res1 = new Reservation();
	// 	res1.setId(1);
	// 	res1.setClient(client);
	// 	// Persist the reservation
	// 	//em.persist(res1);
	// 	// Associate orders with the customers. The association
	// 	// must be set on both sides of the relationship: on the
	// 	// customer side for the orders to be persisted when
	// 	// transaction commits, and on the order side because it
	// 	// is the owning side.
	// 	if (seats > show1.getSeats())
	// 		return -1;
	// 	show1.setSeats(show1.getSeats()-seats);
	// 	show1.getReservations().add(res1);
	// 	res1.setShow(show1);
	// 	em.persist(show1);
	// 	em.persist(res1);
	// 	return res1.getId();
	// }

	// @Override
	// public String cancellReservation(final int id){
	// 	Query q = em.createQuery("select c from Reservation c where c.id = :id");
	// 	q.setParameter("id", id	);
	// 	Reservation res = (Reservation) q.getSingleResult();
	// 	// Merge the customer to the new persistence context
	// 	Reservation res0 = em.merge(res);
	// 	// Delete all records.
	// 	em.remove(res0);
	// 	return "OK";
	// }

	// @Override
	// public String cancellShow(final int id){
	// 	Query q = em.createQuery("select c from Show c where c.id = :id");
	// 	q.setParameter("id", id	);
	// 	Show show = (Show) q.getSingleResult();
	// 	// Merge the customer to the new persistence context
	// 	Show show0 = em.merge(show);
	// 	// Delete all records.
	// 	em.remove(show0);
	// 	return "OK";
	// }

	// @Override
	// public String showName(final int id) {
	// 	Query q = em.createQuery("select c from Show c where c.id = :id");
	// 	q.setParameter("id", id);
	// 	Show show = (Show) q.getSingleResult();
	// 	return show.getName();
	// }

	// @Override
	// public int showCapacity(final int id) {
	// 	Query q = em.createQuery("select c from Show c where c.id = :id");
	// 	q.setParameter("id", id);
	// 	Show show = (Show) q.getSingleResult();
	// 	return show.getSeats();
	// }

	// @Override
	// public String showRoomName(final int id) {
	// 	Query q = em.createQuery("select c from Show c where c.id = :id");
	// 	q.setParameter("id", id);
	// 	Show show = (Show) q.getSingleResult();
	// 	return show.getRoom();
	// }

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
		return "OK";
	}

	@Override
	public String createTour(final String tid, final String name, final String description) {
		Tour tour = new Tour();
		tour.setId(tid);
		tour.setName(name);
		tour.setDescription(description);
		em.persist(tour);
		return "OK";
	}

	@Override
	public String addPoiToTour(final String tid, final String pid) {
		return "OK";
	}

	@Override
	public String removePoiFromTour(final String tid, final String pid) {
		return "OK";
	}

	@Override
	public String createPoi(final String pid, final String name, final String description, final double latitude, final double longitude) {
		if (orders == null || orders.size() != 2) {
			throw new RuntimeException(
					"Unexpected number of orders: " + ((orders == null) ? "null" : "" + orders.size()));
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
		return "OK";
	}

	@Override
	public String modifyPoiDescription(final String pid, final String newDescription) {
		return "OK";
	}
}
