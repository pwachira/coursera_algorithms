/**
* Class that implements a double ende queue.
* @since 1.0
* @author peter wachira
* @version 1.0
*/


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that implements a double ended queue.
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
  /**
   * Reference to first node.
   */
  private Node first;
  /**
   * Reference to last node.
   */
  private Node last;
  /**
   * size of the queue.
   */
  private int queueSize;
  /**
   * Constructor.
   */
    public Deque() {
        queueSize = 0;
        first = null;
        last = null;
    }
    /**
     * @return  boolean indicating if the deque is empty.
     */
    public  boolean isEmpty() {
        return first == null;
    }
    /**
     * @return  current size of the queue.
     */
    public  int size() {
        return queueSize;

    }

    /**
     * Adds a new Item to the start of the queue.
     * @param item Item to be added
     */
    public  void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Item supplied is null");
            }

        Node oldFirst = first;
        Node newfirst = new Node();
        newfirst.item =  item;
        first = newfirst;
        first.prev = null;
        if (oldFirst == null) {
            last = first;
            last.next = null;
            } else {
                first.next = oldFirst;
                oldFirst.prev = first;
            }
        queueSize++;

    }
    /**
     * Adds a new Item to the end of the queue.
     * @param item Item to be added
     */
    public  void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Item supplied is null");
            }

        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
            first.prev = null;
        } else {
                 oldlast.next = last;
                 last.prev = oldlast;
            }
        queueSize++;

    }
    /**
     * Removes item from the start of the queue.
     * @return  Item removed
     */
    public  Item removeFirst() {
        if (isEmpty()) {
            throw new UnsupportedOperationException(
               "Attempting to remove from an empty queue");
        }
        Item firstItem = first.item;

        first = first.next;

        if (isEmpty()) {
          last = null;
          } else {
            first.prev = null;
            }

        queueSize--;
        return firstItem;
    }
    /**
     * Removes item from the end of the queue.
     * @return  Item removed
     */
    public  Item removeLast() {
        if (isEmpty()) {
            throw new UnsupportedOperationException(
                "Attempting to remove from an empty queue");
        }
        Item lastItem = last.item;
        last = last.prev;

        if (last == null) {
          first = null;
          } else {
            last.next = null;
        }

        queueSize--;
        return lastItem;

    }

/**
 * Node.
 * @author peterwachira
 *
 */
    private class Node {
      /**
       * Implementation of iterator.
       */
        private Item item;
      /**
       * Implementation of iterator.
       */
         private Node next;
         /**
          * Implementation of iterator.
          */
         private Node prev;

    }
    /**
     * Implementation of iterator.
     */
    private class ItemIterator implements Iterator<Item> {

       /**
        * reference to current node.
        */
       private Node current;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
           if (!hasNext()) {
                throw new NoSuchElementException("No more items available");
                }

           Item item = current.item;
           current = current.next;
           return item;
        }
        @Override
        public void remove() {
        }

    }
    /**
     * Iterator.
     * @return Iterator Iterator
     */
    public  Iterator<Item> iterator() {
        // TODO Auto-generated method stub
        return new ItemIterator();
    }
/**
 * main method.
 * @param args String array input
 */
    public static void main(String[] args) {
        try {
            Deque<String> deck = new Deque<String>();
        deck.addFirst("One");
        deck.removeLast();

        deck.addFirst("One");
        deck.removeFirst();

        deck.addLast("One");
        deck.addLast("Two");
        deck.addFirst("Three");
        deck.removeLast();
        deck.removeLast();
        deck.removeLast();
        deck.removeLast();
       // int size  = deck.size();
        } catch (Exception ex) {
        }
    }



}