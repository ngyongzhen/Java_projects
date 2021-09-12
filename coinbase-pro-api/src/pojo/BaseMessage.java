package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "type")
@JsonSubTypes({
	 @Type(value = HeartbeatMessage.class, name = "heartbeat"),
	 @Type(value = TickerMessage.class, name = "ticker"),
	 @Type(value = SubscriptionsMessage.class, name = "subscriptions"),
	 @Type(value = L2SnapshotMessage.class, name = "snapshot"),
	 @Type(value = L2UpdateMessage.class, name = "l2update")
})
public class BaseMessage {
	protected String type;
	private long sequence;
	private String time;

	@JsonProperty("product_id")
	private String productId;

	public String getType() {
		return type;
	}

	public long getSequence() {
		return sequence;
	}

	public String getTime() {
		return time;
	}

	public String getProductId() {
		return productId;
	}
}
