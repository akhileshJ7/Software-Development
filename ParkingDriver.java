/**
 * Course: CS-1011
 * Term: Fall 2018
 * Assignment: Lab 8
 * Author: Akhilesh Janaswamy
 * Date: 10/29/18
 */
package janaswamya;



/**
 * This is main class which calls methods of other two classes
 */
public class ParkingDriver {
    /**
     * Main test method: calls other tests.
     *
     * @param args Ignored command-line arguments
     */
    public static void main(String[] args) {
        testSmallLot();
        testTinyDistrict();
        testComingAndGoing();
        testOverfillingLot();
        testParkingLot();
        testDistrict();
        testHeavyUsage();
        System.out.println("All tests finished.");
    }

    ///////////////////////////////////////////////////////////
    // simple tests - basic functionality

    public static void testSmallLot() {
        ParkingLot driveway = new ParkingLot("blacktop", 1);
        if (!driveway.getColor().equals("blacktop"))
            System.out.println(".color() not working");
        if (Math.abs(ParkingLot.CLOSED_THRESHOLD - 80) > 0.01)
            System.out.println(">>> Incorrect threshold for closed lot.");
        if (driveway.isClosed())
            System.out.println(">>> Empty driveway is closed.");
        driveway.markVehicleEntry(5);
        if (!driveway.isClosed())
            System.out.println(">>> Driveway with something in it is not closed.");
        driveway.displayStatus();
        driveway.markVehicleExit(400);
        if (driveway.vehiclesInLot() != 0)
            System.out.println(">>> Empty driveway should have no vehicles in it.");
        if (driveway.closedMinutes() != 395)
            System.out.println(">>> Wrong number of minutes while sleeping overnight.");
    }

    /**
     * Create district with small parking lots, fill them, then
     * ensure one is not closed.
     */
    public static void testTinyDistrict() {
        District ourTown = new District("red", 1, "green", 1, "blue", 2);
        System.out.println("Tiny district:");
        ourTown.markVehicleEntry(2, 5);
        ourTown.markVehicleEntry(1, 7);
        ourTown.markVehicleEntry(3, 10);
        ourTown.markVehicleEntry(3, 12);
        if (!ourTown.isClosed())
            System.out.println(">>> Error in tiny district at 12: should be closed.");
        ourTown.markVehicleExit(2, 15);
        if (ourTown.isClosed())
            System.out.println(">>> Error in tiny district at 15: should be open.");
        ourTown.displayStatus();
        ourTown.markVehicleExit(1, 17);
        ourTown.markVehicleEntry(2, 18);
        if (ourTown.isClosed())
            System.out.println(">>> Error in tiny district at 18: should be open.");
        System.out.println("Lots were closed for " + ourTown.closedMinutes()
                + " min. in tiny district.");
        System.out.println();
    }

    ///////////////////////////////////////////////////////////
    // parking lot tests

    /**
     * Test what happens when lot fills, empties, then refills.
     */
    public static void testComingAndGoing() {
        ParkingLot busy = new ParkingLot(5);
        busy.markVehicleEntry(5);
        busy.markVehicleEntry(10);
        busy.markVehicleEntry(12);
        if ( busy.isClosed() )
            System.out.println(">>> Error: Busy lot should not be closed at time 12");
        busy.markVehicleEntry(15);
        if ( !busy.isClosed() )
            System.out.println(">>> Error: Busy lot should be closed at time 15");
        if ( busy.vehiclesInLot() != 4 )
            System.out.println(">>> Error: Busy lot should have four vehicles at time 15");
        busy.markVehicleEntry(20);
        if ( busy.vehiclesInLot() != 5 )
            System.out.println(">>> Error: Busy lot should have five vehicles at time 20");
        busy.markVehicleExit(23);
        if ( busy.vehiclesInLot() != 4 )
            System.out.println(">>> Error: Busy lot should have four vehicles at time 23");
        busy.markVehicleExit(25);
        busy.markVehicleExit(25);
        if ( busy.closedMinutes() != 10 )
            System.out.println(">>> Error: busy parking lot should have been closed for 10 minutes at time 30");
        busy.markVehicleEntry(33);
        busy.markVehicleEntry(35);
        if ( !busy.isClosed() )
            System.out.println(">>> Error: Busy lot should be closed at time 35");
        if ( busy.vehiclesInLot() != 4 )
            System.out.println(">>> Error: Busy lot should have four vehicles at time 35");
        busy.markVehicleEntry(40);
        if ( busy.vehiclesInLot() != 5 )
            System.out.println(">>> Error: Busy lot should have five vehicles at time 40");
        busy.markVehicleExit(45);
        if ( busy.vehiclesInLot() != 4 )
            System.out.println(">>> Error: Busy lot should have four vehicles at time 45");
        busy.markVehicleExit(50);
        busy.markVehicleExit(54);
        busy.markVehicleExit(60);
        busy.markVehicleExit(60);
        if ( busy.closedMinutes() != 25 )
            System.out.println(">>> Error: busy parking lot should have been closed for 25 minutes");
        if ( busy.vehiclesInLot() != 0 )
            System.out.println(">>> Error: Busy lot should be empty at end");
    }

    /**
     * Test some boundary conditions on lot closure including what happens if a vehicle
     * enters when the lot is marked as being closed.
     */
    public static void testOverfillingLot() {
        ParkingLot driveway = new ParkingLot("blacktop", 9);
        for (int i = 0; i < 7; ++i) {
            driveway.markVehicleEntry(2);
        }
        driveway.markVehicleEntry(3);
        if (!driveway.isClosed()) {
            System.out.println(">>> Error: expected 9-vehicle driveway to be closed at time 3");
        }
        driveway.markVehicleExit(12);
        if (driveway.isClosed()) {
            System.out.println(">>> Error: expected 9-vehicle driveway to be open at time 12");
        }
        if (driveway.closedMinutes() != 9) {
            System.out.println(">>> Error: 9-vehicle driveway test should have 9 minutes closed but you have "
                    + driveway.closedMinutes());
        }

        driveway = new ParkingLot(10);
        for (int i = 0; i < 7; ++i) {
            driveway.markVehicleEntry(2);
        }
        driveway.markVehicleEntry(3); // 8: now closed
        if (!driveway.isClosed()) {
            System.out.println(">>> Error: expected 10-vehicle driveway to be closed at time 3");
        }
        driveway.markVehicleExit(12); // 7: no longer closed
        if (driveway.isClosed()) {
            System.out.println(">>> Error: expected 10-vehicle driveway to be open at time 12");
        }
        driveway.markVehicleEntry(13); // 8: now closed
        driveway.markVehicleExit(15); // 7: no longer closed
        if (driveway.closedMinutes() != 11) {
            System.out.println(">>> Error: 10-vehicle driveway test should have 11 closed minutes but you have "
                    + driveway.closedMinutes());
        }

        ParkingLot pad = new ParkingLot("white", 9);
        for (int i = 0; i < 7; ++i) {
            pad.markVehicleEntry(2);
        }
        pad.markVehicleEntry(3); // 8 closed
        if (!pad.isClosed()) {
            System.out.println(">>> Error: expected pad to be closed at time 3");
        }
        pad.markVehicleEntry(4); // 9
        if (pad.vehiclesInLot() != 9) {
            System.out.println(">>> Error: expected 9 vehicles on pad at time 4");
        }
        pad.markVehicleExit(11); // 8
        pad.markVehicleExit(12); // 7: no longer full
        if (pad.isClosed()) {
            System.out.println(">>> Error: expected pad to be open at time 12");
        }
        if (pad.closedMinutes() != 9) {
            System.out.println(">>> Error: test with over-full lot should have 9 minutes closed but you have "
                    + driveway.closedMinutes());
        }
    }

    /**
     * Uses other static methods to test the ParkingLot class.
     * Creates a lot with four spaces that is used for most of
     * the tests.
     */
    public static void testParkingLot() {
        ParkingLot lot = new ParkingLot(4);

        if (!lot.getColor().equals("test"))
            System.out.println(">>> Lot name should be 'test'.");

        testFillingLot(lot);
        testRefillingLot(lot);
        testEmptyingLot(lot);

        test0TimeEntryExit();

        testFillingTo80Percent();
    }

    /**
     * Tests adding three vehicles to a lot with four spaces. Vehicles enter
     * at times 10, 12, and 13, the status is checked. Another vehicle
     * enters at time 20 and the status is checked again. One vehicle
     * then leaves followed by a final status check.
     *
     * @param lot A parking lot with four spaces
     */
    private static void testFillingLot(ParkingLot lot) {
        System.out.println("Testing ParkingLot");
        lot.markVehicleEntry(10);
        lot.markVehicleEntry(12);
        lot.markVehicleEntry(13);
        lot.displayStatus();
        if (lot.isClosed())
            System.out.println(">>> Error: lot closed with just 3 vehicles.");
        if (lot.closedMinutes() != 0)
            System.out.println(">>> Error: closed minutes should be 0.");
        lot.markVehicleEntry(20);
        lot.displayStatus();
        if (!lot.isClosed())
            System.out.println(">>> Error: lot not closed with 4 vehicles.");
        lot.markVehicleExit(31);
        if (lot.isClosed())
            System.out.println(">>> Error: lot closed after first exit.");
        if (lot.closedMinutes() != 11)
            System.out.println(">>> Error: expected 11 minutes of closed time, got "
                    + lot.closedMinutes() + ".");
    }

    /**
     * This continues the test started in testFillingLot and assumes
     * initially there are three vehicles in the lot. One leaves and
     * two more enter. A vehicle leaves at 57 minutes, and the status
     * is rechecked. Finally, the parking lot is returned to closed at 71
     * minutes.
     *
     * @param lot A parking lot with four spaces; must be same lot passed to testFillingLot()
     */
    private static void testRefillingLot(ParkingLot lot) {
        if (lot.vehiclesInLot() != 3)
            System.out.println(">>> Error: expected 3 vehicles on lot.");
        lot.markVehicleExit(45);
        // lot has 2 vehicles
        lot.displayStatus();
        lot.markVehicleEntry(50);
        lot.markVehicleEntry(52);
        lot.markVehicleExit(57);    // add 5 minutes
        // returned to 3 vehicles
        if (lot.vehiclesInLot() != 3)
            System.out.println(">>> Error: expected 3 vehicles on lot at 57.");
        lot.markVehicleEntry(71);
        if (!lot.isClosed())
            System.out.println(">>> Error: expected closed lot at 71 minutes.");
    }

    /**
     * This continues the test started in testRefillingLot and assumes
     * all four spaces are filled. A vehicle leaves at 79 minutes,
     * followed by vehicles at 91, 92, and 94. The status is checked to
     * make sure no vehicles are on the lot.
     *
     * @param lot A parking lot with four spaces; must be same lot passed to testReFillingLot()
     */
    private static void testEmptyingLot(ParkingLot lot) {
        if (lot.vehiclesInLot() != 4)
            System.out.println(">>> Error: expected 4 vehicles on lot.");
        lot.markVehicleExit(79);    // add 8 minutes
        if (lot.closedMinutes() != 24)
            System.out.println(">>> Error: expected 24 minutes of closed time, got "
                    + lot.closedMinutes() + ".");
        if (lot.isClosed())
            System.out.println(">>> Error: expected non-closed lot at 79 minutes.");
        lot.markVehicleExit(91);
        lot.markVehicleExit(92);
        lot.markVehicleExit(94);
        lot.displayStatus();
        if (lot.vehiclesInLot() != 0)
            System.out.println(">>> Error: lot should be empty at 94 minutes.");
        if (lot.closedMinutes() != 24)
            System.out.println(">>> Error: expected 24 minutes of closed time at end");
        System.out.println();
    }

    /**
     * Test entry/exit where the time is in the past, and that entering
     * and exiting within the same minute works.
     */
    public static void test0TimeEntryExit() {
        ParkingLot lot = new ParkingLot(6);

        lot.markVehicleEntry(50);
        if (lot.vehiclesInLot() != 1)
            System.out.println(">>> Error: expecting 1 vehicle at time 50.");
        // try to enter, exit in past
        lot.markVehicleEntry(49);
        if (lot.vehiclesInLot() != 1)
            System.out.println(">>> Error: expecting 1 vehicle at time -1.");
        lot.markVehicleExit(49);
        if (lot.vehiclesInLot() != 1)
            System.out.println(">>> Error: expecting 1 vehicle at time -1b.");
        // enter, exit all at once
        lot.markVehicleEntry(51);
        lot.markVehicleEntry(51);
        lot.markVehicleEntry(51);
        if (lot.vehiclesInLot() != 4)
            System.out.println(">>> Error: expecting 4 vehicles at time 51.");
        if (lot.isClosed())
            System.out.println(">>> Error: lot should not be closed at time 51.");
        lot.markVehicleEntry(51);
        if (lot.vehiclesInLot() != 5)
            System.out.println(">>> Error: expecting 5 vehicles at time 51b.");
        if (!lot.isClosed())
            System.out.println(">>> Error: lot should be closed at time 51b.");
        lot.markVehicleExit(51);
        if (lot.vehiclesInLot() != 4)
            System.out.println(">>> Error: expecting 4 vehicles at time 51c.");
        if (lot.closedMinutes() != 0)
            System.out.println(">>> Error: expecting 0 minutes closed at time 51c.");
    }

    /**
     * Test putting 16 vehicles into a lot with 19 spaces. This is 84%,
     * just enough to be above the threshold. For the first check,
     * 15 vehicles are added and the test confirms the lot is not closed.
     * One more is added and the fact it is flled is confirmed. Then
     * all vehicles are removed and the system checks that the lot is
     * empty and that there was just one minute where it was closed.
     */
    public static void testFillingTo80Percent() {
        ParkingLot lot = new ParkingLot(19);
        for (int i = 0; i < 15; i++)
            lot.markVehicleEntry(i);
        if (lot.vehiclesInLot() != 15)
            System.out.println(">>> Error: should have 15 vehicles on lot");
        if (lot.closedMinutes() != 0)
            System.out.println(">>> Error: expected 0 minutes of closed time at 15");
        if (lot.isClosed())
            System.out.println(">>> Error: expected non-closed lot at 15 minutes.");
        lot.markVehicleEntry(16);
        if (!lot.isClosed())
            System.out.println(">>> Error: expected closed lot at 16 minutes.");
        lot.markVehicleExit(17);
        if (lot.isClosed())
            System.out.println(">>> Error: expected non-closed lot at 17 minutes.");
        for (int i = 0; i < 15; i++)
            lot.markVehicleExit(i + 17);
        if (lot.vehiclesInLot() != 0)
            System.out.println(">>> Error: no vehicles should be on lot.");
        if (lot.isClosed())
            System.out.println(">>> Error: expected non-closed lot after all left.");
        if (lot.closedMinutes() != 1)
            System.out.println(">>> Error: lot was closed 1 minute.");
    }

    ///////////////////////////////////////////////////////////
    // district tests

    /**
     * Test District class
     */
    public static void testDistrict() {
        District airport = new District("brown", 10,
                "green", 15,
                "black", 12);
        for (int i = 0; i < 7; i++) {
            airport.markVehicleEntry(1, i);
            airport.markVehicleEntry(2, i);
            airport.markVehicleEntry(2, i);
            airport.markVehicleEntry(3, i);
            if (airport.isClosed())
                System.out.println(">>> Error: airport closed at time 7.");
        }
        System.out.println("Airport at time 7:");
        airport.displayStatus();
        System.out.println();

        airport.markVehicleEntry(1, 8);
        if (airport.isClosed())
            System.out.println(">>> Error: airport closed at time 8.");
        System.out.println("Airport at time 8:");
        airport.displayStatus();
        System.out.println();

        airport.markVehicleEntry(3, 9);
        airport.markVehicleEntry(3, 10);
        airport.markVehicleEntry(3, 10);
        if (!airport.isClosed())
            System.out.println(">>> Error: airport not closed at time 10.");
        System.out.println("Airport at time 10:");
        airport.displayStatus();
        System.out.println();
    }

    static void testHeavyUsage() {
        System.out.println("Testing heavier usage.");
        District town = new District("pink", 25, "blue", 30, "gray", 10);

        String usage = "111243432523124321322156421123666324121345534534221" +
                // fill up lots
                "1111111122111122221111211122222233222222" +
                // a period of constant in/out traffic
                "4141414141525252525252636363636363636363636363636363636414141" +
                // a number of vehicles leave
                "55556556655454645546454446" +
                // random behavior at the end of the period
                "113232312434524621525241255146254245654241422211122661212321232";
        int min = 60;             // no activity until an hour into the day
        for (int i = 0; i < usage.length(); i++) {
            char cmd = usage.charAt(i);
            if (cmd == 's')
                town.displayStatus();
            else if (cmd <= '3')
                town.markVehicleEntry((int) (cmd - '0'), min);
            else
                town.markVehicleExit((int) (cmd - '3'), min);
            min++;
        }
        System.out.println("At end of day, all lots closed " + town.closedMinutes()
                + " min.");
        town.displayStatus();
        System.out.println();
    }
}
