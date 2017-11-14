package TourTouristClient;

import javax.naming.*;

import java.util.*;

import enterprise.entity_bean_api.TourManagementSession;


public class TouristClient {

    public static void main(String[] args) {

        TourManagementSession tm;

        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
     
            // glassfish default port value will be 3700,
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
            InitialContext ic = new InitialContext(props);
            tm = (TourManagementSession) ic.lookup("java:global/entity-bean/TourManagementSessionBean!enterprise.entity_bean_api.TourManagementSession");
            String result;

            // First listing of tours.
            System.out.print("\nListing tours...");
            result = tm.listTours();
            System.out.println(result);

            // Waiting for actions.
            System.in.read();

            // Second listing of tours.
            System.out.print("\nListing tours...");
            result = tm.listTours();
            System.out.println(result);

            // Waiting for actions.
            System.in.read();

            // Third listing of tours.
            System.out.print("\nListing tours...");
            result = tm.listTours();
            System.out.println(result);

            // Select a tour
            System.out.print("\nSelecting tour...");
            result = tm.selectTour("T1");
            System.out.println(result);

            // Waiting for actions.
            System.in.read();

            // Fourth listing of tours.
            System.out.print("\nListing tours...");
            result = tm.listTours();
            System.out.println(result);

            // Waiting for actions.
            System.in.read();

            // Fifth listing of tours.
            System.out.print("\nListing tours...");
            result = tm.listTours();
            System.out.println(result);

        } catch(Exception e) {
            e.printStackTrace();
        }
    
    }

}