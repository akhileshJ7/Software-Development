package janaswamya;
/*
 * SE1021
 * Winter 2017-18
 * Lab9 Final Project
 * Created: 2/14/2018
 */

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Controller for the second window to handle the methods
 */
public class KernalController {
    private Image image;
    private Stage stage;
    private Controller controller;
    @FXML
    private TextField upperLeft ;
    @FXML
    private TextField upperCenter;
    @FXML
    private TextField upperRight;
    @FXML
    private TextField left;
    @FXML
    private TextField center;
    @FXML
    private TextField right;
    @FXML
    private TextField lowerLeft;
    @FXML
    private TextField lowerCenter;
    @FXML
    private TextField lowerRight;
    @FXML
    private TextField divisor;
    /**
     * method used to set stage
     * @param stage object to Stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    /**
     * methos used to set Controller object
     * @param controller object to Controller class
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }


    /**
     * This method is a action handler for apply button
     * @param event object to ActionEvent
     */
    @FXML
    public void apply(ActionEvent event) {
        try {
            float sum = getsum();
            if (sum > 0) {
                Float div = Float.parseFloat(divisor.getText());
                Float up = Float.parseFloat(upperCenter.getText());
                Float c = Float.parseFloat(center.getText());
                Float l = Float.parseFloat(left.getText());
                Float r = Float.parseFloat(right.getText());
                Float lc = Float.parseFloat(lowerCenter.getText());
                float[] kernel = {0F, up / div, 0F,
                        l / div, c / div, r / div,
                        0F, lc / div, 0F};
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
                BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, kernel));
                BufferedImage result = op.filter(bufferedImage, null);
                controller.setImage(SwingFXUtils.toFXImage(result, null));
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Number format").showAndWait();
        } catch (NullPointerException e){
            new Alert(Alert.AlertType.ERROR, "Error with the division").showAndWait();
        }
    }


    /**
     * method the set the stage
     *
     * @param image object to Image class
     */
    public void setImage(Image image){
        this.image = image;
    }

    /**
     *
     * this method is to get sum of all fields
     *
     * @return sum float value
     */
    public float getsum (){
        float sums = Float.parseFloat(upperLeft.getText())+Float.parseFloat(upperCenter.getText())
                +Float.parseFloat(upperRight.getText())+Float.parseFloat(left.getText())
                +Float.parseFloat(center.getText())+Float.parseFloat(right.getText())
                +Float.parseFloat(lowerLeft.getText())+Float.parseFloat(lowerCenter.getText())
                +Float.parseFloat(lowerRight.getText());
        return sums;
    }



    /**
     * Handler method to blur button
     * @param event object to ActionEvent
     */
    @FXML
    public void blur(ActionEvent event) {
        upperLeft.setText("0");
        upperCenter.setText("1");
        upperRight.setText("0");
        left.setText("1");
        center.setText("5");
        right.setText("1");
        lowerLeft.setText("0");
        lowerCenter.setText("1");
        lowerRight.setText("0");
    }


    /**
     * handler method the sharpen button
     * @param event object to ActionEvent
     */
    @FXML
    public void sharpen(ActionEvent event) {
        upperLeft.setText("0");
        upperCenter.setText("-1");
        upperRight.setText("0");
        left.setText("-1");
        center.setText("5");
        right.setText("-1");
        lowerLeft.setText("0");
        lowerCenter.setText("-1");
        lowerRight.setText("0");
       }


    /**
     * habdler method to edge buttton
     * @param event object to ActionEvent
     */
    @FXML
    public void edge(ActionEvent event) {
        upperLeft.setText("0");
        upperCenter.setText("-1");
        upperRight.setText("0");
        left.setText("-1");
        center.setText("4");
        right.setText("-1");
        lowerLeft.setText("0");
        lowerCenter.setText("-1");
        lowerRight.setText("0");
       }
}
