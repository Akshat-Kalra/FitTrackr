package persistence;

import model.Workout;
import model.WorkoutLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/* ==== Source Attribution / Reference =====
   The methods in this class has been referenced from the JsonSerializationDemo provided
   with the description of Project on edX.
 */

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WorkoutLog wl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkoutLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkoutLog.json");
        try {
            WorkoutLog wl = reader.read();

            assertEquals(0, wl.getListOfWorkouts().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkoutLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkoutLog.json");
        try {
            WorkoutLog wl = reader.read();
            List<Workout> workoutLog = wl.getListOfWorkouts();
            assertEquals(2, workoutLog.size());
            checkExercise(1, "test1", 3, 10, 50, workoutLog.get(0).getExerciseList().get(0));
            checkExercise(2, "test2", 4, 10, 70, workoutLog.get(0).getExerciseList().get(1));
            checkExercise(1, "test3", 3, 10, 40, workoutLog.get(1).getExerciseList().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

