package ProducerConsumerWithSemaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

class Store {
    int maxSize;
    List<Object> items;

    public Store(int maxSize) {
        this.maxSize = maxSize;
        this.items = new ArrayList<>();
    }

    public  void addItem(String producerName) {
        items.add(new Object());
        System.out.println(producerName + " Produced a new item. The new size is " + items.size());

    }

    public  void removeItem(String consumerName) {
        items.remove(items.size() - 1);
        System.out.println((consumerName + " Consumed an item. The new size is " + items.size()));
    }
}

class Producer implements Runnable {
    Store store;
    Semaphore producerSemaphore;
    Semaphore consuemerSemaphore;
    String name;

    public Producer(Store store, Semaphore producerSemaphore, Semaphore consuemerSemaphore, String name) {
        this.store = store;
        this.producerSemaphore = producerSemaphore;
        this.consuemerSemaphore = consuemerSemaphore;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(name + " is waiting to produce an item...");
                producerSemaphore.acquire();

                System.out.println(name + " is producing an item...");
                store.addItem(name);
                System.out.println(name + " has produced an item.");
                consuemerSemaphore.release();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    Store store;
    Semaphore producerSemaphore;
    Semaphore consuemerSemaphore;
    String name;

    public Consumer(Store store, Semaphore producerSemaphore, Semaphore consuemerSemaphore, String name) {
        this.store = store;
        this.producerSemaphore = producerSemaphore;
        this.consuemerSemaphore = consuemerSemaphore;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(name + " is waiting to consume an item...");
                consuemerSemaphore.acquire();
                System.out.println(name + " is consuming an item...");
                store.removeItem(name);
                System.out.println( name + " has consumed an item.");
                producerSemaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

public class ProducerConsumerWithSemaphore {

    public static void main(String[] args) {
        Store store = new Store(5);
        Semaphore producerSemaphore = new Semaphore(5);
        Semaphore consumerSemaphore = new Semaphore(0);

        for (int i = 1; i <= 8; i++) {
            Thread producer = new Thread(new Producer(store, producerSemaphore, consumerSemaphore, "Producer-" + i));
            producer.start();
        }

        for (int i = 1; i <= 8; i++) {
            Thread consumer = new Thread(new Consumer(store, producerSemaphore, consumerSemaphore, "Consumer-" + i));
            consumer.start();
        }
    }

}

// executable commands in the terminal:
// javac ProducerConsumerWithSemaphore.java
// java ProducerConsumerWithSemaphore