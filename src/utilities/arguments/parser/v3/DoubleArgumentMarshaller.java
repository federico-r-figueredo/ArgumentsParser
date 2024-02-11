package utilities.arguments.parser.v3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleArgumentMarshaller implements ArgumentMarshaller {
	private double doubleValue = 0.0;
	
	@Override
	public void set(Iterator<String> currentArgument) throws ArgumentsException {
		String parameter = null;
		try {
			parameter = currentArgument.next();
			this.doubleValue = Double.parseDouble(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgumentsException(ErrorCode.MISSING_DOUBLE);
		} catch (NumberFormatException e) {
			throw new ArgumentsException(ErrorCode.INVALID_DOUBLE, parameter);
		}
	}

	public static double getValue(ArgumentMarshaller argumentMarshaller) {
		if (argumentMarshaller != null && argumentMarshaller instanceof DoubleArgumentMarshaller) {
			return ((DoubleArgumentMarshaller) argumentMarshaller).doubleValue;
		} else {
			return 0.0;
		}
	}
}
