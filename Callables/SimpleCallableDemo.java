import java.util.concurrent.Callable;
class SimpleCallableDemo
{

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
            // throw new Exception("Intentional Exception for demonstration");
            Thread.sleep(5000); // Simulate time-consuming task
            System.out.println("Thread Name: " + Thread.currentThread().getName());
            return number * number;
        }
    }

    public static void main(String[] args) 
    {
        SquareCalculator task = new SquareCalculator(5);
        try {
            Integer result = task.call();
            System.out.println("Square of 5 is: " + result);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("An error occurred: " + e.getMessage());
        }
       
  
        System.out.println("The Main Thread Ends");
    }
}