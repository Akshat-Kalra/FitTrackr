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
// WorkoutLoggerApplicationGUI
/* ==== Source Attribution / Reference =====
// Learned swing syntax from https://www.youtube.com/watch?v=Kmgo00avvEw&pp=ygUKamF2YSBzd2luZw%3D%3D
 */

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


    ImageIcon icon = new ImageIcon("src/main/ui/images/weightlifting.png");
    Image image = icon.getImage();
    Image newImg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);

    ImageIcon icon2 = new ImageIcon("src/main/ui/images/dumbbell.png");
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
        } else if (e.getSource() == displayVolumeButton) {
            displayVolume();
        } else if (e.getSource() == showProgressButton) {
            showProgress();
        } else if (e.getSource() == saveButton) {
            saveWorkout();
        } else if (e.getSource() == loadButton) {
            loadWorkout();
        } else if (e.getSource() == quitButton) {
            quitApplication();
        }
    }



    // MODIFIES: this
    // EFFECTS: resets the fields.
    private void resetFields() {
        exerciseName.setText("");
        sets.setText("");
        reps.setText("");
        weight.setText("");
    }

    // MODIFIES: this
    // EFFECTS: adds a workout to the listOfWorkout
    private void addWorkout() {
        Workout workout = new Workout();
        JPanel counterPanel = new JPanel();


        addWorkoutHelper(counterPanel);

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

    // MODIFIES: counterPanel
    // EFFECTS: helper to addWorkout(), helps in designing the panel to add the number of exercises.
    private void addWorkoutHelper(JPanel counterPanel) {
        counterPanel.add(Box.createVerticalStrut(15));
        counterPanel.add(new JLabel("Enter the number of exercises you did: "));
        counterPanel.add(exerciseNumber);
        counterPanel.setLayout(new BoxLayout(counterPanel, BoxLayout.Y_AXIS));

    }

    // MODIFIES: this
    // EFFECTS: helper to addWorkout(), displays a panel to the user to enter the details of the exercise and then
    //          returns the Exercise.
    private Exercise addExercisePanel(Integer number) {
        Exercise exercise;
        JPanel addWorkoutPanel = new JPanel();
        initAddWorkoutPanel(addWorkoutPanel);
        addWorkoutPanel.setLayout(new BoxLayout(addWorkoutPanel, BoxLayout.Y_AXIS));
        int result = JOptionPane.showConfirmDialog(null, addWorkoutPanel,
                "Exercise no" + " " + number, JOptionPane.OK_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            exercise = new Exercise(number, exerciseName.getText(), Integer.parseInt(sets.getText()),
                    Integer.parseInt(reps.getText()), Integer.parseInt(weight.getText()));
            return exercise;
        }

        return new Exercise(1, "TEST", 1, 1, 1);


    }

    // MODIFIES: addWorkoutPanel
    // EFFECTS: helper to addExercisePanel(), initializes the panel that asks for the details of the exercise.
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


    // EFFECTS: display all previous workouts added to listOfWorkout
    private void displayWorkouts() {
        ArrayList<Workout> displayWorkout = listOfWorkout.getListOfWorkouts();

        JPanel displayPanel = new JPanel();

        displayPanel.add(Box.createVerticalStrut(15));
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));

        displayWorkoutsHelper(displayPanel, displayWorkout,1);
        displayPanel.add(new JLabel("Total Volume: " + volume));

        JScrollPane scrollPane = new JScrollPane(displayPanel);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        JFrame displayFrame = new JFrame("Workouts");

        displayFrame.getContentPane().add(scrollPane);
        displayFrame.pack();
        displayFrame.setVisible(true);



    }

    // MODIFIES: this
    // EFFECTS: helper to displayWorkouts(), helps in the adding the workouts to the JPanel.
    private void displayWorkoutsHelper(JPanel displayPanel, ArrayList<Workout> displayWorkout, int i) {

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
                displayPanel.add(Box.createVerticalStrut(10));
                volume += exercise.exerciseVolume();
            }
            i++;
        }
        this.volume = volume;
    }





    // MODIFIES: this
    // EFFECTS: removes the inputted number of workout from listOfWorkout.
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
                            "Workout Successfully Removed", "Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Workout Does Not Exist", "Unsuccessful",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    // EFFECTS: displays the total volume from all the workouts in listOfWorkout.
    private void displayVolume() {
        int vol = 0;
        ArrayList<Workout> displayWorkout = listOfWorkout.getListOfWorkouts();
        for (Workout workout : displayWorkout) {
            ArrayList<Exercise> exercises = workout.getExerciseList();
            for (Exercise exercise : exercises) {
                vol += exercise.exerciseVolume();
            }
        }

        JOptionPane.showMessageDialog(null,
                String.format("Your Total Volume is: " + vol),
                "Total Volume",
                JOptionPane.INFORMATION_MESSAGE);
    }


    // EFFECTS: displays every time you did the provided exercises with sets, reps and weight and volume
    //          also displays the difference in volume from when you did the exercise most recently from when you
    //          did the exercise for the first time.
    private void showProgress() {

        JPanel progressPanel = new JPanel();

        showProgressHelper(progressPanel);

        int number = JOptionPane.showConfirmDialog(null, progressPanel,
                "Number of Exercises: ",JOptionPane.OK_CANCEL_OPTION);

        if (number == JOptionPane.OK_OPTION) {
            if (exerciseName.getText().length() == 0) {
                JOptionPane.showMessageDialog(null,
                        String.format("Please enter an exercise name"),
                        "Enter valid name",
                        JOptionPane.INFORMATION_MESSAGE);
                exerciseName.setText("");
            } else {
                if (listOfWorkout.getListOfAnExercise(exerciseName.getText()).isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            String.format("You haven't done this exercise"),
                            "Enter valid name",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    displayProgress();
                }
            }
        }


    }

    // MODIFIES: this
    // EFFECTS: helper to showProgress(), initializes the panel that asks for the name of the exercise that you want
    //          to see progress in.
    private void showProgressHelper(JPanel progressPanel) {
        progressPanel.add(Box.createVerticalStrut(15));
        exerciseName.setText("");
        progressPanel.add(new JLabel("Enter the name of the exercise you want to see progress in (case sensitive): "));
        progressPanel.add(exerciseName);
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));
    }

    // EFFECTS: helper to showProgress(), displays the progress in the inputted exercise.
    private void displayProgress() {
        ArrayList<Exercise> exercises = listOfWorkout.getListOfAnExercise(exerciseName.getText());

        JPanel displayProgressPanel = new JPanel();
        displayProgressPanel.add(Box.createVerticalStrut(15));
        displayProgressPanel.setLayout(new BoxLayout(displayProgressPanel, BoxLayout.Y_AXIS));
        displayProgressPanel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));


        displayProgressHelper(exercises, displayProgressPanel);


        int volDay1 = exercises.get(0).exerciseVolume();
        int volDay2 = exercises.get(exercises.size() - 1).exerciseVolume();

        if ((volDay2 - volDay1) == 0) {
            displayProgressPanel.add(new JLabel("No progress since Day 1......Work Harder!!!"));
        } else {
            displayProgressPanel.add(new JLabel("Damnn boyyy....you had" + " "
                    + (volDay2 - volDay1) + " " + "increase in volume since Day 1"));
        }

        JScrollPane scrollPane2 = new JScrollPane(displayProgressPanel);
        scrollPane2.setPreferredSize(new Dimension(700, 500));
        JFrame displayProgressFrame = new JFrame("Progress: " + exerciseName.getText());

        displayProgressFrame.getContentPane().add(scrollPane2);
        displayProgressFrame.pack();
        displayProgressFrame.setVisible(true);


    }

    // EFFECTS: helper to displayProgress(), helps in the adding the exercises to the displayProgressPanel.
    private void displayProgressHelper(ArrayList<Exercise> exercises, JPanel displayProgressPanel) {
        int i = 1;
        int volume = 0;
        for (Exercise exercise : exercises) {
            displayProgressPanel.add(new JLabel("Day" + i));
            displayProgressPanel.add(Box.createVerticalStrut(5));
            displayProgressPanel.add(new JLabel("\t \t Sets:" + exercise.getSets()));
            displayProgressPanel.add(Box.createVerticalStrut(5));
            displayProgressPanel.add(new JLabel("\t \t Reps:" + exercise.getReps()));
            displayProgressPanel.add(Box.createVerticalStrut(5));
            displayProgressPanel.add(new JLabel("\t \t Weight:" + exercise.getWeight()));
            displayProgressPanel.add(Box.createVerticalStrut(5));
            displayProgressPanel.add(new JLabel("\t \t Exercise Volume:" + exercise.exerciseVolume()));
            displayProgressPanel.add(Box.createVerticalStrut(5));
            volume += exercise.exerciseVolume();
            i++;
        }
        this.volume = volume;
        displayProgressPanel.add(new JLabel("Total Volume: " + volume));
    }

    // EFFECTS: saves the workoutLog to file
    private void saveWorkout() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfWorkout);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    String.format("Saved " + "Workout Log" + " to " + JSON_STORE), "Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    String.format("Unable to save to file: " + JSON_STORE), "Unsuccessful",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workoutLog from file
    private void loadWorkout() {
        try {
            listOfWorkout = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    String.format("Loaded " + "Workout Log" + " from " + JSON_STORE), "Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    String.format("Unable to read from file: " + JSON_STORE), "Unsuccessful",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: quits the application.
    private void quitApplication() {
        System.exit(0);
        frame.dispose();
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

    // MODIFIES: this
    // EFFECTS: changes the icon set to label when the mouse enters the label.
    @Override
    public void mouseEntered(MouseEvent e) {
        Icon icon2 = new ImageIcon(newImg2);
        label.setIcon(icon2);
    }

    // MODIFIES: this
    // EFFECTS: changes the icon set to label when the mouse exits the label.
    @Override
    public void mouseExited(MouseEvent e) {
        Icon icon = new ImageIcon(newImg);
        label.setIcon(icon);
    }


}
