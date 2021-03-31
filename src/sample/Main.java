package sample;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    /*
       JOB SECTION
     */

    //Application Title
    private final Label headingLabel = new Label("Agency Application");

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
    private final TextField jobTypeField = new TextField();

    //Job Skill
    private final Label jobSkillLabel = new Label("Main Skill");
    private final TextField jobSkillField = new TextField();

    //Job salary section
    private final Label jobSalaryLabel = new Label("Job salary £");
    private final TextField jobSalaryField = new TextField();

    //Job Experience (years)
    private final Label jobExperienceLabel= new Label("Experience");

    //    private TextField jobMinYearsOfExperienceField = new TextField();
    private final ComboBox <String> jobExperienceCombo = new ComboBox();

//    private ComboBox<String> yearBox;

    //Add buttons
    private final Button addJobBtn = new Button("Add Job");
    private final Button displayAllJobsBtn = new Button("Display all Jobs");
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
    //    private TextField jobMinYearsOfExperienceField = new TextField();
    private final ComboBox <String> applicantExperienceCombo = new ComboBox();

    //Add buttons
    private final Button addApplicantBtn = new Button("Add Applicant");
    private final Button displayAllApplicantsBtn = new Button("Display all Applicants");
    private final Button applicantInstructionsBtn = new Button("Applicant Instructions");


    //Add a Text Area
    private final TextArea area2 = new TextArea();

    //Add more buttons
    private final Button displayApplicantBtn = new Button("Display Applicant");
    private final Button removeApplicantBtn = new Button("Remove Applicant");
    private final Button matchJobsBtn = new Button("Match a job");

    //To retrieve email and job
    private final Label applicantEmailLabel2 = new Label("Email");
    private final TextField applicantEmailField2 = new TextField();
    private final Label jobIDLabel2 = new Label("Job ID");
    private final TextField jobIDField2 = new TextField();

    //Final button to save all data and leave the application
    private final Button saveAndQuit = new Button("Save and Quit");


    @Override
    public void start(Stage stage) throws Exception{

        //**** JOB SECTION ****

        //Read the two files
        JobFileHandler.readJobList(jobList);
        ApplicantFileHandler.readApplicantList(applicantList);

        // Create HBoxes
        HBox jobDetailsPart1 = new HBox(10);
        HBox jobDetailsPart2 = new HBox(10);
        HBox jobButtons = new HBox(10);
        //Add items to the combo box
        jobExperienceCombo.getItems().addAll("1","2","3","4","5","6","7","8","9","10");

        //Add components to the HBoxes
        jobDetailsPart1.getChildren().addAll(jobIDLabel,jobIDField,jobNameLabel, jobNameField,jobLocationLabel,jobLocationField,jobTypeLabel,jobTypeField);
        jobDetailsPart2.getChildren().addAll(jobSkillLabel,jobSkillField,jobSalaryLabel,jobSalaryField, jobExperienceLabel,jobExperienceCombo);
        jobButtons.getChildren().addAll(addJobBtn,displayAllJobsBtn,displayAllMatchesBtn,jobInstructionsBtn);

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
        applicantButtons.getChildren().addAll(addApplicantBtn,displayAllApplicantsBtn, applicantInstructionsBtn);
        retrieveJob.getChildren().addAll(applicantEmailLabel2,applicantEmailField2,jobIDLabel2,jobIDField2);
        applicantOptionsButtons.getChildren().addAll(displayApplicantBtn,removeApplicantBtn,matchJobsBtn);


        //Create a VBox
        VBox root = new VBox(10);

        //Add all components to VBox
        root.getChildren().addAll(headingLabel,jobDetailsPart1, jobDetailsPart2,jobButtons,area1,applicantDetails,
                applicantButtons,area2,applicantOptions, retrieveJob, applicantOptionsButtons,saveAndQuit);

        //Create a Scene
        Scene scene =new Scene(root,Color.BEIGE);

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

        //Customise buttons
        addJobBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        addJobBtn.setTooltip(new Tooltip("Add a job to the List"));
        addJobBtn.setFont(new Font("Cambria", 14));
        displayAllJobsBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        displayAllJobsBtn.setTooltip(new Tooltip("Display all registered jobs"));
        displayAllJobsBtn.setFont(new Font("Cambria", 14));

        displayAllMatchesBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        displayAllMatchesBtn.setTooltip(new Tooltip("Display all applicants that match with a job"));
        displayAllMatchesBtn.setFont(new Font("Cambria", 14));

        jobInstructionsBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        jobInstructionsBtn.setTooltip(new Tooltip("Display instructions in how to register a job in the application"));
        jobInstructionsBtn.setFont(new Font("Cambria", 14));

        addApplicantBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        addApplicantBtn.setTooltip(new Tooltip("Register an applicant"));
        addApplicantBtn.setFont(new Font("Cambria", 14));

        displayAllApplicantsBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        displayAllApplicantsBtn.setTooltip(new Tooltip("Display all applicants"));
        displayAllApplicantsBtn.setFont(new Font("Cambria", 14));

        applicantInstructionsBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        applicantInstructionsBtn.setTooltip(new Tooltip("Display instructions in how to register an applicant"));
        applicantInstructionsBtn.setFont(new Font("Cambria", 14));

        displayApplicantBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        displayApplicantBtn.setTooltip(new Tooltip("Display a particular applicant"));
        displayApplicantBtn.setFont(new Font("Cambria", 14));

        removeApplicantBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        removeApplicantBtn.setTooltip(new Tooltip("Remove an applicant"));
        removeApplicantBtn.setFont(new Font("Cambria", 14));

        matchJobsBtn.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY)));
        matchJobsBtn.setTooltip(new Tooltip("Find a job that matches your skills"));
        matchJobsBtn.setFont(new Font("Cambria", 14));

        saveAndQuit.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(10), Insets.EMPTY)));
        saveAndQuit.setTooltip(new Tooltip("Save all data and leave the application"));
        saveAndQuit.setFont(new Font("Cambria", 14));

        //Call private methods for Button event handlers
        addJobBtn.setOnAction( e ->addJobHandler());
        displayAllJobsBtn.setOnAction(e -> displayAllJobsHandler());
        displayAllMatchesBtn.setOnAction(e -> displayAllMatchesHandler());
        jobInstructionsBtn.setOnAction(e -> jobInstructionsHandler());
        addApplicantBtn.setOnAction( e ->  addApplicantHandler());
        displayAllApplicantsBtn.setOnAction( e ->  displayAllApplicantsHandler());
        applicantInstructionsBtn.setOnAction( e ->  applicantInstructionsHandler());
        displayApplicantBtn.setOnAction( e ->  displayApplicantHandler());
        removeApplicantBtn.setOnAction( e ->  removeApplicantHandler());
        matchJobsBtn.setOnAction( e ->  matchAJobHandler());
        saveAndQuit.setOnAction( e ->  saveAndQuitHandler());

        //Application Scene

        stage.setScene(scene);
        stage.setTitle("Agency Application");
        stage.setResizable(false); //the user cannot resize the application screen
        stage.show();
    }

    // Event handler methods

    //Add job
    private void addJobHandler() {
        String jobIDEntered = jobIDField.getText();
        String jobNameEntered = jobNameField.getText();
        String jobLocationEntered = jobLocationField.getText();
        String jodTypeEntered = jobTypeField.getText();
        String jobSkillEntered = jobSkillField.getText();
        String jobSalaryEntered = jobSalaryField.getText();
        String jobYearsEntered = jobExperienceCombo.getValue();

        try {
            //Check if there are errors
            if (jobIDEntered.length() == 0 || jobNameEntered.length() == 0 || jobLocationEntered.length() == 0 ||
                    jodTypeEntered.length() == 0 || jobSkillEntered.length() == 0 || jobSalaryEntered == null || jobYearsEntered == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Warning: Make sure all fields are entered!");
                alert.showAndWait();
            } else {
                Job myJob = new Job(jobIDEntered, jobNameEntered, jobLocationEntered, jodTypeEntered, jobSkillEntered,
                        Double.parseDouble(jobSalaryEntered), Integer.parseInt(jobYearsEntered));
                boolean checkJobDetails = myJob.check();
                if (checkJobDetails) {
                    boolean ok = jobList.addJob(myJob);
                    if (ok) {
                        jobIDField.setText("");
                        jobNameField.setText("");
                        jobLocationField.setText("");
                        jobTypeField.setText("");
                        jobSkillField.setText("");
                        jobSalaryField.setText("");
                        jobExperienceCombo.setValue("");

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Job added to the list!");
                        alert.showAndWait();
                    } else {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setContentText("Job already on the system!");
                        alert2.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("ID must contain only 4 characters!");
                    alert.showAndWait();
                }
            }
        } catch (InputMismatchException e) {
            area1.setText("" + e);
        } catch (NumberFormatException t) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Error: Salary must have only digits!");
            alert2.showAndWait();
        }
    }

    //Display all Jobs
    private void displayAllJobsHandler() {
        if (jobList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Job List is empty!");
            alert.showAndWait();
        } else {
            area1.setText("Total of jobs: "+ jobList.totalOfJobs()+"\n\n");
            area1.setText(""+ jobList.jList);

        }
    }

    //Method to display all matches
    private void displayAllMatchesHandler() {
        if (myMatches.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No matches recorded!");
            alert.showAndWait();
        } else {

            area1.setText(" **** Matches ***\n");
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


    //APPLICANT METHODS SECTION


    //Add Applicant
    private void addApplicantHandler(){

        String emailEntered = applicantEmailField.getText();
        String applicantNameEntered = applicantNameField.getText();
        String skill1Entered = applicantSkill1Field.getText();
        String skill2Entered = applicantSkill2Field.getText();
        String experience = applicantExperienceCombo.getValue();

        try {
            if (applicantNameEntered.length() == 0 || emailEntered.length() == 0 || skill1Entered.length() == 0 ||
                    skill2Entered.length() == 0 || experience == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Do not leave the fields blank!");
                alert.showAndWait();
            } else {
                Applicant applicant = new Applicant( emailEntered,applicantNameEntered, skill1Entered, skill2Entered, Integer.parseInt(experience));
                boolean checkApplicantDetails = applicant.check();
                if (checkApplicantDetails) {
                    boolean ok = applicantList.addApplicant(applicant);
                    if (ok) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Applicant Added!");
                        alert.showAndWait();
                    } else {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setContentText("Applicant already on the system!");
                        alert2.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Name and Skills must not have digits!");
                    alert.showAndWait();
                }
                applicantEmailField.setText("");
                applicantNameField.setText("");
                applicantSkill1Field.setText("");
                applicantSkill2Field.setText("");
                applicantExperienceCombo.setValue("");
            }
        } catch (InputMismatchException e) {
            area2.setText(""+ e);
        }
    }

    //Display all applicants
    private void displayAllApplicantsHandler() {
        if (applicantList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Applicant List is empty!");
            alert.showAndWait();
        } else {
            area2.setText("Total of applicants: "+ applicantList.getTotal()+"\n\n");
            area2.setText(""+ applicantList.aList);

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

    // Display a specified applicant
    private void displayApplicantHandler() {

        String emailEntered = applicantEmailField2.getText();
        if (emailEntered.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No retrieve! Enter your email to retrieve the applicant details!");
            alert.showAndWait();
        } else if (applicantList.getApplicant(emailEntered).isPresent()) {
            area2.setText("" + applicantList.getApplicant(emailEntered));
            applicantEmailField2.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No match! Check if the email is correct and try again!");
            alert.showAndWait();
        }
    }

    //Remove an applicant
    private void removeApplicantHandler() {
        String emailEntered = applicantEmailField2.getText();
        if (emailEntered.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Enter email to remove the applicant!");
            alert.showAndWait();
        } else {
            if (applicantList.getApplicant(emailEntered).isPresent()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "" , ButtonType.YES, ButtonType.NO);
                alert.setContentText("Do you want to delete "+ applicantList.getApplicant(emailEntered).get().getName().toUpperCase()+ " ?");
                String response = alert.showAndWait().get().getText();
                if (response.equals("Yes")) {
                    applicantList.removeApplicant(emailEntered);
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setContentText("Applicant successfully removed!");
                    alert2.showAndWait();
                } else {
                    Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert3.setContentText("Applicant kept in the list!");
                    alert3.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("No match! Check if the email is correct and try again!");
                alert.showAndWait();
            }
            applicantEmailField2.setText("");
            jobIDField2.setText("");
        }
    }

    //Match a job
    private void matchAJobHandler() {
        String emailEntered = applicantEmailField2.getText();
        String jobIDEntered = jobIDField2.getText();

        try {
            if (emailEntered.length() == 0 || jobIDEntered.length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No retrieve! Enter your email and job name!");
                alert.showAndWait();
            } else {
                if (applicantList.getApplicant(emailEntered).isEmpty() || jobList.getJob(jobIDEntered).isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error: Jobs and Applicant Lists are empty.");
                    alert.showAndWait();
                } else if (applicantList.getApplicant(emailEntered).isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error: Applicant List is empty!");
                    alert.showAndWait();
                } else if (jobList.getJob(jobIDEntered).isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error: Job List is empty!");
                    alert.showAndWait();
                } else {
                    Optional<Job> retrieveJob = jobList.getJob(jobIDEntered);
                    if (retrieveJob.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Error! Check in the text area the available jobs and try again!");
                        alert.showAndWait();
                    } else {
                        if ((applicantList.getApplicant(emailEntered).get().getSkill_1().equalsIgnoreCase(jobList.getJob(jobIDEntered).get().getPrimarySkill()) ||
                                applicantList.getApplicant(emailEntered).get().getSkill_2().equalsIgnoreCase(jobList.getJob(jobIDEntered).get().getPrimarySkill())) &&
                                applicantList.getApplicant(emailEntered).get().getYourExperience() >= jobList.getJob(jobIDEntered).get().getExperience()) {

                            myMatches.put(emailEntered, jobIDEntered);

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText("Congratulations! You have the necessary skills for this job! ");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Error: This job does not match with you! ");
                            alert.showAndWait();
                            area2.setText("REASON: You must have the skill required for the job and the minimum experience required!");
                            area2.setText("");
                        }
                    }
                }
                applicantEmailField2.setText("");
                jobIDField2.setText("");
            }
        } catch (InputMismatchException e) {

            area2.setText(""+ e);
        }
    }
    //Save and Quit
    private void saveAndQuitHandler() {
        JobFileHandler.writeJobList(jobList);
        ApplicantFileHandler.writeApplicantList(applicantList);
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

