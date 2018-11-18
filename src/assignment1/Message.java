/**
 * 
 */
package assignment1;

/**
 * @author sohilshrestha
 * Message Model File 
 * Constructs a message 
 * 
 */
public class Message {
	private int senderPID;
	private int senderPort;
	
	private int msgType;
	private String data;
	
	private int logicalClockValue;
	private boolean deliverable;
	
	public Message( int senderPID,int senderPort, String data,int logicalClockValue) {
		this.senderPID = senderPID;
		this.senderPort = senderPort; this.data = data;
		this.logicalClockValue = logicalClockValue;
		deliverable = false;
	}
	public int getSenderPID() {
		return senderPID;
	}

	public int getSenderPort() {
		return senderPort;
	}

	public int getMsgType() {
		return msgType;
	}

	public String getData() {
		return data;
	}

	public int getLogicalClockValue() {
		return logicalClockValue;
	}

	public boolean isDeliverable() {
		return deliverable;
	}
	public void setDeliverable(boolean deliverable) {
		this.deliverable = deliverable;
	}
	
}

