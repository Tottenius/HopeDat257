package model.FoodPackage;

public enum FoodsEnum {
    //Protein
    Beef(0.0481), Pork(0.0042), Chicken(0.00206), Lamb(0.0454),
    Cod(0.0016), Salmon(0.0027), Eggs(0.0016),
    //Dairy
    Milk(0.0011), Cream(0.006), Butter(0.0127), Cheese(0.0097),
    //Carbs
    Pasta(0.0012), Bread(0.0008), Rice(0.0023), Potatoes(0.0002),
    //Vegetables
    Carrots(0.0002), Tomatoes(0.0015), Lettuce(0.0003), Cabbage(0.0005),
    Broccoli(0.0005), Onions(0.0001),
    //Fruits
    Apples(0.0003), Bananas(0.0006), Oranges(0.0008);

    private double em;

    FoodsEnum(double emission) {
        this.em = emission;
    }

    public double getEmission() {
        return em;
    }

}
