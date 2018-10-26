package design_pattern.FlyWeight;

import java.util.HashMap;
import java.util.Map;

public class FlyWeightFactory {
    //享元池
    private static Map<String, ConcreteFlyweight> map = new HashMap<>();

    public static ConcreteFlyweight getConcreteFlyweight(String color) {
        if (map.get(color) != null) {
            return map.get(color);
        } else {
            ConcreteFlyweight concreteFlyweight = new ConcreteFlyweight(color);
            map.put(color, concreteFlyweight);
            return concreteFlyweight;
        }
    }
}
