package design_pattern.Mediator;


import java.util.HashMap;
import java.util.Map;

/**
 * @author Fighter.
 */
public class ConcreteMediator implements Mediator {

    private Map<String, Department> map = new HashMap<>();

    @Override
    public void register(String dName, Department department) {
        map.put(dName, department);
    }

    @Override
    public void command(String dName) {
        map.get(dName).selfAction();
    }
}
