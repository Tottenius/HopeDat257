package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodList {

    private enum FoodListEnum {
        Meat, Spices, Carbs
    }

    private enum FoodListEnumMeat {
        Beaf, Chicken, Lamb
    }

    private Map<Integer, FoodListEnum> FoodListNames;
    private Map<Double, FoodListEnumMeat> FoodListNamesMeat;

    private void initFoodNames() {
        FoodListNames = new HashMap<>();

        FoodListNames.put(1, FoodListEnum.Meat);
        FoodListNames.put(2, FoodListEnum.Spices);
        FoodListNames.put(3, FoodListEnum.Carbs);
    }

    private void initFoodListNamesMeat() {
        FoodListNamesMeat = new HashMap<>();


        //Double-värdet är utsläpp/gram
        FoodListNamesMeat.put(0.0144, FoodListEnumMeat.Beaf);
        FoodListNamesMeat.put(0.0025, FoodListEnumMeat.Chicken);
    }

}
