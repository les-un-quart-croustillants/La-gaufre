package Modele.Plateau;

public class Couple {
    public int x, y;

    public Couple() {
        this(0,0);
    }

    public Couple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "("+ x + ","+ y + ")";
    }
}
