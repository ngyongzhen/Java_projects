import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class InputReader {
	private static final Logger logger = Logger.getLogger("InputReader");
	
	public static String read(final String prompt) {
		System.out.println(prompt);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String name = "";
		try {
			name = reader.readLine();
			reader.close();
		} catch (IOException e) {
			logger.warning("IOException occurred");
		}
		return name;
	}
}
