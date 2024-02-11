package utilities.arguments.parser.v3;

import java.util.Iterator;

public class StringArgumentMarshaller implements ArgumentMarshaller {
	private String stringValue = "";
	
	@Override
	public void set(Iterator<String> currentArgument) throws ArgumentsException {
		try {
			this.stringValue = currentArgument.next();
		} catch (Exception e) {
			throw new ArgumentsException(ErrorCode.MISSING_STRING);
		}
	}

	public static String getValue(ArgumentMarshaller argumentMarshaller) {
		if (argumentMarshaller != null && argumentMarshaller instanceof StringArgumentMarshaller) {
			return ((StringArgumentMarshaller) argumentMarshaller).stringValue;
		} else {
			return "";
		}
	}
}
