package utilities.arguments.parser.v3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntegerArgumentMarshaller implements ArgumentMarshaller {
	private int integerValue = 0;
	
	@Override
	public void set(Iterator<String> currentArgument) throws ArgumentsException {
		String parameter = null;
		try {
			parameter = currentArgument.next();
			this.integerValue = Integer.parseInt(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgumentsException(ErrorCode.MISSING_INTEGER);
		} catch (NumberFormatException e) {
			throw new ArgumentsException(ErrorCode.INVALID_INTEGER, parameter);
		}
	}

	public static int getValue(ArgumentMarshaller argumentMarshaller) {
		if (argumentMarshaller != null && argumentMarshaller instanceof IntegerArgumentMarshaller) {
			return ((IntegerArgumentMarshaller) argumentMarshaller).integerValue;
		} else {
			return 0;
		}
	}
}
