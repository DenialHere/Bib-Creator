
public class FileInvalidException extends Exception {
	public FileInvalidException() {
		//Once a file invalid exception is thrown display this default message
		//Within the file validation class a more detailed message will be displayed
		System.out.println("Error: Input file cannot be parsed due to missing information (i.e.month={},title={},etc.)");
	}
}
