package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutLogTest {

    private WorkoutLog listOfWorkout;
    private Workout workout1;
    private Workout workout2;
    private Workout workout3;
    private Exercise ex1;
    private Exercise ex2;
    private Exercise ex3;
    private Exercise ex4;


    @BeforeEach
    void runBefore() {
        listOfWorkout = new WorkoutLog();
        workout1 =  new Workout();
        workout2 =  new Workout();
        workout3 =  new Workout();
        ex1 = new Exercise(1,"bench press", 3, 10, 70);
        ex2 = new Exercise(2,"dumbell rows", 4, 12, 40);
        ex3 = new Exercise(3,"deadlifts", 2, 5, 200);
        ex4 = new Exercise(4,"squats", 3, 5, 225);
        workout1.addExercise(ex1);
        workout1.addExercise(ex4);
        workout2.addExercise(ex2);
        workout3.addExercise(ex3);
    }

    @Test
    void testConstructor() {
        assertTrue(listOfWorkout.isEmpty());
    }

    @Test
    void addWorkoutTestSingular() {
        assertTrue(listOfWorkout.addWorkout(workout1));
        assertFalse(listOfWorkout.isEmpty());
        assertEquals(listOfWorkout.getListOfWorkouts().get(0), workout1);
    }

    @Test
    void addWorkoutTestMultiple() {
        assertTrue(listOfWorkout.addWorkout(workout1));
        assertTrue(listOfWorkout.addWorkout(workout2));
        assertTrue(listOfWorkout.addWorkout(workout3));
        assertFalse(listOfWorkout.isEmpty());
        assertEquals(listOfWorkout.getListOfWorkouts().get(0), workout1);
        assertEquals(listOfWorkout.getListOfWorkouts().get(1), workout2);
        assertEquals(listOfWorkout.getListOfWorkouts().get(2), workout3);
    }

    @Test
    void removeWorkoutTestSingularWorkoutInList() {
        assertTrue(listOfWorkout.addWorkout(workout1));
        assertEquals(listOfWorkout.getListOfWorkouts().size(), 1);
        assertTrue(listOfWorkout.removeWorkout(workout1));
        assertTrue(listOfWorkout.isEmpty());


    }

    @Test
    void removeWorkoutTestMultipleWorkoutInList() {
        assertTrue(listOfWorkout.addWorkout(workout1));
        assertTrue(listOfWorkout.addWorkout(workout2));
        assertTrue(listOfWorkout.addWorkout(workout3));
        assertEquals(listOfWorkout.getListOfWorkouts().size(), 3);
        assertTrue(listOfWorkout.removeWorkout(workout1));
        assertFalse(listOfWorkout.isEmpty());

        ArrayList<Workout> temp = new ArrayList<Workout>();
        temp.add(workout2);
        temp.add(workout3);

        assertEquals(listOfWorkout.getListOfWorkouts(), temp);

    }

    @Test
    void removeWorkoutFromIndexTestSingular() {
        assertTrue(listOfWorkout.addWorkout(workout1));
        assertEquals(listOfWorkout.getSize(), 1);
        assertTrue(listOfWorkout.removeWorkoutFromIndex(0));
        assertTrue(listOfWorkout.isEmpty());
    }

    @Test
    void removeWorkoutFromIndexTestMultiple() {
        assertTrue(listOfWorkout.addWorkout(workout1));
        assertTrue(listOfWorkout.addWorkout(workout2));
        assertTrue(listOfWorkout.addWorkout(workout3));
        assertEquals(listOfWorkout.getSize(), 3);
        assertTrue(listOfWorkout.removeWorkoutFromIndex(1));

        ArrayList<Workout> temp = new ArrayList<Workout>();
        temp.add(workout1);
        temp.add(workout3);

        assertEquals(listOfWorkout.getListOfWorkouts(), temp);
    }


}
