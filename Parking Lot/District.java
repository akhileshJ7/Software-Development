/**
 * Course: CS-1011
 * Term: Fall 2018
 * Assignment: Lab 9
 * Author: Akhilesh Janaswamy
 * Date: 11/7/18
 */
package janaswamya;

/**
 * This is districts class which gives thre status of each lats
 */
public class District {
    /**
     * Final variable which is constant throughout the program
     */
    public static int MAX_LOTS=20;
    private ParkingLot[] lots = new ParkingLot[MAX_LOTS];
    private int numLots;
    private int entryClose=0;
    private int exitClose=0;
    private int totalClosed;




    /**
     * Set up a District constructor.
     *
     **/
    public District() {

        this.totalClosed=0;
        this.lots = new ParkingLot[MAX_LOTS];
    }



    /**
     * add a array of parking lot
     * @param color
     * @param capacity
     * @return newIndex of the array
     */
    public int add(String color, int capacity)
    {

        int newIndex   = numLots;
        lots[newIndex] = new ParkingLot(color,capacity);
        numLots++;
        return newIndex;
    }



    /**
     *
     * @param index
     * @return lots of given index
     */
    public ParkingLot getLot(int index)
    {
        return lots[index];
    }



    /**
     * @return   STring of the array which has the display statement
     */
    public String toString() {
        String display="District status:\n";
        for(int i=0;i<numLots;i++)
        {
            display=display+lots[i].toString()+"\n";
        }
        return display;
    }



    /**
     * Record a vehicle entering a given lot at a given time.
     *
     * @param lotNumber Number of lot, 1-3
     * @param time      Entry time in minutes since all lots were opened.
     *                  This calls ParkingLot.markVehicleEntry for the lot corresponding
     *                  to lotNumber (1 -> lot1, 2 -> lot2, 3 -> lot3).
     *                  If lotNumber is out of range, the behavior is unspecified.
     */
    public void markVehicleEntry(int lotNumber, int time) {
        boolean isFull = false;
        if (isClosed()){
            isFull = true;
        }
        getLot(lotNumber).markVehicleEntry(time);
        if (isClosed() && !isFull) {
            entryClose = time;
        }
    }



    /**
     * Record a vehicle exiting a given lot at a given time.
     *
     * @param lotNumber Number of lot, 1-3
     * @param time      Entry time in minutes since all lots were opened.
     *                  This calls ParkingLot.markVehicleExit for the lot corresponding
     *                  to lotNumber (1 -> lot1, 2 -> lot2, 3 -> lot3).
     *                  If lotNumber is out of range, the behavior is unspecified.
     */
    public void markVehicleExit(int lotNumber, int time) {
        boolean isfull = false;
        if(isClosed()) {
            exitClose = time;
            isfull=true;
        }
        getLot(lotNumber).markVehicleExit(time);
        if (!isClosed() && isfull) {
            lotsClosed();
        }

    }



    /**
     * Checks the status of all three lots in the district and
     * returns true if they are all closed and false otherwise.
     *
     * @return whether all lots are closed in the district
     */
    public boolean isClosed() {
        int i=0;
        for(i=0;i<numLots;i++)
        {
            if(!lots[i].isClosed())
            {
                return false;
            }
        }
        return true;
    }



    /**
     *
     * Records the time from markvehicleEntry and marVehicleExit
     * Sets up total time when all lots are closed
     */

    private void  lotsClosed(){
        int closedMin=exitClose-entryClose;
        totalClosed=totalClosed+closedMin;
    }



    /**
     * Computes the time all lots were reported as closed.
     *
     * @return number of minutes all three lots were closed
     */
    public int closedMinutes() {
        return (totalClosed);

    }



    /**
     *
     * @return total number od vechiles in all lots
     */
    public int vehiclesParkedInDistrict(){
        int total=0;
        for(int i=0;i<=numLots-1;i++){

            total=total+lots[i].vehiclesInLot();
        }
        return total;
    }
}
