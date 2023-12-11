package a02a.e1;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class DietImpl implements Diet{


    private Map<String, Map<Nutrient, Integer>> foods;
    private Predicate<Double> pre;
    private Predicate<Map<Nutrient, Double>> pre2;
    private Map<Nutrient, Double> overall;


    public DietImpl(Predicate<Double> pre, Predicate<Map<Nutrient, Double>> pre2){
        this.foods = new HashMap<>();
        this.overall = new HashMap<>();
        for (Nutrient nut : Nutrient.values()) {
            overall.put(nut, 0.0);
        }
        this.pre = pre;
        this.pre2 = pre2;
    }

    @Override
    public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
        this.foods.put(name, nutritionMap);
    }

    @Override
    public boolean isValid(Map<String, Double> dietMap) {
        double sum = 0;
        for (String food : dietMap.keySet()) {
            toCalories(this.foods.get(food), dietMap.get(food));
        }
        sum = caloriesSum();
        return pre.test(sum) && pre2.test(overall);
    }
    

    public void toCalories(final Map<Nutrient, Integer> food, final double weight){
        final double multip = weight/100;
        for (Nutrient nut : food.keySet()) {
            overall.put(nut, overall.get(nut) + (food.get(nut)*multip));
        }
        
    }

    public double caloriesSum(){
        double sum = 0;
        for (Nutrient nut : this.overall.keySet()) {
            sum+= overall.get(nut);
        }
        return sum;
    }
    
}
