package model;

public class Exercise {
    private final int exerciseNumber;
    private String exerciseName;
    private int sets;
    private int reps;
    private int weight;

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: creates an exercise with the inputted exerciseNumber, exerciseName, sets, reps, weight.
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

    public int exerciseVolume() {
        return (this.sets * this.reps * this.weight);
    }
}
