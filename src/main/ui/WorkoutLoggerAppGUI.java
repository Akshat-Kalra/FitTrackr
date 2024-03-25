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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class WorkoutLoggerAppGUI implements ActionListener, MouseListener {

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

    ImageIcon icon = new ImageIcon("/Users/akshatkalra/Desktop/CPSC210/project_r0z2a/src/main/ui/images/weightlifting.png");
    Image image = icon.getImage();
    Image newImg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);

    ImageIcon icon2 = new ImageIcon("/Users/akshatkalra/Desktop/CPSC210/project_r0z2a/src/main/ui/images/dumbbell.png");
    Image image2 = icon2.getImage();
    Image newImg2 = image2.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);


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
        frame = new JFrame("FitTrackr");
        panel = new JPanel();


        addButton = new JButton("Add workout");
        deleteButton = new JButton("Delete Workout");
        displayWorkoutsButton = new JButton("Display Workouts");
        displayVolumeButton = new JButton("Display Total Volume");
        showProgressButton = new JButton("Show progress");
        saveButton = new JButton("Save Workouts");
        loadButton = new JButton("Load Previous Workouts");
        quitButton = new JButton("QUIT");

        label = new JLabel();
        label.addMouseListener(this);
        Icon icon = new ImageIcon(newImg);
        label.setIcon(icon);



        panel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        panel.setPreferredSize(new Dimension(500, 700));


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

        if (count == JOptionPane.OK_OPTION) {
            if (Integer.valueOf(exerciseNumber.getText()) <= 0) {
                JOptionPane.showMessageDialog(null,
                        String.format("Please enter a positive number!!!"),
                        "Enter a positive number",
                        JOptionPane.INFORMATION_MESSAGE);
                exerciseNumber.setText("");
            } else {
                for (int j = 0; j < Integer.valueOf(exerciseNumber.getText()); j++) {
                    resetFields();
                    workout.addExercise(addExercisePanel(j + 1));
                }
                exerciseNumber.setText("");
                listOfWorkout.addWorkout(workout);
            }
        }
    }


    private void displayWorkouts() {
        ArrayList<Workout> displayWorkout = listOfWorkout.getListOfWorkouts();
        JPanel displayPanel = new JPanel();
        displayPanel.add(Box.createVerticalStrut(15));
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        displayPanel.setPreferredSize(new Dimension(200, 500));


        int i = 1;
        int volume = 0;
        for (Workout workout : displayWorkout) {
            displayPanel.add(new JLabel("Workout Number" + i));
            displayPanel.add(Box.createVerticalStrut(5));
            ArrayList<Exercise> exercises = workout.getExerciseList();
            for (Exercise exercise : exercises) {
                displayPanel.add(new JLabel("\t Exercise Number:" + exercise.getExerciseNumber()));
                displayPanel.add(Box.createVerticalStrut(5));
                displayPanel.add(new JLabel("\t \t Exercise Name:" + exercise.getExerciseName()));
                displayPanel.add(Box.createVerticalStrut(5));
                displayPanel.add(new JLabel("\t \t Sets:" + exercise.getSets()));
                displayPanel.add(Box.createVerticalStrut(5));
                displayPanel.add(new JLabel("\t \t Reps:" + exercise.getReps()));
                displayPanel.add(Box.createVerticalStrut(5));
                displayPanel.add(new JLabel("\t \t Weight:" + exercise.getWeight()));
                displayPanel.add(Box.createVerticalStrut(5));
                displayPanel.add(new JLabel("\t \t Exercise Volume:" + exercise.exerciseVolume()));
                displayPanel.add(Box.createVerticalStrut(5));
                volume += exercise.exerciseVolume();
            }
            i++;
        }
        this.volume = volume;
        displayPanel.add(new JLabel("Total Volume: " + volume));
        JOptionPane.showMessageDialog(null, displayPanel, "Workouts", JOptionPane.PLAIN_MESSAGE);


    }

    private Exercise addExercisePanel(Integer number) {
        JPanel addWorkoutPanel = new JPanel();
        initAddWorkoutPanel(addWorkoutPanel);
        addWorkoutPanel.setLayout(new BoxLayout(addWorkoutPanel, BoxLayout.Y_AXIS));
        int result = JOptionPane.showConfirmDialog(null, addWorkoutPanel,
                "Exercise no" + " " + number, JOptionPane.OK_CANCEL_OPTION);

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


    @Override
    public void mouseClicked(MouseEvent e) {
        //
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Icon icon2 = new ImageIcon(newImg2);
        label.setIcon(icon2);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Icon icon = new ImageIcon(newImg);
        label.setIcon(icon);
    }


}
