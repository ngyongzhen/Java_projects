package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderPool {
	private final Logger logger = Logger.getLogger("OrderPool"); 
	private final List<Order> freeOrders;
	private int maxSize = 0;
	
	public OrderPool(int size) {
		freeOrders = new ArrayList<>(size);
		expandPool(size);
		this.maxSize = size;
	}
	
	/**
	 * Releases order back into pool for reuse
	 * @param order
	 */
	public void release(Order order) {
		freeOrders.add(order);
	}
	
	private void expandPool(int size) {
		logger.info("Expanding order pool size by: " + size);
		for(int i = 0; i < size; i++) {
			freeOrders.add(new Order());
		}
	}
	
	/**
	 * Gets a clean order from the pool
	 */
	public Order getOrder() {
		Order order = null;
		if(freeOrders.size() == 0) {
			logger.warning("No free order in pool");
			expandPool(maxSize);
		}
		order = freeOrders.remove(0);
		order.clear();
		return order;
	}
}
