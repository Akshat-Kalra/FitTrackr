package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseTest {

    private Exercise exercise;
    private final int number = 1;
    private final String name = "bench press";
    private final int sets = 3;
    private final int reps = 10;
    private final int weight = 70;


    @BeforeEach
    void runBefore() {
        exercise = new Exercise(number, name, sets, reps, weight);
    }

    @Test
    void testConstructor() {
        assertEquals(exercise.getExerciseNumber(), 1);
        assertEquals(exercise.getExerciseName(), "bench press");
        assertEquals(exercise.getSets(), 3);
        assertEquals(exercise.getReps(), 10);
        assertEquals(exercise.getWeight(), 70);
    }

    @Test
    void exerciseVolumeTest() {
        assertEquals(exercise.exerciseVolume(), 3 * 10 * 70);
    }
}