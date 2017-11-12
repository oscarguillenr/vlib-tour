// CHECKSTYLE:OFF
package eu.telecomsudparis.rabbitmq.tutorial;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rabbitmq.http.client.Client;
import com.rabbitmq.tools.jsonrpc.JsonRpcException;

public class TestScenario3 {
	
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
		RPCServer rpcServer = new RPCServer();
		new Thread(rpcServer).start();
		Assert.assertNotNull(c.getExchanges().stream().filter(q -> q.getName().equals(RPCServer.EXCHANGE_NAME)));
		Assert.assertNotNull(c.getBindings().stream().filter(b -> b.getRoutingKey().equals(RPCServer.BINDING_KEY)));
		RPCClient rpcClient8 = new RPCClient();
		Assert.assertEquals("URL1 : 1 1",rpcClient8.createGroupAndJoinIt("1", "1"));
		RPCClient rpcClient9 = new RPCClient();
		Assert.assertEquals("URL1 : 2 1",rpcClient9.createGroupAndJoinIt("2", "1"));
		RPCClient rpcClient10 = new RPCClient();
		Assert.assertEquals("URL2 : 1 1",rpcClient10.joinAGroup("1", "1"));
		RPCClient rpcClient11 = new RPCClient();
		Assert.assertEquals("URL2 : 1 2",rpcClient11.joinAGroup("1", "2"));

		Thread.sleep(5000); // wait for consume to read the message
		rpcServer.close();
		rpcClient8.close();
		rpcClient9.close();
		rpcClient10.close();
		rpcClient11.close();
	}
	
	@AfterClass
	public static void tearDown() throws InterruptedException, IOException {
		new ProcessBuilder("rabbitmqctl", "stop_app").inheritIO().start().waitFor();
		new ProcessBuilder("rabbitmqctl", "stop").inheritIO().start().waitFor();
	}
}
