import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;

public class SyncWithLock {
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
        private static Lock lock = new ReentrantLock();
        private static int balance = 0;

        public static int getBalance() {
            return balance;
        }

        public static void deposit(int amount) {
            lock.lock();

            try {
                int newbalance = balance + amount;
                Thread.sleep(5);

                balance = newbalance;
            } catch (InterruptedException ex) {
            } finally {
                lock.unlock();
            }
        }
    }
}
