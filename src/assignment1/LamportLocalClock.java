package assignment1;
/**
 * @author sohilshrestha
 * Follows Lamport Algorithm 
 * For each process 
 * 
 */

public class LamportLocalClock {
	int localClock;
	public LamportLocalClock() {
		localClock = 0 ;
	}
	//Calls when the local event happens / Issue M  Step 1 Lamport
	public void incrementClock() {
		localClock++;
	}
	//Updates the local clock based on the comparing the timestamp of received messages
	public void compareTimeStamp(int receivedTimeStamp) {
		if(localClock<receivedTimeStamp)
			localClock = receivedTimeStamp+1; //delivers the message and updates the time stamp according to lamport Algorithm Step 3
		else
			localClock++;
	}
	
	//Returns localClock of a process
	public int getLocalClock() {
		return localClock;
	}

}
