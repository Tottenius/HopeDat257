package model.FoodPackage;

public enum FoodsEnum {

    Beef(0.0481), Pork(0.0042), Chicken(0.00206), Lamb(0.0454),
    Milk(0.0011), Cream(0.006), Butter(0.0127), Cheese(0.0097),
    Fish(0.0024), Pasta(0.0012), Bread(0.0008), Rice(0.0023),
    Potatoes(0.0002), Carrots(0.0002), Tomatoes(0.0015), Lettuce(0.0003),
    Apples(0.0003), Bananas(0.0006);

    private double em;

    FoodsEnum(double emission) {
        this.em = emission;
    }

    public double getEmission() {
        return em;
    }

}
