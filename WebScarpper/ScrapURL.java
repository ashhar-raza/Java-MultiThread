package WebScarpper;

public class ScrapURL implements Runnable {

    private final String url;

    public ScrapURL(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // TODO: handle exception
            Thread.currentThread().interrupt();
        }
    }
}
