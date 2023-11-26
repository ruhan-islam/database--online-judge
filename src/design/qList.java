package design;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class qList {
    private static Stage stage = Main.stage;
    private static double screenWidth = Main.screenWidth;
    private static double screenHeight = Main.screenHeight;

    public static void showPage() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(qList.class.getResource("qList.fxml"));

        Region contentRootRegion = null;
        try {
            contentRootRegion = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Set a default "standard" or "100%" resolution
        double origW = screenWidth;
        double origH = screenHeight;

        //If the Region containing the GUI does not already have a preferred width and height, set it.
        //But, if it does, we can use that setting as the "standard" resolution.
        if (contentRootRegion.getPrefWidth() == Region.USE_COMPUTED_SIZE)
            contentRootRegion.setPrefWidth(origW);
        else
            origW = contentRootRegion.getPrefWidth();

        if (contentRootRegion.getPrefHeight() == Region.USE_COMPUTED_SIZE)
            contentRootRegion.setPrefHeight(origH);
        else
            origH = contentRootRegion.getPrefHeight();

        //Wrap the resizable content in a non-resizable container (Group)
        Group group = new Group(contentRootRegion);

        //Place the Group in a StackPane, which will keep it centered
        StackPane rootPane = new StackPane();
        rootPane.getChildren().add(group);

        //Create the scene initally at the "100%" size
        Scene scene = new Scene(rootPane, origW, origH);

        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind(scene.widthProperty().divide(origW));
        group.scaleYProperty().bind(scene.heightProperty().divide(origH));

        stage.setScene(scene);
        stage.show();
    }
}
