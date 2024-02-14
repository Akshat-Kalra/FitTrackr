package model;

import java.util.ArrayList;

// Represents a Workout which is a list of exercises (listOfExercise).
public class Workout {
    private ArrayList<Exercise> exerciseList;
    private int workoutDay;

    /*
    EFFECTS: assigns an empty array list to exerciseList
     */
    public Workout() {
        exerciseList = new ArrayList<Exercise>();
    }

    /*

     * MODIFIES: this
     * EFFECTS: adds the Exercise provided to the exerciseList and returns true.
     */
    public boolean addExercise(Exercise exercise) {
        this.exerciseList.add(exercise);
        return true;
    }


    public ArrayList<Exercise> getExerciseList() {
        return exerciseList;
    }

    /*
     * EFFECTS: returns true if the exerciseList is empty; false if not empty.
     */
    public boolean isEmpty() {
        return exerciseList.isEmpty();
    }


}
