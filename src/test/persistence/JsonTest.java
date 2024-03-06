package persistence;

import model.Exercise;
import model.Workout;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* ==== Source Attribution / Reference =====
   The methods in this class has been referenced from the JsonSerializationDemo provided
   with the description of Project on edX.
 */

public class JsonTest {

    protected void checkExercise(int number, String exerciseName, int sets, int reps, int weight, Exercise exercise) {
        assertEquals(number, exercise.getExerciseNumber());
        assertEquals(exerciseName, exercise.getExerciseName());
        assertEquals(sets, exercise.getSets());
        assertEquals(reps, exercise.getReps());
        assertEquals(weight, exercise.getWeight());

    }
}
