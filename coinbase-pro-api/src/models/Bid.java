package models;

public class Bid extends Order{
	public static final String SIDE = "buy";
	
	public Bid() {
		
	}
	public Bid(double price, double size) {
		super(price, size);
	}
}
