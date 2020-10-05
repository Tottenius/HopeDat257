package model.FoodPackage;

public enum FoodsEnum {

    Beaf(0.0144), Chicken(0.0025), Lamb(3), Pork(12);

    private double em;

    FoodsEnum(double emission) {
        this.em = emission;
    }

    public double getEmission() {
        return em;
    }

}
