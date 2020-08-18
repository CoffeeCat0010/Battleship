import java.util.Random;

public class Ocean {
    public static final int BOARD_SIZE = 20;
    private static Ship[] startingShips = {new BattleShip(), new BattleCruiser(), new Cruiser(), new Cruiser(), new LightCruiser(), new LightCruiser(), new Destroyer(),
            new Destroyer(), new Destroyer(), new Submarine(), new Submarine(), new Submarine(), new Submarine()};
    private Ship[][] ships;
    private boolean hitAttempts[][];
    private int shotsFired;
    private int hitCount;
    public int shipsSunk;

    public Ocean() {
        ships = new Ship[BOARD_SIZE][BOARD_SIZE];
        hitAttempts = new boolean[BOARD_SIZE][BOARD_SIZE];
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                ships[y][x] = new EmptySea();
            }
        }
    }

    public void placeAllShipsRandomly() {
        int shipsPlaced = 0;
        Random random = new Random(System.currentTimeMillis());
        BattleShip battleShip = new BattleShip();
        int row, column, placed;
        boolean horizontal;
        for (int i = 0; i < 13; i++) {
            do {
                row = random.nextInt(BOARD_SIZE);
                column = random.nextInt(BOARD_SIZE);
                horizontal = random.nextBoolean();
            } while (!startingShips[i].okToPlaceShip(row, column, horizontal, this));
                startingShips[i].placeShipAt(row, column, horizontal, this);
        }
    }

    public boolean isOccupied(int row, int column) {
        if (row < 0 || row > 19 || column < 0 || column > 19) return false;
        return !ships[row][column].getShipType().equals("Empty");
    }

    public boolean shootAt(int row, int column) {
        boolean result = ships[row][column].shootAt(row, column);
        hitAttempts[row][column] = true;
        if (ships[row][column].isSunk() && result == true) shipsSunk++;
        return result;
    }

    public boolean isGameOver() {
        return shipsSunk >= 13;
    }

    void print() {
        for (int i = 0; i < BOARD_SIZE ; i++) {
            System.out.print("\t"+ i);
        }
        for (int row = 0; row < BOARD_SIZE ; row++) {
            System.out.println();
            System.out.print(row + "\t");
            for(int column = 0; column < BOARD_SIZE; column++){
                if (hitAttempts[row][column] == false) {
                    System.out.print(".\t");
                }else{
                    if(ships[row][column].getShipType().equals("Empty")) {
                        System.out.print("-\t");
                    }
                    else {
                        System.out.print(ships[row][column] + "\t");
                    }
                }
            }
        }
        System.out.println();
    }

    public static String debugPrint(Ship[][] ocean) {
        String output = new String();
        for (int y = 0; y < 20 ; y++) {
            for (int x = 0; x < 20; x++) {
                output = output + (ocean[y][x] + "\t");
            }
            output = output + "\n";
        }
        return output;
    }


    public int getShotsFired() {
        return shotsFired;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getShipsSunk() {
        return shipsSunk;
    }

    public Ship[][] getShipsArray() {
        return ships;
    }
}
