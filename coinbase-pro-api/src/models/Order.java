package models;

public abstract class Order {
	private double price;
	private double size;
	
	Order(){		
	}
	
	Order(final double price, final double size){
		this.price = price;
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public double getSize() {
		return size;
	}

	public void setSize(final double size) {
		this.size = size;
	}
	
	public void clear() {
		price = 0;
		size = 0;
	}
}
