package Saper;

public class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.y = y;
        this.x = x;
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Coord) {
            Coord to = (Coord)o;
            return to.x == x && to.y == y;
        }
        return super.equals(o);
    }
}
