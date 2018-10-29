package design_pattern.Builder;

/**
 * @author Fighter.
 */
public class Client {
    public static void main(String[] args) {
        AirShipDirector airShipDirector = new AirShipDirectorImpl(new AirShipBuilderImpl());
        AirShip airShip = airShipDirector.directAirShip();
        System.out.println(airShip.getEngine().getName());
        airShip.launch();
    }
}
