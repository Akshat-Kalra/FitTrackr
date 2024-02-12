package model;

import java.util.ArrayList;

public class ListOfWorkout {
    private ArrayList<Workout> listOfWorkouts;

    public ListOfWorkout() {
        listOfWorkouts = new ArrayList<Workout>();
    }

    public boolean addWorkout(Workout workout) {
        listOfWorkouts.add(workout);
        return true;
    }

    public boolean removeWorkout(Workout workout) {
        listOfWorkouts.remove(workout);
        return true;
    }

    public boolean removeWorkoutFromIndex(int number) {
        listOfWorkouts.remove(number);
        return true;
    }



    public ArrayList<Workout> getListOfWorkouts() {
        return listOfWorkouts;
    }

    public boolean isEmpty() {
        return listOfWorkouts.isEmpty();
    }

    public int getSize() {
        return listOfWorkouts.size();
    }
}
