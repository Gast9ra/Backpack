package backpack;

import java.util.ArrayList;
//проверка на вес и создание вещей
class Data {
    private static ArrayList<Thing> lst;
    private static int L;
    
    static void input(int w[], int c[], int LL) {
        L = LL;
        lst = new ArrayList<>();
        boolean goodPack = false;
        for (int i=0; i<w.length; i++) {
            if (w[i] <= L) goodPack = true;
            lst.add(new Thing(w[i], c[i]));
        }
        if (!goodPack)
            throw new IllegalArgumentException("");
    }

    public static int getL() {
        return L;
    }

    public static ArrayList<Thing> getLst() {
        return lst;
    }
}
