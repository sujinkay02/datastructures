import java.net.URL;
import java.util.Scanner;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Assignment2 {
	
	// Array of Place objects
	public static Place[] places;
	
	// Method that reads the data file
	public static void readData() {
		
		// Open an input stream to the data file
		URL webFile;
		Scanner input;
		String line;
		
		try {
			webFile = new URL("http://www.cs.brynmawr.edu/cs206/DataFiles/uszipcodes.csv");
			
			// Create a new Scanner for the input file
			input = new Scanner(webFile.openStream());
			
			// Read the first line from the input file
			line = input.nextLine();
			
			// Split the first line into pieces
			String[] tokens = line.split(",");
			
			// Extract the number of entries, convert to an integer
			int numberEntries = Integer.parseInt(tokens[0]);
			
			// Print this number out
			//System.out.println(numberEntries);
			
			// Define an array to contain N place objects 
			// N = number of entries in the file
			places = new Place[numberEntries];
						
			// Use increments to put all the objects into the places array
			int i = 0;
			
			// Read each line, and print it out to the console
			while (input.hasNextLine()) {
				
				// Read the line
				line = input.nextLine();
				
				// Parse each line into pieces
				String[] tokens2 = line.split(",");
				
				// Put all the objects into the places array
				// Each place's info containing only zip, town, state
				places[i] = new Place(tokens2[0], tokens2[1], tokens2[2]);
				
				// Output the place's info to console, in the form "town, state zip"
				//System.out.println(places[i].toString1());		
				
				// Increase increment by 1	
				i++;
			}
			
		} catch (IOException e) { 
			// Display stack trace
			e.printStackTrace();
			
			// Print error message
			System.err.println("Error: Data file could not be opened. Please check URL and try again.");

			// Terminate the program
			System.exit(1);
			
		} // try-catch block
		
	} // readData()

	
	// Method that returns the index in the array of places if the zip code
	// is found in the array (p), -1 otherwise
	public static int search(Place[] p, String zipCode) {
		
		// Loop through each element in the array
		for (int i=0; i < p.length; i++) {
			
			// If the element is found, return its index
			if (p[i].equals(zipCode)) {
				return i;
			}
		}
		// If not found, return -1
		return -1;
		
	} // search()
	
	public static void main(String[] args) {
		readData();
		String message;
		String answer;
		String[] options = { "Yes", "No" };
		int response = 0;
		
		// Loop to print out objects in places array
		//for (int i=0; i < places.length; i++) {
		//	System.out.println(places[i].toString1());
		//}

		// Take user requests
		do {
			// Get input from user
			String zipCode = JOptionPane.showInputDialog("Enter a zip code: ");

			// Print user input to console
			System.out.println("You asked me to search for zip code: " + zipCode);

			// Make sure input isn't empty or invalid
			if (zipCode != null) {

				// Search for zip code in the array of Place objects
				int searchIndex = search(places, zipCode);

				// Print out different messages depending on if element was found or not
				if (searchIndex >= 0) {
					message = "The zip code " + zipCode + " belongs to " + places[searchIndex];
				}
				else {
					message = "The zip code " + zipCode + " does not exist.";
				}

				// Output results of search to console
				System.out.println(message);

				// Output results of search to dialog window
				// At the same time, ask user if they want to do it again
				response = JOptionPane.showOptionDialog(null, 
						message + "\n Do you want me to search again?",
						"Results...",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, // do not use a custom icon
						options, // the titles of buttons
						options[0]); // default button title
				
				// Print user interactions (results dialog) to console
				if (response == 0) {
					answer = "Yes";
				}
				else {
					answer = "No";
				}
				
				System.out.println("Do you want me to search again? " + answer + "\n");
			}
			
		} while (response == 0); 
			
		// Print goodbye message to console, once user stops searching
		System.out.println("Good Bye!");
		
	} // main()

} // Assignment2()

