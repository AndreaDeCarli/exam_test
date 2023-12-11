package a02a.e2;

public interface Controller {

    void setCurrentLocation(Pair<Integer, Integer> currentLocation);

    boolean checkLimits(Pair<Integer, Integer> coord);

    Pair<Integer, Integer> hit();

    Pair<Integer, Integer> getNextCell();

    void changeDirection();

    void isOver();

}