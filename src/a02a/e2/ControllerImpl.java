package a02a.e2;

import java.util.HashSet;
import java.util.Set;

public class ControllerImpl implements Controller{

    private int size;
    private Set<Pair<Integer, Integer>> enabled;
    private Pair<Integer, Integer> UP = new Pair<>(0, -1);
    private Pair<Integer, Integer> DOWN = new Pair<>(0, 1);
    private Pair<Integer, Integer> LEFT = new Pair<>(-1, 0);
    private Pair<Integer, Integer> RIGHT = new Pair<>(1, 0);
    private Pair<Integer, Integer> currentDirection;
    private Pair<Integer, Integer> currentLocation;



    @Override
    public void setCurrentLocation(Pair<Integer, Integer> currentLocation) {
        this.currentLocation = currentLocation;
        enabled.add(currentLocation);
    }

    public ControllerImpl(final int size){
        this.size = size;
        this.currentDirection = UP;
        this.enabled = new HashSet<>();
    }

    @Override
    public boolean checkLimits(Pair<Integer, Integer> coord) {
        return coord.getX() >=0 && coord.getX()<size && coord.getY()>=0 && coord.getY() < size && !enabled.contains(coord);
    }

    @Override
    public Pair<Integer, Integer> hit() {
        Pair<Integer, Integer> temp = getNextCell();
        enabled.add(temp);
        currentLocation = temp;
        return temp;
    }

    @Override
    public Pair<Integer, Integer> getNextCell() {
        Pair<Integer, Integer> temp = new Pair<Integer,Integer>(currentLocation.getX() + currentDirection.getX(), currentLocation.getY() + currentDirection.getY());
        if (checkLimits(temp)){
            return temp;
        }
        else{
            changeDirection();
            return getNextCell();
        }
    }

    @Override
    public void changeDirection(){
        if (currentDirection == UP){
            currentDirection = RIGHT;
            return;
        }
        if (currentDirection == RIGHT){
            currentDirection = DOWN;
            return;
        }
        if (currentDirection == DOWN){
            currentDirection = LEFT;
            return;
        }
        if (currentDirection == LEFT){
            currentDirection = UP;
            return;
        }
    }

    public Pair<Integer, Integer> offset(Pair<Integer, Integer> coord, Pair<Integer, Integer> offset){
        return new Pair<Integer,Integer>(coord.getX() + offset.getX(), coord.getY() + offset.getY());
    }

    @Override
    public void isOver() {
        if (
            enabled.contains(offset(currentLocation,UP)) &&
            enabled.contains(offset(currentLocation, DOWN)) &&
            enabled.contains(offset(currentLocation, LEFT)) &&
            enabled.contains(offset(currentLocation, RIGHT))
        ){
            System.exit(0);
        }
    }

    
}
