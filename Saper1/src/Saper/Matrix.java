package Saper;

class Matrix {
    private Box[] [] matrix;
    Matrix(Box defaultBox) {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coord coord : Ranges.getAllCoords())
            matrix[coord.x][coord.y] = defaultBox;
    }
    Box get(Coord coord) {
        if (Ranges.inRanges(coord)) {
            return matrix[coord.x][coord.y];
        }
        return null;
    }
    void set(Coord coord, Box box) {
        if (Ranges.inRanges(coord)) {
            matrix[coord.x][coord.y] = box;
        }
    }
}
