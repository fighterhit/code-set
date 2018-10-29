package design_pattern.Bridge;

/**
 * @author Fighter.
 */
public interface Brand {
    String getBrand();
}

class Lenovo implements Brand{

    @Override
    public String getBrand() {
        return "联想";
    }
}

class Hasee implements Brand{

    @Override
    public String getBrand() {
        return "神舟";
    }
}