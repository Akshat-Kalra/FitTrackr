package model;

import java.util.ArrayList;

// Represents a WorkoutLog which is a list of workouts (listOfWorkout).
public class WorkoutLog {
    private ArrayList<Workout> listOfWorkouts;

    /*
    EFFECTS: assigns an empty array list to listOfWorkouts
     */
    public WorkoutLog() {
        listOfWorkouts = new ArrayList<Workout>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds the Workout provided to the listOfWorkouts and returns true.
     */
    public boolean addWorkout(Workout workout) {
        listOfWorkouts.add(workout);
        return true;
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes the Workout provided from the listOfWorkouts and returns true.
     */
    public boolean removeWorkout(Workout workout) {
        listOfWorkouts.remove(workout);
        return true;
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes the Workout from the index provided from the listOfWorkouts and returns true.
     */
    public boolean removeWorkoutFromIndex(int number) {
        listOfWorkouts.remove(number);
        return true;
    }



    public ArrayList<Workout> getListOfWorkouts() {
        return listOfWorkouts;
    }

    /*
     * REEQUIRES: name has a non-zero length
     * EFFECTS: returns a list of exercises
     *          with every exercise ever done with the provided name.
     */
    public ArrayList<Exercise> getListOfAnExercise(String name) {
        ArrayList<Exercise> temp = new ArrayList<Exercise>();
        for (Workout workout : listOfWorkouts) {
            for (Exercise exercise : workout.getExerciseList()) {
                if (exercise.getExerciseName().equals(name)) {
                    temp.add(exercise);
                }

            }
        }
        return temp;
    }

    /*
     * EFFECTS: returns true if the listOfWorkouts is empty; false if not empty.
     */
    public boolean isEmpty() {
        return listOfWorkouts.isEmpty();
    }

    public int getSize() {
        return listOfWorkouts.size();
    }
}
