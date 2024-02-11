package utilities.arguments.parser.v3;

import java.util.Iterator;

public interface ArgumentMarshaller {
	void set(Iterator<String> currentArgument) throws ArgumentsException;
}
