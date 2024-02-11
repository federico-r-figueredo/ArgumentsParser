package utilities.arguments.parser.v3;

import java.util.Iterator;

public class BooleanArgumentMarshaller implements ArgumentMarshaller {
	private boolean booleanValue = false;
	
	@Override
	public void set(Iterator<String> currentArgument) throws ArgumentsException {
		this.booleanValue = true;
	}

	public static boolean getValue(ArgumentMarshaller argumentMarshaller) {
		if (argumentMarshaller != null && argumentMarshaller instanceof BooleanArgumentMarshaller) {
			return ((BooleanArgumentMarshaller) argumentMarshaller).booleanValue;
		} else {
			return false;
		}
	}
}
