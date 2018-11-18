package assignment1;

import java.io.IOException;
import java.util.logging.LogManager;

/**
 * @author sohilshrestha
 *
 */
public class P3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				LogManager.getLogManager().reset();
		Process p3;
		try {
			p3 = new Process(3, 1236, "224.0.0.1");
			p3.startProcessThread();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
