package iuh.fit;

public class WhippedCream extends AddonDecorator {
    public WhippedCream(Beverage beverage) { super(beverage); }

    @Override
    public String getDescription() { return beverage.getDescription() + ", thêm Kem tươi"; }
    @Override
    public double cost() { return beverage.cost() + 10.0; } // Thêm 10k
}