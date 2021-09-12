package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/***
 * https://docs.pro.coinbase.com/#the-heartbeat-channel
 * @author Yongzhen
 *
 */
public class HeartbeatMessage extends BaseMessage {
	private String name;
	
	@JsonProperty("last_trade_id")
	private long lastTradeId;

	public long getLastTradeId() {
		return lastTradeId;
	}
}
