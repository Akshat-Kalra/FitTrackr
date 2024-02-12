package model;

import java.util.ArrayList;

public class Workout {
    private ArrayList<Exercise> exerciseList;
    private int workoutDay;

    public Workout() {
        exerciseList = new ArrayList<Exercise>();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public boolean addExercise(Exercise exercise) {
        this.exerciseList.add(exercise);
        return true;
    }

    public ArrayList<Exercise> getExerciseList() {
        return exerciseList;
    }

    public boolean isEmpty() {
        return exerciseList.isEmpty();
    }


}
