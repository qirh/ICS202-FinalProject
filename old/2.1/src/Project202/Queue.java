package Project202;

public class Queue<T>{
    private java.util.LinkedList<T> list = new java.util.LinkedList<T>();
    
    public Queue(){
    }
    public void clear(){
        list.clear();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public T firstEl(){
        return list.getFirst();
    }
    public T dequeue(){
        return list.removeFirst();
    }
    public void enqueue(T el){
    	if(el == null)			//easier
    		return;
    	else
    		list.add(el);
    }
    public String toString(){
        return list.toString();
    }
}