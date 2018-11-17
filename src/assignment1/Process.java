/**
 * 
 */
package assignment1;

/**
 * @author sohilshrestha Process Model file
 */
public class Process {
	private int PID;
	private int PORT;
	private String IPaddress;

	public Process(int PID, int PORT, String IPaddress) {
		this.PID = PID;
		this.PORT = PORT;
		this.IPaddress = IPaddress;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public int getPORT() {
		return PORT;
	}

	public void setPORT(int pORT) {
		PORT = pORT;
	}

	public String getIPaddress() {
		return IPaddress;
	}

	public void setIPaddress(String iPaddress) {
		IPaddress = iPaddress;
	}

}
