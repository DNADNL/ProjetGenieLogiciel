
public class NotExpectedValueException extends Exception{

private String name;
	
	public NotExpectedValueException(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
