import java.util.List;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.Configuration;
import utils.Constants;

public class CoinbaseConnection {
	private final String url = Configuration.getInstance()
			.getPropertyValue(Constants.CONFIG_PROPERTY_COINBASE_WEBSOCKET_URL, "");
	private final Websocket websocket;
	private boolean isConnected = false;

	private final Logger logger = Logger.getLogger("CoinbaseConnection");

	public CoinbaseConnection(CoinbaseWebsocketMessageHandler handler) {
		websocket = new Websocket(url, handler);
		logger.info("Attempting connection");
		try {
			websocket.connect();
			isConnected = true;
		} catch (Exception e) {
			logger.warning("Failed to open websocket connection to: " + url);
			isConnected = false;
		}
	}

	public void sendSubscriptionMessage(final List<String> productIds) {
		sendMessage(createSubscriptionJson(productIds).toString());
	}

	public boolean isConnected() {
		return isConnected;
	}

	private void sendMessage(final String message) {
		websocket.sendMessage(message);
	}

	private JSONObject createSubscriptionJson(final List<String> productIds) {
		JSONObject json = new JSONObject();
		json.put("type", "subscribe");
		JSONArray productIdsJsonArray = new JSONArray(productIds);
		json.put("product_ids", productIdsJsonArray);
		addChannelsToJson(json, productIdsJsonArray);
		return json;
	}

	private void addChannelsToJson(final JSONObject json, final JSONArray productIds) {
		json.append("channels", "level2");
		json.append("channels", "heartbeat");
		JSONObject ticker = new JSONObject();
		ticker.put("name", "ticker");
		ticker.put("product_ids", productIds);
		json.append("channels", ticker);
	}
}
