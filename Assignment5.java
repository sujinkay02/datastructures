import java.net.URL;
import java.util.Scanner;
import java.io.*;

public class Assignment5 {

	// BST of Place objects, called places
	public static BST<Place> places;

	private static Place found;


	// Counting comparisons
	//	private static int numberComparisons = 0;
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

		//System.out.println(places);


		// Useful query loop variables and strings
		boolean more = true;
		String userInput = null;
		String inputTown = null;
		String inputState = null;

		do {

			// Prompt the user to enter a location (town, state)
			System.out.print("Query: ");

			// Read from console
			userInput = console.nextLine();
			String tokens[] = userInput.split(",");
			if (tokens.length > 1) {
				inputTown = tokens[0];
				inputState = tokens[1].trim();



				// Search
				numberSearchesTotal++;
				//numberComparisons = 0;

				// Search for town, state in the ArrayList of Place objects
				int result = search(places, inputTown, inputState);     // the search happens here
				totalComparisons += places.numberComparisons;

				// Print out different messages depending on if element was found or not
				if (result != -1) {
					System.out.println(userInput + "\n" + found);

					// Keep track of the total number of comparisons needed to answer a successful query
					successfulComparisons += places.numberComparisons;

					// Keep track of the total number of successful searches done
					successfulSearchesTotal++;	
				}
				else {
					System.out.println("Error: " + userInput + " does not exist. Please check spelling and try again.");

					// Keep track of total number of unsuccessful searches done
					unsuccessfulSearchesTotal++;

					// Keep track of the number of comparisons needed to answer an unsuccessful query
					unsuccessfulComparisons += places.numberComparisons;
				}

				// Print out all comparison data
				System.out.println("Number of searches total: " + numberSearchesTotal); // added
				System.out.println("Number of comparisons needed to answer this query: " + places.numberComparisons);
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
			}
			else {
				System.out.println("Error: Please enter in the form: Town, ST.");
			}

			// Does user want more?
			System.out.print("\nWould you like to search again? (Y/N) ");
			String response = console.nextLine();
			more = response.equalsIgnoreCase("y");
		} while (more);
		System.out.println("The tree has " + places.size() + " items.");
		System.out.println("Height of tree: " + places.height() + "\n");
	} // main()


	// Method that returns 1 if the user input is found in the BST, -1 otherwise
	public static int search(BST<Place> p, String town, String state) {

		// Create a new place object in order to compare (want to compare objects, not strings)
		Place plc = new Place(town, state);
		found = p.find(plc);

		if (found != null) {
			return 1;
		}
		else {
			return -1;
		}
	} // search()



	// Method that reads the data file
	public static void readData() {

		// Define a Binary Search Tree
		places = new BST<Place>();

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

				// if a town has multiple zip codes, store them together
				Place p = new Place(zip, town, state, pop, female, male);
				//	System.out.println(p);

				Place addPlace = places.find(p);
				//	System.out.println(addPlace);
				//	System.out.println(zip);

				//System.out.println(addPlace);


				// if the town is not already in the BST, then add it
				if (addPlace == null) {
					places.add(p);
					//System.out.println(p);
				}
				// otherwise, just add the zip code, updated total pop, male, female
				else {
					addPlace.addZips(p);
					addPlace.addPops(p);
					addPlace.addFem(p);
					addPlace.addMale(p);
				}

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

} // Assignment5()
