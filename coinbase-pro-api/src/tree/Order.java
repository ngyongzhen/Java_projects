package tree;

/***
 * Order Book tree doubly-linked node.
 * Ordered by price where left < current < right.
 * @author Yongzhen
 *
 */
public class Order implements Comparable<Order>{
	private Order parent;
	private Order left;
	private Order right;
	private double price;
	private double size;
	
	public Order() {
		clear();
	}
	
	public Order getParent() {
		return parent;
	}
	
	public void setParent(final Order parent) {
		this.parent = parent;
	}
	
	public Order getLeft() {
		return left;
	}
	
	public void setLeft(final Order left) {
		this.left = left;
	}
	
	public Order getRight() {
		return right;
	}
	
	public void setRight(final Order right) {
		this.right = right;
	}
	
	public double getSize() {
		return size;
	}
	
	public void setSize(final double size) {
		this.size = size;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(final double price) {
		this.price = price;
	}
	
	public void clear() {
		parent = null;
		left = null;
		right = null;
		price = 0;
		size = 0;
	}

	@Override
	public int compareTo(Order o) {
		if(price < o.getPrice()) {
			return -1;
		}
		if(price > o.getPrice()) {
			return 1;
		}
		return 0;
	}
}
