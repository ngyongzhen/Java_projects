package tree;

import java.util.Stack;

public class AsksOrderBook extends OrderBook {
	
	private int count = 0;
	
	public AsksOrderBook(int maxOrderCount) {
		super(maxOrderCount);
	}

	@Override
	boolean isWithinPriceScope(double price) {
		return price <= highest.getPrice() * 5;
	}

	@Override
	public void print() {
		// Use stack to reverse the print order
		Stack<Order> orders = new Stack<>();
		count = 0;
		sortedOrders.stream().forEach(order -> {
			if(count <= 10) {
				orders.add(order);
				count++;
			}
		});
		while (!orders.isEmpty()) {
			Order order = orders.pop();
			System.out.println("ASK Price [" + order.getPrice() + "] Quantity: [" + order.getSize() + "]");
		}
	}
}
