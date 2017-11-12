// CHECKSTYLE:OFF
package vlibtour.communicationsystem;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rabbitmq.http.client.Client;
import com.rabbitmq.tools.jsonrpc.JsonRpcException;

public class TestScenario {
	
	private static Client c;
	
	@BeforeClass
	public static void setUp() throws IOException, InterruptedException, URISyntaxException {
		try {
			new ProcessBuilder("rabbitmqctl", "stop").inheritIO().start().waitFor();
		} catch (IOException | InterruptedException e) {
		}
		Thread.sleep(1000);
		new ProcessBuilder("rabbitmq-server", "-detached").inheritIO().start().waitFor();
		new ProcessBuilder("rabbitmqctl", "stop_app").inheritIO().start().waitFor();
		new ProcessBuilder("rabbitmqctl", "reset").inheritIO().start().waitFor();
		new ProcessBuilder("rabbitmqctl", "start_app").inheritIO().start().waitFor();
		c =  new Client("http://127.0.0.1:15672/api/", "guest", "guest");
	}
	
	@Test
	public void test() throws IOException, TimeoutException, JsonRpcException, InterruptedException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
		//LobbyRoomRPCServer rpcServer = new LobbyRoomRPCServer();
		System.out.println("TESTING");
		String groupId = "Group1";
		String userId = "User1";
		String tourId = "Tour1";
		String userId2 = "User2";
		String userId3 = "User3";
		String uri = "amqp://localhost";
		CommunicationSystem commSys = new CommunicationSystem(groupId, userId);
		//Assert.assertNotNull(c.getExchanges().stream().filter(q -> q.getName().equals(groupId + "_" + userId)));
		//Assert.assertNotNull(c.getBindings().stream().filter(b -> b.getRoutingKey().equals(LobbyRoomRPCServer.BINDING_KEY)));
		// Client creation.
		CommunicationSystemParticipant client1 = new CommunicationSystemParticipant(groupId, userId, tourId, uri);
		CommunicationSystemParticipant client2 = new CommunicationSystemParticipant(groupId, userId2, tourId, uri);
CommunicationSystemParticipant client3 = new CommunicationSystemParticipant(groupId, userId3, tourId, uri);
		//Publishing messages and Receiving messages 
		client1.publishMessage("all","Hello");
		client2.receiveMessage();
		client3.receiveMessage();
		client2.publishMessage(userId3, "Hello client 3");
		client3.receiveMessage();
		//Close the clients and the communication system
		client1.close();
		client2.close();
		client3.close();
		commSys.close();
	}
	
	@AfterClass
	public static void tearDown() throws InterruptedException, IOException {
		new ProcessBuilder("rabbitmqctl", "stop_app").inheritIO().start().waitFor();
		new ProcessBuilder("rabbitmqctl", "stop").inheritIO().start().waitFor();
	}
}
