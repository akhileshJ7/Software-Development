package janaswamya;
/*
 * SE1021
 * Winter 2017-18
 * Lab9 Final Project
 * Created: 2/14/2018
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main class which calls all the fxml files to open the windown and set window dimensions
 */
public class Main extends Application {
    public final int WIDTH = 550;
    public final int HEIGHT = 400;

    /**
     *method which starts and calls the stage for the windows
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("scene1.fxml").openStream());
        primaryStage.setTitle("Image Manipulator");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();

        Controller controller = fxmlLoader.getController();
        controller.setPane((Pane)root);
        FXMLLoader secondaryLoader = new FXMLLoader();
        Stage stage2 = new Stage();
        Parent root2 = secondaryLoader.load(getClass().getResource("kernal.fxml").openStream());
        KernalController kernalController = secondaryLoader.getController();
        stage2.setTitle("Hide Filter");
        stage2.setScene(new Scene(root2));
        stage2.hide();
        controller.setStage(stage2);
        controller.setKernal(kernalController);
        kernalController.setStage(primaryStage);
        kernalController.setController(controller);
    }


    /**
     * Methos to laught the windown
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
