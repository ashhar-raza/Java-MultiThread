import java.io.IOException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {


    static class BankTask implements Runnable
    {
        private String taskName ;

        BankTask( String name)
        {
              this.taskName = name;
        }

        @Override
        public void run()
        {
            System.out.println("-------------------------- Executing Task " + taskName + " by " + Thread.currentThread().getName() + " --------------------------");
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
            System.out.println("************************** Competed Task " + taskName + " by " + Thread.currentThread().getName() + " **************************");
        }
    }

    public static void main(String[] args) throws IOException
    {
        ExecutorService fixedExecutor = Executors.newFixedThreadPool(5);

        for(int i = 1 ; i <= 20 ; i++)
        {
            fixedExecutor.submit(new BankTask("Task " + i));
        }
        //
        fixedExecutor.shutdown();
        //
        System.out.println("All tasks submitted.");
    }
}
