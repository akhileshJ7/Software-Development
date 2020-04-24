package janaswamya;

/**
 * @author Akhilesh and course SE1011_021
 *         <p/>
 *         Performs and records the Activity of the parking Lot.
 */



public class ParkingLot {
    public static double CLOSED_THRESHOLD = 80.0;
    private String color;
    private int capacity;
    private int numberVehicle;
    private int lastExit;
    private int lastEntry;
    private int totalClosed;
    private int prevTime;
    /**
     * Set up a Parking Lot constructor.
     *
     * @param color    identification color
     * @param capacity Maximum number of vehicles for l0t
     **/
    public ParkingLot(String color,int capacity){
        this.color=color;
        this.totalClosed=0;
        this.prevTime=0;
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
        if (prevTime<=time) {
            boolean isFull = false;
            if (isClosed())
                isFull = true;
            numberVehicle = numberVehicle + 1;
            if (isClosed() && !isFull)
                lastEntry = time;
            prevTime=time;
        }

    }    /**
     * Record a vehicle entering a given lot at a given time.
     *

     * @param time      Exit time in minutes since all lots were opened.
     *                 This records the total number of Vehicles in the lot
     *                  And also the Time when it is Closed
     */

    public void markVehicleExit(int time) {
        if (prevTime<= time) {
            boolean isfull = false;
            if (isClosed()) {
                lastExit = time;
                isfull = true;
            }
            numberVehicle = numberVehicle - 1;
            if (!isClosed() && isfull)
                timesClosed();

            prevTime=time;
        }


    }

    /**
     * Records the time from markvehicleEntry and marVehicleExit
     * Sets up total time the lot is closed
     */
    private void timesClosed(){
        int closedMin=lastExit-lastEntry;
        totalClosed=totalClosed+closedMin;
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
        double n =(double)numberVehicle;
        double c=(double)capacity;
        double a=(n/c)*100.0;
        if(a>=CLOSED_THRESHOLD){
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Computes the time lot was reported as closed.
     *
     * @return number of minutes lot were closed
     */

    public int closedMinutes(){


        return (totalClosed);
    }

    /**
     * Computes the parking lot percentage
     *
     * @return color and "CLOSED" if the Closed signs is up
     *         else the Color and the occupied percentage.
     */


    public void displayStatus(){
        double numberV =(double)numberVehicle;
        double cap=(double)capacity;
        double percent=(numberV/cap)*100;
        if(percent>=CLOSED_THRESHOLD)
            System.out.println(color+"\tparking lot Status: CLOSED");
        else {
            System.out.format(color + "\tparking lt Status: %.1f%%\n",percent);


        }
    }
}