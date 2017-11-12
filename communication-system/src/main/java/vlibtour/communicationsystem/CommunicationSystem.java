package vlibtour.communicationsystem;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;

/**
 * The RPC server.
 */
public class CommunicationSystem {
	/**
	 * the name of the queue.
	 */
	//public static final String SYS_QUEUE_NAME = "visit-management";
	/**
	 * the name of the exchange.
	 */
	private static String exchangeName;
	/**
	 * the binding key of the RPC server for receiving the RPC calls.
	 */
	public static final String ALL_MESS_KEY = "*.all.#";

	/**
	 * the binding key of the RPC server for receiving the RPC calls.
	 */
	public static final String SERVER_MESS_KEY = "*.gameserver.#";
	
	/**
	 * URL used to connecto to the infrastructure.
	 */
	private final String uriGroup = "amqp://localhost";

	/**
	 * the connection to the RabbitMQ broker.
	 */
	private Connection connection;
	/**
	 * the channel for consuming and producing.
	 */
	private Channel channel;

	/**
	 * default constructor of the RPC server.
	 * 
	 * @param groupId of the communication system
	 * @param userId creator of the group
	 * 
	 * @throws IOException
	 *             the communication problems.
	 * @throws TimeoutException
	 *             broker to long to connect to.
	 * @throws URISyntaxException error building the URL.
	 * @throws NoSuchAlgorithmException algorithm error.
	 * @throws KeyManagementException binding error.
	 */
	public CommunicationSystem(final String groupId, final String userId) throws IOException, TimeoutException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {

		// Establishing the connection
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUri(uriGroup);
		connection = factory.newConnection();
		channel = connection.createChannel();
		
		// Creation of an exchange of type direct
		exchangeName = groupId + "_" + userId;
		channel.exchangeDeclare(exchangeName, "topic");

	}
	
	/**
	 * Returns the name of the exchange.
	 * @return the name of the exchange.
	 */
	public static String getExchangeName() {
		return exchangeName;	
	}

	/**
	 * Returns the uri of the group.
	 * @return the uri of the group.
	 */
	public String getURIGroup() {
		return uriGroup;	
	}

	/**
	 * closes the channel and the connection with the broker.
	 * 
	 * @throws IOException
	 *             communication problem.
	 * @throws TimeoutException
	 *             broker to long to communicate with.
	 */
	public void close() throws IOException, TimeoutException {
		if (channel != null) {
			channel.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
}
