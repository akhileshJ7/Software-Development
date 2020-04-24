package janaswamya;
/*
 * SE1021
 * Winter 2017-18
 * Lab10 Final Project
 * Created: 2/21/2018
 */

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Helper class to read and write and transform image.
 */
public class ImageIO {

    /**
     * method to read the image file with extension and return image
     * @param file oblect to file class
     * @return image after reading
     * @throws IOException
     */
    public static Image read(File file) throws IOException{
        String path = file.toString();
        Image image= null;
        if (path.endsWith(".msoe") || path.endsWith(".MSOE")) {
            image = readMSOE(file);
        } else if (path.endsWith(".bmsoe")) {
            image = readBMSOE(file);
        } else {
            BufferedImage bufferedImage = javax.imageio.ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
        }
        return image;
    }


    /**
     * method to write the image file with extension and return i
     * @param image object to image class
     * @param file object to file class
     * @throws IOException
     */
    public static void write(Image image,File file) throws IOException {
        String name = file.getName();
        String extension = name.substring(name.indexOf(".") + 1);
        extension = extension.toLowerCase();
        if (extension.equals("msoe")) {
            writeMSOE(image, file);
        } else if (extension.equals("bmsoe")) {
            writeBMSOE(image, file);
        } else {
            try {
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
                javax.imageio.ImageIO.write(bufferedImage, extension, file);
            } catch (IOException e) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


    /**
     * method to write the image in msoe file format
     * @param image object to image class
     * @param file object to file class
     * @throws FileNotFoundException
     */
    private static void writeMSOE(Image image,File file) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        PrintWriter printWriter = new PrintWriter(fileOutputStream);
        PixelReader pixelReader = image.getPixelReader();
        printWriter.write("MSOE\n");
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        String dimensions = width+" "+height+"\n";
        printWriter.write(dimensions);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixelColor= pixelReader.getColor(j, i).hashCode();
                String hexColor = Integer.toHexString(pixelColor);
                printWriter.write("#" + hexColor);
            }
            printWriter.write("\n");
        }printWriter.close();

    }


    /**
     * method to write image in BMSOE file format
     * @param image object to image class
     * @param file object to file class
     * @throws IOException
     */
    private static void writeBMSOE(Image image,File file) throws IOException {
        DataOutputStream dout = new DataOutputStream(new FileOutputStream(file));
        PixelReader pixelReader = image.getPixelReader();
        dout.writeByte('B');
        dout.writeByte('M');
        dout.writeByte('S');
        dout.writeByte('O');
        dout.writeByte('E');
        dout.writeInt((int) image.getWidth());
        dout.writeInt((int) image.getHeight());
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color colors = pixelReader.getColor(j, i);
                dout.writeByte((byte) colors.getRed()*255);
                dout.writeByte((byte) colors.getGreen()*255);
                dout.writeByte((byte) colors.getBlue()*255);
                dout.writeByte((byte) colors.getOpacity()*255);
            }
        }
        for(int c=0;c<junk((int) image.getWidth());c++){
            dout.writeByte(0);
        }
    }


    /**
     * method to decode the text MSOE file and return the image
     * @param file object to file class
     * @throws FileNotFoundException
     */
    private static Image readMSOE(File file) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(file.toString());
        Scanner scr = new Scanner(inputStream);
        String name = scr.next();
        int width = scr.nextInt();
        int height = scr.nextInt();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String hexcode = scr.next();
                Color color = Color.web(hexcode);
                pixelWriter.setColor(j, i, color);
            }
        }
        scr.close();
        return writableImage;
    }

    /**
     * method to read the binary bmsoe file and return image
     * @param file object to file class
     * @return image after reading binary file
     * @throws IOException
     */
    private static Image readBMSOE(File file)throws IOException {
        DataInputStream din = new DataInputStream(new FileInputStream(file));
        byte b = din.readByte();
        byte m = din.readByte();
        byte s = din.readByte();
        byte o = din.readByte();
        byte e = din.readByte();
        int width = din.readInt();
        int height = din.readInt();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double red = (0x000000FF& din.readByte())/255.0;  //convert binary to double and make in a range of 0-1
                double green = (0x000000FF& din.readByte())/255.0;//convert binary to double and make in a range of 0-1
                double blue = (0x000000FF& din.readByte())/255.0; //convert binary to double and make in a range of 0-1
                double opacity=(0x000000FF& din.readByte())/255.0;//convert binary to double and make in a range of 0-1
                Color c = new Color(red,green,blue,opacity);
                pixelWriter.setColor(j,i,c);
            }
            if(junk(width)!=0){
                din.skip(junk(width));
            }
        }
        return writableImage;
    }


    /**
     * Lamba Expression for setting the color to negative using Tranformable interface
     */
    public static Transformable negative = (x,y,color) ->{
        Color nColor = new Color((1-color.getRed()),(1-color.getGreen()),(1-color.getBlue() ),color.getOpacity());
        return nColor;
    };


    /**
     * Lamba Expression for setting the color to gray using Tranformable interface
     */
    public  static Transformable gray = (x,y,color) -> {
        double redValue = color.getRed()*0.2126;
        double green = color.getGreen()*0.7152;
        double blue = color.getBlue()*0.0722;
        double opacity= color.getOpacity();
        double singleValue = redValue + green + blue;
        Color rColor = new Color(singleValue, singleValue,singleValue,opacity);
        return rColor;
    };


    /**
     * Lamba Expression for setting the color to red using Tranformable interface
     */
    public  static Transformable red = (x,y,color) -> {
        double red =color.getRed();
        double green =0;
        double blue =0;
        double opacity= color.getOpacity();
        Color gColor = new Color(red,green,blue,opacity);
        return gColor;
    };


    /**
     * Lamba Expression for setting the color to redgray using Tranformable interface
     */
    public static Transformable redGray = (x,y,color) -> {
        Color rgColor;
        if(y%2==0){
            rgColor = ImageIO.red.transform(x, y, color);
        } else{
            rgColor = ImageIO.gray.transform(x, y, color);
        }
        return rgColor;
    };

    /**
     * Method to satisfy the requirement of divible by 16
     * @param width
     * @return
     */
    public static int junk(int width){
        if(width % 16 == 0){
            return 0;
        }else{
            return (16-((width*4)%16))/4;
        }
    }
}
