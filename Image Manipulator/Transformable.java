package janaswamya;
/*
 * SE1021
 * Winter 2017-18
 * Lab9 Final Project
 * Created: 2/14/2018
 */

import javafx.scene.paint.Color;


/**
 * Inferace tranformable to be used as a fuction
 */
public interface Transformable {
    /**
     * tranform method to set color
     * @param x
     * @param y
     * @param color
     * @return
     */
    Color transform(int x,int y,Color color);
}
