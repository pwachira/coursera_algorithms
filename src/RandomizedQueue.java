
import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item>{

  private Item[] items ;
  private int N = 0;
  
  @SuppressWarnings("unchecked")
  public RandomizedQueue() {// construct an empty randomized queue
    items =  (Item[]) new Object[1];
    
  }                 
  public boolean isEmpty()  {
    return N == 0;
    
  }               
  public int size() {
    return N ;
    
  }                        // return the number of items on the queue
  public void enqueue(Item item) {
    if (item == null) {
      throw new NullPointerException();
    } else {
      if (items.length == N) {
        resize (2 * items.length);
      }
      items[N++] = item;
      
    } 
  }
  private void resize(int capacity) {
    @SuppressWarnings("unchecked")
    Item[] copy = (Item[]) new Object[capacity];
    for (int i = 0; i < N; i++) {
      copy[i] = items[i];
    }
    items = copy;
    
  }
  public Item dequeue() {
    Item item;
    if (isEmpty() ) {
      throw new UnsupportedOperationException();
    } 
    int newSizeIndex = --N;
    int randIndex = getRandomIndex(newSizeIndex); 
      
      
       item = items[randIndex];
     
      items[randIndex] = items[newSizeIndex];
      items[newSizeIndex] = null;
      
      if (N == items.length/4) {
      resize(items.length/2);
   
    }
    return item;
   
  }
  private int getRandomIndex(int index) {
    return N==0 ? 0 : StdRandom.uniform(index); 
  }
  
  public Item sample() { 
    return items[getRandomIndex(N -1)];
  }                    
      

  @Override
  public Iterator<Item> iterator() {
    return new ItemIterator();
  }
  
  private class ItemIterator implements Iterator<Item> {

    @Override
    public boolean hasNext() {
      return !isEmpty();
    }

    @Override
    public Item next() {
      int rand = getRandomIndex(N -1);
      Item item = items[rand];
      Item lastItem = items[N-1];
      swapWithLast (item,lastItem);
      
      return item;
    }
    
  }
  
  private void swapWithLast(Item randItem, Item lastItem) {
    Item temp = randItem;
    randItem = lastItem;
    lastItem = temp;
  }
  
  public static void main(String[] args) {
    RandomizedQueue<String> rq = new RandomizedQueue<String>();
    StdOut.println(rq.sample());
    rq.enqueue("A");
    rq.enqueue("B");
    rq.enqueue("C");
    rq.enqueue("D");
    
    String first = rq.dequeue();
    StdOut.println(first);
    String second =  rq.dequeue();
    StdOut.println(second);
    String third =  rq.dequeue();
    StdOut.println(third);
    String fourth  = rq.dequeue();
    StdOut.println(fourth);
    
   
  }

  


}
