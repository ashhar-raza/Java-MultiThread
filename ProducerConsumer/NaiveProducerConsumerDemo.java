import java.util.ArrayList;
import java.util.List;

class Store
{
    int maxSize;
    List<Object> items;

    public Store (int maxSize)
    {
        this.maxSize = maxSize;
        this.items = new ArrayList<>();
    }
    
    public List<Object> getItems()
    {
        return items;
    }

    public int getMaxSize()
    {
        return maxSize;
    }

    public void addItem(String name)
    {
        items.add(new Object());
        System.out.println( "✅ " +name + " Produced an item. Total items: " + items.size());
    }

    public void removeItem(String name)
    {
        items.remove(items.size() - 1);
        System.out.println( "🗑️ " + name + " Consumed an item. Total items: " + items.size()); 
    }

}

class Producer implements Runnable
{
    private Store store;
    private String name;

    public Producer(Store store, String name)
    {
        this.store = store;
        this.name = name;
    }

    @Override
    public void run()
    {
        while(true)
        {
            if(store.getItems().size() < store.getMaxSize())
            {
                store.addItem(name);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO: handle exception
                Thread.currentThread().interrupt();
            }
        }
    }

}


class Consumer implements Runnable
{
    private Store store;
    private String name;

    public Consumer(Store store, String name)
    {
        this.store = store;
        this.name = name;
    }

    @Override
    public void run()
    {
        while(true)
        {
            if(store.getItems().size() > 0)
            {
                store.removeItem(name);
            }

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
public class NaiveProducerConsumerDemo
{

   public static void main(String[] args)
   {
        Store store = new Store(5);
       
        for(int i = 1 ; i <= 5 ; i++)
        {
            Thread producer = new Thread(new Producer(store , "Producer-" + i));
            producer.start();
        }

        for(int i = 1 ; i <= 5 ; i++)
        {
            Thread consumer = new Thread(new Consumer(store , "Consumer-" + i));
            consumer.start();
        }
   }


}


// executable terminal command:
// javac NaiveProducerConsumerDemo.java 
//  java NaiveProducerConsumerDemo