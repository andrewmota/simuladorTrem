public class DNode<E> {
	private E element;
	private DNode<E> next;
	private DNode<E> previous;

	public DNode(E element) {
		this (element, null, null);
	}
	public DNode(E element, DNode<E> next, DNode<E> previous) {
		super();
		this.element = element;
		this.next = next;
		this.previous = previous;
	}
	public E getElement() {
		return element;
	}
	public void setElement(E element) {
		this.element = element;
	}
	public DNode<E> getNext() {
		return next;
	}
	public void setNext(DNode<E> next) {
		this.next = next;
	}
	public DNode<E> getPrevious() {
		return previous;
	}
	public void setPrevious(DNode<E> previous) {
		this.previous = previous;
	}

}