package design_pattern.Builder;

/**
 * @author Fighter.
 */
public class AirShip {
    //轨道舱
    private OrbitalModule orbitalModule;
    //发动机
    private Engine engine;
    //逃逸塔
    private EscapeTower escapeTower;

    public void launch() {
        System.out.println("launch!");
    }

    public OrbitalModule getOrbitalModule() {
        return orbitalModule;
    }

    public void setOrbitalModule(OrbitalModule orbitalModule) {
        this.orbitalModule = orbitalModule;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public EscapeTower getEscapeTower() {
        return escapeTower;
    }

    public void setEscapeTower(EscapeTower escapeTower) {
        this.escapeTower = escapeTower;
    }
}

class OrbitalModule {
    private String name;

    public OrbitalModule(String myOrbitalmodule) {
        name = myOrbitalmodule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Engine {
    private String name;

    public Engine(String myEngine) {
        name = myEngine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class EscapeTower {
    private String name;

    public EscapeTower(String myEscapeTower) {
        name = myEscapeTower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

