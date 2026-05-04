import java.util.ArrayList;
import java.util.List;

class SyncStore {
    private int maxSize;
    List<Object> items;

    public SyncStore(int maxSize) {
        this.maxSize = maxSize;
        this.items = new ArrayList<>();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public List<Object> getItem() {
        return items;
    }

    public void addItem(String producerName) {
        items.add(new Object());
        System.out.println("✅ " + producerName + " Produced an item. Total items: " + items.size());
    }

    public void removeItem(String consumerName) {
        items.remove(items.size() - 1);
        System.out.println("🗑️ " + consumerName + " Consumed an item. Total items: " + items.size());
    }
}

class SyncProducer implements Runnable {
    private SyncStore store;
    private String name;

    public SyncProducer(SyncStore store, String name) {
        this.store = store;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (store) {
                if (store.getItem().size() < store.getMaxSize()) {
                    store.addItem(name);
                }

            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SyncConsumer implements Runnable {
    private SyncStore store;
    private String name;

    public SyncConsumer(SyncStore store, String name) {
        this.store = store;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (store) {
                if (store.getItem().size() > 0) {
                    store.removeItem(name);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SyncProducerConsumer {

    public static void main(String[] args) {
        SyncStore store = new SyncStore(5);

        for (int i = 1; i <= 5; i++) {
            Thread producer = new Thread(new SyncProducer(store, "Producer-" + i));
            producer.start();
        }
        for (int i = 1; i <= 5; i++) {
            Thread consumer = new Thread(new SyncConsumer(store, "Consumer-" + i));
            consumer.start();
        }
    }

}

// executing commands in the terminal:
// javac SyncProducerConsumer.java
// java SyncProducerConsumer
