/**
 * Course: CS-1011
 * Term: Fall 2018
 * Assignment: Lab 8
 * Author: Akhilesh Janaswamy
 * Date: 10/29/18
 */
package janaswamya;


/**
 * This is districts class which gives thre status of each lats
 */
public class District {
    private ParkingLot lot1;
    private ParkingLot lot2;
    private ParkingLot lot3;
    private int entryClose;
    private int time;
    private int closedMin=0;



    /**
     * Set up a district with three parking lots.
     *
     * @param color1    Lot 1 identification color
     * @param capacity1 Maximum number of vehicles for lot 1
     * @param color2    Lot 2 identification color
     * @param capacity2 Maximum number of vehicles for lot 2
     * @param color3    Lot 3 identification color
     * @param capacity3 Maximum number of vehicles for lot 3
     */
    public District(String color1, int capacity1,
                    String color2, int capacity2,
                    String color3, int capacity3) {
        lot1 = new ParkingLot(color1, capacity1);
        lot2 = new ParkingLot(color2, capacity2);
        lot3 = new ParkingLot(color3, capacity3);
    }



    /**
     * Display status information for all three lots.
     * See ParkingLot.displayStatus for the format for each.
     */
    public void displayStatus() {
        lot1.displayStatus();
        lot2.displayStatus();
        lot3.displayStatus();
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
        if (time >= this.time) {
            this.time = time;
            if (!isClosed()) {

                if (lotNumber == 1) {
                    lot1.markVehicleEntry(time);
                }
                if (lotNumber == 2) {
                    lot2.markVehicleEntry(time);

                }
                if (lotNumber == 3) {
                    lot3.markVehicleEntry(time);

                }
                if (isClosed()) {
                    this.entryClose = time;
                }

            } else {
                if (lotNumber == 1) {
                    lot1.markVehicleEntry(time);
                }
                if (lotNumber == 2) {
                    lot2.markVehicleEntry(time);
                }
                if (lotNumber == 3) {
                    lot3.markVehicleEntry(time);
                }
            }
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
        if (time >= this.time) {
            this.time = time;
            if (isClosed()) {
                if (lotNumber == 1)
                    lot1.markVehicleExit(time);
                if (lotNumber == 2)
                    lot2.markVehicleExit(time);
                if (lotNumber == 3)
                    lot3.markVehicleExit(time);

                if (!isClosed()) {
                    closedMin = closedMin + (time - this.entryClose);
                }
            }
            else {
                if (lotNumber == 1) {
                    lot1.markVehicleExit(time);
                }
                if (lotNumber == 2) {
                    lot2.markVehicleExit(time);
                }
                if (lotNumber == 3) {
                    lot3.markVehicleExit(time);
                }
            }
        }
    }



    /**
     * Checks the status of all three lots in the district and
     * returns true if they are all closed and false otherwise.
     *
     * @return whether all lots are closed in the district
     */
    public boolean isClosed() {
        if (lot1.isClosed() && lot2.isClosed() && lot3.isClosed())
            return true;
        else
            return false;
    }

    /**
     * Computes the time all lots were reported as closed.
     *
     * @return number of minutes all three lots were closed
     */
    public int closedMinutes() {
        return closedMin;

    }

}

