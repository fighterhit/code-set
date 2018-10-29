package design_pattern.Facade;

/**
 * @author Fighter.
 */
public class Facade {

    private SubSystem subSystem = new SubSystem();

    public void watchMovie() {
        subSystem.turnOnTV();
        subSystem.setCD("a movie");
        subSystem.starWatching();
    }
}
