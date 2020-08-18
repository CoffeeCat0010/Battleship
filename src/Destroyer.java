public class Destroyer extends Ship {
    public Destroyer() {
        super(4);
    }

    @Override
    public String getShipType() {
        return "destroyer";
    }
}
