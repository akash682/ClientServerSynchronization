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
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogManager.getLogManager().reset();
		try {
			Process p1 = new Process(1, 1234, "224.0.0.1");
			p1.startProcessThread();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
