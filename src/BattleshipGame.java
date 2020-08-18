import java.util.Scanner;

public class BattleshipGame {
    public static void main(String[] args){
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        ocean.print();
        while(!ocean.isGameOver()){
            boolean hit = false;
            System.out.println("Please input 5 coordinates to hit like so: row, column; row, column; row, column; row, column; row, column");
            Scanner input = new Scanner(System.in);
            String coordinates;
            coordinates = input.nextLine();
            coordinates = coordinates.replaceAll(" ", "");
            String[] coords = coordinates.split(";");
            for(String str: coords) {
                String[] yx = str.split(",");
                int row = Integer.parseInt(yx[0]);
                int column = Integer.parseInt(yx[1]);
                if(ocean.shootAt(row, column)) hit = true;
            }
            if(hit)
                System.out.println("Hit!");
            else
                System.out.println("Miss!");
            ocean.print();
            if(ocean.isGameOver()) System.out.println("You Win!");
        }
    }
}
