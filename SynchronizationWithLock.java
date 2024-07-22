import java.util.concurrent.*;

/*
 * We will get right answer since we use synchronized keyword to ensure lock
 */

public class SynchronizationWithLock {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executor.execute(new AddAnAmount());
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("What is balance? " + Account.getBalance());

    }

    private static class AddAnAmount implements Runnable {
        public void run() {
            Account.deposit(1);
        }
    }

    private static class Account {
        private static int balance = 0;

        public static int getBalance() {
            return balance;
        }

        // ADD SYNCHRONIZED KEYWORD TO ENSURE LOCK AND MAKE METHOD THREAD SAFE
        public static synchronized void deposit(int amount) {
            int newbalance = balance + amount;

            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
            }
            balance = newbalance;
        }
    }
}