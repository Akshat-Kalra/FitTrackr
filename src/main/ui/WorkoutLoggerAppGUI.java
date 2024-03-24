package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Workout;
import model.WorkoutLog;
import model.Exercise;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class WorkoutLoggerAppGUI implements ActionListener {

    private WorkoutLog listOfWorkout;
    private int volume;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/workoutLog.json";

    private JFrame frame;
    private JPanel panel;

    private JButton addButton;
    private JButton deleteButton;
    private JButton displayWorkoutsButton;
    private JButton displayVolumeButton;
    private JButton showProgressButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    private JLabel label;

    private JTextField exerciseNumber;
    private JTextField exerciseName;
    private JTextField sets;
    private JTextField reps;
    private JTextField weight;


    // MODIFIES: this
    // EFFECTS: runs the WorkoutLogger application with GUI
    public WorkoutLoggerAppGUI() {
        initComponents();
        initGUI();
        addListeners();
    }

    // MODIFIES: this
    // EFFECTS: Initializes the GUI of the application

    private void initGUI() {
        frame = new JFrame("WorkoutLogger App Menu");
        frame.setSize(1000, 1000);
        panel = new JPanel();

        // for commit



        addButton = new JButton("Add workout");
        deleteButton = new JButton("Delete Workout");
        displayWorkoutsButton = new JButton("Display Workouts");
        displayVolumeButton = new JButton("Display Total Volume");
        showProgressButton = new JButton("Show progress");
        saveButton = new JButton("Save Workouts");
        loadButton = new JButton("Load Previous Workouts");
        quitButton = new JButton("QUIT");

        ImageIcon icon = new ImageIcon("/Users/akshatkalra/Desktop/CPSC210/project_r0z2a/src/main/ui/weightlifting.png");
        Image image = icon.getImage();
        Image newImg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        label = new JLabel();
        label.setIcon(icon);



        panel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        panel.setPreferredSize(new Dimension(300, 300));


        initAlignments();

        addButtons(panel);

        frame.add(panel, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



    }


    // MODIFIES: this
    // EFFECTS: Initializes the elements alignment
    private void initAlignments() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayWorkoutsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayVolumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        showProgressButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // MODIFIES: this
    // EFFECTS: Adds required button to panel

    private void addButtons(JPanel panel) {
        panel.add(label);
        panel.add(Box.createVerticalStrut(10));
        panel.add(addButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(deleteButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(displayWorkoutsButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(displayVolumeButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(showProgressButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(saveButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(loadButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(quitButton);
        panel.add(Box.createVerticalStrut(10));

    }


    // MODIFIES: this
    // EFFECTS: initializes fields
    private void initComponents() {
        listOfWorkout = new WorkoutLog();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        exerciseNumber = new JTextField();
        exerciseName = new JTextField();
        sets = new JTextField();
        reps = new JTextField();
        weight = new JTextField();

    }


    // MODIFIES: this
    // EFFECTS: Adds listeners to the buttons
    private void addListeners() {
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        displayWorkoutsButton.addActionListener(this);
        displayVolumeButton.addActionListener(this);
        showProgressButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        quitButton.addActionListener(this);

    }


    // EFFECTS: Process action when a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addWorkout();
        } else if (e.getSource() == deleteButton) {
            deleteWorkout();
        }  else if (e.getSource() == displayWorkoutsButton) {
            displayWorkouts();
        } //else if (e.getSource() == displayVolumeButton) {
//            displayVolume();
//        } else if (e.getSource() == showProgressButton) {
//            showProgress();
//        } else if (e.getSource() == saveButton) {
//            saveWorkout();
//        } else if (e.getSource() == loadButton) {
//            loadWorkout();
//        } else if (e.getSource() == quitButton) {
//            quitApplication();
//        } else {
//            System.out.println("Selection not valid...");
//        }
    }



    private void addWorkout() {
        countExercise();

    }

    private void resetFields() {
        exerciseName.setText("");
        sets.setText("");
        reps.setText("");
        weight.setText("");
    }

    private void countExercise() {
        Workout workout = new Workout();
        JPanel counterPanel = new JPanel();
        counterPanel.add(Box.createVerticalStrut(15));
        counterPanel.add(new JLabel("Enter the number of exercises you did: "));
        counterPanel.add(exerciseNumber);

        counterPanel.setLayout(new BoxLayout(counterPanel, BoxLayout.Y_AXIS));

        int count = JOptionPane.showConfirmDialog(null, counterPanel,
                "Number of Exercises: ",JOptionPane.OK_CANCEL_OPTION);
        System.out.println(Integer.valueOf(exerciseNumber.getText()));

        if (count == JOptionPane.OK_OPTION) {
            if (Integer.valueOf(exerciseNumber.getText()) <= 0) {
                System.out.printf("Pls enter a positive number.....");
            } else {
                for (int j = 0; j < Integer.valueOf(exerciseNumber.getText()); j++) {
                    resetFields();
                    workout.addExercise(addExercisePanel(j + 1));
                }
                exerciseNumber.setText("");
                listOfWorkout.addWorkout(workout);
                // displayWorkouts();
            }
        }
    }

    // for testing: remove before commiting
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

    private Exercise addExercisePanel(Integer number) {
        JPanel addWorkoutPanel = new JPanel();
        initAddWorkoutPanel(addWorkoutPanel);
        addWorkoutPanel.setLayout(new BoxLayout(addWorkoutPanel, BoxLayout.Y_AXIS));
        int result = JOptionPane.showConfirmDialog(null, addWorkoutPanel,
                "Number of Exercises.",JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            Exercise exercise = new Exercise(number, exerciseName.getText(), Integer.parseInt(sets.getText()),
                    Integer.parseInt(reps.getText()), Integer.parseInt(weight.getText()));
            return exercise;
        }

        return new Exercise(1, "TEST", 1, 1, 1);


    }

    private void initAddWorkoutPanel(JPanel addWorkoutPanel) {
        addWorkoutPanel.add(Box.createVerticalStrut(10));
        addWorkoutPanel.add(new JLabel("Enter name of the exercise: "));
        addWorkoutPanel.add(exerciseName);
        addWorkoutPanel.add(Box.createVerticalStrut(10));
        addWorkoutPanel.add(new JLabel("Enter number of sets: "));
        addWorkoutPanel.add(sets);
        addWorkoutPanel.add(Box.createVerticalStrut(10));
        addWorkoutPanel.add(new JLabel("Enter number of reps: "));
        addWorkoutPanel.add(reps);
        addWorkoutPanel.add(Box.createVerticalStrut(10));
        addWorkoutPanel.add(new JLabel("Enter weight (lbs): "));
        addWorkoutPanel.add(weight);
    }


    private void deleteWorkout() {

        JTextField number = new JTextField();

        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));
        deletePanel.add(new JLabel("Enter number of the workout you want to remove: "));
        deletePanel.add(number);

        int result = JOptionPane.showConfirmDialog(null, deletePanel,
                "Remove Workout",JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            if (Integer.valueOf(number.getText()) <= listOfWorkout.getSize()
                    && Integer.valueOf(number.getText()) >= 1) {
                if (listOfWorkout.removeWorkoutFromIndex(Integer.valueOf(number.getText()) - 1)) {
                    JOptionPane.showMessageDialog(null,
                            "Workout Succesfully Removed", "Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Workout Does Not Exist", "Unsuccessful",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }






}
