package persistence;

import model.Exercise;
import model.WorkoutLog;
import model.Workout;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/* ==== Source Attribution / Reference =====
   The methods in this class has been referenced from the JsonSerializationDemo provided
   with the description of Project on edX.
 */

// Represents a reader that reads workoutLog from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workoutLog from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WorkoutLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkoutLog(jsonObject);
    }

    // EFFECTS: parses workoutLog from JSON object and returns it
    private WorkoutLog parseWorkoutLog(JSONObject jsonObject) {
        WorkoutLog workoutLog = new WorkoutLog();
        addWorkoutLog(workoutLog, jsonObject);
        return workoutLog;

    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // MODIFIES: workoutLog
    // EFFECTS: parses workoutLog from JSON object and returns it
    private void addWorkoutLog(WorkoutLog workoutLog, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("workoutLog");
        for (Object json: jsonArray) {
            JSONObject workoutJson = (JSONObject) json;
            Workout workout = new Workout();
            addWorkout(workout, workoutJson);
            workoutLog.addWorkout(workout);
        }
    }

    // MODIFIES: workoutLog
    // EFFECTS: parses workout from JSON object and adds them to workoutLog
    private void addWorkout(Workout workout, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("workout");

        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(workout, nextExercise);
        }
    }

    // MODIFIES: workoutLog
    // EFFECTS: parses exercise from JSON object and adds them to workout
    private void addExercise(Workout workout, JSONObject jsonObject) {
        int no = jsonObject.getInt("no.");
        String name = jsonObject.getString("name");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        int weight = jsonObject.getInt("weight");
        Exercise exercise = new Exercise(no, name, sets, reps, weight);

        workout.addExercise(exercise);
    }
}
