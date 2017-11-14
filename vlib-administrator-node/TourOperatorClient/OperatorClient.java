package TourOperatorClient;

import javax.naming.*;

import java.util.*;

import enterprise.entity_bean_api.TourManagementSession;


public class OperatorClient {

    public static void main(String[] args) {

        TourManagementSession tm;

        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
     
            // glassfish default port value will be 3700,
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
            InitialContext ic = new InitialContext(props);
            NamingEnumeration<NameClassPair> list = ic.list("");
            while (list.hasMore()) {
                System.out.println(list.next().getName());
            }
            tm = (TourManagementSession) ic.lookup("java:global/entity-bean/TourManagementSessionBean!enterprise.entity_bean_api.TourManagementSession");
            String result;

            // Create 4 Pois
            System.out.print("\nCreating POI Musée Grévin...");
            result = tm.createPoi("P1", "Musée Grévin", "Description first poi", 60, 48.871799, 2.342355);
            System.out.println(result);
            System.out.print("Creating POI Pyramide du Louvres...");
            result = tm.createPoi("P2", "Pyramide du Louvres", "Description second poi", 20, 48.860959, 2.335757);
            System.out.println(result);
            System.out.print("Creating POI Les catacombes de Paris...");
            result = tm.createPoi("P3", "Les catacombes de Paris", "Description third poi", 60, 48.833566, 2.332416);
            System.out.println(result);
            System.out.print("Creating POI Tour Eiffel...");
            result = tm.createPoi("P4", "Tour Eiffel", "Description fourth poi", 60, 48.8583701, 2.2944813);
            System.out.println(result);

            // Create 2 tour
            System.out.print("\nCreating Tour The unusual Paris...");
            result = tm.createTour("T1", "The unusual Paris", "The unusual Paris description");
            System.out.println(result);

            System.out.print("\nCreating Tour The unusual Paris 2...");
            result = tm.createTour("T2", "The unusual Paris 2", "The unusual Paris 2 description");
            System.out.println(result);

            // Add pois to Tours
            System.out.print("\nAdding Poi Musée Grévin...");
            result = tm.addPoiToTour("T1", "P1");
            System.out.println(result);
            System.out.print("Adding Poi Pyramide du Louvres...");
            result = tm.addPoiToTour("T1", "P2");
            System.out.println(result);
            System.out.print("Adding Poi Les catacombes de Paris...");
            result = tm.addPoiToTour("T1", "P3");
            System.out.println(result);

            System.out.print("\nAdding Poi Pyramide du Louvres...");
            result = tm.addPoiToTour("T2", "P2");
            System.out.println(result);
            System.out.print("Adding Poi Les catacombes de Paris...");
            result = tm.addPoiToTour("T2", "P3");
            System.out.println(result);
            System.out.print("Adding Poi Tour Eiffel...");
            result = tm.addPoiToTour("T2", "P4");
            System.out.println(result);

            // Modify some POI descriptions
            System.out.print("Modyfying Musée de Grévin description...");
            result = tm.modifyPoiDescription("P1", "New description 1");
            System.out.println(result);
            System.out.print("Modyfying Pyramide du Louvres description...");
            result = tm.modifyPoiDescription("P2", "New description 2");
            System.out.println(result);
            

            // Remove somes pois from tour
            System.out.print("Removing poi Musée de Grévin from tour...");
            result = tm.removePoiFromTour("T1", "P1");
            System.out.println(result);
            System.out.print("Removing poi Pyramide du Louvres from tour...");
            result = tm.removePoiFromTour("T1", "P2");
            System.out.println(result);
            System.out.print("Removing poi Pyramide du Louvres from tour...");
            result = tm.removePoiFromTour("T2", "P2");
            System.out.println(result);
            System.out.print("Removing poi Les catacombes de Paris from tour...");
            result = tm.removePoiFromTour("T2", "P3");
            System.out.println(result);

            // Remove pois
            System.out.print("Removing poi Musée de Grévin...");
            result = tm.removePoi("P1");
            System.out.println(result);
            System.out.print("Removing poi Pyramide du Louvres...");
            result = tm.removePoi("P2");
            System.out.println(result);
            System.out.print("Removing poi Les catacombes de Paris...");
            result = tm.removePoi("P3");
            System.out.println(result);
            System.out.print("Removing poi Tour Eiffel...");
            result = tm.removePoi("P4");
            System.out.println(result);

            // Remove tours
            // System.out.print("Removing Tour The unusual Paris...");
            // result = tm.removeTour("T1");
            // System.out.println(result);
            // System.out.print("Removing Tour The unusual Paris 2...");
            // result = tm.removeTour("T2");
            // System.out.println(result);

        } catch(Exception e) {
            e.printStackTrace();
        }
    
    }

}