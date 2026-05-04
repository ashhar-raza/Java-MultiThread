package MillionsRecord;

import java.util.concurrent.Callable;

public class SumTask implements Callable<Long> {
    
    int [] arr;
    int start;
    int end;

    public SumTask(int [] arr , int start , int end)
    {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }


    @Override
    public Long call() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += arr[i];
        }
        return sum;
    }
}
