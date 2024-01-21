import java.util.ArrayList;

public class Box {

    int count = 0;
    static final int LUCKY_ACE_CREAM = 7;

    private ArrayList<IceCream> box;

    public Box() {
        this.box = new ArrayList<>();

    }
    public synchronized void putIceCreamInABox (IceCream iceCream){
        box.add(iceCream);
        count++;
        if (count == LUCKY_ACE_CREAM){
            System.out.println(Thread.currentThread().getName() + " создал счасливую мороженку.");
        }
    }

    public synchronized int fillingTheBox (){
        return count;
    }
    public synchronized void setCount(int count) {
        this.count = count;
    }
    public synchronized void toEmptyBox() {
        box.clear();
    }
}
