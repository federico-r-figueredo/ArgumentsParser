package utilities.arguments.parser.v3;

import java.util.*;

public class ArgumentsParser {
	private Map<Character, ArgumentMarshaller> marshallers;
	private Set<Character> argumentsFound;
	private ListIterator<String> currentArgument;
	
	public ArgumentsParser(String schema, String[] arguments) throws ArgumentsException {
		this.marshallers = new HashMap<Character, ArgumentMarshaller>();
		this.argumentsFound = new HashSet<Character>();
		
		parseSchema(schema);
		parseArguments(Arrays.asList(arguments));
	}
	
	private void parseSchema(String schema) throws ArgumentsException {
		for (String element : schema.split(",")) {
			if (element.length() > 0) {
				parseSchemaElement(element.trim());
			}
		}
	}

	private void parseSchemaElement(String element) throws ArgumentsException {
		char elementID = element.charAt(0);
		String elementTail = element.substring(1);
		validateSchemaElementID(elementID);
		if (elementTail.isEmpty()) {
			this.marshallers.put(elementID, new BooleanArgumentMarshaller());
		} else if (elementTail.equals("*")) {
			this.marshallers.put(elementID, new StringArgumentMarshaller());
		} else if (elementTail.equals("#")) {
			this.marshallers.put(elementID, new IntegerArgumentMarshaller());
		} else if (elementTail.equals("##")) {
			this.marshallers.put(elementID, new DoubleArgumentMarshaller());
		} else {
			throw new ArgumentsException(ErrorCode.INVALID_ARGUMENT_NAME, elementID, elementTail);
		}
	}
	
	private void validateSchemaElementID(char elementID) throws ArgumentsException {
		if (!Character.isLetter(elementID)) {
			throw new ArgumentsException(ErrorCode.INVALID_ARGUMENT_NAME, elementID, null);
		}		
	}

	private void parseArguments(List<String> arguments) throws ArgumentsException {
		for (this.currentArgument = arguments.listIterator(); currentArgument.hasNext();) {
			String argument = currentArgument.next();
			if (argument.startsWith("-")) {
				parseArgumentCharacters(argument.substring(1));
			} else {
				currentArgument.previous();
				break;
			}
		}
	}

	private void parseArgumentCharacters(String argumentCharacters) throws ArgumentsException {
		for (int i = 0; i < argumentCharacters.length(); i++) {
			parseArgumentCharacter(argumentCharacters.charAt(i));
		}
	}

	private void parseArgumentCharacter(char argumentCharacter) throws ArgumentsException {
		ArgumentMarshaller argumentMarshaller = this.marshallers.get(argumentCharacter);
		if (argumentMarshaller == null) {
			throw new ArgumentsException(ErrorCode.UNEXPECTED_ARGUMENT, argumentCharacter, null);
		} else {
			this.argumentsFound.add(argumentCharacter);
			try {
				argumentMarshaller.set(this.currentArgument);
			} catch (ArgumentsException e) {
				e.setErrorArgumentID(argumentCharacter);
				throw e;
			}
		}
	}
	
	public boolean has(char argument) {
		return this.argumentsFound.contains(argument);
	}
	
	public int nextArgument() {
		return this.currentArgument.nextIndex();
	}
	
	public boolean getBoolean(char argument) {
		return BooleanArgumentMarshaller.getValue(this.marshallers.get(argument));
	}
	
	public String getString(char argument) {
		return StringArgumentMarshaller.getValue(this.marshallers.get(argument));
	}
	
	public int getInteger(char argument) {
		return IntegerArgumentMarshaller.getValue(this.marshallers.get(argument));
	}
	
	public double getDouble(char argument) {
		return DoubleArgumentMarshaller.getValue(this.marshallers.get(argument));
	}
}
