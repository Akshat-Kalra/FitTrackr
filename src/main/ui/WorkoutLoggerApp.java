package ui;

import model.Workout;
import model.WorkoutLog;
import model.Exercise;

import java.util.ArrayList;
import java.util.Scanner;

// WorkoutLoggerApplication
public class WorkoutLoggerApp {
    private WorkoutLog listOfWorkout;
    private Scanner input;
    private int volume;
    @SuppressWarnings("checkstyle:OperatorWrap")
    private String asciiArt = "      _                                   _\n" +
            "    _| |                                 | |_\n" +
            "   | | |______OOOOo__________oOOOO_______| | |\n" +
            "  [| | |--------(`,----------\\`,---------| | |]\n" +
            "   |_| |      )  (            )  (       | |_|\n" +
            "     |_|      /  |            |  \\       |_|\n" +
            "              |  |  \\\\\\//  |  |\n" +
            "              \\  /  | -  - |  \\  /\n" +
            "              /  \\ (  a  a  ) /  \\\n" +
            "              |   | |  L   | |   |\n" +
            "              |   | \\  ==  / |   |\n" +
            "              |   /_.\\____/._\\   |\n" +
            "               \\   ||      ||   /\n" +
            "                \\  | '-..-' |  /\n" +
            "                |  ;  CPSC  ;  |\n" +
            "                | /   210    \\ |\n" +
            "                 \\            /\n" +
            "                  |          |\n" +
            "                  |    __    |\n" +
            "                  |===[LI]===|\n" +
            "                  )\"\"\"`\"\"`\"\"\"(\n" +
            "                 /            \\\n" +
            "                /    ,____,    \\\n" +
            "               /'-._ .'  '. _.-'\\\n" +
            "              /     /      \\     \\\n" +
            "              |    /        \\    |\n" +
            "              (_  /          \\  _)\n" +
            "               |  `\\        /`  |\n" +
            "               |___|        |___|\n" +
            "               |===/        \\===|\n" +
            "             _/\\._(          )_./\\_\n" +
            "           /`      |         |    _`\\\n" +
            "           `\"\"\"\"\"`\"           \"\"`\"\"`";


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
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tA -> Add workout");
        System.out.println("\tD -> Delete Workout");
        System.out.println("\tS -> Display Workouts");
        System.out.println("\tV -> Display Total Volume");
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
    public void displayVolume() {
        System.out.printf("Your total volume for all the added workouts is %s",volume);
    }


}
