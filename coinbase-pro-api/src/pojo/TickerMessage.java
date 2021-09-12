package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/***
 * https://docs.pro.coinbase.com/#the-ticker-channel
 * @author Yongzhen
 *
 */
public class TickerMessage extends BaseMessage{
	@JsonProperty("trade_id")
	private String tradeId;
	private String price;
	private String side;
	
	@JsonProperty("last_size")
	private String lastSize;
	
	@JsonProperty("best_bid")
	private String bestBid;
	
	@JsonProperty("best_ask")
	private String bestAsk;

	public String getTradeId() {
		return tradeId;
	}

	public String getPrice() {
		return price;
	}

	public String getSide() {
		return side;
	}

	public String getLastSize() {
		return lastSize;
	}

	public String getBestBid() {
		return bestBid;
	}

	public String getBestAsk() {
		return bestAsk;
	}
	
	
}
