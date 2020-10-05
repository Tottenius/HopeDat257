package model.FoodPackage;

public class Foods implements FoodInterface {

    private FoodsEnum foodType;
    private int amount;

    public Foods(int amount, FoodsEnum foodType) {
        this.amount = amount;
        this.foodType = foodType;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public double getEmission() {
        return amount * foodType.getEmission();
    }
}
