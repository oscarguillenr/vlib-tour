package vlibtour.communicationsystem;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.tools.jsonrpc.JsonRpcException;

import java.net.URISyntaxException;

/**
 * The RPC client.
 */
public class CommunicationSystemParticipant {

	/**
	 * the queue of the new participant.
	 */
	private final String clientQueueName;
	/**
	 * the binding key of the client.
	 */
	private final String clientBindingKey;
	/**
	 * the id of the participant.
	 */
	private final String clientUserId;

	/**
	 * the tour of the participant.
	 */
	private final String clientTourId;

	/**
	 * the name of the exchange.
	 */
	private final String exchangeName;

	/**
	 * the connection to the RabbitMQ broker.
	 */
	private Connection connection;
	/**
	 * the channel for producing and consuming.
	 */
	private Channel channel;

	
	/**
	 * default constructor of the RPC client.
	 * 
	 * @param groupId of the communication system
	 * @param userId creator of the group
	 * @param tourId tour id of the group
	 * @param uriGroup uri of the connection
	 *
	 * @throws IOException
	 *             the communication problems.
	 * @throws TimeoutException
	 *             broker to long to connect to.
	 * @throws JsonRpcException
	 *             JSON problem in marshaling or un-marshaling.
	 * @throws URISyntaxException error with the URL of the factory.
	 * @throws NoSuchAlgorithmException error finding the algorithm.
	 * @throws KeyManagementException binding error.
	 */
	public CommunicationSystemParticipant(final String groupId, final String userId, final String tourId, final String uriGroup) 
			throws IOException, TimeoutException, JsonRpcException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {

		// Create a new connection for the participant		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri(uriGroup);
		connection = factory.newConnection();
		channel = connection.createChannel();

		//Exchange declaration
		exchangeName = CommunicationSystem.getExchangeName();
		channel.exchangeDeclare(exchangeName, "topic");

		// Creation of a queue of name visit-management
		boolean durable = true;
		clientUserId = userId;
		clientTourId = tourId;
		clientQueueName = clientTourId + "_" + clientUserId;
		channel.queueDeclare(clientQueueName, durable, false, false, null);

		// Binds the queue of the system server to the topic exchange
		// to receive all the messages and those specific to the server.
		clientBindingKey = "*." + clientQueueName + ".#";
		channel.queueBind(clientQueueName, exchangeName, CommunicationSystem.ALL_MESS_KEY);
		channel.queueBind(clientQueueName, exchangeName, clientBindingKey);

	}
	
	/**  
	 * Send a message to the group.		
	 *
	 * @param receiverId of the participant that receives the message.
	 *		all for message broadcast.
	 * 	    userId for a specific user.
	 * @param message to be publish
	 * @throws IOException error
	 */
	void publishMessage(final String receiverId, final String message) throws IOException {
		String sendingKey;		
		if (receiverId.equals("all")) {
			sendingKey = clientUserId + "." + receiverId;
		} else {
		 	sendingKey = clientUserId + "." + clientTourId + "_" + receiverId;
		}
		channel.basicPublish(exchangeName, sendingKey, null, message.getBytes());
		System.out.println(" [x] " + clientUserId + " sends to " + receiverId + " : '" + message + "'");
	}

	/**  
	 * Receives messages.		
	 *
	 * @throws IOException error.  
	 */
	void receiveMessage() throws IOException {
		System.out.println(" [*] " + clientUserId + " waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
      	@Override
      	public void handleDelivery(final String consumerTag, final Envelope envelope, final AMQP.BasicProperties properties, final byte[] body) throws IOException {
        	String message = new String(body, "UTF-8");
        	System.out.println(" [x] " + clientUserId + " received '" + envelope.getRoutingKey() + "':'" + message + "'");
		}
    	};
    	channel.basicConsume(clientQueueName, true, consumer);
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
		String tourId = argv[2];
		String uri = "amqp://localhost";
		CommunicationSystemParticipant communicationClient = new CommunicationSystemParticipant(groupId, userId, tourId, uri);
		communicationClient.close();
	}
}
