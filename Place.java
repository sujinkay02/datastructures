public class Place implements Comparable<Place>{
	// Data Fields
	private String zip;
	private String town;
	private String state;
	private int population;
	private int males;
	private int females;
	
	
	// Constructors
	public Place(String _zip, String _town, String _state, int _population, int _males, int _females) {
		zip = _zip;
		town = _town;
		state = _state;
		population = _population;
		males = _males;
		females = _females;
		
	} // Place()
	
	
	// Accessors
	public String getZip() {
		return zip;
	} // getZip()
	
	public String getTown() {
		return town;
	} // getTown()
	
	public String getState() {
		return state;
	} // getState()
	
	public int getPopulation() {
		return population;
	} // getPopulation()
	
	public int getMales() {
		return males;
	} // getMales()
	
	public int getFemales() {
		return females;
	} // getFemales()
	
	
	// Predicates
	// Takes user input of the form "town, state"  as its parameter, returns true if object's town and state
	// are the same as the input, false otherwise
	public boolean equals(String townST) {
		// No case-sensitivity: convert the strings being compared to upper case
		return townST.equals(this.getTown() + this.getState());
	} // equals()
	
	public int compareTo(Place p) {
		return (this.getTown() + this.getState()).compareTo(p.getTown() + p.getState());
	}

	// Print Method 
	public String toString() {
		return "Found...\nTown: " + town + ", " + state + ", " + zip + " " + "\nPopulation: " + population + " (" + females + " Female, " + males + " Male)\n";
	} // toString()

		
} // class Place()


