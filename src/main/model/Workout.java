package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a Workout which is a list of exercises (listOfExercise).
public class Workout implements Writable {
    private ArrayList<Exercise> exerciseList;

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


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("workout", workoutToJson());
        return json;
    }

    // EFFECTS: returns exercise in this exerciseList as a JSON array
    private JSONArray workoutToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : exerciseList) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

}
