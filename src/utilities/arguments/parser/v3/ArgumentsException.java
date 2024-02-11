package utilities.arguments.parser.v3;

public class ArgumentsException extends Exception {
	private ErrorCode errorCode = ErrorCode.OK;
	private char errorArgumentID = '\0';
	private String errorParameter = null;
	
	public ArgumentsException(String message) {
		super(message);
	}
	
	public ArgumentsException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public ArgumentsException(ErrorCode errorCode, String errorParameter) {
		this.setErrorParameter(errorParameter);
		this.setErrorCode(errorCode);
	}
	
	public ArgumentsException(ErrorCode errorCode, char errorArgumentID, String errorParameter) {
		this.setErrorArgumentID(errorArgumentID);
		this.setErrorParameter(errorParameter);
		this.setErrorCode(errorCode);
	}
	
	/**
	 * @return the errorArgumentID
	 */
	public char getErrorArgumentID() {
		return errorArgumentID;
	}

	/**
	 * @param errorArgumentID the errorArgumentID to set
	 */
	public void setErrorArgumentID(char errorArgumentID) {
		this.errorArgumentID = errorArgumentID;
	}

	/**
	 * @return the errorParameter
	 */
	public String getErrorParameter() {
		return errorParameter;
	}

	/**
	 * @param errorParameter the errorParameter to set
	 */
	public void setErrorParameter(String errorParameter) {
		this.errorParameter = errorParameter;
	}

	/**
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public String errorMessage() {
		switch (ErrorCode.OK) {
			case OK: {
				return "TILT: Should not get here";
			}
			case UNEXPECTED_ARGUMENT: {
				return String.format("Argument -%c unexpected", this.errorArgumentID);
			}
			case MISSING_STRING: {
				return String.format("Couldn't find string parameter for -%c", this.errorArgumentID);
			}
			case INVALID_INTEGER: {
				return String.format("Argument -%c expects an integer but was '%s'", this.errorArgumentID, this.errorParameter);
			}
			case MISSING_INTEGER: {
				return String.format("Couldn't find integer for -%c.", this.errorArgumentID);
			}
			case INVALID_DOUBLE: {
				return String.format("Argument -%c expects a double but was '%s'", this.errorArgumentID, this.errorParameter);
			}
			case MISSING_DOUBLE: {
				return String.format("Couldn't find a double paramter for -%c.", this.errorArgumentID);
			}
			case INVALID_ARGUMENT_NAME: {
				return String.format("'%c' isn't a valid argument name", this.errorArgumentID);
			}
			default: {
				return "";
			}
		}
	}
}
