import java.io.IOException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CacheThreadPool
{
    static class BankTask implements Runnable
    {
        private String taskName;

        public BankTask(String taskName)
        {
            this.taskName = taskName;
        }

        @Override
        public void run()
        {
            System.out.println("-------------------------- Executing Task " + taskName + " by " + Thread.currentThread().getName() + " --------------------------");
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
            System.out.println("************************** Competed Task " + taskName + " by " + Thread.currentThread().getName() + " **************************");
        }
    }


    public static void main(String[] args)
    {
        ExecutorService cacheExecutor = Executors.newCachedThreadPool();

        System.out.println("Submitting First Batch of Tasks");

        for(int i = 1 ; i <= 5 ; i++)
        {
            cacheExecutor.submit(new BankTask("Task " + i));
        }
        
        System.out.println("Submitting Second Batch of Tasks");

        for(int i = 6 ; i <= 10 ; i++)
        {
            cacheExecutor.submit(new BankTask("Task " + i));
        }

        cacheExecutor.shutdown();
        //
        System.out.println("All tasks submitted.");
    }
}