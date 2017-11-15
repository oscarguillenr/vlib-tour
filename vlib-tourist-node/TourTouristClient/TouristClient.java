package TourTouristClient;

import javax.naming.*;

import java.util.*;

import enterprise.entity_bean_api.TourManagementSession;

import java.io.*;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import client.Position;
import client.Station;
import client.Stations;


public final class TouristClient {

    /**
     * the URI.
     */
    public static  String REST_URI ;
    
    /**
     * the default constructor is private to avoid instantiation.
     */
    private TouristClient() {
    }

    public static void main(String[] args) throws IOException {

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

            String tmp = "";
            char C='\0';
            System.out.println("---------------------------------------------------");
            /**
            * Real server
            *
            */
            // System.out.println("------------- R E A L   S E R V E R ------------------");
            // Properties properties = new Properties();
            // FileInputStream input = new FileInputStream("TourTouristClient/bikestation.properties");
            // properties.load(input);
            // REST_URI = "https://" + properties.getProperty("jcdecaux.rooturl");
            // Client client = ClientBuilder.newClient();
            // URI uri = UriBuilder.fromUri(REST_URI).build();
            // WebTarget service = client.target(uri);
            // tmp = "";
            // C='\0';
            // System.out.print("Enter the required station (must be a correct station): ");
            // try {
            //     while ((C=(char) System.in.read()) !='\n')
            //     {
            //         if (C != '\r')  tmp = tmp+C;
         
            //     }
            // }
            // catch (IOException e)
            // {
            //     System.out.println("Erreur de frappe");
            //     System.exit(0);
            // }
            // System.out.println("Station Number "+ tmp +" : \n" + service.path("stations/"+tmp).queryParam("contract", "Paris").queryParam("apiKey", "ff213b68fcf8331b598e4aec192ec62f57bf08ba").request().accept(MediaType.APPLICATION_JSON).get(Station.class));
            // System.out.println("---------------------------------------------------");
            // System.out.println("---------------------------------------------------");
            // System.out.println("all stations     : \n"
            //         + service.path("stations").queryParam("contract", "Paris").queryParam("apiKey", "ff213b68fcf8331b598e4aec192ec62f57bf08ba").request().accept(MediaType.APPLICATION_JSON).get(Stations.class));

        } catch(Exception e) {
            e.printStackTrace();
        }
    
    }

    private static String getOutputAsJSON(final WebTarget service) {
        return service.request().accept(MediaType.APPLICATION_JSON).get(String.class);
    }

}