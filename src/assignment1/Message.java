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
	private int mID;
	private int senderPID;
	private int senderPort;
	

	private String recieverAddress;
	private int recieverPort;
	private int msgType;
	private String data;
	
	private int logicalClockValue;
	private boolean deliverable;
	public int getmID() {
		return mID;
	}
	public void setmID(int mID) {
		this.mID = mID;
	}
	public int getSenderPID() {
		return senderPID;
	}
	public void setSenderPID(int senderPID) {
		this.senderPID = senderPID;
	}
	public int getSenderPort() {
		return senderPort;
	}
	public void setSenderPort(int senderPort) {
		this.senderPort = senderPort;
	}
	public String getRecieverAddress() {
		return recieverAddress;
	}
	public void setRecieverAddress(String recieverAddress) {
		this.recieverAddress = recieverAddress;
	}
	public int getRecieverPort() {
		return recieverPort;
	}
	public void setRecieverPort(int recieverPort) {
		this.recieverPort = recieverPort;
	}
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getLogicalClockValue() {
		return logicalClockValue;
	}
	public void setLogicalClockValue(int logicalClockValue) {
		this.logicalClockValue = logicalClockValue;
	}
	public boolean isDeliverable() {
		return deliverable;
	}
	public void setDeliverable(boolean deliverable) {
		this.deliverable = deliverable;
	}
	
}

