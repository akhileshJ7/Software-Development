/**
 * Course: CS-1011
 * Term: Fall 2018
 * Assignment: Lab 7
 * Author: Akhilesh Janaswamy
 * Date: 09/12/18
 */

package janaswamya;

/**
 * Class containing the part of program for lab 7 in fall 2018 CS-1011 course which performs
 * a battle simulator game.
 * @version 2018-10-22
 */
public class Die {
    private int numSides;
    private int currentValue;

    //no return type is a constructor
    public Die(int numSides) {

        this.numSides = numSides;
    }

    public int getNumSides() {

        return numSides;
    }

    public int getCurrentValue() {

        return currentValue;
    }

    public void roll() {

        currentValue = (int) (Math.random() * numSides) + 1;
    }
}
