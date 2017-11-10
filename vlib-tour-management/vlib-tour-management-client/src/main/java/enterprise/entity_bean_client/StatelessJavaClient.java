
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
			System.out.print("\nCreating POI...");
			String result = sb.createPoi("P1", "First Poi","Poi number 1",111.1,151.15);
			System.out.print("\nCreating Tour...");
			result = sb.createTour("T2", "Second Tour","Tour 2");
			System.out.print("\nAdding Poi into tour1...");
			result = sb.addPoiToTour("T1", "P1");
			String tours = sb.listTours();
			System.out.println(tours);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
