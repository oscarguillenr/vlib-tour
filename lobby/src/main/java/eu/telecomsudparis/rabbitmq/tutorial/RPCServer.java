package eu.telecomsudparis.rabbitmq.tutorial;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.tools.jsonrpc.JsonRpcServer;

/**
 * The RPC server.
 */
public class RPCServer implements Runnable {
	/**
	 * the name of the queue.
	 */
	public static final String LOBBY_QUEUE_NAME = "lobby-queue";
	/**
	 * the name of the exchange.
	 */
	public static final String EXCHANGE_NAME = "lobby-queue";
	/**
	 * the binding key of the RPC server for receiving the RPC calls.
	 */
	public static final String BINDING_KEY = "vlobby";
	/**
	 * the connection to the RabbitMQ broker.
	 */
	private Connection connection;
	/**
	 * the channel for consuming and producing.
	 */
	private Channel channel;
	/**
	 * the RabbitMQ JSON RPC server.
	 */
	private JsonRpcServer rpcServer;

	/**
	 * default constructor of the RPC server.
	 * 
	 * @throws IOException
	 *             the communication problems.
	 * @throws TimeoutException
	 *             broker to long to connect to.
	 */
	public RPCServer() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, BINDING_KEY);
		Boolean durable = true;
		channel.queueDeclare(LOBBY_QUEUE_NAME, durable, false, false, null);
		//channel.confirmSelect(); OPTIONAL
		channel.queueBind(LOBBY_QUEUE_NAME, EXCHANGE_NAME, BINDING_KEY);
		// Construct a server that talks to the outside world using the
		// given channel and queue name.
		rpcServer = new JsonRpcServer(channel, LOBBY_QUEUE_NAME, this.getClass(), this);
	}

	/**  
	 *	Creates a group for a tour instance and joins it.		
	 *	
	 * @param groupId id of the user.
	 * @param userId id of the participant.
	 * 
	 * @return an URL, which is used to connect to the infrastructure for 
	 * 		    the group communication dedicated to the visit. 
	 *
	 */
	public String createGroupAndJoinIt(final String groupId, final String userId) {
		//ConnectionFactory factory = new ConnectionFactory();
		//factory.setHost("localhost");
		//connection = factory.newConnection();
		//factory.setUri("amqp://localhost");
		//channel = connection.createChannel();
		//channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		//Boolean durable = true;
		//channel.queueDeclare(LOBBY_QUEUE_NAME, durable, false, false, null);
		//channel.confirmSelect(); OPTIONAL
		//channel.queueBind(LOBBY_QUEUE_NAME, EXCHANGE_NAME, BINDING_KEY);

		return "URL1 : " + groupId + " " + userId; 
	}

	/** 
	 * Joins a group for a tour instance, that is a visit. 
	 * 
	 * @param groupId id of the group.
	 * @param userId id of the participant.
	 *
	 * @return returns an URL, which is used to connect to the infrastructure 
	 * 		    for the dedicated group communication. 
	 */
	public String joinAGroup(final String groupId, final String userId) {
		return "URL2 : " + groupId + " " + userId; 
	}


	@Override
	public void run() {
		try {
			rpcServer.mainloop();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		if (rpcServer != null) {
			rpcServer.terminateMainloop();
			rpcServer.close();
		}
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
	 *            there is no command line arguments.
	 * @throws Exception
	 *             communication problem with the broker.
	 */
	public static void main(final String[] argv) throws Exception {
		RPCServer rpcServer = new RPCServer();
		rpcServer.run();
	}
}
