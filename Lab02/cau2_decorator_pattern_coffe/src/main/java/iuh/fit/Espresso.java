package iuh.fit;

public class Espresso implements Beverage {
    @Override
    public String getDescription() { return "Cà phê Espresso"; }
    @Override
    public double cost() { return 20.0; } // 20k
}
