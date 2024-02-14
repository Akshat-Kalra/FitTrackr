package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutTest {

    private Workout workout;
    private Exercise ex1;
    private Exercise ex2;
    private Exercise ex3;


    @BeforeEach
    void runBefore() {
        ex1 = new Exercise(1,"bench press", 3, 10, 70);
        ex2 = new Exercise(2,"dumbell rows", 4, 12, 40);
        ex3 = new Exercise(3,"deadlifts", 2, 5, 200);
        workout = new Workout();

    }

    @Test
    void testConstructor() {
        assertTrue(workout.getExerciseList().isEmpty());
    }

    @Test
    void addExerciseTestSingular() {
        assertTrue(workout.addExercise(ex1));
        assertFalse(workout.getExerciseList().isEmpty());
        assertEquals(workout.getExerciseList().get(0), ex1);

    }

    @Test
    void addExerciseTestMultiple() {
        assertTrue(workout.addExercise(ex1));
        assertTrue(workout.addExercise(ex2));
        assertTrue(workout.addExercise(ex3));
        assertFalse(workout.getExerciseList().isEmpty());
        assertEquals(workout.getExerciseList().size(), 3);
        assertEquals(workout.getExerciseList().get(0), ex1);
        assertEquals(workout.getExerciseList().get(1), ex2);
        assertEquals(workout.getExerciseList().get(2), ex3);

    }

    @Test
    void getExerciseListTestSingular() {
        ArrayList<Exercise> temp = new ArrayList<Exercise>();
        temp.add(ex1);

        workout.addExercise(ex1);

        assertEquals(workout.getExerciseList(), temp);

    }

    @Test
    void getExerciseListTestMultiple() {
        ArrayList<Exercise> temp = new ArrayList<Exercise>();
        temp.add(ex1);
        temp.add(ex2);
        temp.add(ex3);

        workout.addExercise(ex1);
        workout.addExercise(ex2);
        workout.addExercise(ex3);

        assertEquals(workout.getExerciseList(), temp);

    }

    @Test
    void isEmptyTest() {
        assertTrue(workout.isEmpty());
        workout.addExercise(ex1);
        assertFalse(workout.isEmpty());
    }

}
