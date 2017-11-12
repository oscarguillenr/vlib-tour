package vlibtour.lobbyroom;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.tools.jsonrpc.JsonRpcClient;
import com.rabbitmq.tools.jsonrpc.JsonRpcException;

/**
 * The RPC client.
 */
public class LobbyRoomRPCClient {
	/**
	 * the connection to the RabbitMQ broker.
	 */
	private Connection connection;
	/**
	 * the channel for producing and consuming.
	 */
	private Channel channel;
	/**
	 * the RabbitMQ JSON RPC client.
	 */
	private JsonRpcClient jsonRpcClient;
	/**
	 * the Fibonacci service.
	 */
	private LobbyRoomService client;

	/**
	 * default constructor of the RPC client.
	 * 
	 * @throws IOException
	 *             the communication problems.
	 * @throws TimeoutException
	 *             broker to long to connect to.
	 * @throws JsonRpcException
	 *             JSON problem in marshaling or un-marshaling.
	 */
	public LobbyRoomRPCClient() throws IOException, TimeoutException, JsonRpcException {
		// Create a new connection for the client		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();
		
		channel.exchangeDeclare(LobbyRoomRPCServer.EXCHANGE_NAME, "direct");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, LobbyRoomRPCServer.EXCHANGE_NAME, LobbyRoomRPCServer.BINDING_KEY);

		jsonRpcClient = new JsonRpcClient(channel, LobbyRoomRPCServer.EXCHANGE_NAME, LobbyRoomRPCServer.BINDING_KEY);
		client = (LobbyRoomService) jsonRpcClient.createProxy(LobbyRoomService.class);
	}
	
	/**
	 * calls the createGroupAndJointIt service.
	 * 
	 * @param groupId id of the group.
	 * @param userId id of the participant.
	 * @return an URL, which is used to connect to the infrastructure for 
	 * 		   the group communication dedicated to the visit. 
	 */
	public String createGroupAndJoinIt(final String groupId, final String userId) {
		System.out.println(" [x] " + userId + " : Requesting creation of group (" + groupId + ")");
		String url = client.createGroupAndJoinIt(groupId, userId);
		System.out.println(" [.] Got '" + url + "'");
		return url;
	}

	/**
	 * calls the joinAGroup service.
	 * 
	 * @param groupId id of the group.
	 * @param userId id of the participant.
	 * @return an URL, which is used to connect to the infrastructure for 
	 * 		   the group communication dedicated to the visit. 
	 */

	public String joinAGroup(final String groupId, final String userId) {
		System.out.println(" [x] " + userId + " : Requesting joining the group (" + groupId + ")");
		String url = client.joinAGroup(groupId, userId);
		System.out.println(" [.] Got '" + url + "'");
		return url;
	}

	/**
	 * closes the JSON RPC client, the channel and the connection with the broker.
	 * 
	 * @throws IOException
	 *             communication problem.
	 * @throws TimeoutException
	 *             broker to long to communicate with.
	 */
	public void close() throws IOException, TimeoutException {
		jsonRpcClient.close();
		if (channel != null) {
			channel.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	/**
	 * the main method of the example.
	 * 
	 * @param argv
	 *            the command line argument is the number of the call.
	 * @throws Exception
	 *             communication problem with the broker.
	 */
	public static void main(final String[] argv) throws Exception {
		String groupId = argv[0];
		String userId = argv[1];
		LobbyRoomRPCClient rpcClient = new LobbyRoomRPCClient();
		rpcClient.createGroupAndJoinIt(groupId, userId);
		rpcClient.joinAGroup(groupId, "2");
		rpcClient.close();
	}
}
