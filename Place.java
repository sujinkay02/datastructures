public class Place {
	// Attributes
	private String zip;
	private String town;
	private String state;
	
	// Constructors
	public Place(String _zip, String _town, String _state) {
		zip = _zip;
		town = _town;
		state = _state;
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
	
	// Predicates
	// Takes a zip code as its parameter, returns true if object's zip code
	// is the same as the parameter, false otherwise
	public boolean equals(String zipCode) {
		return zipCode.equals(zip);
	} // equals()
	
	// Print Method 1 (to print all the places in the Place array in the form "town, state zip")
	public String toString1() {
		return town + ", " + state + " " + zip;
	} // toString1()
	
	// Print Method 2 (to print only "town, state" to use for user interaction)
	public String toString2() {
		return town + ", " + state;
	} // toString2()
		
} // class Place()