package WebScarpper;

import java.util.concurrent.Callable;

public class ScarpUrlCallable implements Callable<Void> {

    private final String url;

    public ScarpUrlCallable(String url) {
        this.url = url;
    }

    @Override
    public Void call() throws Exception {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }
        return null;
    }
}
