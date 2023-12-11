package a02a.e1;



import a02a.e1.Diet.Nutrient;

public class DietFactoryImpl implements DietFactory{


    @Override
    public Diet standard() {
        return new DietImpl(food -> food<=2000 && food>=1500, i -> true);
    }

    @Override
    public Diet lowCarb() {
        return new DietImpl(food -> food<=1500 && food>=1000, i-> {
            return i.get(Nutrient.CARBS) <= 300;
        });
    }

    @Override
    public Diet highProtein() {
        return new DietImpl(food -> food<=2500 && food>=2000, i-> {
            return i.get(Nutrient.CARBS) <= 300 && i.get(Nutrient.PROTEINS)>= 1300;
        });
    }

    @Override
    public Diet balanced() {
        return new DietImpl(food -> food<=2000 && food>=1600, i -> {
            return i.get(Nutrient.CARBS) >= 600 &&
            i.get(Nutrient.PROTEINS) >= 600 &&
            i.get(Nutrient.FAT) >= 400 &&
            i.get(Nutrient.FAT) + i.get(Nutrient.PROTEINS) <= 1100;
        });
    }
    
}
