package driver;

import utilities.arguments.parser.v3.ArgumentsException;
import utilities.arguments.parser.v3.ArgumentsParser;

public class Program {

	public static void main(String[] arguments) {
		try {
			ArgumentsParser argumentParser = new ArgumentsParser("l,p#,d*", arguments);
			boolean logging = argumentParser.getBoolean('l');
			int port = argumentParser.getInteger('p');
			String directory = argumentParser.getString('d');
			executeApplication(logging, port, directory);
		} catch (ArgumentsException e) {
			System.out.printf("Argument error: %s\n", e.errorMessage());
		}
	}

	private static void executeApplication(boolean logging, int port, String directory) {
		System.out.printf("logging is %s, port:%d, directory:%s\n",logging, port, directory);
	}

}
