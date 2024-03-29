# FitTrackr

## About the Application

**What will the application do?**
- This application is a **comprehensive health and fitness companion**. 

- The application will store user workouts including exercises, sets, reps
and weight lifted. The application will also calculate the total workout volume and will store it as well.
- The application will also let the user see their previous workouts and the total volume they performed in that workout.
- The application will also generate graphical visualizations that will display the trend in user's total workout volume
over days and weeks, which will let the user know if they are making progress or not.

**Who will use it?**

- This application will be used by people who are into fitness and like
to scientifically optimize their workouts and log their progress.


**Why is this project of interest to you?**

- I have used workout trackers in the past, but all
of these tools are scattered over the web and there is no such application
that can do it all.
- Also, most of the fitness trackers I have used provide the same basic functionalities
and keep the rest of them behind a paywall.
- I also wanted to make a tracker that can track fitness volume over time, which can essentially show progressive overload and ensure muscle hypertrophy.

## User Stories
- As a user, I want to be able to add exercises to a workout.
- As a user, I want to be able to log my workouts by adding my workouts to a workout logger.
- As a user, I want to be able to see the list of my Previous Workouts in the Workout Logger.
- As a user, I want to be able to see my Total Overall Workout Volume.
- As a user, I want to be able to see my progress in a particular exercises using the volume 
metric and see how much I progressed in volume from Day 1. 
- As a user, I want to be able to save my workout log to file (if I so choose).
- As a user, I want to be able to be able to load my workout log from file (if I so choose).


# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking on the 
"Add workout" button on the Menu Frame when you first run the application. Enter the number of exercises when prompted.
And then enter the exercise name with set, reps and weight(in lbs), and then click "OK".
  - Repeat this n number of times, where n is the number of exercises you inputted.
  - After clicking "OK", the workout will be logged in the workout logger.
  - You can add another workout by repeating the instuction again, and this time the workout will be labelled as Workout
Number 2.

- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking on the
"Show progress" button in the menu, which will "display a subset of the Xs that satisfy some criterion specified by the user"
according to the edX requirements.
  - Specifically, it will ask for the exercise you want to see you progress in, and then it will display all the instances
  of that particular exercises you ever did from Day 1 with sets, reps and weight and then it will also calculate the
  total volume you did of that exercise.
  - And then it will also display the increase in volume from Day 1, essentially showing how much you have progressed
  since Day 1.


- You can locate my visual component by running that application and the hovering your mouse over my logo image which
will then change to a different image, and then it will get reverted back to the original image when your mouse exits the
image.

- You can save the state of my application by clicking on the "Save Workouts" button on the Menu Frame.
- You can reload the state of my application by clicking on the "Load Previous Workouts" button on the Menu Frame.