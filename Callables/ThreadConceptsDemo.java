import java.util.concurrent.*;

public class ThreadConceptsDemo {

    // =========================
    // 1. wait() / notify()
    // =========================
    static class SharedResource {
        private boolean available = false;

        public synchronized void produce() throws InterruptedException {
            while (available) {
                wait();
            }
            System.out.println("Produced by " + Thread.currentThread().getName());
            available = true;
            notify(); // wake one thread
        }

        public synchronized void consume() throws InterruptedException {
            while (!available) {
                wait();
            }
            System.out.println("Consumed by " + Thread.currentThread().getName());
            available = false;
            notify();
        }
    }

    static class Producer implements Runnable {
        private SharedResource resource;

        Producer(SharedResource resource) {
            this.resource = resource;
        }

        public void run() {
            try {
                for (int i = 0; i < 3; i++) {
                    resource.produce();
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {
        private SharedResource resource;

        Consumer(SharedResource resource) {
            this.resource = resource;
        }

        public void run() {
            try {
                for (int i = 0; i < 3; i++) {
                    resource.consume();
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // =========================
    // 2. join()
    // =========================
    static class JoinExample {
        public static void runDemo() throws Exception {
            Thread t1 = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Worker Thread Finished");
                } catch (Exception e) {}
            });

            t1.start();

            t1.join(); // main waits

            System.out.println("Main resumes after join");
        }
    }

    // =========================
    // 3. Executor + Callable
    // =========================
    static class BankTask implements Callable<String> {
        String taskName;

        BankTask(String name) {
            this.taskName = name;
        }

        @Override
        public String call() throws Exception {
            System.out.println("Executing " + taskName + " on " + Thread.currentThread().getName());
            Thread.sleep(1000);
            return "Completed " + taskName;
        }
    }

    static class ExecutorExample {
        public static void runDemo() throws Exception {
            ExecutorService executor = Executors.newFixedThreadPool(3);

            Future<String>[] futures = new Future[5];

            for (int i = 0; i < 5; i++) {
                futures[i] = executor.submit(new BankTask("Task " + (i + 1)));
            }

            for (Future<String> f : futures) {
                System.out.println(f.get()); // wait for result
            }

            executor.shutdown();
        }
    }

    // =========================
    // MAIN METHOD
    // =========================
    public static void main(String[] args) throws Exception {

        System.out.println("===== wait/notify Demo =====");
        SharedResource resource = new SharedResource();

        Thread producer = new Thread(new Producer(resource));
        Thread consumer = new Thread(new Consumer(resource));

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("\n===== join() Demo =====");
        JoinExample.runDemo();

        System.out.println("\n===== Executor Demo =====");
        ExecutorExample.runDemo();

        System.out.println("\nMain Thread Ends");
    }
}

// executing commands in terminal  
// javac ThreadConceptsDemo.java
// java ThreadConceptsDemo 
