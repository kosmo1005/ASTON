
public class IceCreamMan implements Runnable {
    private Box box;
    Store store;


    public IceCreamMan(Box box, Store store) {
        this.box = box;
        this.store = store;
    }

    @Override
    public void run() {
        makeIceCream();
    }

    public synchronized void makeIceCream() {
        while (true){
            while (box.fillingTheBox() < 20){
                box.putIceCreamInABox(new IceCream(iceCreamWeight(), cupWidth(), cupDepth()));
                System.out.println("Мороженщик сделал мороженое " +
                        box.fillingTheBox() + " в потоке " + Thread.currentThread().getName());
            }
            synchronized (box) {
                try {
                    System.out.println("Мороженщик ждёт, пока кладовщик отнесет мороженое. Поток" +
                            Thread.currentThread().getName());
                    box.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (store.getCount() >= 15) {
                break;
            }
        }
    }

    private double iceCreamWeight() {
        return 150 + generateRandomNumber();
    }

    private double cupWidth() {
        return 14 + generateRandomNumber();
    }

    private double cupDepth() {
        return 27 + generateRandomNumber();
    }

    private double generateRandomNumber() {
        double max = 20.9;
        double min = 1;
        return min + (Math.random() * max - min + 1);
    }


}

