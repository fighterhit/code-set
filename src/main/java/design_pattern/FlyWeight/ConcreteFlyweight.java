package design_pattern.FlyWeight;

/**
 *
 */
public class ConcreteFlyweight implements FlyWeight {
    private String color;

    public ConcreteFlyweight(String color) {
        this.color = color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void display(Point point) {
        System.out.println("颜色：" + color);
        System.out.println("位置：" + point.getX() + "---" + point.getY());
    }
}
