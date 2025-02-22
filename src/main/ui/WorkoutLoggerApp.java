package ui;

import model.Workout;
import model.WorkoutLog;
import model.Exercise;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// WorkoutLoggerApplication
/* ==== Source Attribution / Reference =====
   Some of the methods in this class has been referenced from the TellerApp provided
   with the description of Project on edX.
   Specifically : runWorkoutLoggerApp() <-> runTeller()
                   init()
                   processCommand(String command)
                   displayMenu()
 */
public class WorkoutLoggerApp {
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/workoutLog.json";
    private WorkoutLog listOfWorkout;
    private Scanner input;
    private int volume;
    private final String asciiArt = "      _                                   _\n"
            + "    _| |                                 | |_\n"
            + "   | | |______OOOOo__________oOOOO_______| | |\n"
            + "  [| | |--------(`,----------\\`,---------| | |]\n"
            + "   |_| |      )  (            )  (       | |_|\n"
            + "     |_|      /  |            |  \\       |_|\n"
            + "              |  |  \\\\\\//  |  |\n"
            + "              \\  /  | -  - |  \\  /\n"
            + "              /  \\ (  a  a  ) /  \\\n"
            + "              |   | |  L   | |   |\n"
            + "              |   | \\  ==  / |   |\n"
            + "              |   /_.\\____/._\\   |\n"
            + "               \\   ||      ||   /\n"
            + "                \\  | '-..-' |  /\n"
            + "                |  ;  CPSC  ;  |\n"
            + "                | /   210    \\ |\n"
            + "                 \\            /\n"
            + "                  |          |\n"
            + "                  |    __    |\n"
            + "                  |===[LI]===|\n"
            + "                  )\"\"\"`\"\"`\"\"\"(\n"
            + "                 /            \\\n"
            + "                /    ,____,    \\\n"
            + "               /'-._ .'  '. _.-'\\\n"
            + "              /     /      \\     \\\n"
            + "              |    /        \\    |\n"
            + "              (_  /          \\  _)\n"
            + "               |  `\\        /`  |\n"
            + "               |___|        |___|\n"
            + "               |===/        \\===|\n"
            + "             _/\\._(          )_./\\_\n"
            + "           /`      |         |    _`\\\n"
            + "           `\"\"\"\"\"`\"           \"\"`\"\"`";


    // EFFECTS: runs the WorkoutLoggerApplication application
    public WorkoutLoggerApp() {
        System.out.println("\nWelcome to LifeFitPro");
        System.out.println(asciiArt);
        runWorkoutLoggerApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runWorkoutLoggerApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }


    }

    // MODIFIES: this
    // EFFECTS: initializes listOfWorkout
    private void init() {
        listOfWorkout = new WorkoutLog();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tA -> Add workout");
        System.out.println("\tD -> Delete Workout");
        System.out.println("\tS -> Display Workouts");
        System.out.println("\tV -> Display Total Volume");
        System.out.println("\tP -> Show progress");
        System.out.println("\tW -> Save Workouts");
        System.out.println("\tL -> Load Previous Workouts");
        System.out.println("\tQ -> QUIT");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addWorkout();
        } else if (command.equals("d")) {
            deleteWorkout();
        } else if (command.equals("s")) {
            displayWorkouts();
        } else if (command.equals("v")) {
            displayVolume();
        } else if (command.equals("p")) {
            showProgress();
        } else if (command.equals("w")) {
            saveWorkout();
        } else if (command.equals("l")) {
            loadWorkout();
        } else {
            System.out.println("Selection not valid...");
        }
    }



    // MODIFIES: this
    // EFFECTS: adds a workout to the listOfWorkout
    private void addWorkout() {
        Workout workout = new Workout();
        System.out.println("Enter the number of exercises you did:");
        int i = input.nextInt();
        if (i <= 0) {
            System.out.printf("Pls enter a positive number.....");
        } else {
            for (int j = 0; j < i; j++) {
                System.out.printf("Enter exercise number %s: ", j + 1);
                String exerciseName = input.next();
                System.out.printf("Enter number of sets: ");
                int sets = input.nextInt();
                System.out.printf("Enter number of reps: ");
                int reps = input.nextInt();
                System.out.printf("Enter weight (lbs): ");
                int weight = input.nextInt();
                Exercise exercise = new Exercise(j + 1, exerciseName, sets, reps, weight);
                workout.addExercise(exercise);
            }
            listOfWorkout.addWorkout(workout);
        }

    }

    // EFFECTS: display all previous workouts added to listOfWorkout
    private void displayWorkouts() {
        ArrayList<Workout> displayWorkout = listOfWorkout.getListOfWorkouts();
        System.out.println("All previous workouts displayed below:");
        int i = 1;
        int volume = 0;
        for (Workout workout : displayWorkout) {
            System.out.printf("\tWorkout Number: %s%n", i);
            ArrayList<Exercise> exercises = workout.getExerciseList();
            for (Exercise exercise : exercises) {
                System.out.printf("\t Exercise Number: %s%n", exercise.getExerciseNumber());
                System.out.printf("\t \t Exercise Name: %s%n", exercise.getExerciseName());
                System.out.printf("\t \t Sets: %s%n", exercise.getSets());
                System.out.printf("\t \t Reps: %s%n", exercise.getReps());
                System.out.printf("\t \t Weight: %s%n", exercise.getWeight());
                System.out.printf("\t \t Exercise Volume: %s%n", exercise.exerciseVolume());
                volume += exercise.exerciseVolume();
            }
            i++;
        }
        this.volume = volume;
        System.out.printf("Total Volume: %s%n", volume);
    }

    // MODIFIES: this
    // EFFECTS: removes the inputted number of workout from listOfWorkout.
    private void deleteWorkout() {
        System.out.printf("Enter number of the workout you want to remove: ");
        int number = input.nextInt();

        if (number <= listOfWorkout.getSize() && number >= 1) {
            listOfWorkout.removeWorkoutFromIndex(number - 1);
            System.out.println("\t Workout removed successfully");
        } else {
            System.out.println("\t ------------------Workout does not exist------------------");
        }
    }

    // EFFECTS: displays the total volume from all the workouts in listOfWorkout.
    private void displayVolume() {
        System.out.printf("Your total volume for all the added workouts is %s", volume);
    }

    // EFFECTS: displays every time you did the provided exercises with sets, reps and weight and volume
    //          also displays the difference in volume from when you did the exercise most recently from when you
    //          did the exercise for the first time.
    private void showProgress() {
        System.out.printf("Enter the name of the exercise you want to see progress in (case sensitive):\n ");
        String name = input.next();
        if (listOfWorkout.getListOfAnExercise(name).isEmpty()) {
            System.out.printf("You haven't done this exercise");
        } else {
            ArrayList<Exercise> exercises = listOfWorkout.getListOfAnExercise(name);

            System.out.printf("Wow....you made a lot of progress in %s", name);
            renderExercises(exercises);

            int volDay1 = exercises.get(0).exerciseVolume();
            int volDay2 = exercises.get(exercises.size() - 1).exerciseVolume();

            if ((volDay2 - volDay1) == 0) {
                System.out.printf("No progress since Day 1......Work Harder!!!");
            } else {
                System.out.printf("Damnn boyyy....you had %s increase in volume since Day 1", volDay2 - volDay1);
            }
        }

    }

    // EFFECTS: renders the exercises' sets, reps, weight and exercise volume given
    //          an array list of exercises.
    private void renderExercises(ArrayList<Exercise> exercises) {
        int i = 1;
        for (Exercise exercise : exercises) {
            System.out.printf("\nDay %s%n", i);
            System.out.printf("\t \t Sets: %s%n", exercise.getSets());
            System.out.printf("\t \t Reps: %s%n", exercise.getReps());
            System.out.printf("\t \t Weight: %s%n", exercise.getWeight());
            System.out.printf("\t \t Exercise Volume: %s%n", exercise.exerciseVolume());
            i++;
        }
    }

    // EFFECTS: saves the workoutLog to file
    private void saveWorkout() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfWorkout);
            jsonWriter.close();
            System.out.println("Saved " + "Workout Log" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workoutLog from file
    private void loadWorkout() {
        try {
            listOfWorkout = jsonReader.read();
            System.out.println("Loaded " + "Workout Log" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }






}
