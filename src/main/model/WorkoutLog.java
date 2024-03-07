package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a WorkoutLog which is a list of workouts (listOfWorkout).
public class WorkoutLog implements Writable {
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

    /* REQUIRES: Provided Workout has to be in listOfWorkouts
     * MODIFIES: this
     * EFFECTS: removes the Workout provided from the listOfWorkouts and returns true.
     */
    public boolean removeWorkout(Workout workout) {
        listOfWorkouts.remove(workout);
        return true;
    }

    /*
     * REQUIRES: index > 0 and there has to be a Workout present in the provided index value.
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
     * REQUIRES: name has a non-zero length
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("workoutLog", workoutLogToJson());
        return json;
    }

    // EFFECTS: return workout in this listOfWorkouts as a JSON array
    private JSONArray workoutLogToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Workout w : listOfWorkouts) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }
}
