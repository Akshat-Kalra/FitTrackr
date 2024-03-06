package persistence;

import model.Exercise;
import model.Workout;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

//    protected void checkWorkout(Workout workout) {
//
//    }


    protected void checkExercise(int number, String exerciseName, int sets, int reps, int weight, Exercise exercise) {
        assertEquals(number, exercise.getExerciseNumber());
        assertEquals(exerciseName, exercise.getExerciseName());
        assertEquals(sets, exercise.getSets());
        assertEquals(reps, exercise.getReps());
        assertEquals(weight, exercise.getWeight());

    }
}
