package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodList {
    
    //Används inte i nuläget

    public FoodList() {
        initFoodNames();
        initFoodListNamesMeat();
    }

    public enum FoodListEnum {
        Meat(1), Spices(2), Carbs(3);

        private int em;

        FoodListEnum(int emission){
            this.em = emission;
        }
        public int getEmission(){
            return em;
        }
    }

    public enum FoodListEnumMeats {
        Beaf(0.0144), Chicken(0.0025), Lamb(3);

        private double em;

        FoodListEnumMeats(double emission) {
            this.em = emission;
        }

        public double getEmission() {
            return em;
        }
    }

    private Map<Integer, FoodListEnum> FoodListNames;
    private Map<Double, FoodListEnumMeats> FoodListNamesMeat;

    private void initFoodNames() {
        FoodListNames = new HashMap<>();

        FoodListNames.put(1, FoodListEnum.Meat);
        FoodListNames.put(2, FoodListEnum.Spices);
        FoodListNames.put(3, FoodListEnum.Carbs);
    }

    private void initFoodListNamesMeat() {
        FoodListNamesMeat = new HashMap<>();

        //Double-värdet är utsläpp/gram
        FoodListNamesMeat.put(0.0144, FoodListEnumMeats.Beaf);
        FoodListNamesMeat.put(0.0025, FoodListEnumMeats.Chicken);
    }

}
