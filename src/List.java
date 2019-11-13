public interface List<E> {

	public boolean isEmpty ();
	public boolean isFull ();
	public E get (int n);
	public int numElements ();
	public void insertFirst (E element);
	public void insertLast (E element);
	public void insert (E element, int pos);
	public E removeFirst ();
	public E removeLast ();
	public E remove (int pos);
	public int search (E element);
}