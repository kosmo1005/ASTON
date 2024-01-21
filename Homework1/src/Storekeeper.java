import java.util.concurrent.TimeUnit;

public class Storekeeper implements Runnable {
    private Box box;
    private Store store;

    public Storekeeper(Box box, Store store) {
        this.box = box;
        this.store = store;
    }

    @Override
    public void run() {
        takeToStorage();
        }

    private synchronized void takeToStorage (){
        while (true){
            if (store.getCount() >= 30) {
                System.out.println("Склад заполнен");
                break;
            }
            if(box.fillingTheBox() >= 20){
                System.out.println("Кладовщик уносит коробку с мороженым на склад.");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (store){
                    store.increment();
                }
                synchronized (box){
                    box.setCount(0);
                    box.toEmptyBox();
                    box.notifyAll();
                }

            }

        }

    }
}
