import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BibCreator {

	public static void main(String[] args) {
		//This variable determines when the program will terminate
		int loop = 0;
		
		
		System.out.println("Welcome to Danial's BibCreator! \n");
		System.out.println("==================================================================================================\n");
		
		//Loop through all the 10 files and pass them through to validation
		for (int i = 1; i <= 10; i++) {
			FileValidation.validation(i);
		}
		
		Scanner scanner = new Scanner(System.in);
		
		while (loop <= 1) {
		System.out.println("\nPlease enter the name of the file you would like to review: ");
		String search = scanner.nextLine();
		//Increment once regardless if an exception is thrown or not
		//If an exception is thrown it will give the user another chance
		//If no exception is thrown we will increment later to terminate the program
		loop++;
		try {
			//Creating new buffered reader object to read the file the user entered
			BufferedReader br = new BufferedReader(new FileReader(search));
			String line;
			//Loop and print out the file
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				//Incrementing once again because the user entered a valid file
				loop++;
			}
			//Closing  the buffered reader object
			br.close();

			
				
		} catch (FileNotFoundException e) {
			//If the user enters an invalid file twice display this message and terminate the program
			if (loop > 1) {
				System.out.println("\nCannot the find file '" + search + "', the program will not terminate.");	
			}
			//If the user enters an invalid file for the first time display this message and continue the program.
			else {
				System.out.println("\nCannot the find file '" + search + "', You can try one more time before the program will terminate.");	
			}
			
			
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
		
		}
		//Once the program has finished running display this message and close the scanner object
		System.out.println("\nThank you for using my program.");
		scanner.close();
}
}
