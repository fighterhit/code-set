package design_pattern.Builder;

/**
 * @author Fighter.
 */
public interface AirShipBuilder {
    Engine buildEngine();
    OrbitalModule buildOrbitalModule();
    EscapeTower buildEscapeTower();
}
