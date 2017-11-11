
package enterprise.entity_bean_client;

import javax.naming.InitialContext;

import enterprise.entity_bean_entity.POI;

import enterprise.entity_bean_entity.Tour;

import enterprise.entity_bean_api.StatelessSession;

public class StatelessJavaClient {
	public static void main(String args[]) {
		StatelessSession sb;
	        try {
			InitialContext ic = new InitialContext();
			sb = (StatelessSession) ic.lookup("enterprise.entity_bean_api.StatelessSession");
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
