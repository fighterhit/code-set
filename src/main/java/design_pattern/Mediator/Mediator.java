package design_pattern.Mediator;

/**
 * Mediator
 */
public interface Mediator {
    void register(String dName, Department department);

    void command(String dName);
}
