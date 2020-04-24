/**
 * Course: CS-1011
 * Term: Fall 2018
 * Assignment: Lab 9
 * Author: Akhilesh Janaswamy
 * Date: 11/7/18
 */
package janaswamya;

import java.text.DecimalFormat;

/**
 * This is class for each parking lot
 */
public class ParkingLot {
    /**
     *Final variable which is constant throughout the program
     */
    public static double CLOSED_THRESHOLD = 80.0;
    private String color;
    private int capacity;
    private int numberVehicle;
    private int lastExit;
    private int lastEntry;
    private int totalMinClosed;
    private int previousTime;



    /**
     * Set up a Parking Lot constructor.
     *
     * @param color    identification color
     * @param capacity Maximum number of vehicles for l0t
     **/
    public ParkingLot(String color,int capacity){
        this.color=color;

        this.totalMinClosed=0;
        this.previousTime=0;
        this.numberVehicle=0;
        this.capacity=capacity;
    }



    /**
     * Set up a Parking Lot constructor with one parameter.
     *
     * @param capacity Maximum number of vehicles for l0t
     **/
    public ParkingLot(int capacity){
        this.capacity=capacity;
        this.color="test";

    }



    /**
     *
     * @return display status of the pakingLot as a String
     */
    public String toString() {

        DecimalFormat df = new DecimalFormat("##.#");
        double percent = ((double) numberVehicle / capacity)*100;
        if (isClosed()) {
            return ("Status for " + color + " parking lot: " + numberVehicle + " vehicles (CLOSED)");
        }
        else {
            return ("Status for " + color + " parking lot: " + numberVehicle + " vehicles ("+(df.format(percent))+"%)");
        }
    }



    /**
     *
     * @return  color of thr parking lot
     */

    public String getColor(){
        return color;

    }



    /**
     * Record a vehicle entering a given lot at a given time.
     *

     * @param time      Entry time in minutes since all lots were opened.
     *                 This records the total number of Vehicles in the lot
     *                  And also the Time when it is opened
     */
    public void markVehicleEntry(int time ) {
        if (previousTime<=time) {
            boolean isFull = false;
            if (isClosed()){
                isFull = true;
            }
            numberVehicle = numberVehicle + 1;
            if (isClosed() && !isFull) {
                lastEntry = time;
            }
            previousTime=time;
        }

    }



    /**
     * Record a vehicle entering a given lot at a given time.
     *

     * @param time      Exit time in minutes since all lots were opened.
     *                 This records the total number of Vehicles in the lot
     *                  And also the Time when it is Closed
     */
    public void markVehicleExit(int time) {
        if (previousTime<= time) {
            boolean isfull = false;
            if (isClosed()) {
                lastExit = time;
                isfull = true;
            }
            numberVehicle = numberVehicle - 1;
            if (!isClosed() && isfull) {
                timesClosed();
            }
            previousTime=time;
        }
    }



    /**
     * Records the time from markvehicleEntry and marVehicleExit
     * Sets up total time the lot is closed
     */
    private void timesClosed(){
        int closedMin=lastExit-lastEntry;
        totalMinClosed=totalMinClosed+closedMin;
    }



    /**
     * Computes the nuber of vehicles in lot
     *
     * @return number of vehickes in lot
     */
    public int vehiclesInLot(){

        return numberVehicle;
    }



    /**
     * Checks the status of  lot and
     * returns true if Closed and false otherwise.
     *
     * @return whether all lots are closed in the district
     */
    public boolean isClosed(){
        return (double)numberVehicle / capacity * 100.0 >= CLOSED_THRESHOLD;
    }



    /**
     * Computes the time lot was reported as closed.
     *
     * @return number of minutes lot were closed
     */
    public int closedMinutes(){
        return (totalMinClosed);
    }
}
