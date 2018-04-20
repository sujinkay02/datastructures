import java.util.ArrayList;

public class Place implements Comparable<Place>{
	// Data Fields
	public ArrayList<String> zipCodes;		// ArrayList to hold all the zip codes of a townST
	private String town;
	private String state;
	private int population;
	private int males;
	private int females;

	int totalPop = 0;
	int totalMale = 0;
	int totalFem = 0;

	// Constructors
	public Place(String _zip, String _town, String _state, int _population, int _males, int _females) {
		zipCodes = new ArrayList<String>();
		zipCodes.add(_zip);

		population = _population;
		town = _town;
		state = _state;
		males = _males;
		females = _females;
	} // Place()

	public Place(String _town, String _state) {
		town = _town;
		state = _state;
	} // Place()

	// Accessors
	public ArrayList<String> getZip() {
		return zipCodes;
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
	public int compareTo(Place p) {
		return (this.getTown().toUpperCase() + this.getState().toUpperCase()).compareTo(p.getTown().toUpperCase() + p.getState().toUpperCase());
	} // compareTo()

	// use this to add the zip codes to the existing node, rather than create an entirely new one
	public void addZips(Place p) {
		zipCodes.add(p.getZip().get(0));
	} // addZips()

	// add the populations of the different zip codes for each town
	public void addPops(Place p) {
		totalPop += p.getPopulation();
	} // addPops()

	// add the male pops for each zip code for the same town
	public void addMale(Place p) {
		totalMale += p.getMales();
	} // addMale()

	// add the female pops for each zip code for the same town
	public void addFem(Place p) {
		totalFem += p.getFemales();
	} // addFem()




	// Print Method 
	public String toString() {
		return "Found...\nTown: " + town + ", " + state + ", " + zipCodes + " " + "\nPopulation: " + totalPop + " (" + totalFem + " Female, " + totalMale + " Male)\n";
	} // toString()


} // class Place()


