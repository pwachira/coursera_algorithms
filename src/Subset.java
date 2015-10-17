import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Subset {


  public static void main(String[] args) {
    RandomizedQueue<String> randQueue = new RandomizedQueue<String>();
    int k = Integer.valueOf(args[0]);
    while (!StdIn.isEmpty()) {
      randQueue.enqueue(StdIn.readString());
    }

    for (int i = 0; i < k; i++) {
      StdOut.println(randQueue.dequeue());
    }
  }
}
