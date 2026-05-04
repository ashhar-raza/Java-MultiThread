import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableWithExecutors {
    

    static class SquareCalculator implements Callable<Integer>
    {
        private int number;

        public SquareCalculator(int number)
        {
            this.number = number;
        }

        @Override
        public Integer call() throws Exception
        {
            Thread.sleep(5000); // Simulate time-consuming task
            System.out.println("Thread Name: " + Thread.currentThread().getName());
            return number * number;
        }
    }

    public static void main(String[] args) throws Exception{

        ExecutorService executor =  Executors.newSingleThreadExecutor();

        SquareCalculator task = new SquareCalculator(5);

        Future<Integer> future = executor.submit(task);




        //Retrieve the result of the computation
        Integer result = future.get(); // This will block until the result is available

        System.out.println("Square of 5 is: " + result);

        // Shutdown the executor service
        executor.shutdown();
        
    }
}


//Executing commands in terminal
//javac CallableWithExecutors.java
//java CallableWithExecutors