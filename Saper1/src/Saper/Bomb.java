package Saper;

class Bomb {
    private Matrix bombMap;
    private int totalBomb;
    Bomb(int totalBomb) {
        this.totalBomb = totalBomb;
        fixBombCount();
    }
    void start() {
        bombMap = new Matrix(Box.ZERO);
        for(int i = 0; i < totalBomb;i++) {
            placeBomb();
        }
    }
    private void fixBombCount() {
        int maxBomb = Ranges.getSize().x * Ranges.getSize().y / 2;
        if(totalBomb > maxBomb) {
            totalBomb = maxBomb;
        }
    }
    private void placeBomb() {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, Box.BOMB);
            insNumbersAroundBomb(coord);
            break;
        }
    }
    Box get(Coord coord) {
        return bombMap.get(coord);
    }
    private void  insNumbersAroundBomb(Coord coord) {
        for (Coord around : Ranges.getCoordAround(coord)) {
            if(Box.BOMB != bombMap.get(around))
            bombMap.set(around, bombMap.get(around).getNextNumberBox());
        }
    }
    public int getTotalBombs() {
        return totalBomb;
    }
}
