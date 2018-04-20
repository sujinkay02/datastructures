import java.util.Arrays;

public class BST<E extends Comparable<E>> extends BinaryTree<E> { 
	// adding this to count
	public int numberComparisons = 0;
	// Data field(s)
	private int size = 0;
	
	// Constructor(s)
	
	// Methods
	public int size() {
		return this.size;
	} // size()
	
	public void clear() {
		root = null;
		size = 0;
	} // clear()
	
	public void add(E item) {
		root = add(root, item);
		size++;
	} // add(item)

	
	private Node<E> add(Node<E> node, E item) {
		
		// insert item (preserving BST) at or below node
		// returns node at/under which item was inserted
		if (node == null) {
			// insert item right here
			return new Node<E>(item);
		}
		else if (item.compareTo(node.data) <= 0) {
			// item is less than or equal to the data in node
			node.left = add(node.left, item);
			return node;
		}
		else {
			// item is greater than the data in localRoot
			node.right = add(node.right, item);
			return node;
		}
		
	} // add(node, item)

	public E find(E item) {
		// reset the numberComparisons to zero each time you do a search
		numberComparisons = 0;
		return find(root, item);	
	} // find(item)

	private E find(Node<E> node, E item) {
		
		if (node == null) {
			return null;
		}
		int compResult = item.compareTo(node.data);
		// keep track of the number of comparisons
		numberComparisons++;
		
		if (compResult == 0)
			return node.data;
		else if (compResult < 0)
			return find(node.left, item);
		else
			return find(node.right, item);
		
	} // find(node, item)
	
	
	public static void show(String[] places, String msg) {
		// useful method to print out an array, a[]
		System.out.print(msg + ": [ ");
		for (String place : places ) {
			System.out.print(place + ", ");
		}
		System.out.println("]");
	} // show()	
	
	public static void main(String[] args) {
		// Part A
		String[] places = {"Cambridge", "Crapo", "Eugene", "Bryn Mawr", "Boring", "Hell", "Walla Walla", "Surprise", "Joseph", "Romance", "Mars", "Nuttsville", "Rough and Ready", "Dynamite", "Good Grief"};
		BST<String> data = new BST<String>();
		// add the places to the BST
		for (String place : places) {
			data.add(place);
		}
		
		System.out.println("The tree has " + data.size() + " items.");
		System.out.println("Height of tree: " + data.height() + "\n");
		
		// print results of tree traversals
		System.out.println(data);
		
		// Part B
		// clear the tree
		data.clear();
		System.out.println("\nThe tree has " + data.size() + " items.");
		System.out.println("Height of tree: " + data.height() + "\n");	
		
		// sort places in ascending order
		show(places, "Places in unsorted list");
		Arrays.sort(places);
		show(places, "Places in sorted list");
	
		// add sorted places to the BST
		for (String place: places) {
			data.add(place);
		}

		System.out.println("The tree has " + data.size() + " items.");
		System.out.println("Height of tree: " + data.height() + "\n");
		
		// print results of tree traversals
		System.out.println(data);

	} // main()
	// create a new constructor for the new place object that I need to make in order to use compareTo
	// create an arraylist to hold all the zip codes
	// only add the townSt ONCE in the binary tree
	// compare the number of comparisons, size and height of tree before and after fixing zip codes
	// change the print statement to print all the zip codes for a place
	
} // class BST<E>
