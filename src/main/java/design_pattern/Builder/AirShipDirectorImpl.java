package design_pattern.Builder;

/**
 * @author Fighter.
 */
public class AirShipDirectorImpl implements AirShipDirector {

    private AirShipBuilder builder;

    public AirShipDirectorImpl(AirShipBuilder builder) {
        this.builder = builder;
    }

    @Override
    public AirShip directAirShip() {
        Engine engine = builder.buildEngine();
        OrbitalModule orbitalModule = builder.buildOrbitalModule();
        EscapeTower escapeTower = builder.buildEscapeTower();
        AirShip airShip = new AirShip();
        airShip.setEngine(engine);
        airShip.setOrbitalModule(orbitalModule);
        airShip.setEscapeTower(escapeTower);
        return airShip;
    }
}
