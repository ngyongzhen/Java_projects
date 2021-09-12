package pojo;

/***
 * https://docs.pro.coinbase.com/#the-heartbeat-channel
 * @author Yongzhen
 *
 */
public class L2UpdateMessage extends BaseMessage {
	public final static int SIDE_INDEX = 0;
	public final static int PRICE_INDEX = 1;
	public final static int SIZE_INDEX = 2;
	
	private String[][] changes;

	public String[][] getChanges() {
		return changes;
	}
}
