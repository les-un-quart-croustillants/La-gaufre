package Modele;

public class Couple {
    public int i, j;

    public Couple() {
        this(0,0);
    }

    public Couple(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public String toString() {
        return "("+ i + ","+ j + ")";
    }
}
