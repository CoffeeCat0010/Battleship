import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OceanTest {
    Ocean testOcean;

    @Before
    public void setUp() throws Exception {
        testOcean = new Ocean();
    }

    @Test
    public void placeAllShipsRandomly() {
        testOcean.placeAllShipsRandomly();
        boolean isAShip = false;
        for (int y = 0; y < 19; y++) {
            for (int x = 0; x < 19; x++) {
                boolean anotherShipNearBy = false;
                if (!testOcean.getShipsArray()[y][x].getShipType().equals("Empty")) {
                    //order matters because this is a "short-circuit and" and the second condition cannot exist if the first is false
                    anotherShipNearBy = ((x - 1) > 0 && !testOcean.getShipsArray()[y][x - 1].getShipType().equals("Empty")) &&
                            ((y - 1) > 0 && !testOcean.getShipsArray()[y - 1][x].getShipType().equals("Empty")) &&
                            ((x + 1) < 19 && !testOcean.getShipsArray()[y][x + 1].getShipType().equals("Empty")) &&
                            ((y + 1) < 19 && !testOcean.getShipsArray()[y + 1][x].getShipType().equals("Empty")) &&
                            ((x - 1) > 0 && (y - 1 > 0) && !testOcean.getShipsArray()[y - 1][x - 1].getShipType().equals("Empty")) &&
                            ((x - 1) > 0 && (y + 1 < 19) && !testOcean.getShipsArray()[y + 1][x - 1].getShipType().equals("Empty")) &&
                            ((x + 1) < 19 && (y + 1 < 19) && !testOcean.getShipsArray()[y + 1][x + 1].getShipType().equals("Empty")) &&
                            ((x + 1) < 19 && (y - 1 > 0) && !testOcean.getShipsArray()[y - 1][x + 1].getShipType().equals("Empty"));
                    isAShip = true;
                }
                assertFalse("There is another ship too close", anotherShipNearBy);
            }
        }
        assertTrue("Ship expected", isAShip);
    }

    @Test
    public void isOccupied() {
        testOcean.getShipsArray()[1][2] = new BattleCruiser();
        assertTrue("This is expected to be true", testOcean.isOccupied(1, 2));
        assertFalse("This is expected to be false", testOcean.isOccupied(19, 19));
        assertFalse("This should be false if the square being tested is not within the game board", testOcean.isOccupied(14, 30));
    }

    @Test
    public void shootAt() {
        BattleShip testShip1 = new BattleShip();
        Cruiser testShip2 = new Cruiser();
        testShip1.placeShipAt(10, 10, true, testOcean);
        testShip2.placeShipAt(18, 18, false, testOcean);
        assertTrue("Expected hit to be true", testOcean.shootAt(17, 18));
        assertTrue("Expected hit to be true", testOcean.shootAt(18, 18));
        assertFalse("Expected hit to be false", testOcean.shootAt(19, 18));
        assertFalse("Expected hit to be false", testOcean.shootAt(18, 17));
        assertFalse("Expected hit to be false", testOcean.shootAt(18, 19));

        assertTrue("Expected hit to be true", testOcean.shootAt(10, 9));
        assertTrue("Expected hit to be true", testOcean.shootAt(10, 10));
        assertFalse("Expected hit to be false", testOcean.shootAt(10, 11));
        assertFalse("Expected hit to be false", testOcean.shootAt(11, 10));
        assertFalse("Expected hit to be false", testOcean.shootAt(9, 10));
    }

    @Test
    public void isGameOver() {
        testOcean.shipsSunk = 12;
        Submarine sub = new Submarine();
        sub.placeShipAt(0, 2, true, testOcean);
        testOcean.shootAt(0, 0);
        testOcean.shootAt(0, 1);
        testOcean.shootAt(0, 2);
        assertTrue(testOcean.isGameOver());
    }

}