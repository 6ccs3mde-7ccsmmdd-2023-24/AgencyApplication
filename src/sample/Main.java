/**
 * This is the Graphical interface class that will substitute the MainMenu_TESTER.
 * I have included instructions for the user in how to add jobs, applicants and match a job.
 * @author Alexandro Cipriano da Silva Filho
 * ID: u1818267
 */
package sample;
import java.io.*;
import java.util.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {

    private static Job_List jobList = new Job_List();
    private static Applicant_List applicantList = new Applicant_List();
    private static Map<String,String> myMatches = new HashMap<>();

    // WIDTH and HEIGHT of GUI stored as constants
    private final int WIDTH = 1000;
    private final int HEIGHT = 700;

    //Application Title
    private final Label headingLabel = new Label("Welcome to Agency Application");

    /*
       JOB SECTION
     */

    //Job Id
    private final Label jobIDLabel = new Label("Job ID");
    private final TextField jobIDField = new TextField();

    //Job Name section
    private final Label jobNameLabel = new Label("Job Name");
    private final TextField jobNameField = new TextField();

    //Job location section
    private final Label jobLocationLabel = new Label("Job Location");
    private final TextField jobLocationField = new TextField();

    //Job Description section
    private final Label jobTypeLabel = new Label("Type of Contract");
    private final ComboBox <String> jobTypeCombo = new ComboBox();


    //Job Skill
    private final Label jobSkillLabel = new Label("Main Skill");
    private final TextField jobSkillField = new TextField();

    //Job salary section
    private final Label jobSalaryLabel = new Label("Job salary £");
    private final TextField jobSalaryField = new TextField();

    //Job Experience (years)
    private final Label jobExperienceLabel= new Label("Experience");
    private final ComboBox <String> jobExperienceCombo = new ComboBox();

//

    //Add buttons
    private final Button addJobBtn = new Button("Add Job");
    private final Button displayJobBtn = new Button("Display Job");
    private final Button removeJobBtn = new Button("Remove Job");
    private final Button updateJobBtn = new Button("Update Job");
    private final Button displayJobsBtn = new Button("Display all Jobs");
    private final Button displayAllMatchesBtn = new Button("Display all Matches");
    private final Button jobInstructionsBtn = new Button("Job Instructions");

    //Add a Text Area
    private final TextArea area1 = new TextArea();


    /*
     *      NOW WE ARE GOING TO TO PLAN THE APPLICANT SECTION
     */

    //Applicant Name
    private final Label applicantNameLabel = new Label("Name");
    private final TextField applicantNameField = new TextField();

    //Applicant email
    private final Label applicantEmailLabel = new Label("Email");
    private final TextField applicantEmailField = new TextField();

    //Applicant skill 1
    private final Label applicantSkill1Label = new Label("Skill 1");
    private final TextField applicantSkill1Field = new TextField();

    //Applicant skill 2
    private final Label applicantSkill2Label = new Label("Skill 2");
    private final TextField applicantSkill2Field = new TextField();

    //Applicant experience (years)
    private final Label applicantExperienceLabel= new Label("Experience");
    private final ComboBox <String> applicantExperienceCombo = new ComboBox();

    //Add buttons
    private final Button addApplicantBtn = new Button("Add Applicant");
    private final Button displayApplicantBtn = new Button("Display Applicant");
    private final Button removeApplicantBtn = new Button("Remove Applicant");
    private final Button updateApplicantBtn = new Button("Update Applicant");


    //Add a Text Area
    private final TextArea area2 = new TextArea();

    //Add more buttons
    private final Button displayApplicantsBtn = new Button("Display all Applicants");
    private final Button matchJobsBtn = new Button("Match a job");
    private final Button applicantInstructionsBtn = new Button("Applicant Instructions");

    //Final button to save all data and leave the application
    private final Button saveAndQuit = new Button("Save and Quit");


    @Override
    public void start(Stage stage) throws Exception{

        //**** JOB SECTION ****

        //Read the two files
        readJobList(jobList);
        readApplicantList(applicantList);

        // Create HBoxes
        HBox jobDetailsPart1 = new HBox(10);
        HBox jobDetailsPart2 = new HBox(10);
        HBox jobButtons = new HBox(10);
        //Add items to the combo box
        jobExperienceCombo.getItems().addAll("1","2","3","4","5","6","7","8","9","10");

        //Add types of contract to the combo box
        jobTypeCombo.getItems().addAll("Full-Time","Part-Time", "Internship", "Placement");

        //Add components to the HBoxes
        jobDetailsPart1.getChildren().addAll(jobIDLabel,jobIDField,jobNameLabel, jobNameField,jobLocationLabel,jobLocationField,jobTypeLabel,jobTypeCombo);
        jobDetailsPart2.getChildren().addAll(jobSkillLabel,jobSkillField,jobSalaryLabel,jobSalaryField, jobExperienceLabel,jobExperienceCombo);
        jobButtons.getChildren().addAll(addJobBtn,displayJobBtn,removeJobBtn,updateJobBtn,displayJobsBtn,displayAllMatchesBtn,jobInstructionsBtn);

        //**** APPLICANT SECTION ****

        HBox applicantDetails = new HBox(10);
        applicantDetails.setAlignment(Pos.CENTER);
        HBox applicantButtons = new HBox(10);
        applicantButtons.setAlignment(Pos.CENTER);
        HBox applicantOptions = new HBox(10);
        HBox retrieveJob = new HBox(10);
        retrieveJob.setAlignment(Pos.CENTER);
        HBox applicantOptionsButtons = new HBox(10);
        applicantOptionsButtons.setAlignment(Pos.CENTER);

        //Add items to the combo box
        applicantExperienceCombo.getItems().addAll("1","2","3","4","5","6","7","8","9","10");

        //Add components to the HBoxes
        applicantDetails.getChildren().addAll(applicantNameLabel, applicantNameField, applicantEmailLabel,applicantEmailField, applicantSkill1Label,
                applicantSkill1Field, applicantSkill2Label, applicantSkill2Field, applicantExperienceLabel, applicantExperienceCombo);
        applicantButtons.getChildren().addAll(addApplicantBtn,displayApplicantBtn, removeApplicantBtn,updateApplicantBtn);
        applicantOptionsButtons.getChildren().addAll(displayApplicantsBtn,matchJobsBtn,applicantInstructionsBtn);


        //Create a VBox
        VBox root = new VBox(10);

        //Add all components to VBox
        root.getChildren().addAll(headingLabel,jobDetailsPart1, jobDetailsPart2,jobButtons,area1,applicantDetails,
                applicantButtons,area2,applicantOptions, retrieveJob, applicantOptionsButtons,saveAndQuit);

        //Create a Scene
        Scene scene = new Scene(root,Color.BEIGE);

        //Add a font style to the heading
        Font font = new Font("Cambria", 30);
        headingLabel.setFont(font);

        //Set alignment of HBoxes
        jobDetailsPart1.setAlignment(Pos.CENTER);
        jobDetailsPart2.setAlignment(Pos.CENTER);
        jobButtons.setAlignment(Pos.CENTER);

        //Set alignment to VBox
        root.setAlignment(Pos.CENTER);

        jobDetailsPart1.setMinWidth(WIDTH);
        jobDetailsPart1.setMaxWidth(WIDTH);

        jobDetailsPart2.setMinWidth(WIDTH);
        jobDetailsPart2.setMaxWidth(WIDTH);

        jobButtons.setMinWidth(WIDTH);
        jobButtons.setMaxWidth(WIDTH);

        applicantDetails.setMinWidth(WIDTH);
        applicantDetails.setMaxWidth(WIDTH);

        applicantButtons.setMinWidth(WIDTH);
        applicantButtons.setMaxWidth(WIDTH);

        retrieveJob.setMinWidth(WIDTH);
        retrieveJob.setMaxWidth(WIDTH);

        applicantOptionsButtons.setMinWidth(WIDTH);
        applicantOptionsButtons.setMaxWidth(WIDTH);

        root.setMinSize(WIDTH,HEIGHT);
        root.setMaxSize(WIDTH,HEIGHT);

        area1.setMaxSize(WIDTH - 200, HEIGHT/5);
        area2.setMaxSize(WIDTH - 200,HEIGHT/5);

        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        //Customise the VBox border and background
        BorderStroke style = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(0), new BorderWidths(2) );
        root.setBorder(new Border(style));
        root.setBackground(Background.EMPTY);

        //Customise Job Buttons
        addJobBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        addJobBtn.setTooltip(new Tooltip("Add a job to the List"));
        addJobBtn.setFont(new Font("Cambria", 14));

        displayJobBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        displayJobBtn.setTooltip(new Tooltip("Display a Job by entering its ID"));
        displayJobBtn.setFont(new Font("Cambria", 14));

        removeJobBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        removeJobBtn.setTooltip(new Tooltip("Remove a Job of the Application by entering its ID"));
        removeJobBtn.setFont(new Font("Cambria", 14));

        updateJobBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        updateJobBtn.setTooltip(new Tooltip("Update Job details"));
        updateJobBtn.setFont(new Font("Cambria", 14));

        displayJobsBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        displayJobsBtn.setTooltip(new Tooltip("Display Registered Jobs from a position"));
        displayJobsBtn.setFont(new Font("Cambria", 14));

        displayAllMatchesBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        displayAllMatchesBtn.setTooltip(new Tooltip("Display all applicants that match with a job"));
        displayAllMatchesBtn.setFont(new Font("Cambria", 14));

        jobInstructionsBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        jobInstructionsBtn.setTooltip(new Tooltip("Display instructions in how to register a job in the application"));
        jobInstructionsBtn.setFont(new Font("Cambria", 14));


        //Customise Applicant Buttons
        addApplicantBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        addApplicantBtn.setTooltip(new Tooltip("Register an applicant"));
        addApplicantBtn.setFont(new Font("Cambria", 14));

        removeApplicantBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        removeApplicantBtn.setTooltip(new Tooltip("Remove an applicant"));
        removeApplicantBtn.setFont(new Font("Cambria", 14));

        updateApplicantBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        updateApplicantBtn.setTooltip(new Tooltip("Update an applicant"));
        updateApplicantBtn.setFont(new Font("Cambria", 14));

        displayApplicantBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        displayApplicantBtn.setTooltip(new Tooltip("Display a particular applicant"));
        displayApplicantBtn.setFont(new Font("Cambria", 14));

        displayApplicantsBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        displayApplicantsBtn.setTooltip(new Tooltip("Display applicants starting by a position"));
        displayApplicantsBtn.setFont(new Font("Cambria", 14));


        matchJobsBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        matchJobsBtn.setTooltip(new Tooltip("Find a job that matches your skills"));
        matchJobsBtn.setFont(new Font("Cambria", 14));

        applicantInstructionsBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        applicantInstructionsBtn.setTooltip(new Tooltip("Display instructions in how to register an applicant"));
        applicantInstructionsBtn.setFont(new Font("Cambria", 14));

        //Customise the Save and Quit Button
        saveAndQuit.setBackground(new Background(new BackgroundFill(Color.CHOCOLATE, new CornerRadii(10), Insets.EMPTY)));
        saveAndQuit.setTooltip(new Tooltip("Save all data and leave the application"));
        saveAndQuit.setFont(new Font("Cambria", 14));

        //Call private Job methods for Button event handlers
        addJobBtn.setOnAction( e ->addJobHandler());
        displayJobBtn.setOnAction(e -> displayJobHandler());
        updateJobBtn.setOnAction(e -> updateJobHandler());
        removeJobBtn.setOnAction(e -> removeJobHandler());
        displayJobsBtn.setOnAction(e -> displayJobsHandler());
        displayAllMatchesBtn.setOnAction(e -> displayAllMatchesHandler());
        jobInstructionsBtn.setOnAction(e -> jobInstructionsHandler());

        //Call private Job methods for Button event handlers
        addApplicantBtn.setOnAction( e ->  addApplicantHandler());
        removeApplicantBtn.setOnAction( e ->  removeApplicantHandler());
        updateApplicantBtn.setOnAction(e ->updateApplicantHandler());
        displayApplicantBtn.setOnAction( e ->  displayApplicantHandler());
        displayApplicantsBtn.setOnAction(e ->  displayApplicantsHandler());
        matchJobsBtn.setOnAction( e ->  matchAJobHandler());
        applicantInstructionsBtn.setOnAction( e ->  applicantInstructionsHandler());

        //Call private saveAndQuit method
        saveAndQuit.setOnAction( e ->  saveAndQuitHandler());

        //Application Scene
        stage.setScene(scene);
        stage.setTitle("Agency Application");
        stage.setResizable(false); //the user cannot resize the application screen
        stage.show();
    }


    /**
     * The following methods have been developed for Job Section
     */

    //------------------------------------------------------------ BEGINNING OF JOB METHODS SECTION------------------------------------------------------------------------

    //Add job
    private void addJobHandler() {

        String jobIDEntered = jobIDField.getText();
        String jobNameEntered = jobNameField.getText();
        String jobLocationEntered = jobLocationField.getText();
        String jodTypeEntered =  jobTypeCombo.getValue();
        String jobSkillEntered = jobSkillField.getText();
        String jobSalaryEntered = jobSalaryField.getText();
        String jobYearsEntered = jobExperienceCombo.getValue();

        try {
            //Check if there are errors
            if (jobIDEntered.length() == 0 || jobNameEntered.length() == 0 || jobLocationEntered.length() == 0 ||
                    jodTypeEntered == null || jobSkillEntered.length() == 0 || jobSalaryEntered == null || jobYearsEntered == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Warning: Make sure all fields above are entered!");
                alert.showAndWait();
            } else  if (Double.parseDouble(jobSalaryEntered) < 0) {
                area1.setText("Salary cannot have negative number!");
            } else if (Integer.parseInt(jobYearsEntered) < 0) {
                area1.setText("Experience cannot have negative number!");
            } else {
                //Switch statement for the type of contract
                switch (jodTypeEntered) {
                    case "Full-Time" ->  jodTypeEntered = String.valueOf(JobTypeOfContract.FULL_TIME);
                    case "Part-Time" -> jodTypeEntered = String.valueOf(JobTypeOfContract.PART_TIME);
                    case "Internship" -> jodTypeEntered = String.valueOf(JobTypeOfContract.INTERNSHIP);
                    case "Placement" -> jodTypeEntered = String.valueOf(JobTypeOfContract.WORK_PLACEMENT);
                }
                Job myJob = new Job(jobIDEntered, jobNameEntered, jobLocationEntered, JobTypeOfContract.valueOf(jodTypeEntered), jobSkillEntered,
                        Double.parseDouble(jobSalaryEntered), Integer.parseInt(jobYearsEntered));
                boolean checkJobDetails = myJob.check();
                if (checkJobDetails) {
                    boolean ok = jobList.addJob(myJob);
                    if (ok) {
                        jobIDField.setText("");
                        jobNameField.setText("");
                        jobLocationField.setText("");
                        jobSkillField.setText("");
                        jobSalaryField.setText("");
                        jobExperienceCombo.setValue("");

                        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Job added to the list!", new ButtonType("OK"));
                        alert.showAndWait();

                    } else {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR,"Job already on the system!", new ButtonType("OK"));
                        alert2.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING,"ID must contain only 4 characters!", new ButtonType("OK"));
                    alert.showAndWait();
                }
            }
            /*
             * A catch exception thrown by EasyScannerPlus to indicate that the token retrieved does not match the pattern
             * for the expected type, or that the token is out of range for the expected type. Also throw the AgencyException
             * if a value is negative
             */
        } catch (InputMismatchException e) {
            area1.setText("" + e);

        } catch (NumberFormatException t) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Error: Salary must have only digits!");
            alert2.showAndWait();
        }
    }

    //Display a job
    private void displayJobHandler() {

        try {
            String jobID;
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter Job ID");
            dialog.setTitle("Retrieve a Job");
            jobID = dialog.showAndWait().get();
            if (jobID.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Enter the job ID");
                alert.showAndWait();
            } else {
                //Retrieve the job using the Optional class
                Optional<Job> job = jobList.getJob(jobID);
                if (job.isPresent()) {
                    area1.setText("" + job.toString());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Job not found in the list");
                    alert.showAndWait();
                }
            }
            //A catch block when the user presses the "cancel Button" to cancel the section
        } catch (NoSuchElementException t) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Section Cancelled");
            alert2.showAndWait();
        }
    }

    //Update job
    private void updateJobHandler() {
        try {
            String jobID;
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter Job ID");
            dialog.setTitle("Update a Job");
            jobID = dialog.showAndWait().get();
            if (jobID.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("ID field must not be left empty");
                alert.showAndWait();
            } else {
                //Retrieve the job using the Optional class
                Optional<Job> job = jobList.getJob(jobID);
                if (job.isPresent()) {

                    ChoiceDialog <String> choiceDialog = new ChoiceDialog("Title", "Location", "Type","Skill","Salary","Experience","Keep the Original info");
                    choiceDialog.setHeaderText("What would you like to update? ");
                    String response = choiceDialog.showAndWait().get();
                    switch (response) {
                        case "Title" -> {
                            area1.setText("Current job Name: " + jobList.getJob(jobID).get().getJobTitle());
                            dialog = new TextInputDialog();
                            dialog.setHeaderText("Enter new name for the Job");
                            String newTitle = dialog.showAndWait().get();
                            String newJobTitle = jobList.getJob(jobID).get().setJobTitle(newTitle);
                            Alert jobTitle = new Alert(Alert.AlertType.CONFIRMATION);
                            jobTitle.setContentText("Job name successfully updated!");
                            jobTitle.showAndWait();
                            area1.setText("New job Name: " + newJobTitle);
                        }
                        case "Location" -> {
                            area1.setText("Current Job Location: " + jobList.getJob(jobID).get().getLocation());
                            dialog = new TextInputDialog();
                            dialog.setHeaderText("Enter new Location for the Job");
                            String newLocation = dialog.showAndWait().get();
                            String newJobLocation = jobList.getJob(jobID).get().setLocation(newLocation);
                            Alert jobLocation = new Alert(Alert.AlertType.CONFIRMATION);
                            jobLocation.setContentText("Job Location successfully updated!");
                            jobLocation.showAndWait();
                            area1.setText("New Job Location: " + newJobLocation);
                        }
                        case "Type" -> {
                            area1.setText("Current Type of Contract: " + jobList.getJob(jobID).get().getLocation());
                            ChoiceDialog<String> choice = new ChoiceDialog<>("Full-Time", "Part-Time", "Internship", "Placement");
                            choice.setContentText("Choose a new Contract Type");
                            choice.setHeaderText("Choice Contract Dialog");
                            String contract = choice.showAndWait().get();
                            //A switch statement to help choosing the type of contract
                            switch (contract) {
                                case "Full-Time" -> {
                                    contract = String.valueOf(JobTypeOfContract.FULL_TIME);
                                    jobList.getJob(jobID).get().setType(JobTypeOfContract.valueOf(contract));
                                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"Contract changed!",new ButtonType("OK"));
                                    confirmation.showAndWait();
                                }
                                case "Part-Time" -> {
                                    contract = String.valueOf(JobTypeOfContract.PART_TIME);
                                    jobList.getJob(jobID).get().setType(JobTypeOfContract.valueOf(contract));
                                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"Contract changed!",new ButtonType("OK"));
                                    confirmation.showAndWait();
                                }
                                case "Internship" -> {
                                    contract = String.valueOf(JobTypeOfContract.INTERNSHIP);
                                    jobList.getJob(jobID).get().setType(JobTypeOfContract.valueOf(contract));
                                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"Contract changed!",new ButtonType("OK"));
                                    confirmation.showAndWait();
                                }
                                case "Placement" -> {
                                    contract = String.valueOf(JobTypeOfContract.WORK_PLACEMENT);
                                    jobList.getJob(jobID).get().setType(JobTypeOfContract.valueOf(contract));
                                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"Contract changed!",new ButtonType("OK"));
                                    confirmation.showAndWait();
                                }
                            }
                        }
                        case "Skill" -> {
                            area1.setText("Current Main Skill: " + jobList.getJob(jobID).get().getPrimarySkill());
                            dialog = new TextInputDialog();
                            dialog.setHeaderText("Enter new Main Skill for the Job");
                            String newSkill = dialog.showAndWait().get();
                            String newJobSkill = jobList.getJob(jobID).get().setPrimarySkill(newSkill);
                            Alert jobSkill = new Alert(Alert.AlertType.CONFIRMATION);
                            jobSkill.setContentText("Job Main Skill successfully updated!");
                            jobSkill.showAndWait();
                            area1.setText("New Job Main Skill: " + newJobSkill);
                        }
                        case "Salary" -> {
                            area1.setText("Current Job Salary: " + jobList.getJob(jobID).get().getSalary());
                            dialog = new TextInputDialog();
                            dialog.setHeaderText("Enter new Salary for the Job");
                            String newSalary = dialog.showAndWait().get();
                            double salary = Double.parseDouble(newSalary);
                            if (salary < 0) {
                                Alert jobSalary = new Alert(Alert.AlertType.ERROR);
                                jobSalary.setContentText("Error: Salary cannot be negative!");
                                jobSalary.showAndWait();
                            } else {
                                double newJobSalary = jobList.getJob(jobID).get().setSalary(salary);
                                Alert errorSalaryNumber = new Alert(Alert.AlertType.CONFIRMATION);
                                errorSalaryNumber.setContentText("Job name successfully updated!");
                                errorSalaryNumber.showAndWait();
                                area1.setText("New Job Salary: " + newJobSalary);
                            }
                        }
                        case "Experience" -> {
                            area1.setText("Current Job Experience: " + jobList.getJob(jobID).get().getExperience());
                            dialog = new TextInputDialog();
                            dialog.setHeaderText("Enter new Experience ");
                            String newExperience = dialog.showAndWait().get();
                            int experience = Integer.parseInt(newExperience);
                            if (experience < 0) {
                                Alert errorExperienceNumber = new Alert(Alert.AlertType.ERROR);
                                errorExperienceNumber.setContentText("Error: Experience cannot be negative!");
                                errorExperienceNumber.showAndWait();
                            } else {
                                int newJobExperience = jobList.getJob(jobID).get().setExperience(experience);
                                Alert jobExperience = new Alert(Alert.AlertType.CONFIRMATION);
                                jobExperience.setContentText("Job name successfully updated!");
                                jobExperience.showAndWait();
                                area1.setText("New Job Experience: " + newJobExperience);
                            }
                        }
                        case "Keep the Original info" -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Job's information kept!",new ButtonType("OK"));
                            alert.showAndWait();
                        }
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Job not found in the list");
                    alert.showAndWait();
                }
            }

            //This catch block will execute if the salary and or experience have characters
        } catch (NumberFormatException e) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText(""+e);
            alert2.showAndWait();

            //A catch block when the user presses the "cancel Button" to cancel the section
        } catch (NoSuchElementException t) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Section Cancelled");
            alert2.showAndWait();
        }

    }

    //Remove Job from list
    private void  removeJobHandler() {
        try {
            String jobID;
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Remove a job from List");
            dialog.setHeaderText("Enter job ID");
            jobID = dialog.showAndWait().get();
            if (jobID.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("ID field must not be left empty");
                alert.showAndWait();
            } else {
                //Retrieve the job using the Optional class
                Optional<Job> job = jobList.getJob(jobID);
                Alert alert;
                if (job.isPresent()) {
                    alert = new Alert(Alert.AlertType.WARNING, "Are you sure you wish to delete this job?\n" +
                            "The records of the applicants that match\nwith this job will be lost.", new ButtonType("Yes"), new ButtonType("No"));
                    String response = alert.showAndWait().get().getText();
                    if (response.equals("Yes")) {
                        jobList.removeJob(jobID);
                        alert = new Alert(Alert.AlertType.CONFIRMATION, "Job successfully removed!", new ButtonType("OK"));
                    } else {
                        alert = new Alert(Alert.AlertType.CONFIRMATION, "Job kept in the list!", new ButtonType("OK"));
                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR, "Job not found in the list");
                }
                alert.showAndWait();
            }

            //A catch block when the user presses the "cancel Button" to cancel the section
        } catch (NoSuchElementException t) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Section Cancelled");
            alert2.showAndWait();
        }
    }

    //Display Jobs From a position
    private void displayJobsHandler() {

        String fromWhere;
        try {
            area1.setText("");
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Where would like to start seeing the records from? ");
            fromWhere = dialog.showAndWait().get();
            int startFromHere = Integer.parseInt(fromWhere);
            if (jobList.isEmpty()) {
                area1.setText("Error: Job List is empty!");
            } else {
                if (startFromHere < 0 || startFromHere > jobList.getTotalOfJobs()) {
                    area1.setText("There are no jobs starting in this position");
                } else {
                    area1.appendText("Total of jobs: "+ jobList.getTotalOfJobs()+ "\n");
                    for (int count = 0; count < 5; count++) {
                        int i = count + startFromHere - 1;
                        if (i >= jobList.getTotalOfJobs()) {
                            break;
                        }
                        area1.appendText(""+ ( count + 1)+ ")" +
                                jobList.jList.get(i).toString() +'\n'+
                                "This job is currently at position "+ i + " in the list.\n"+
                                "-----------------------------------------------------------");
                    }
                }
            }
            //This catch block will execute if the salary and or experience have characters
        } catch (NumberFormatException e) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText(""+e);
            alert2.showAndWait();

            //A catch block when the user presses the "cancel Button" to cancel the section
        } catch (NoSuchElementException t) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Section Cancelled");
            alert2.showAndWait();
        }
    }
    //Method to display all matches
    private void displayAllMatchesHandler() {
        area1.setText("");
        if (myMatches.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No matches recorded!");
            alert.showAndWait();
        } else {

            area1.setText("------- Matches -------\n");
            for (String i : myMatches.keySet()) {
                if (applicantList.getApplicant(i).isPresent() && jobList.getJob(myMatches.get(i)).isPresent())  {
                    area1.setText("Applicant's name: "+ applicantList.getApplicant(i).get().getName().toUpperCase() +"\n"  +
                        "Job matched: "+ jobList.getJob(myMatches.get(i)).get().getJobTitle().toUpperCase()+"\n\n");
                }
            }
        }
    }

    // Job instructions
    private void jobInstructionsHandler() {
        area1.setText("In this section you can register jobs, retrieve a job, retrieve all jobs and retrieve all applicants that has a job match.\n" +
                "Here are some tips for this section:\n" +
                "1) Register the job: Click on this button to register a job;\n" +
                "  1.1) You can add jobs as there is no limit;\n" +
                "  1.2) Enter all the job details in the fields .If you do not enter all details the job will not be registered;\n" +
                "  1.3) Make sure the job ID does not exceed 4 digits. If this is the case a warning pop up message will show on your screen; \n" +
                "  1.4) The registration will failure if the job has been already registered on the system;\n" +
                "  1.5) If the system does not find any errors the job will be recorded in the system;\n" +
                "2) Display all jobs: Click on this button to retrieve the list of jobs.\n " +
                "  2.1) Make sure the list is not empty otherwise the system will output a message for this warning;\n" +
                "  2.2) If the the job name is not found a message will appear indicating the failure, otherwise the system will retrieve the job entered to you.\n" +
                "3) List all matches: Click on this button to retrieve the list of all matches.\n" +
                "  3.1) A failure message will appear if the job or applicant lists are empty;\n" +
                "  3.1) A failure message will appear if there are no matches yet recorded.");


    }
    //------------------------------------------------------------ END OF JOB METHODS SECTION------------------------------------------------------------------------


    /**
     * The following methods have been developed for Applicant Section
     */

    //------------------------------------------------------------ BEGINNING OF APPLICANT METHODS SECTION------------------------------------------------------------------------

    //Add Applicant
    private void addApplicantHandler(){

        String emailEntered = applicantEmailField.getText();
        String applicantNameEntered = applicantNameField.getText();
        String skill1Entered = applicantSkill1Field.getText();
        String skill2Entered = applicantSkill2Field.getText();
        String experience = applicantExperienceCombo.getValue();

        try {
            area2.setText("");
            if (applicantNameEntered.length() == 0 || emailEntered.length() == 0 || skill1Entered.length() == 0 ||
                    skill2Entered.length() == 0 || experience == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Warning: Make sure all fields above are entered!");
                alert.showAndWait();
            } else if (Integer.parseInt(experience) < 0) {
                area2.setText("Experience cannot have negative value!");
            } else {
                Applicant applicant = new Applicant(emailEntered, applicantNameEntered, skill1Entered, skill2Entered, Integer.parseInt(experience));
                boolean checkApplicantDetails = applicant.check();
                if (checkApplicantDetails) {
                    boolean ok = applicantList.addApplicant(applicant);
                    if (ok) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Applicant Added!", new ButtonType("OK"));
                        alert.showAndWait();
                    } else {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR,"Applicant already on the system!", new ButtonType("OK") );
                        alert2.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Name and Skills must not have digits!", new ButtonType("OK"));
                    alert.showAndWait();
                }
                applicantEmailField.setText("");
                applicantNameField.setText("");
                applicantSkill1Field.setText("");
                applicantSkill2Field.setText("");
                applicantExperienceCombo.setValue("");
            }
            /*
             * A catch exception thrown by EasyScannerPlus to indicate that the token retrieved does not match the pattern
             * for the expected type, or that the token is out of range for the expected type. Also throw the AgencyException
             * if a value is negative
             */
        } catch (InputMismatchException e) {
            area2.setText(""+ e);
        }
    }

    //Remove an applicant
    private void removeApplicantHandler() {

        try {
            area2.setText("");
            String email;
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Remove an Applicant from List");
            dialog.setHeaderText("Enter your email");
            email = dialog.showAndWait().get();
            if (email.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Email field must not be left empty!");
                alert.showAndWait();
            } else {
                //Retrieve the job using the Optional class
                Optional<Applicant> applicant = applicantList.getApplicant(email);
                Alert alert;
                if (applicant.isPresent()) {
                    alert = new Alert(Alert.AlertType.WARNING, "Are you sure you wish to delete this applicant?", new ButtonType("Yes"), new ButtonType("No"));
                    String response = alert.showAndWait().get().getText();
                    if (response.equals("Yes")) {
                        applicantList.removeApplicant(email);
                        alert = new Alert(Alert.AlertType.CONFIRMATION, "Applicant successfully removed!", new ButtonType("OK"));
                    } else {
                        alert = new Alert(Alert.AlertType.CONFIRMATION, "Applicant kept in the list!", new ButtonType("OK"));
                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR, "Applicant not found in the list");
                }
                alert.showAndWait();
            }

            //A catch block when the user presses the "cancel Button" to cancel the section
        } catch (NoSuchElementException t) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Section Cancelled");
            alert.showAndWait();
        }
    }

    // Update an Applicant
    private void updateApplicantHandler() {
        try {
            area2.setText("");
            String email;
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter your Email");
            dialog.setTitle("Update an Applicant");
            email = dialog.showAndWait().get();
            if (email.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Email field must not be left empty");
                alert.showAndWait();
            } else {
                //Retrieve the job using the Optional class
                Optional<Applicant> applicant = applicantList.getApplicant(email);
                if (applicant.isPresent()) {
                    ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("Your Name","Skill 1","Skill 2","Your Experience");
                    choiceDialog.setContentText("What would you like to update?");
                    String response = choiceDialog.showAndWait().get();

                    switch (response) {

                        case "Your Name" -> {
                            area2.setText("Your Current Name: " + applicantList.getApplicant(email).get().getName());
                            dialog = new TextInputDialog();
                            dialog.setHeaderText("Enter new Name");
                            String newName = dialog.showAndWait().get();
                            applicantList.getApplicant(email).get().setName(newName);
                            Alert applicantName = new Alert(Alert.AlertType.CONFIRMATION);
                            applicantName.setContentText("Applicant's name successfully updated!");
                            applicantName.showAndWait();
                        }
                        case "Skill 1" -> {
                            area2.setText("Your current Skill 1: " + applicantList.getApplicant(email).get().getSkill_1());
                            dialog = new TextInputDialog();
                            dialog.setHeaderText("Enter your new Skill 1");
                            String newSkill1 = dialog.showAndWait().get();
                            applicantList.getApplicant(email).get().setSkill_1(newSkill1);
                            Alert applicantSkill1 = new Alert(Alert.AlertType.CONFIRMATION);
                            applicantSkill1.setContentText("Applicant's Skill 1 successfully updated!");
                            applicantSkill1.showAndWait();
                        }
                        case "Skill 2" -> {
                            area2.setText("Your current Skill 2: " + applicantList.getApplicant(email).get().getSkill_2());
                            dialog = new TextInputDialog();
                            dialog.setHeaderText("Enter your new Skill 2");
                            String newSkill2 = dialog.showAndWait().get();
                             applicantList.getApplicant(email).get().setSkill_2(newSkill2);
                            Alert applicantSkill2 = new Alert(Alert.AlertType.CONFIRMATION);
                            applicantSkill2.setContentText("Applicant's Skill 2 successfully updated!");
                            applicantSkill2.showAndWait();
                        }
                        case "Your Experience" -> {
                            area2.setText("Your current Experience: " + applicantList.getApplicant(email).get().getYourExperience()+ " years.");
                            dialog = new TextInputDialog();
                            dialog.setHeaderText("Enter your new Experience");
                            String newExperience = dialog.showAndWait().get();
                            int experience = Integer.parseInt(newExperience);
                            if (experience < 0) {
                                Alert errorExperienceNumber = new Alert(Alert.AlertType.ERROR);
                                errorExperienceNumber.setContentText("Error: Experience cannot be negative!");
                                errorExperienceNumber.showAndWait();
                            } else {
                                 applicantList.getApplicant(email).get().setYourExperience(experience);
                                Alert applicantExperience = new Alert(Alert.AlertType.CONFIRMATION);
                                applicantExperience.setContentText("Applicant's Experience successfully updated!");
                                applicantExperience.showAndWait();
                            }
                        }
                        case "Keep the Original info" -> {
                             Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Original Applicant's information kept!",new ButtonType("OK"));
                            alert.showAndWait();
                        }
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Applicant not found in the list");
                    alert.showAndWait();
                }
            }
            //This catch block will execute if the experience have characters
        } catch (NumberFormatException e) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText(""+e);
            alert2.showAndWait();

            //A catch block when the user presses the "cancel Button" to cancel the section
        } catch (NoSuchElementException t) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Section Cancelled");
            alert2.showAndWait();
        }
    }

    // Display a specified applicant
    private void displayApplicantHandler() {
        try {
            area2.setText("");
            String email;
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter your email");
            dialog.setTitle("Retrieve an Applicant");
            email = dialog.showAndWait().get();
            if (email.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Error: Email field must  be entered!");
                alert.showAndWait();
            } else {
                //Retrieve the job using the Optional class
                Optional<Applicant> applicant = applicantList.getApplicant(email);
                if (applicant.isPresent()) {
                    area2.setText("" + applicant.toString());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Applicant not found in the list");
                    alert.showAndWait();
                }
            }
            //A catch block when the user presses the "cancel Button" to cancel the section
        } catch (NoSuchElementException t) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Section Cancelled");
            alert2.showAndWait();
        }
    }

    //Display all applicants
    private void displayApplicantsHandler() {

        area2.setText("");
        String fromWhere;
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Where would like to start seeing the records from? ");
            fromWhere = dialog.showAndWait().get();
            int startFromHere = Integer.parseInt(fromWhere);
            if (applicantList.isEmpty()) {
                area2.setText("Error: Applicant List is empty!");
            } else {
                if (startFromHere < 0 || startFromHere > applicantList.getTotalOfApplicants()) {
                    area2.setText("There are no applicants starting in this position");
                } else {
                    area2.appendText("Total of applicants: "+ applicantList.getTotalOfApplicants()+ "\n");
                    for (int count = 0; count < 5; count++) {
                        int i = count + startFromHere - 1;
                        if (i >= applicantList.getTotalOfApplicants()) {
                            break;
                        }
                        area2.appendText(""+ ( count + 1)+ ")" +
                                applicantList.aList.get(i).toString() +'\n'+
                                "This applicant is currently at position "+ i + " in the list.\n"+
                                "-----------------------------------------------------------\n");
                    }
                }
            }
            //This catch block will execute if the salary and or experience have characters
        } catch (NumberFormatException e) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText(""+e);
            alert2.showAndWait();

            //A catch block when the user presses the "cancel Button" to cancel the section
        } catch (NoSuchElementException t) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Section Cancelled");
            alert2.showAndWait();
        }
    }

    //Applicant instructions
    private void applicantInstructionsHandler() {

        area2.setText("In this section you can register applicants, remove applicants, show list of applicants,and match jobs if the required skill matches.\n" +
                "Here are some tips for this section:\n"+
                "1) Register the applicants: Click on this button to register an applicant.\n" +
                "  1.1) The applicant fields must not be left blank, otherwise a failure will occur;\n" +
                "  1.2) Check your name does not contain digits;\n" +
                "  1.3) The registration will failure if the email has been already taken. If that happens, choose another email and try again;\n" +
                "  1.4) If there are no errors the system will register the applicant.\n" +
                "2) Display all applicants: Click on this button to output the list of applicants.If the list is empty the system will pop up a message.\n" +
                "3) Display applicant: Click on this buttons to output the  chosen applicant.\n" +
                "  3.1) Enter your email and click display applicant button. If the email is not found the system will output a message for the failure;\n" +
                "4) Remove Applicant: Click on this button to remove the applicant by entering the email.\n" +
                "  4.1) If the email is not found a  pop up message will appear indicating the failure, otherwise the applicant will be removed from the system.\n" +
                "5) Match a job: Click on this button to match a job by entering your email and the desired job.\n " +
                "  5.1) The system will check the email for validation and check your skills.\n" +
                "  5.2) The system will check if the job entered is on the system. A match will be produced if one of your two skills match with job skill,\n" +
                "        otherwise the system will output a message for the failure of the match.\n" +
                "6) Save all your data and leave the application by pressing the save and quit button.");

    }

    //Match a job
    private void matchAJobHandler() {

        try {
            String email, jobID;
            if (jobList.isEmpty()) {
                area2.setText("Job Match not available as there are no jobs registered!");
            }
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Enter your email");
            email = dialog.showAndWait().get();
            Optional<Applicant> findApplicant = applicantList.getApplicant(email);
            if (findApplicant.isEmpty()) {
                area2.setText("Applicant not found!");
            } else {
                area2.setText("\t\t\t\t ---- Available Jobs ----");
                for (Job job : jobList.jList) {
                    area2.setText("Job ID: "+ job.getJobId() + "\t\t Job Title:"+ job.getJobTitle() +"\t\tLocation: "+ job.getLocation()+"\n");

                    dialog = new TextInputDialog();
                    dialog.setHeaderText("Choose a job from the list using its ID");
                    jobID = dialog.showAndWait().get();
                    Optional<Job> findJob = jobList.getJob(jobID);
                    if (findJob.isEmpty()) {
                        area2.setText("Error: Check the List to see the available jobs.");
                    } else {
                        // Execute this block of code if one of the applicant's skills match with the chosen job
                        if ((findApplicant.get().getSkill_1().equalsIgnoreCase(findJob.get().getPrimarySkill()) ||
                                findApplicant.get().getSkill_2().equalsIgnoreCase(findJob.get().getPrimarySkill())) &&
                                findApplicant.get().getYourExperience() >= findJob.get().getExperience()) {

                            myMatches.put(email, jobID);
                            area2.setText("Congratulations: You have the skills necessary for this job!\n");
                        } else {
                            area2.setText("I m sorry! This job does not match you!\n"+
                                    "REASON: You must have the skill required for the job and the minimum experience required!");
                        }
                    }
                }
            }
            //A catch block when the user presses the "cancel Button" to cancel the section
        } catch (NoSuchElementException t) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Section Cancelled");
            alert2.showAndWait();
        }
    }
    //------------------------------------------------------------ END OF APPLICANTS METHODS SECTION------------------------------------------------------------------------

    //Save and Quit
    private void saveAndQuitHandler() {

        writeJobList(jobList);
        writeApplicantList(applicantList);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Saving all Data...\nThank you for using the App!", new ButtonType("Leave"));
        alert.showAndWait();
        Platform.exit();
    }

    // -----------------------------------------METHODS TO WRITE AND READ THE APPLICANTS AND JOBS FILES ----------------------------------------------------
    //Method for writing the file
    public static void writeJobList(Job_List jobList) {

        //Usage of the try-with-resources to close the file safely
        try (FileWriter jobFile = new FileWriter("Jobs.txt");
             PrintWriter jobWriter = new PrintWriter(jobFile)) {

            //Write each element of the list to the file
            for (Job item : jobList.jList) {

                jobWriter.println(item.getJobId());
                jobWriter.println(item.getJobTitle());
                jobWriter.println(item.getLocation());
                jobWriter.println(item.getType());
                jobWriter.println(item.getPrimarySkill());
                jobWriter.println(item.getSalary());
                jobWriter.println(item.getExperience());

            }
            //Handle the exception thrown by the FileWriter methods
        } catch (IOException e) {
            System.out.println("There is a problem with the file!");
        }
    }

    //Method for reading the Job file
    public static void readJobList(Job_List jobList) {
        String ID, jobName, location, type, primarySkill, strSalary, strExperience;
        int experience;
        double salary;

        //Usage of the try-with-resources to close the file safely
        try (FileReader jobFile = new FileReader("Jobs.txt");
             BufferedReader jobStream = new BufferedReader(jobFile)
        ) {
            ID = jobStream.readLine(); //To read the first line of the file
            while (ID != null) {

                //Read the remaining of the first record, then all the rest of records until the end of the file
                jobName = jobStream.readLine();
                location = jobStream.readLine();
                type = jobStream.readLine();
                primarySkill = jobStream.readLine();
                strSalary = jobStream.readLine();

                //Convert the salary from String to Double
                salary = Double.parseDouble(strSalary);
                strExperience = jobStream.readLine();

                //Convert Experience from String to Integer
                experience = Integer.parseInt(strExperience);
                Job myJob = new Job(ID, jobName, location, JobTypeOfContract.valueOf(type), primarySkill, salary, experience);
                jobList.addJob(myJob);
                ID = jobStream.readLine();
            }
        }
        //Handle the exception if the file is not found
        catch (FileNotFoundException e) {
            System.out.println("Jobs.txt not found!");
        } catch (NumberFormatException e) {
            System.out.print("");
        }
        //Handle the exception thrown by the FileReader methods
        catch (IOException exception) {
            System.out.println("Problem with the file\n");
        }
        //Handle the exception the type of contract of the job. (Contract is of type 'ENUM' )
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
    //Method for writing the Applicant file
    static void writeApplicantList(Applicant_List applicantList) {

        //Usage of the try-with-resources to close the file safely
        try (FileWriter applicantFile = new FileWriter("Applicants.txt");
             PrintWriter applicantWriter = new PrintWriter(applicantFile)) {

            //Write each element of the list to the file
            for (Applicant item : applicantList.aList) {

                applicantWriter.println(item.getEmail());
                applicantWriter.println(item.getName());
                applicantWriter.println(item.getSkill_1());
                applicantWriter.println(item.getSkill_2());
                applicantWriter.println(item.getYourExperience());
            }

            //Handle the exception thrown by the FileWriter methods
        } catch (IOException e) {
            System.out.println("There is a problem with the file!");
        }
    }

    //Method for reading the  Applicant file
    static void readApplicantList(Applicant_List applicantList) {
        String email,name, skillOne, skillTwo;
        String stringExperience;
        int experience;

        //Usage of the try-with-resources to close the file safely
        try (FileReader applicantFile = new FileReader("Applicants.txt");
             BufferedReader applicantStream = new BufferedReader(applicantFile)) {
            email = applicantStream.readLine(); //To read the first line of the file
            while (email != null) {

                //Read the remaining of the first record, then all the rest of records until the end of the file
                name = applicantStream.readLine();
                skillOne = applicantStream.readLine();
                skillTwo = applicantStream.readLine();
                stringExperience = applicantStream.readLine();

                //Convert the salary from String to Integer
                experience = Integer.parseInt(stringExperience);
                Applicant myApplicant = new Applicant( email,name, skillOne, skillTwo, experience);
                applicantList.addApplicant(myApplicant);
                email = applicantStream.readLine();
            }
            //Handle the exception if the file is not found
        } catch (FileNotFoundException e) {
            System.out.println("Applicants.txt  not found!\n");
        } catch (NumberFormatException e) {
            System.out.print("");
        } catch (IOException e) { //Handle the exception thrown by the FileReader methods
            System.out.println("Problem with the file");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}