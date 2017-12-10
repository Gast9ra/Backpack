package backpack;

//конструктор вещи
public class Thing
        implements Comparable<Thing> {

    private int weight;
    private int price;

    private double d;

    Thing(int weight, int price) {
        this.weight = weight;
        this.price = price;

        d = (double) price / price;
    }

    int getPrice() {
        return price;
    }



    int getWeight() {
        return weight;
    }


    @Override
    public int compareTo(Thing o) {
        return ((Double) o.d).compareTo((Double) d);
    }
}
