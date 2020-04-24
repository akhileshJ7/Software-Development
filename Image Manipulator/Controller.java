package janaswamya;
/*
 * SE1021
 * Winter 2017-18
 * Lab10 Final Project
 * Created: 2/21/2018
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller class for scene1 fxml to impolimrnt the the action given in fxml
 */
public class Controller {

    @FXML
    private Pane pane;
    @FXML
    private Button filter;
    @FXML
    private ImageView imageView;
    private Image image;
    private Image image2;
    private Stage stage;
    private KernalController kernelController;
    private FileChooser fileChooser = new FileChooser();
    private File selectedFile;

    /**
     * Listener to button  open when it is clicked
     *
     * @param e onject to event
     */
    @FXML
    private void open(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.PNG",
                        "*.JPG", "*.jpg", "*.gif", "*.GIF", "*.msoe", "*.MSOE", "*.BMSOE", "*.bmsoe"));
        selectedFile = fileChooser.showOpenDialog(null);
        try {
            image = janaswamya.ImageIO.read(selectedFile);
            image2 = image;
        } catch (NullPointerException el) {
            new Alert(Alert.AlertType.ERROR, "No file has be selected.").showAndWait();
        } catch (FileNotFoundException el) {
            new Alert(Alert.AlertType.ERROR, "File is not found.").showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        imageView.setImage(image);
    }


    /**
     * Listener to button reload when it is clicked
     *
     * @param e object to ActionEvent
     **/
    @FXML
    public void reload(ActionEvent e) {
        try {
            imageView.setImage(image);
        } catch (NullPointerException el) {
            new Alert(Alert.AlertType.ERROR, "There is no image ").showAndWait();
        }
    }


    /**
     * Listener to button negative when it is clicked
     *
     * @param e object to ActionEvent
     */
    @FXML
    public void negative(ActionEvent e) {
        try {
            Transformable transformable = janaswamya.ImageIO.negative;
            PixelReader pr = image2.getPixelReader();
            WritableImage wi = new WritableImage((int) image2.getWidth(), (int) image2.getHeight());
            PixelWriter pw = wi.getPixelWriter();
            for (int i = 0; i < image2.getHeight(); i++) {
                for (int j = 0; j < image2.getWidth(); j++) {
                    Color c = pr.getColor(j, i);
                    Color updateColor = transformable.transform(j, i, c);
                    pw.setColor(j, i, updateColor);
                }
            }
            image = wi;
            imageView.setImage(wi);
        } catch (NullPointerException el) {
            new Alert(Alert.AlertType.ERROR, "There is no image ").showAndWait();
        }

    }


    /**
     * Listener to button grayScale when it is clicked
     *
     * @param e object to ActionEvent
     */
    @FXML
    public void grayScale(ActionEvent e) {
        try {
            Transformable transformable = janaswamya.ImageIO.gray;
            PixelReader pr = image.getPixelReader();
            WritableImage wi = new WritableImage((int) image.getWidth(), (int) image.getHeight());
            PixelWriter pw = wi.getPixelWriter();
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    Color c = pr.getColor(j, i);
                    Color updateColor = transformable.transform(j, i, c);
                    pw.setColor(j, i, updateColor);
                }
            }
            image = wi;
            imageView.setImage(wi);
        } catch (NullPointerException el) {
            new Alert(Alert.AlertType.ERROR, "There is no image ").showAndWait();
        }
    }

    /**
     * Listener to button red when it is clicked
     *
     * @param e object to ActionEvent
     */
    @FXML
    public void red(ActionEvent e) {
        try {
            Transformable transformable = janaswamya.ImageIO.red;
            PixelReader pr = image.getPixelReader();
            WritableImage wi = new WritableImage((int) image.getWidth(), (int) image.getHeight());
            PixelWriter pw = wi.getPixelWriter();
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    Color c = pr.getColor(j, i);
                    Color updateColor = transformable.transform(j, i, c);
                    pw.setColor(j, i, updateColor);
                }
            }
            image = wi;
            imageView.setImage(wi);
        } catch (NullPointerException el) {
            new Alert(Alert.AlertType.ERROR, "There is no image ").showAndWait();
        }
    }

    /**
     * Listener to button red-Gray when it is clicked
     *
     * @param e object to ActionEvent
     */
    @FXML
    public void redGray(ActionEvent e) {
        try {
            Transformable transformable = janaswamya.ImageIO.redGray;
            PixelReader pr = image.getPixelReader();
            WritableImage wi = new WritableImage((int) image.getWidth(), (int) image.getHeight());
            PixelWriter pw = wi.getPixelWriter();
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    Color c = pr.getColor(j, i);
                    Color updateColor = transformable.transform(j, i, c);
                    pw.setColor(j, i, updateColor);
                }
            }
            image = wi;
            this.imageView.setImage(wi);
        } catch (NullPointerException el) {
            new Alert(Alert.AlertType.ERROR, "There is no image ").showAndWait();
        }
    }


    /**
     * Listener to button save when it is clicked
     *
     * @param e object to ActionEvent
     */
    @FXML
    public void save(ActionEvent e) {
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Save Image");
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                janaswamya.ImageIO.write(image, file);
            }
        } catch (FileNotFoundException el) {
            new Alert(Alert.AlertType.ERROR, "File is not found.").showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Listerner to button show filter and hide filter when it os clicked
     *
     * @param e onject to ActionEvent
     */
    public void showFilter(ActionEvent e) {
        kernelController.setImage(image);
        if (stage.isShowing()) {
            filter.setOnMouseClicked((event) -> {
                filter.setText("Show Filter");
            });
            stage.hide();
        } else {
            Stage myStage = (Stage) pane.getScene().getWindow();
            stage.setX(myStage.getX());
            stage.setY(myStage.getY() + myStage.getHeight());
            stage.setWidth(myStage.getWidth());
            filter.setOnMouseClicked((event) -> {
                filter.setText("Hide Filter");
            });
            stage.show();
        }
    }


    /**
     * method the set the image
     *
     * @param image onject to the image class
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * method the set the stage
     *
     * @param stage object to the Stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    /**
     * method to set the pane
     *
     * @param imagePane object to the imagePane
     */
    public void setPane(Pane imagePane) {
        this.pane = imagePane;
    }


    /**
     * method the set the kernalController object
     *
     * @param kernalController object to KernalCOntroller class
     */
    public void setKernal(KernalController kernalController) {
        this.kernelController = kernalController;
    }


    /**
     * Method which displays thehexcode of the pixel
     *
     * @param mouseEvent object to MouseEvent
     */
    public void mouseClicked(MouseEvent mouseEvent) {
        try {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            PixelReader pr = image.getPixelReader();
            Color c = pr.getColor((int) x, (int) y);
            System.out.println("Color code of the Pixel is" + c);
        } catch (IndexOutOfBoundsException e) {
            new Alert(Alert.AlertType.ERROR, "File is not found.").showAndWait();
        }
    }
}



