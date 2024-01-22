// Средняя скорость работы в 1 поток 380-410ms (задача была наполнить коробку 5млн мороженок).
// Средняя скорость работы в 2 потока 900-1200ms

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Box box = new Box();
        Store store = new Store();
        Thread iceMan1 = new Thread(new IceCreamMan(box,store));
        Thread iceMan2 = new Thread(new IceCreamMan(box,store));

        iceMan1.start();
        iceMan2.start();
        iceMan1.join();
        iceMan2.join();

        ExecutorService iceCreamManExecutors = Executors.newFixedThreadPool(5);
        Box boxForExecutors = new Box();
        Store storeForExecutor = new Store();
        for (int i = 0; i < 5; i++) {
            iceCreamManExecutors.execute(new IceCreamMan(boxForExecutors, storeForExecutor));
            System.out.println("Мороженщик заступил на смену");
        }
        Thread storekeeper = new Thread(new Storekeeper(boxForExecutors,storeForExecutor));
        storekeeper.start();
            System.out.println("Кладовщик заступил на смену");
            iceCreamManExecutors.shutdown();

        }

    }


