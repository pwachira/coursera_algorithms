import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] items;
  private int N = 0;

  
  @SuppressWarnings("unchecked")
  public RandomizedQueue() {
    items =  (Item[]) new Object[1];
    
  }                 
  public boolean isEmpty()  {
    return N == 0;
    
  }               
  public int size() {
    return N;
    
  }                        // return the number of items on the queue
  public void enqueue(Item item) {
    if (item == null) {
      throw new NullPointerException();
    } else {
      if (items.length == N) {
        resize(2 * items.length);
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
    if (isEmpty()) {
      throw new UnsupportedOperationException();
    } 
    int randIndex = getRandomIndex(N-1); 
    
    int newSizeIndex = --N;
    
      
       item = items[randIndex];
     
      items[randIndex] = items[newSizeIndex];
      items[newSizeIndex] = null;
      
      if (N == items.length/4) {
      resize(items.length/2);
   
    }
    return item;
   
  }
  private int getRandomIndex(int index) {
    return index == 0 ? 0 : StdRandom.uniform(index); 
  }
  
  public Item sample() { 
    if (isEmpty()) {
      StdOut.println("Attempting to sample empty queue;");
    } 
    return items[getRandomIndex(N)];
  }                    
      

  @Override
  public Iterator<Item> iterator() {
    return new ItemIterator();
  }
  
  private class ItemIterator implements Iterator<Item> {
    private int[] iteratorItems;
    private int iterCounter;
    private boolean hasNext = true;
    
    public ItemIterator() {
      if (N == 0) { hasNext = false;
      } else {
        hasNext = true;
      iteratorItems =   new int[N];
      for (int i = 0; i < N; i++) {
        iteratorItems[i] = i;
      }
      iterCounter = N - 1;
      }
      
    }
    @Override
    public boolean hasNext() {
      return hasNext;
    }

    @Override
    public Item next() {
      if (iterCounter < 0) {
        throw new NoSuchElementException();
      }

        int rand = getRandomIndex(iterCounter);
        Item item = items[iteratorItems[rand]];
        int tmp = iteratorItems[rand];
        iteratorItems[rand] = iteratorItems[iterCounter];
        iteratorItems[iterCounter--] = tmp;
        if (iterCounter < 0) { hasNext = false; }
        return item;
   
  
    }
    @Override
    public void remove() {
      // TODO Auto-generated method stub
      
    }
    
  }
  
 
  
  public static void main(String[] args) {
    RandomizedQueue<String> rq = new RandomizedQueue<String>();
   // StdOut.println(rq.sample());
    rq.enqueue("A");
  //  StdOut.println(rq.sample());
    rq.enqueue("B");
    rq.enqueue("C");
    rq.enqueue("D");
    
    for (String str:rq) {
      StdOut.println(str);
    }
    
    StdOut.println(rq.sample());
    StdOut.println(rq.sample());
    StdOut.println(rq.sample());
   StdOut.println(rq.sample());
    StdOut.println();
    StdOut.println();
    
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
