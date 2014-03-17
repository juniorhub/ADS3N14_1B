public class Nodo<T> {
	
	private T dado;
	private Nodo<T> next;

	public Nodo(T i)
	{
		dado = i;
		next = null;
	}
	
	public void setData(T i)
	{
		dado = i;
		next = null;
	}
	
	public T getData()
	{
		return dado;
	}

	public void setNext(Nodo<T> next)
	{
		this.next = next;
	}
	
	public Nodo<T> getNext()
	{
		return next;
	}
}