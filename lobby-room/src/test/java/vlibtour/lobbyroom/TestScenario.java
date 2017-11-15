// CHECKSTYLE:OFF
package vlibtour.lobbyroom;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rabbitmq.http.client.Client;
import com.rabbitmq.tools.jsonrpc.JsonRpcException;

import vlibtour.lobbyroom.client.LobbyRoomRPCClient;
import vlibtour.lobbyroom.server.LobbyRoomRPCServer;

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
	public void test() throws IOException, TimeoutException, JsonRpcException, InterruptedException {
		LobbyRoomRPCServer rpcServer = new LobbyRoomRPCServer();
		new Thread(rpcServer).start();
		Assert.assertNotNull(c.getExchanges().stream().filter(q -> q.getName().equals(LobbyRoomRPCServer.EXCHANGE_NAME)));
		Assert.assertNotNull(c.getBindings().stream().filter(b -> b.getRoutingKey().equals(LobbyRoomRPCServer.BINDING_KEY)));
		// Id of the entities
		String userId1 = "User1";
		String userId2 = "User2";
		String userId3 = "User3";
		String groupId1 = "Group1";
		String groupId2 = "Group2";
		// Group creation
		LobbyRoomRPCClient rpcClient1 = new LobbyRoomRPCClient();
		// User1 creates Group1
		String url1 = rpcClient1.createGroupAndJoinIt(groupId1, userId1);
		Assert.assertEquals("amqp://localhost",url1);
		//User2 creates Group2
		LobbyRoomRPCClient rpcClient2 = new LobbyRoomRPCClient();
		String url2 = rpcClient2.createGroupAndJoinIt(groupId2, userId2);
		Assert.assertEquals("amqp://localhost",url2);
		//Group joining
		LobbyRoomRPCClient rpcClient3 = new LobbyRoomRPCClient();
		//User3 joins Group1
		String url3 = rpcClient3.createGroupAndJoinIt(groupId1, userId3);
		Assert.assertEquals("amqp://localhost",url3);
		
		Thread.sleep(5000); // wait for consume to read the message
		rpcServer.close();
		rpcClient1.close();
		rpcClient2.close();
		rpcClient3.close();
		
	}
	
	@AfterClass
	public static void tearDown() throws InterruptedException, IOException {
		new ProcessBuilder("rabbitmqctl", "stop_app").inheritIO().start().waitFor();
		new ProcessBuilder("rabbitmqctl", "stop").inheritIO().start().waitFor();
	}
}
