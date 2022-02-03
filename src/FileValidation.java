import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileValidation {
	//This variable will keep track of how many invalid files there are
	static int filesEmpty = 0;
	
	
	public static void validation(int fileNumber) {

	//Creating variables for each one of the parameters in the .bib files 
	String author = null;
	String journal = null;
	String title = null;
	String year = null;
	String volume = null;
	String number = null;
	String pages = null;
	String keywords = null;
	String doi = null;
	String ISSN = null;
	String month = null;
	//This will be incremented for each article in the ACM format
	int ACMNum = 1;
	//This will store which part of the file the error takes place in
	String error = null;
	//Creating a new buffered read object and setting it to null
	BufferedReader br = null;
	String line;
	
	
	try {
			//Setting the ACM number to 1 so it resets after each new file
			ACMNum = 1;
			//Creating a new buffer reader object which will increment each pass though
			br = new BufferedReader(new FileReader("latex" + fileNumber + ".bib"));
			
			//Creating print writer objects & files for each format and incrementing like previous
			PrintWriter IEEE = new PrintWriter(new FileOutputStream("IEEE" + fileNumber + ".json", true));
			PrintWriter ACM = new PrintWriter(new FileOutputStream("ACM" + fileNumber + ".json", true));
			PrintWriter NJ = new PrintWriter(new FileOutputStream("NJ" + fileNumber + ".json", true));
			File IEEEFile = new File("IEEE" + fileNumber + ".json");
			File ACMFile = new File("ACM" + fileNumber + ".json");
			File NJFile = new File("NJ" + fileNumber + ".json");
			
			
			//Loop until we reach the end of the file
			//If we find an invalid file we keep track of what the error is,
			//we close the print writer, delete the file, and then throw an exception
		while ((line = br.readLine()) != null) {
			if (line.contains("author={}")) {
				error = "author";
				NJ.close();
				ACM.close();
				IEEE.close();
				IEEEFile.delete();
				ACMFile.delete();
				NJFile.delete();
				throw new FileInvalidException();
			}
			if (line.contains("journal={}")) {
				error = "journal";
				NJ.close();
				ACM.close();
				IEEE.close();
				IEEEFile.delete();
				ACMFile.delete();
				NJFile.delete();
				throw new FileInvalidException();
			}
			if (line.contains("title={}")) {
				error = "title";
				NJ.close();
				ACM.close();
				IEEE.close();
				IEEEFile.delete();
				ACMFile.delete();
				NJFile.delete();
				throw new FileInvalidException();
			}
			if (line.contains("year={}")) {
				error = "year";
				NJ.close();
				ACM.close();
				IEEE.close();
				IEEEFile.delete();
				ACMFile.delete();
				NJFile.delete();
				throw new FileInvalidException();
			}
			if (line.contains("volume={}")) {
				error = "volume";
				NJ.close();
				ACM.close();
				IEEE.close();
				IEEEFile.delete();
				ACMFile.delete();
				NJFile.delete();
				throw new FileInvalidException();
			}
			if (line.contains("number={}")) {
				error = "number";
				NJ.close();
				ACM.close();
				IEEE.close();
				IEEEFile.delete();
				ACMFile.delete();
				NJFile.delete();
				throw new FileInvalidException();
			}
			if (line.contains("pages={}")) {
				error = "pages";
				NJ.close();
				ACM.close();
				IEEE.close();
				IEEEFile.delete();
				ACMFile.delete();
				NJFile.delete();
				throw new FileInvalidException();
			}
			if (line.contains("keywords={}")) {
				NJ.close();
				ACM.close();
				IEEE.close();
				error = "keywords";
				IEEEFile.delete();
				ACMFile.delete();
				NJFile.delete();
				throw new FileInvalidException();
			}
			if (line.contains("doi={}")) {
				error = "doi";
				NJ.close();
				ACM.close();
				IEEE.close();
				IEEEFile.delete();
				ACMFile.delete();
				NJFile.delete();
				throw new FileInvalidException();
			}
			if (line.contains("ISSN={}")) {
				error = "ISSN";
				NJ.close();
				ACM.close();
				IEEE.close();
				IEEEFile.delete();
				ACMFile.delete();
				NJFile.delete();
				throw new FileInvalidException();

			}
			if (line.contains("month={}")) {
				error = "month";
				NJ.close();
				ACM.close();
				IEEE.close();
				IEEEFile.delete();
				ACMFile.delete();
				NJFile.delete();
				throw new FileInvalidException();
			}
			//Getting the data within the files
			else if (line.contains("author={")) {
				author = line.substring(8 , line.indexOf("}"));
			}
			else if (line.contains("journal={")){
				journal = line.substring(9 , line.indexOf("}"));
			}
			else if (line.contains("title={")){
				title = line.substring(7 , line.indexOf("}"));
			}
			else if (line.contains("year={")){
				year = line.substring(6 , line.indexOf("}"));
			}
			else if (line.contains("volume={")){
				volume = line.substring(8 , line.indexOf("}"));
			}
			else if (line.contains("volume={")){
				volume = line.substring(8 , line.indexOf("}"));
			}
			else if (line.contains("number={")){
				number = line.substring(8 , line.indexOf("}"));
			}
			else if (line.contains("pages={")){
				pages = line.substring(7 , line.indexOf("}"));
			}
			else if (line.contains("keywords={")){
				keywords = line.substring(10 , line.indexOf("}"));
			}
			else if (line.contains("doi={")){
				doi = line.substring(5 , line.indexOf("}"));
			}
			else if (line.contains("ISSN={")){
				ISSN = line.substring(6 , line.indexOf("}"));
			}
			else if (line.contains("month={") ){
				month = line.substring(7 , line.indexOf("}"));
				
				//Replacing and with , in IEEE files
				String authorIEEE = author.replace(" and", ",");
				
				int ACMstart;
				String authorACME;
				//Replace and with et al only if there is more than one author
				if (author.contains("and")) {
					//Finding where and ends so we can add et al. in its place and delete everything after it
					ACMstart = author.indexOf(" and ");
					authorACME = author.substring(0 ,ACMstart + 1) + "et al.";
					
				}
				else {
					authorACME = author;
				}
				
				//Replacing and with & in NJ files
				String authorNJ = author.replace("and", "&");
				
				//Formating and printing the files	
				IEEE.println(authorIEEE + ". " +  '"' + title.trim() + '"' + ", " + journal + ", vol. " + volume + ", no. " 
				+ number + ", p." + pages + ", " + month + " " + year + ".");
				
				ACM.println("[" + ACMNum + "]" + "    " + authorACME.trim() + " " + year + ". " + title + ". " 
				+ journal + ". " + volume + "," + number + "(" + year + "), " + pages + ". DOI::https://doi.org/10.1109/TVT.2018.2789899.");
				ACMNum++;
				
				NJ.println(authorNJ.trim() + ". " + title + ". " + journal + ". " + volume + "," + pages + "(" + year + ").");
						
			}

		}
		//Once the loop is complete we will close all the print writer objects
		NJ.close();
		ACM.close();
		IEEE.close();
		//Once we finish going through all the files we will display this message
		if (fileNumber == 10) {
			System.out.println("==================================================================================================");
			System.out.println("A total of " + filesEmpty + " files were invalid, and could not be processed. All the other " + (10- filesEmpty) + " valid files have been created.");
		}
		

		
	}
	catch (FileInvalidException e) {
		System.out.println("Field: '" + error + "' is empty" + " in latex" + fileNumber + ".bib, files were not created for this file! \n");
		//Increment the number of files that contain errors
		filesEmpty++;

	}
	catch (FileNotFoundException e) {
		//If the latex files don't exist we will display this message and close the program
        System.out.println("Could not open the file Latex" + fileNumber + ".bib.");
        System.out.println("Program will terminate after closing any opened files.");
        System. exit(0); 
        }
	
	catch (IOException e) {
		System.out.println(e.getMessage());
	}
	
	
}

}
