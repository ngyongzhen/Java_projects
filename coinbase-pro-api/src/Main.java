import java.util.ArrayList;
import java.util.List;

import tree.AsksOrderBook;
import tree.BidsOrderBook;
import tree.OrderBook;
import utils.Configuration;
import utils.Constants;

public class Main {
	private static final int ORDER_BOOK_LEVELS = Math.max(
			Configuration.getInstance().getPropertyValueAsInt(Constants.CONFIG_PROPERTY_ORDER_BOOK_LEVELS, 10), 10);
	
	public static void main(String[] args) throws Exception {
		OrderBook asksOrderBook = new AsksOrderBook(ORDER_BOOK_LEVELS);
		OrderBook bidsOrderBook = new BidsOrderBook(ORDER_BOOK_LEVELS);
		CoinbaseWebsocketMessageHandler coinbaseWebsocketMessageHandler = new CoinbaseWebsocketMessageHandler(asksOrderBook, bidsOrderBook);
		CoinbaseConnection coinbaseConnection = new CoinbaseConnection(coinbaseWebsocketMessageHandler);
		
		List<String> productIds = new ArrayList<String>();
		getProductIdsFromInput(productIds);
		coinbaseConnection.sendSubscriptionMessage(productIds);
		
		while (true) {
			
		}
	}
	
	private static void getProductIdsFromInput(List<String> productIds) {
		productIds.add(InputReader.read(Constants.INPUT_PROMPT_PRODUCT_ID));
	}
}