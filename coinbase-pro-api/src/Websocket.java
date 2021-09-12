import java.net.URI;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class Websocket extends Endpoint{
	private final String url;
	private final Logger logger = Logger.getLogger("Websocket");
	private final MessageHandler.Whole<String> messageHandler;
	
	private Session session;

	public Websocket(String url, MessageHandler.Whole<String> messageHandler) {
		super();
		this.url = url;
		this.messageHandler = messageHandler;
	}

	@Override
	public void onOpen(final Session session, final EndpointConfig arg1) {
		this.session = session;
		logger.info("Session opened");
		session.addMessageHandler(messageHandler);
	}

	@Override
	public void onError(final Session session, final Throwable thr) {
		session.addMessageHandler(messageHandler);
	}

	@Override
	public void onClose(final Session session, final CloseReason closeReason) {
		session.addMessageHandler(messageHandler);
	}

	public void connect() throws Exception {
		logger.info("Connecting to: " + url);
		try {
			WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
			webSocketContainer.setDefaultMaxBinaryMessageBufferSize(1024 * 1024);
			webSocketContainer.setDefaultMaxTextMessageBufferSize(1024 * 1024);
			webSocketContainer.connectToServer(this, new URI(url));
		} catch (Exception e) {
			logger.severe("Failed to connect");
		}
	}

	public void sendMessage(final String message) {
		try {
			logger.info("Sending " + message);
			session.getAsyncRemote().sendText(message);
		} catch (Exception e) {
			logger.severe("Failed to send");
		}
	}
}
