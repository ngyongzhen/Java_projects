package models;

public class Ask extends Order{
	public static final String SIDE = "sell";
	
	public Ask() {
		
	}
	public Ask(double price, double size) {
		super(price, size);
	}
}