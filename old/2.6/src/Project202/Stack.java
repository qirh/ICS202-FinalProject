package Project202;


public class Stack<E>{
    private java.util.ArrayList<E> pool = new java.util.ArrayList<E>();
    public Stack() {
    }
    public Stack(int n) {
        pool.ensureCapacity(n);
    }
    public void clear() {
        pool.clear();
    }
    public boolean isEmpty() {
        return pool.isEmpty();
    }
    public E topEl() {
        if (isEmpty())
            throw new java.util.EmptyStackException();
        return pool.get(pool.size()-1);
    }
    public E pop() {
        if (isEmpty())
            throw new java.util.EmptyStackException();
        return pool.remove(pool.size()-1);
    }
    public void push(E el) {
    	if(el == null)
    		return;
        pool.add(el);
    }
    public String toString() {
        return pool.toString();
    }
}


