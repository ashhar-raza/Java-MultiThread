package WebScarpper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebScrapper {

    public static void main(String[] args) throws InterruptedException, Exception {
        List<String> urls = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            urls.add("http://example.com/page" + i);
        }

        System.out.println("For Runnable: press 1 and for Callable: press 2");
        int choice = new java.util.Scanner(System.in).nextInt();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        if (choice == 1) {

            runScrapper(urls, fixedThreadPool, "Fixed Thread Pool");
            runScrapper(urls, cachedThreadPool, "Cached Thread Pool");
        } else if (choice == 2) {
            runScrapperCallable(urls, fixedThreadPool, "Fixed Thread Pool");
            runScrapperCallable(urls, cachedThreadPool, "Cached Thread Pool");
        } else {
            System.out.println("Invalid choice");
        }

    }

    public static void runScrapper(List<String> urls, ExecutorService executor, String poolType) {
        long startTime = System.currentTimeMillis();

        for (String url : urls) {
            executor.submit(new ScrapURL(url));
        }

        executor.shutdown(); // stop accepting new tasks


        try {
            // Wait until all tasks are finished
            executor.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println(poolType + " took " + (endTime - startTime) + " ms");
    }

    public static void runScrapperCallable(List<String> urls, ExecutorService executor, String poolType)
            throws InterruptedException, Exception {
        long startTime = System.currentTimeMillis();
        List<Future<Void>> futures = new ArrayList<>();

        for (String url : urls) {
            futures.add(executor.submit(new ScarpUrlCallable(url)));
        }

        for (Future<Void> future : futures) {
            future.get();
        }

        long endTime = System.currentTimeMillis();

        System.out.println(poolType + " took " + (endTime - startTime) + " ms");

        executor.shutdown(); // stop accepting new tasks
    }

}

// command to compile and run
// javac WebScarpper\WebScrapper.java WebScarpper\ScrapURL.java
// java WebScarpper.WebScrapper