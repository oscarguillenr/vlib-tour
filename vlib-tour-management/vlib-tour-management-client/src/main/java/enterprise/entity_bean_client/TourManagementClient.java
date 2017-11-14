
package enterprise.entity_bean_client;

import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NameClassPair;

import enterprise.entity_bean_entity.POI;

import enterprise.entity_bean_entity.Tour;

import enterprise.entity_bean_api.TourManagementSession;

public class TourManagementClient {
	public static void main(String args[]) {
		TourManagementSession sb;
	    try {
			InitialContext ic = new InitialContext();
			NamingEnumeration<NameClassPair> list = ic.list("");
            while (list.hasMore()) {
                System.out.println(list.next().getName());
            }
			sb = (TourManagementSession) ic.lookup("enterprise.entity_bean_api.TourManagementSession");
			String result;
			// result = sb.removePoi("P1");
			// System.out.print("\nCreating Tour...");
			result = sb.removePoiFromTour("T1", "P2");
			// System.out.print("\nAdding Poi into tour1...");
			// result = sb.addPoiToTour("T1", "P2");
			String tours = sb.listTours();
			System.out.println(tours);
			// result = sb.modifyPoiDescription("P1", "New details");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
