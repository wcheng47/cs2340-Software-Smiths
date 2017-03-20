package main.java.controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import main.java.model.PurityReport;
import main.java.model.SourceReport;
import main.java.model.User;
import com.google.firebase.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.google.firebase.database.*;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;


public class Main extends Application {
    private Stage window;
    private AnchorPane viewReportLayout;
    private static ArrayList<User> userArr = new ArrayList<>();
    private static ArrayList<SourceReport> sourceReportList = new ArrayList<>();
    private static ArrayList<PurityReport> purityReportList = new ArrayList<>();
    private static ArrayList<String> purityLocationsList = new ArrayList<>();
    private static ArrayList<String> purityYearList = new ArrayList<>();
    private static ArrayList<HashMap<String, String>> userArrDatabase = new ArrayList<>();
    private static ArrayList<HashMap<String, Object>> sourceReportListDatabase = new ArrayList<>();
    private static ArrayList<HashMap<String, Object>> purityReportListDatabase = new ArrayList<>();
    private static double maxVirus = 0;
    private static double maxContaminant = 0;
    private boolean isStarted1 = false;
    private boolean isStarted2 = false;
    private boolean isStarted3 = false;
    private boolean isStarted4 = false;
    private boolean isStarted5 = false;
    private FirebaseOptions options;
    private DatabaseReference userRef;
    private DatabaseReference sourceRef;
    private DatabaseReference purityRef;
    private DatabaseReference purityLocationRef;
    private DatabaseReference purityYearRef;

    /*private static ArrayList<LatLong> purityLocationsList = new ArrayList<>();
    private static ArrayList<String> purityYearList = new ArrayList<>();*/

    /**
     * Runs as soon as the program starts, first method that runs. Sets up the database and loads the welcome screen.
     * @param primaryStage the first stage you want to load
     * @throws Exception when database errors
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("STARTED");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("src/main/java/model/cs2340-software-smiths-4665dd93b180.json"))
                .setDatabaseUrl("https://cs2340-software-smiths.firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(options);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //String path = getClass().getResource("UnderTheSea.mp3").toString();
        //Media media = new Media(path);
        //MediaPlayer mp = new MediaPlayer(media);
        //mp.stop();
        String path = getClass().getResource("UnderTheSea.mp3").toString();
        Media media = new Media(path);
        MediaPlayer mp = new MediaPlayer(media);
        mp.play();
        userRef = database.getReference("users");
        sourceRef = database.getReference("sourceReports");
        purityRef = database.getReference("purityReports");
        purityLocationRef = database.getReference("purityLocations");
        purityYearRef = database.getReference("purityYears");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //GenericTypeIndicator<List<User>> t = new GenericTypeIndicator<List<User>>() {};

                if (dataSnapshot.getValue() != null && !isStarted1) {
                    isStarted1=true;
                    userArrDatabase = (ArrayList<HashMap<String, String>>) dataSnapshot.getValue();
                    for(HashMap<String, String> a : userArrDatabase){
                        userArr.add(new User(a.get("firstName"), a.get("lastName"), a.get("userName"), a.get("password"), a.get("type"), a.get("banned"), a.get("logCount")));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        sourceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //GenericTypeIndicator<List<Report>> t = new GenericTypeIndicator<List<Report>>() {};
                if (dataSnapshot.getValue() != null&& !isStarted2) {
                    isStarted2=true;
                    sourceReportListDatabase = (ArrayList<HashMap<String, Object>>) dataSnapshot.getValue();
                    for(HashMap<String, Object> a : sourceReportListDatabase){
                        HashMap<String, Object> dateDatabase = (HashMap<String, Object>) a.get("_date");
                        Date date = new Date();
                        date.setDate(((Long)dateDatabase.get("date")).intValue());
                        date.setHours(((Long)dateDatabase.get("hours")).intValue());
                        date.setMinutes(((Long)dateDatabase.get("minutes")).intValue());
                        date.setMonth(((Long)dateDatabase.get("month")).intValue());
                        date.setSeconds(((Long)dateDatabase.get("seconds")).intValue());
                        date.setYear(((Long)dateDatabase.get("year")).intValue());
                        sourceReportList.add(new SourceReport(((Long)a.get("_reportNumber")).intValue(),(String) a.get("_reporterName"), date, ((Number)a.get("_longitude")).doubleValue(),((Number) a.get("_latitude")).doubleValue(), (String)a.get("_waterType"), (String) a.get("_waterCondition")));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        purityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //GenericTypeIndicator<List<Report>> t = new GenericTypeIndicator<List<Report>>() {};
                if (dataSnapshot.getValue() != null&& !isStarted3) {
                    isStarted3=true;
                    purityReportListDatabase = (ArrayList<HashMap<String, Object>>) dataSnapshot.getValue();
                    for(HashMap<String, Object> a : purityReportListDatabase){
                        HashMap<String, Object> dateDatabase = (HashMap<String, Object>) a.get("_date");
                        Date date = new Date();
                        date.setDate(((Long)dateDatabase.get("date")).intValue());
                        date.setHours(((Long)dateDatabase.get("hours")).intValue());
                        date.setMinutes(((Long)dateDatabase.get("minutes")).intValue());
                        date.setMonth(((Long)dateDatabase.get("month")).intValue());
                        date.setSeconds(((Long)dateDatabase.get("seconds")).intValue());
                        date.setYear(((Long)dateDatabase.get("year")).intValue());
                        purityReportList.add(new PurityReport(((Long)a.get("_reportNumber")).intValue(),(String) a.get("_nameOfWorker"), date, ((Number)a.get("_longitude")).doubleValue(),((Number) a.get("_latitude")).doubleValue(), (String)a.get("_waterOverallCondition"), ((Number) a.get("_virusPPM")).doubleValue(),((Number) a.get("_contaminantPPM")).doubleValue()));
                        setMaxVirus(((Number) a.get("_virusPPM")).doubleValue());
                        setMaxContaminant(((Number) a.get("_contaminantPPM")).doubleValue());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        purityLocationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                if (dataSnapshot.getValue() != null&& !isStarted4) {
                    isStarted4=true;
                    purityLocationsList = (ArrayList<String>) dataSnapshot.getValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        purityYearRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                if (dataSnapshot.getValue() != null&& !isStarted5) {
                    isStarted5=true;
                    purityYearList = (ArrayList<String>) dataSnapshot.getValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        window = primaryStage;
        loadWelcome();
    }

    /**
     * loads the welcome screen
     */
    public void loadWelcome() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/Welcome.fxml"));
            GridPane welcomeLayout = loader.load();

            WelcomeController controller = loader.getController();
            controller.setMainApp(this);

            window.setTitle("Welcome Page");
            Scene welcomeScene = new Scene(welcomeLayout);
            window.setScene(welcomeScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads the application for the specific user
     * @param currentUser the current logged in user
     */
    public void loadApplication(User currentUser) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/ApplicationScreen.fxml"));
            HBox applicationLayout = loader.load();

            ApplicationController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(currentUser);
            window.setTitle("Login Page");
            Scene applicationScene = new Scene(applicationLayout);
            window.setScene(applicationScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * loads the login screen
     */
    public void loadLogin() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/LoginScreen.fxml"));
            AnchorPane loginLayout = loader.load();

            LoginScreenController controller = loader.getController();
            controller.setMainApp(this);

            window.setTitle("Login Page");
            Scene loginScene = new Scene(loginLayout);
            window.setScene(loginScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * loads the registration screen
     */
    public void loadRegister() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RegistrationScreen.fxml"));
            AnchorPane registrationLayout = loader.load();

            RegistrationScreenController controller = loader.getController();
            controller.setMainApp(this);

            window.setTitle("Registration Page");
            Scene registrationScene = new Scene(registrationLayout);
            window.setScene(registrationScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * loads the edit profile screen for the user
     * @param user the user who's profile you're editing
     */
    public void loadEditProfile(User user) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/EditProfileScreen.fxml"));
            AnchorPane editProfileLayout = loader.load();

            EditProfileScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("Edit Profile");
            Scene editProfileScene = new Scene(editProfileLayout);
            window.setScene(editProfileScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * loads the Create Profile Screen
     * @param user the user whose profile you're creating
     */
    public void loadCreateProfile(User user) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/CreateProfileScreen.fxml"));
            AnchorPane createProfileLayout = loader.load();

            CreateProfileScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("Create Profile");
            Scene createProfileScene = new Scene(createProfileLayout);
            window.setScene(createProfileScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * loads the submit source report screen
     * @param user the user who is submitting a report
     */
    public void loadSourceReportPage(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/SubmitSourceReportScreen.fxml"));
            AnchorPane sourceReportLayout = loader.load();

            SubmitSourceReportScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("Submit Source Report");
            Scene createReportScene = new Scene(sourceReportLayout);
            window.setScene(createReportScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * loads the submit water report screen
     * @param user the user who is submitting a report
     */
    public void loadWaterReport(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/SubmitWaterReportScreen.fxml"));
            AnchorPane waterReportLayout = loader.load();

            SubmitWaterReportScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("Submit Source Report");
            Scene createReportScene = new Scene(waterReportLayout);
            window.setScene(createReportScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * loads the view source report table screen
     * @param user the user who is viewing the source report
     */
    public void loadViewSourceReport(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/ViewSourceReportTableScreen.fxml"));
            viewReportLayout = loader.load();
            ViewSourceReportScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);
            window.setTitle("View Source Reports");
            Scene createReportScene = new Scene(viewReportLayout);
            window.setScene(createReportScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * loads the purity report screen
     * @param user the user who is viewing the purity report
     */
    public void loadViewPurityReport(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/ViewSourceReportTableScreen.fxml"));
            viewReportLayout = loader.load();
            ViewSourceReportScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);
            window.setTitle("View Purity Report");
            Scene createReportScene = new Scene(viewReportLayout);
            window.setScene(createReportScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * loads the map screen
     */
    public void loadMap() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/GMap.fxml"));
            BorderPane mapReportLayout = loader.load();

            GMapController controller = loader.getController();
            controller.setMainApp(this);

            window.setTitle("Water Report Map");
            Scene createMapScene = new Scene(mapReportLayout);
            window.setScene(createMapScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * closes the map view
     */
    public void closeMapView() {
        loadApplication(ApplicationController.getCurrentUser());
    }

    /**
     * loads the history graph scene
     * @param user the user who is loading the history graph
     */
    public void loadHistoryGraph(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/HistoryGraph.fxml"));
            AnchorPane historyGraphLayout = loader.load();

            HistoryGraphController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("History Graph");
            Scene createGraphScene = new Scene(historyGraphLayout);
            window.setScene(createGraphScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * loads the graph.
     * @param reportList the list of data points to be plotted
     * @param data the virus/contaminant type
     * @param position the location the user is looking at
     * @param year the year the user is looking at
     * @param user the user who is looking at the graph
     */
    public void loadGraph(ArrayList<PurityReport> reportList, String data, String position, String year, User user) {
        double[] monthSums  = new double[12];
        double[] monthCount = new double[12];
//        for(PurityReport pu: reportList){
//            System.out.println(pu.toString());
//        }
        System.out.println(reportList.size());
        Axis xAxis = new NumberAxis(1, 12, 1);
        Axis yAxis;
        ScatterChart<NumberAxis, NumberAxis> graph;
        XYChart.Series series = new XYChart.Series();
        if(data.equals("Virus")) {
            int virus = (int) maxVirus;
            int count = 0;
            while (virus != 0) {
                virus = virus/10;
                count++;
            }
            double upperYBound = Math.pow(10, count);
            yAxis = new NumberAxis(0, upperYBound, upperYBound/10);

            xAxis.setLabel("Month (January = 1)");
            yAxis.setLabel(data + " (ppm)");
            graph = new ScatterChart<>(xAxis, yAxis);
            graph.setTitle(year + " " + data + " Trend For Location " + position);
            series.setName("Virus vs. Month");
            for(PurityReport report: reportList) {
                Date date = report.get_date();
                Calendar cal = new GregorianCalendar();
                cal.setTime(date);
                System.out.println(Calendar.MONTH);

                int month = cal.get(Calendar.MONTH);
                double virusPPM = report.get_virusPPM();
                monthSums[month] += virusPPM;
                monthCount[month]++;
                System.out.println(virusPPM);
                //series.getData().add(new XYChart.Data((double)month, monthSums[month--]/monthCount[month--]));
            }
            for(int j = 0; j < monthSums.length; j++) {

                int c = j+1;
                System.out.println(monthSums[j]+" "+monthCount[j] + " " +c);
                if(monthCount[j] != 0) {
                    series.getData().add(new XYChart.Data((double)c, monthSums[j]/monthCount[j]));
                }

            }
        } else {
            int contaminant = (int) maxContaminant;
            int count = 0;
            while (contaminant != 0) {
                contaminant = contaminant/10;
                count++;
            }
            double upperYBound = Math.pow(10, count);
            yAxis = new NumberAxis(0, upperYBound, upperYBound/10);

            xAxis.setLabel("Month (January = 1)");
            yAxis.setLabel(data + " (ppm)");
            graph = new ScatterChart<>(xAxis, yAxis);
            graph.setTitle(year + " " + data + " Trend For Location " + position);
            series.setName("Virus vs. Month");
            for(PurityReport report: reportList) {
                Date date = report.get_date();
                Calendar cal = new GregorianCalendar();
                cal.setTime(date);
                System.out.println(Calendar.MONTH);

                int month = cal.get(Calendar.MONTH);
                double contaminantPPM = report.get_contaminantPPM();
                monthSums[month] += contaminantPPM;
                monthCount[month]++;
                System.out.println(contaminantPPM);
                //series.getData().add(new XYChart.Data((double)month, monthSums[month--]/monthCount[month--]));
            }
            for(int j = 0; j < monthSums.length; j++) {
                System.out.println(monthSums[j]+" "+j);
                int c = j+1;
                series.getData().add(new XYChart.Data((double)c, monthSums[j]/monthCount[j]));
            }
        }
        graph.getData().addAll(series);
        Button goBack = new Button("Return");
        goBack.setOnAction((ActionEvent e) ->
                loadApplication(user));
        VBox graphLayout = new VBox();
        graphLayout.setAlignment(Pos.CENTER);
        graphLayout.getChildren().addAll(graph, goBack);
        Scene graphScene = new Scene(graphLayout, 500, 400);
        window.setScene(graphScene);
        window.show();
    }

    public void loadDeleteUser(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/DeleteUser.fxml"));
            AnchorPane deleteUserLayout = loader.load();

            DeleteUserController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("Delete User");
            Scene deleteUserScene = new Scene(deleteUserLayout);
            window.setScene(deleteUserScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadBanUser(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/BanUser.fxml"));
            AnchorPane banUserLayout = loader.load();

            BanUserController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("Ban User");
            Scene banUserScene = new Scene(banUserLayout);
            window.setScene(banUserScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadUnblockUser(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/UnblockUserScreen.fxml"));
            AnchorPane unblockUserLayout = loader.load();

            UnblockUserController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("Unblock User");
            Scene unblockUserScene = new Scene(unblockUserLayout);
            window.setScene(unblockUserScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadDeleteReport(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/DeleteReport.fxml"));
            AnchorPane deleteReportLayout = loader.load();

            DeleteReportController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCurrentUser(user);

            window.setTitle("Delete Report");
            Scene deleteReportScene = new Scene(deleteReportLayout);
            window.setScene(deleteReportScene);
            window.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * adds the user to the database
     * @param user the user you want to add
     * @return false if the username already exists and true if it does not
     */
    public boolean addUser(User user) {
        boolean contains = false;
        for(int j = 0; j < userArr.size(); j++) {
            if(userArr.get(j).getUserName().equals(user.getUserName())) {
                contains = true;
            }
        }
        if(!contains) {
            userArr.add(user);

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Username Exists");
            alert.setContentText("This username is taken!");
            alert.showAndWait();
            return false;
        }

       // ArrayList<HashMap<String, String>> myUserList = getUserList();
        userRef.setValue(userArr);
        return true;
    }

    public void removeUser(int index) {
        userArr.remove(index);
        userRef.setValue(userArr);
    }

    public void banUser(int index) {
        User toBan = userArr.get(index);
        toBan.setBanned("TRUE");
        userRef.setValue(userArr);
    }

    public void incrementLog(int index) {
        userArr.get(index).incrementLogCount();
        userRef.setValue(userArr);
    }

    public void resetLog(int index) {
        userArr.get(index).resetLogCount();
        userRef.setValue(userArr);
    }

    public void deletePurityReport(int index) {
        purityReportList.remove(index);
        purityRef.setValue(purityReportList);
    }

    public void deleteSourceReport(int index) {
        sourceReportList.remove(index);
        sourceRef.setValue(sourceReportList);
    }

    /**
     * adds the Source Report to the database
     * @param report the source report you want to add
     */
    public void addSourceReport(SourceReport report) {
        sourceReportList.add(report);
       // ArrayList<HashMap<String, Object>> mySourceReportList = getSourceReportList();
        sourceRef.setValue(sourceReportList);
    }

    /**
     * adds the purity report to the database
     * @param report the purity report you want to add
     */
    public void addPurityReport (PurityReport report) {
        purityReportList.add(report);
        //ArrayList<HashMap<String, Object>> myPurityReportList = getPurityReportList();
        purityRef.setValue(purityReportList);
    }

    /**
     * adds the purity report location to the database
     * @param position the location you want to add
     */
    public void addPurityLocation (String position) {
        if( !(purityLocationsList.contains(position)) ) {
            purityLocationsList.add(position);
        }
        ArrayList<String> myPurityLocationsList = getPurityLocationsList();
        purityLocationRef.setValue(myPurityLocationsList);
    }

    /**
     * adds the year to the database
     * @param year the year you want to add
     */
    public void addPurityYear (String year) {
        if ( !(purityYearList.contains(year))) {
            purityYearList.add(year);
        }
        ArrayList<String> myPurityYearsList = getPurityYearList();
        purityYearRef.setValue(myPurityYearsList);
    }

    /**
     * gets the list of users
     * @return an arrayList of all the users from the database
     */
    public ArrayList<User> getUserList() {
        return userArr;
    }

    /**
     * gets the list of source report list
     * @return an arrayList of all the source report list from the database
     */
    public ArrayList<SourceReport> getSourceReportList() { return sourceReportList; }

    /**
     *
     * @return
     */
    public ArrayList<PurityReport> getPurityReportList() { return purityReportList; }

    /**
     *
     * @return
     */
    public ArrayList<String> getPurityLocationsList() { return purityLocationsList; }

    /**
     *
     * @return
     */
    public ArrayList<String> getPurityYearList() {return purityYearList; }

    /**
     *
     * @param virus
     */
    public void setMaxVirus(double virus) {
        if(virus >= maxVirus) {
            maxVirus = virus;
        }
    }

    /**
     *
     * @param contaminant
     */
    public void setMaxContaminant(double contaminant) {
        if(contaminant >= maxContaminant) {
            maxContaminant = contaminant;
        }
    }

    public void writeSecurity(String message) {
        try {
            File secLog = new File("C:\\Users\\Yash\\Documents\\2340\\cs2340-software-smiths\\src\\main\\java\\controller\\Security.txt");
            //File secLog = new File(getClass().getResource("Security.txt").toString());
            FileWriter fw = new FileWriter(secLog, true);
            Date date = new Date();
            fw.write(date.toString() + " " + message + "\n");
            fw.close();
        } catch (IOException e) {

            System.out.println("File does not exist");
        }

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
