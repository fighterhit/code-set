package design_pattern.Builder;

/**
 * @author Fighter.
 */
public class AirShipBuilderImpl implements AirShipBuilder {
    @Override
    public Engine buildEngine() {
        System.out.println("My Engine");
        return new Engine("My Engine");
    }

    @Override
    public OrbitalModule buildOrbitalModule() {
        System.out.println("My OrbitalModule");
        return new OrbitalModule("My OrbitalModule");
    }

    @Override
    public EscapeTower buildEscapeTower() {
        System.out.println("My EscapeTower");
        return new EscapeTower("My EscapeTower");
    }
}
