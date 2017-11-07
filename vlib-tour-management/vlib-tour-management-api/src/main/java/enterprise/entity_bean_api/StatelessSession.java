package enterprise.entity_bean_api;

import javax.ejb.Remote;
/**
 * The API of the entity bean.
 */
@Remote
public interface StatelessSession {

	/**
	 * list the set of tours.
	 * 
	 * @return the list of tours if there is no problem.
	 */
	String listTours();

	/**
	 * select a tour
	 *
	 * @param tid
  	 *         the tour identifier
	 * @return the string OK if there is no problem.
	 */
	String selectTour(String tid);

	/**
	 * create a tour.
	 * 
	 * @param tid
  	 *         the tour identifier
  	 * @param name
  	 *         the tour name
  	 * @param description
  	 *         the tour description
	 * @return the string OK if there is no problem.
	 */
	String createTour(String tid, String name, String description);

	/**
	 * add a poi to a tour.
	 * 
	 * @param tid
  	 *         the tour identifier
  	 * @param pid
  	 *		   the poi identifier which will be added
	 * @return the string OK if there is no problem.
	 */
	String addPoi(String tid, String pid);

	/**
	 * remove a poi from a tour.
	 * 
	 * @param tid
  	 *         the tour identifier
  	 * @param pid
  	 *		   the poi identifier which will be removed
	 * @return the string OK if there is no problem.
	 */
	String removePoiFromTour(String tid, String pid);

	/**
	 * create a POI.
	 * 
	 * @param pid
  	 *         the poi identifier
  	 * @param name
  	 *         the poi name
  	 * @param description
  	 *         the poi description
  	 * @param latitude
  	 *         the poi latitude
  	 * @param longitude
  	 *         the poi longitude
	 * @return the string OK if there is no problem.
	 */
	String createPoi(String pid, String name, String description, double latitude, double longitude);

	/**
	 * remove a poi.
	 * 
	 * @param pid
  	 *         the poi identifier
	 * @return the string OK if there is no problem.
	 */
	String removePoi(String pid);

	/**
	 * modify the description of a poi.
	 * 
	 * @param pid
  	 *         the poi identifier
  	 * @param newDescription
  	 *         the new description
	 * @return the string OK if there is no problem.
	 */
	String modifyPoiDescription(String pid, String newDescription);
}
