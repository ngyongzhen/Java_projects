package tree;

import java.util.Collections;

import utils.Utils;

public class BidsOrderBook extends OrderBook {

	private int count = 0;

	public BidsOrderBook(int maxOrderCount) {
		super(maxOrderCount);
	}

	@Override
	Order[] constructInitArray(String[][] snapshot) {
		Order[] orders = super.constructInitArray(snapshot);
		// Since bids come in descending order, we need to reverse it for constructing
		// tree
		Utils.reverseArray(orders);
		return orders;
	}

	@Override
	boolean isWithinPriceScope(double price) {
		return price >= lowest.getPrice() * 0.2;
	}

	@Override
	public synchronized void print() {
		count = 0;
		sortedOrders.stream().sorted(Collections.reverseOrder()).forEach(order -> {
			if (count <= 10) {
				System.out.println("BID Price [" + order.getPrice() + "] Quantity: [" + order.getSize() + "]");
				count++;
			}
		});
	}
}
