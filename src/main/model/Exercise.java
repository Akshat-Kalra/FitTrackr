package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an exercise having a number, name, number of sets performed, reps performed and weight lifted (in lbs).
public class Exercise implements Writable {
    private final int exerciseNumber;
    private String exerciseName;
    private int sets;
    private int reps;
    private int weight;

    /*
     * REQUIRES: number, sets, reps and weight is positive integers.
     *           exerciseName has a non-zero length
     * EFFECTS: creates an Exercise with the provided number,
     *          name, sets performed, repetitions performed,
     *          weight lifted (in lbs).
     */
    public Exercise(int number, String exerciseName, int sets, int reps, int weight) {
        this.exerciseNumber = number;
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public int getExerciseNumber() {
        return this.exerciseNumber;
    }

    public String getExerciseName() {
        return this.exerciseName;
    }

    public int getSets() {
        return this.sets;
    }

    public int getReps() {
        return this.reps;
    }

    public int getWeight() {
        return this.weight;
    }


    /*
     * REQUIRES: sets, reps and weight > 0
     * EFFECTS: returns the total exercise volume, which is just
     *          Volume = (sets performed * reps performed * weight lifted (in lbs)).
     */
    public int exerciseVolume() {
        return (this.sets * this.reps * this.weight);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("no.", exerciseNumber);
        json.put("name", exerciseName);
        json.put("sets", sets);
        json.put("reps", reps);
        json.put("weight", weight);
        return json;
    }
}
