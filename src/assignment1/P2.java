/**
 * 
 */
package assignment1;

import java.io.IOException;
import java.util.logging.LogManager;

/**
 * @author sohilshrestha
 *
 */
public class P2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		LogManager.getLogManager().reset();
		Process p2;
		try {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p2 = new Process(2, 1235, "224.0.0.1");

			p2.startProcessThread();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
