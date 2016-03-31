package exceptions;

// This exception is used when we don't retrieve the expected value of an Object.
// The "name" parameter corresponds to a String giving the name of the Object

@SuppressWarnings("serial")
public class NotExpectedValueException extends Exception{

private String name;
	
	public NotExpectedValueException(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
