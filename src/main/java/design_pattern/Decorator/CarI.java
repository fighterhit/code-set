package design_pattern.Decorator;

/**
 * Component 抽象构件角色
 */
public interface CarI {
    void move();
}

/**
 * ConcreteComponent 具体构建角色（真实对象）
 */
class Car implements CarI {
    @Override
    public void move() {
        System.out.println("run...");
    }
}

/**
 * Decorator 装饰角色
 */
class SuperCar implements CarI {

    CarI carI;

    public SuperCar(CarI carI) {
        this.carI = carI;
    }

    @Override
    public void move() {
        carI.move();
    }
}

/**
 * ConcreteDecorator 具体装饰角色
 */
class FlyCar extends SuperCar {

    public FlyCar(CarI carI) {
        super(carI);
    }

    public void fly() {
        System.out.println("fly...");
    }

    @Override
    public void move() {
        super.move();
        fly();
    }
}

class WaterCar extends SuperCar {

    public WaterCar(CarI carI) {
        super(carI);
    }

    public void swim() {
        System.out.println("swim...");
    }

    @Override
    public void move() {
        super.move();
        swim();
    }
}


