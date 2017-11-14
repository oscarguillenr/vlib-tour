package client;

import java.io.*;
import java.net.URI;
import java.util.Properties;
import java.util.List;
import java.util.Arrays;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Main class.
 *
 */
public final class BikeStationClient {
    /**
	 * the URI.
	 */
	public static  String REST_URI ;
	
	/**
	 * the default constructor is private to avoid instantiation.
	 */
	private BikeStationClient() {
	}

	/**
	 * the main method.
	 * 
	 * @param args
	 *            there is no command line arguments.
	 * @throws IOException 
	 */
	public static void main(final String[] args) throws IOException {
        String tmp = "";
        char C='\0';
        /**
        * Choosing the server type
        *
        */
        System.out.println("---------------------------------------------------");
        System.out.print("Enter the server type (1. Real Server 2. Emulated Server):  ");
        try {
            while ((C=(char) System.in.read()) !='\n')
            {
                if (C != '\r')  tmp = tmp+C;
     
            }
        }
        catch (IOException e)
        {
            System.out.println("Erreur de frappe");
            System.exit(0);
        }
        char c = tmp.charAt(0);
        System.out.println("---------------------------------------------------");
        /**
        * Real server
        *
        */
        if (c == '1') {
            System.out.println("------------- R E A L   S E R V E R ------------------");
    		Properties properties = new Properties();
    		FileInputStream input = new FileInputStream("src/main/resources/bikestation.properties");
            properties.load(input);
            REST_URI = "https://" + properties.getProperty("jcdecaux.rooturl");
    		Client client = ClientBuilder.newClient();
    		URI uri = UriBuilder.fromUri(REST_URI).build();
    		WebTarget service = client.target(uri);
            tmp = "";
            C='\0';
            System.out.print("Enter the required station (must be a correct station): ");
            try {
                while ((C=(char) System.in.read()) !='\n')
                {
                    if (C != '\r')  tmp = tmp+C;
         
                }
            }
            catch (IOException e)
            {
                System.out.println("Erreur de frappe");
                System.exit(0);
            }
            System.out.println("Station Number "+ tmp +" : \n" + service.path("stations/"+tmp).queryParam("contract", "Paris").queryParam("apiKey", "ff213b68fcf8331b598e4aec192ec62f57bf08ba").request().accept(MediaType.APPLICATION_JSON).get(Station.class));
    	    System.out.println("---------------------------------------------------");
            System.out.println("---------------------------------------------------");
            System.out.println("all stations     : \n"
                    + service.path("stations").queryParam("contract", "Paris").queryParam("apiKey", "ff213b68fcf8331b598e4aec192ec62f57bf08ba").request().accept(MediaType.APPLICATION_JSON).get(Stations.class));
        /**
        * Emulated server
        *
        */
        } else if (c == '2') {
            System.out.println("---------- E M U L A T E D   S E R V E R -------------");
            Properties properties = new Properties();
            FileInputStream input = new FileInputStream("src/main/resources/bikestation.properties");
            properties.load(input);
            REST_URI = "http://"+properties.getProperty("bikestation.serveraddress")+"/MyServer";
            Client client = ClientBuilder.newClient();
            URI uri = UriBuilder.fromUri(REST_URI).build();
            WebTarget service = client.target(uri);
            tmp = "";
            C='\0';

            /**
            * Choosing REST methods
            *
            */
            System.out.println("---------------------------------------------------");
            System.out.print("Enter the required action (1. get all stations 2. get one station): ");
            try {
                while ((C=(char) System.in.read()) !='\n')
                {
                    if (C != '\r')  tmp = tmp+C;
         
                }
            }
            catch (IOException e)
            {
                System.out.println("Erreur de frappe");
                System.exit(0);
            }
            c = tmp.charAt(0);
            /**
            * Get ALL 
            *
            */
            if (c == '1') {
                System.out.println("---------------------------------------------------");
                System.out.println("all stations     : \n"
                        + service.path("stations/all").request().accept(MediaType.TEXT_PLAIN).get(String.class));
                System.out.println("---------------------------------------------------");

            /**
            * Get ONE object
            *
            */
            } else if (c == '2') {
                tmp = "";
                C='\0';
                System.out.print("Enter the required station (must be a correct station): ");
                try {
                    while ((C=(char) System.in.read()) !='\n')
                    {
                        if (C != '\r')  tmp = tmp+C;
             
                    }
                }
                catch (IOException e)
                {
                    System.out.println("Erreur de frappe");
                    System.exit(0);
                }
                System.out.println("---------------------------------------------------");
                System.out.println("station by number      : \n" + service.path("stations/"+tmp)
                        .request().accept(MediaType.APPLICATION_JSON).get(Station.class));
                System.out.println("---------------------------------------------------");
            } else
                System.out.println("Wrong action...Bye!");
        } else 
            System.out.println("Wrong server...Bye!");
            
        System.out.println("---------------------------------------------------");

        System.out.println("---------------------------------------------------");
    }

    private static String getOutputAsJSON(final WebTarget service) {
        return service.request().accept(MediaType.APPLICATION_JSON).get(String.class);
    }
}

