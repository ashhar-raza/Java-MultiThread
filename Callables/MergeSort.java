import java.util.Arrays;

public class MergeSort {

    static class Sorting implements Runnable {
        int arr[];

        public Sorting(int arr[]) {
            this.arr = arr;
        }

        @Override
        public void run() {
            Arrays.sort(arr);
            System.out.println(
                    "Thread Name: " + Thread.currentThread().getName() + " - Sorted Array: " + Arrays.toString(arr));
        }
    }

    public static int[] merge(int arr1[], int arr2[]) {
        int[] mergedArr = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        int n = arr1.length, m = arr2.length;

        while ((i < n) && (j < m)) {

            if (arr1[i] < arr2[j]) {
                mergedArr[k++] = arr1[i++];
            } else {
                mergedArr[k++] = arr2[j++];
            }

        }

        while (i < n) {
            mergedArr[k++] = arr1[i++];
        }

        while (j < m) {
            mergedArr[k++] = arr2[j++];
        }
        return mergedArr;

    }

    public static void main(String[] args) {
        int arr1[] = { 5, 2, 9, 1, 5 };
        int arr2[] = { 3, 4, 7, 6 };

        Thread t1 = new Thread(new Sorting(arr1));
        Thread t2 = new Thread(new Sorting(arr2));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
           System.out.println("Merging in " + Thread.currentThread().getName());
            int mergedArr[] = merge(arr1, arr2);
            System.out.println("Merged Array: " + Arrays.toString(mergedArr));
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

// executing commands in terminal
// javac MergeSort.java
// java MergeSort
