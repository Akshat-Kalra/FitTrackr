package persistence;

import model.Exercise;
import model.Workout;
import model.WorkoutLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            WorkoutLog workoutLog = new WorkoutLog();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkoutLog() {
        try {
            WorkoutLog workoutLog = new WorkoutLog();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkoutLog.json");
            writer.open();
            writer.write(workoutLog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkoutLog.json");
            workoutLog = reader.read();
            assertEquals(0, workoutLog.getListOfWorkouts().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkoutLog() {
        try {
            WorkoutLog workoutLog = new WorkoutLog();
            Workout workout1 = new Workout();
            workout1.addExercise(new Exercise(1, "test1", 3, 10, 50));
            workout1.addExercise(new Exercise(2, "test2", 4, 10, 70));
            workoutLog.addWorkout(workout1);
            Workout workout2 = new Workout();
            workout2.addExercise(new Exercise(1, "test3", 3, 10, 40));
            workoutLog.addWorkout(workout2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkoutLog.json");
            writer.open();
            writer.write(workoutLog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkoutLog.json");
            workoutLog = reader.read();
            List<Workout> workouts = workoutLog.getListOfWorkouts();
            assertEquals(2, workouts.size());
            checkExercise(1, "test1", 3, 10, 50, workouts.get(0).getExerciseList().get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
