package tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import pojo.L2SnapshotMessage;
import utils.Configuration;
import utils.Constants;
import utils.Utils;

/***
 * Binary tree using Orders as nodes.
 * 
 * @author Yongzhen
 *
 */
public abstract class OrderBook {
	final int displayCount =  Configuration.getInstance().getPropertyValueAsInt(Constants.CONFIG_PROPERTY_ORDER_BOOK_LEVELS_TO_DISPLAY, 10);
	Order root;
	// Within the order levels
	Order highest = null;
	Order lowest = null;
	int orderCount;
	int maxOrderCount;
	Set<Order> sortedOrders = new TreeSet<>();
	final ConcurrentMap<Double, Order> orderCache;

	private final OrderPool orderPool;
	private boolean hasUpdated = false;

	abstract boolean isWithinPriceScope(double price);

	abstract public void print();

	public OrderBook(int maxOrderCount) {
		root = null;
		this.maxOrderCount = maxOrderCount;
		this.orderPool = new OrderPool(maxOrderCount);
		this.orderCache = new ConcurrentHashMap<Double, Order>(maxOrderCount);
	}

	public void init(String[][] snapshot) {
		Order[] orders = constructInitArray(snapshot);
		highest = orders[0];
		lowest = orders[orders.length - 1];
		root = buildTree(orders, null, 0, orders.length - 1);
		orderCount = orders.length;
	}

	Order[] constructInitArray(String[][] snapshot) {
		Order[] orders = new Order[maxOrderCount];
		for (int i = 0; i < maxOrderCount; i++) {
			orders[i] = orderPool.getOrder();
			orders[i].setPrice(Double.parseDouble(snapshot[i][L2SnapshotMessage.PRICE_INDEX]));
			orders[i].setSize(Double.parseDouble(snapshot[i][L2SnapshotMessage.SIZE_INDEX]));
			sortedOrders.add(orders[i]);
		}
		return orders;
	}
	
	public synchronized boolean onUpdate(double price, double size) {
		hasUpdated = false;
		if (!isCacheHit(price, size)) {
			// Note that if it didn't hit cache, it must be an insertion
			if (size > 0) {
				// Only act on this if its within scope
				if (isWithinPriceScope(price)) {
					root = add(root, null, price, size);
					hasUpdated = true;
				}
			}
		}
		return hasUpdated;
	}

	private boolean isCacheHit(double price, double size) {
		Order order = orderCache.get(price);
		if (order != null) {
			if (size == 0) {
				orderCache.remove(price);
				delete(order);
				hasUpdated = true;
			} else {
				order.setSize(size);
				hasUpdated = true;
			}
			return true;
		}
		return false;
	}

	private Order buildTree(Order[] orders, Order parent, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;
		Order order = orders[mid];
		order.setParent(parent);
		order.setLeft(buildTree(orders, order, start, mid - 1));
		order.setRight(buildTree(orders, order, mid + 1, end));
		orderCache.put(order.getPrice(), order);
		return order;
	}

	/**
	 * Recursive function.
	 * 
	 * @param current
	 * @param price
	 * @param size
	 * @return
	 */
	private Order add(Order current, Order parent, double price, double size) {
		if(current == null) {
			// Base case
			Order newOrder = orderPool.getOrder();
			newOrder.setPrice(price);
			newOrder.setSize(size);
			newOrder.setParent(parent);
			orderCount++;
			orderCache.put(price, newOrder);
			sortedOrders.add(newOrder);
			if(price < lowest.getPrice()) {
				lowest = newOrder;
			} else if (price > highest.getPrice()) {
				highest = newOrder;
			}
			return newOrder;
		} else if (price < current.getPrice()) {
			current.setLeft(add(current.getLeft(), current, price, size));
		} else if (price > current.getPrice()) {
			current.setRight(add(current.getRight(), current, price, size));
		} else {
			current.setPrice(price);
			current.setSize(size);
		}
		return current;
	}

	private void delete(Order order) {
		if (order.getLeft() == null && order.getRight() == null) {
			setNewChildForParent(order.getParent(), order, null);
		}
		// If only one child
		else if (order.getLeft() == null) {
			setNewChildForParent(order.getParent(), order, order.getRight());
		} else if (order.getRight() == null) {
			setNewChildForParent(order.getParent(), order, order.getLeft());
		} else {
			// If two children, find the smallest price from the right subtree
			Order smallestChild = findSmallest(order.getRight());
			relinkParent(order, smallestChild);
		}
		orderCount--;
		orderPool.release(order);
		sortedOrders.remove(order);
	}

	private void relinkParent(Order originalChild, Order newChild) {
		Order orderParent = originalChild.getParent();
		newChild.setParent(orderParent);
		setNewChildForParent(orderParent, originalChild, newChild);
		newChild.setLeft(originalChild.getLeft());
	}

	private void setNewChildForParent(Order parent, Order originalChild, Order newChild) {
		if (parent != null) {
			// Original child is not a root node
			// Find which side does this child belong to
			if (parent.getLeft() != null && parent.getLeft().getPrice() == originalChild.getPrice()) {
				parent.setLeft(newChild);
			} else {
				parent.setRight(newChild);
			}
		}
	}

	/**
	 * Recursive function.
	 * 
	 * @param root
	 * @return
	 */
	private Order findSmallest(Order root) {
		if (root.getLeft() == null) {
			// Base case (found the smallest element)
			return root;
		}
		return findSmallest(root.getLeft());
	}
}
