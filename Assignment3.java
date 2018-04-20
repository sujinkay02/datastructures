import java.util.ArrayList;
import java.net.URL;
import java.util.Scanner;
import java.io.*;

public class Assignment3 {

	// ArrayList of Place objects, called places
	public static ArrayList<Place> places;

	// Counting comparisons
	private static int numberComparisons = 0;
	private static int numberSearchesTotal = 0;
	private static int totalComparisons = 0;
	private static int successfulComparisons = 0;
	private static int successfulSearchesTotal = 0;
	private static int unsuccessfulSearchesTotal = 0;
	private static int unsuccessfulComparisons = 0;

	public static void main(String[] args) {
		// Read the data set
		readData();

		// Query loop
		// Create a Scanner object to enable console input
		Scanner console = new Scanner((System.in));
		
		// Useful query loop variables and strings
		boolean more = true;
		String userInput = null;
		String townST = null;
		
		do {

			// Prompt the user to enter a location (town, state)
			System.out.print("Query: ");

			// Read from console
			userInput = console.nextLine();
			String tokens[] = userInput.split(",");
			if (tokens.length > 1) {
				townST = tokens[0] + tokens[1].trim();
			}
			else {
				System.out.println("Error: Please enter in the form: Town, ST.");
			}
			
			// Search
			numberSearchesTotal++;
			numberComparisons = 0;

			// Search for town, state in the ArrayList of Place objects
			int result = search(places, townST);     // the search happens here
			totalComparisons += numberComparisons;

			// Print out different messages depending on if element was found or not
			if (result != -1) {
				System.out.println(userInput + "\n" + places.get(result));

				// Keep track of the total number of comparisons needed to answer a successful query
				successfulComparisons += numberComparisons;

				// Keep track of the total number of successful searches done
				successfulSearchesTotal++;	
			}
			else {
				System.out.println("Error: " + userInput + " does not exist. Please check spelling and try again.");

				// Keep track of total number of unsuccessful searches done
				unsuccessfulSearchesTotal++;

				// Keep track of the number of comparisons needed to answer an unsuccessful query
				unsuccessfulComparisons += numberComparisons;
			}

			// Print out all comparison data
			System.out.println("Number of comparisons needed to answer this query: " + numberComparisons);
			System.out.println("Average comparisons needed to answer a query: " + (int)(float) totalComparisons/numberSearchesTotal);
			if (successfulSearchesTotal == 0) {
				System.out.println("Average comparisons needed to answer a successful query: " + 0);
			}
			else {
				System.out.println("Average comparisons needed to answer a successful query: " + (int)(float) successfulComparisons/successfulSearchesTotal);
			}
			if (unsuccessfulSearchesTotal == 0) {
				System.out.println("Average comparisons needed to answer an unsuccessful query: " + 0);
			}
			else {
				System.out.println("Average comparisons needed to answer an unsuccessful query: " + (int)(float) unsuccessfulComparisons/unsuccessfulSearchesTotal);
			}

			// Does user want more?
			System.out.print("\nWould you like to search again? (Y/N) ");
			String response = console.nextLine();
			more = response.equalsIgnoreCase("y");
		} while (more);

	} // main()

	

	// Method that returns the index in the places ArrayList if the user input
		// is found in the ArrayList p, -1 otherwise
		public static int search(ArrayList<Place> p, String townST) {
			
		//	Place p = new Place(town, state, "0", "0", "0", )
			//		p.find(ps)
					
			// Loop through each element in the ArrayList
			for (int i=0; i < p.size(); i++) {
				numberComparisons++;

				// If the element is found, return its index
				if (p.get(i).equals(townST)) {
					return i;
				}
			}
			// If not found, return -1
			return -1;

		} // search()


	
	// Method that reads the data file
	public static void readData() {

		// Define an ArrayList (with unspecified number of objects)
		places = new ArrayList<Place>();

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

			// Read each line, and print it out to the console
			while (input.hasNextLine()) {

				// Read the line
				line = input.nextLine();

				// Parse each line into pieces, splitting on commas
				String[] tokens = line.split(",");
				
				String town;
				String state;
				String zip;
				
				town = tokens[1];
				state = tokens[2];
				zip = tokens[0];
				int pop = 0;
				int female = 0;
				int male = 0;
				
				if (tokens.length > 3) {
					try {
						pop = Integer.parseInt(tokens[3]);
						female = Integer.parseInt(tokens[5]);
						male = Integer.parseInt(tokens[4]);
					}
					catch (NumberFormatException e) {
						System.out.println("Error: " + line);
						System.exit(1);
					}
				}

				Place p = new Place(zip, town, state, pop, female, male);
				places.add(p);
			}
			input.close();
			//System.out.println("The data set contains: " + places.size() + " entries.");
			
		} catch (IOException e) { 
			// Executes if exception is thrown
			e.printStackTrace();
			System.err.println("Error: Data file could not be opened. Please check URL and try again.");
			System.exit(1);

		} // try-catch block

	} // readData()
	
} // Assignment3()


