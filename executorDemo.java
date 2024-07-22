import java.util.concurrent.*;

/*
 * ExecutorService is a subinterface of Executor. It provides a more complete
 * framework for managing asynchronous tasks. It provides methods for managing
 * the lifecycle of threads and determining when tasks are completed.
 * 
 * basically thread pool
 * newFixedThreadPool(3) will create a thread pool with 3 threads
 * newCachedThreadPool() will create a thread pool that creates new threads as needed
 * 
 * Suppose that you replace line 6 with
ExecutorService executor = Executors.newFixedThreadPool(1);
What will happen? The three runnable tasks will be executed sequentially because there is
only one thread in the pool.
Suppose you replace line 6 with
ExecutorService executor = Executors.newCachedThreadPool();
 */
public class executorDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new PrintChar('a', 1000));
        executor.execute(new PrintChar('b', 1000));
        executor.execute(new PrintNum(1000));
        // System.out.println("Hello World");
        executor.shutdown();
    }
}