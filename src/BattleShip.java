public class BattleShip extends Ship{

        public BattleShip(){
            super(8);
        }

        @Override
        public String getShipType() {
            return "battleship";
        }
}
