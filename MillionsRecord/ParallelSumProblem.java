package MillionsRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelSumProblem {

    public static void main(String[] args) {
        int size = 1_000_000;
        int[] array = new Random().ints(size, 1, 100).toArray();

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available cores: " + cores);

        List<Future<Long>> futures = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(cores);

        int chunkSize = size / cores;
        for (int i = 0; i < cores; i++) {
            int start = i * chunkSize;
            int end = (i == cores - 1) ? size : start + chunkSize;
            SumTask task = new SumTask(array, start, end);
            futures.add(executor.submit(task));
        }

        long totalSum = 0;

        for (int i = 0; i < futures.size(); i++) {
            try {
                totalSum += futures.get(i).get();
            } catch (Exception e) {
                // TODO: handle exception
                System.err.println("Error retrieving result from thread " + i);
            }

        }

        System.out.println("Total sum: " + totalSum);

        executor.shutdown();
    }
}
// terminal commands
// javac MillionsRecord\ParallelSumProblem.java MillionsRecord\SumTask.java
// java MillionsRecord.ParallelSumProblem
