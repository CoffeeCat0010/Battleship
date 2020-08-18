public abstract class Ship {
    private int bowRow, bowColumn;
    private int length;
    private boolean horizontal;
    private boolean[] hit;

    public Ship(int length) {
        hit = new boolean[8];
        this.length = length;
    }

    public abstract String getShipType();

    public boolean okToPlaceShip(int row, int column, boolean horizontal, Ocean ocean) {
        int maxY, maxX, minY, minX;
        if (horizontal) {
            maxY = row + 1;
            minY = row - 1;
            maxX = column + 1;
            minX = column - length;
        } else {
            maxX = column + 1;
            minX = column - 1;
            maxY = row + 1;
            minY = row - length;
        }
        boolean isOutOfBounds = (maxX - 1) > 19 || (maxY - 1) > 19 || (minX + 1) < 0 || (minY + 1) < 0;
        if (isOutOfBounds) return false;
        for (int y = minY < 0 ? 0 : minY; y <= (maxY > 19 ? 19 : maxY); y++) {
            for (int x = minX < 0 ? 0 : minX; x <= (maxX > 19 ? 19 : maxX); x++) {
                if (!ocean.getShipsArray()[y][x].getShipType().equals("Empty")) return false;
            }
        }
        return true;
    }

    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
       // if (okToPlaceShip(row, column, horizontal, ocean)) {
            setBowColumn(column);
            setBowRow(row);
            for (int i = 0; i < length; i++) {
                if (horizontal)
                    ocean.getShipsArray()[row][column - i] = this;
                else
                    ocean.getShipsArray()[row - i][column] = this;
            }
       // }
    }

    public boolean shootAt(int row, int column) {
        if (isSunk()) return false;
        if (getShipType().equals("Empty")) return false;
        if (!horizontal)
            hit[bowColumn - column] = true;
        else
            hit[bowRow - row] = true;
        return true;
    }

    public boolean isSunk() {
        for (int i = 0; i < length && i < 8; i++) {
            if (hit[i] == false) return false;
        }
        return true;
    }

    public String toString() {
        return isSunk() ? "x" : "S";
    }

    public int getBowRow() {
        return bowRow;
    }

    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    public int getBowColumn() {
        return bowColumn;
    }

    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean[] getHit() {
        return hit;
    }

    public void setHit(boolean[] hit) {
        this.hit = hit;
    }
}
