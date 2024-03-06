package persistence;

import model.WorkoutLog;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of flashcard list to file
    public void write(WorkoutLog workoutLog) {
        JSONObject json = workoutLog.toJson();
        writesToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: appends string to file
    private void writesToFile(String json) {
        writer.append(json);
    }


    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }
}
