import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableWithMultiThreads {

    public static class BankTask implements Callable<String> {
        private String taskName;

        BankTask(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(2000); // Simulate time-consuming task
            return "Task " + taskName + " completed by " + Thread.currentThread().getName();
        }
    }

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 5; i++) {
            BankTask task = new BankTask("Task" + i);
            
            String result = executor.submit(task).get(); // This will block until the result is available

            System.out.println(result);

            System.out.println("Thread Name: " + Thread.currentThread().getName());

            System.out.println("Submitted " + task.taskName);
        }
        executor.shutdown();
        System.out.println("All tasks completed.");
    }
}

// executing commands in terminal
// javac CallableWithMultiThreads.java
// java CallableWithMultiThreads
