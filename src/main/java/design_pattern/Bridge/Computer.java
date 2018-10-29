package design_pattern.Bridge;

/**
 * @author Fighter.
 */
public abstract class Computer {

    Brand brand;

    public Computer(Brand brand) {
        this.brand = brand;
    }

    abstract String getComputerType();

    String getName() {
        return brand.getBrand() + getComputerType();
    }
}

class Desktop extends Computer {
    public Desktop(Brand brand) {
        super(brand);
    }

    @Override
    public String getComputerType() {
        return "PC";
    }
}

class Pad extends Computer {
    public Pad(Brand brand) {
        super(brand);
    }

    @Override
    public String getComputerType() {
        return "平板";
    }
}

class Laptop extends Computer {
    public Laptop(Brand brand) {
        super(brand);
    }

    @Override
    public String getComputerType() {
        return "笔记本";
    }
}