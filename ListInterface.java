
public interface ListInterface<E> {
	public boolean add(E item);
	public void add(int index, E item);
	
	public Object remove(int index);
	public Object set(int index, E item);
	public Object get(int index);
	
	public boolean contains(E item);
	
	public boolean isEmpty();
	public void clear();
	public int size();
	
} // interface ListInterface


