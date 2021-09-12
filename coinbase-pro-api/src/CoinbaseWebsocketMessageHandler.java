import java.util.concurrent.locks.ReentrantLock;

import javax.websocket.MessageHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.BaseMessage;
import pojo.L2SnapshotMessage;
import pojo.L2UpdateMessage;
import tree.OrderBook;
import utils.Constants;

public class CoinbaseWebsocketMessageHandler implements MessageHandler.Whole<String> {
	private final ObjectMapper mapper = new ObjectMapper();
	private final OrderBook asksOrderBook;
	private final OrderBook bidsOrderBook;
	private final ReentrantLock printLock;

	public CoinbaseWebsocketMessageHandler(OrderBook asksOrderBook, OrderBook bidsOrderBook) {
		this.asksOrderBook = asksOrderBook;
		this.bidsOrderBook = bidsOrderBook;
		this.printLock = new ReentrantLock();
	}

	@Override
	public void onMessage(final String messageStr) {
		printLock.lock();
		try {
			BaseMessage message = mapper.readValue(messageStr, BaseMessage.class);
			if (message instanceof L2SnapshotMessage) {
				L2SnapshotMessage snapshot = (L2SnapshotMessage) message;
				asksOrderBook.init(snapshot.getAsks());
				bidsOrderBook.init(snapshot.getBids());
				printOrderBooks();
			} else if (message instanceof L2UpdateMessage) {
				boolean hasOrderBookUpdated = false;
				L2UpdateMessage update = (L2UpdateMessage) message;
				for (int i = 0; i < update.getChanges().length; i++) {
					if (Constants.L2_UPDATE_MESSAGE_SELL_SIDE
							.equals(update.getChanges()[i][L2UpdateMessage.SIDE_INDEX])) {
						if (asksOrderBook.onUpdate(
								Double.parseDouble(update.getChanges()[i][L2UpdateMessage.PRICE_INDEX]),
								Double.parseDouble(update.getChanges()[i][L2UpdateMessage.SIZE_INDEX]))) {
							hasOrderBookUpdated = true;
						}
					} else if (Constants.L2_UPDATE_MESSAGE_BUY_SIDE
							.equals(update.getChanges()[i][L2UpdateMessage.SIDE_INDEX])) {
						if (bidsOrderBook.onUpdate(
								Double.parseDouble(update.getChanges()[i][L2UpdateMessage.PRICE_INDEX]),
								Double.parseDouble(update.getChanges()[i][L2UpdateMessage.SIZE_INDEX]))) {
							hasOrderBookUpdated = true;
						}
					}
				}
				if(hasOrderBookUpdated) {
					printOrderBooks();
				}
			}
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		printLock.unlock();
	}

	private void printOrderBooks() {
		System.out.println("\n\n\n\n\n");
		asksOrderBook.print();
		System.out.println("------------------");
		bidsOrderBook.print();
	}
}
