package design_pattern.Decorator;

/**
 * @author Fighter.
 */
public class Client {
    public static void main(String[] args) {
        Car car = new Car();
        car.move();

        System.out.println("add fly");

        FlyCar flyCar = new FlyCar(car);
        flyCar.move();

        System.out.println("add swim");

        WaterCar waterCar = new WaterCar(flyCar);
        waterCar.move();

    }
}
