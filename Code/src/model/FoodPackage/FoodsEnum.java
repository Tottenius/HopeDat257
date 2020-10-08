package model.FoodPackage;

public enum FoodsEnum {

    Beaf(0.0144), Chicken(0.0025), Lamb(0.0095), Pork(0.01);

    private double em;

    FoodsEnum(double emission) {
        this.em = emission;
    }

    public double getEmission() {
        return em;
    }

}
