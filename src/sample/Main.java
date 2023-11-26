package sample;

import design.qList;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Main extends Application {
    public static Stage stage;
    public static double screenHeight;
    public static double screenWidth;
    public static Connection con;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("OJ");

        stage.setMaximized(true);

        try {
            qList.showPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setOnCloseRequest((event) -> System.exit(0));
    }

    private static void connectDatabase() {
        con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oj", "postgres", "pgs123");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static void main(String[] args) {
        screenHeight = Screen.getPrimary().getBounds().getHeight();
        screenWidth = Screen.getPrimary().getBounds().getWidth();

        connectDatabase();

        launch(args); //start() method will be called
    }
}
