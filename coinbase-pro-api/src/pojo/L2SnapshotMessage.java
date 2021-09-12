package pojo;

/***
 * https://docs.pro.coinbase.com/#the-level2-channel
 * 
 * @author Yongzhen
 *
 */
public class L2SnapshotMessage extends BaseMessage {
	public final static int PRICE_INDEX = 0;
	public final static int SIZE_INDEX = 1;

	private String[][] bids;
	private String[][] asks;

	public String[][] getBids() {
		return bids;
	}

	public String[][] getAsks() {
		return asks;
	}
}
