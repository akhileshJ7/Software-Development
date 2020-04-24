
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
public class Mugwump {
    private int hitPoints;
    private int maxHitPoints;
    private Die d100 = new Die(100);
    private Die d20 = new Die(20);
    private Die d10 = new Die(10);
    private Die d6 = new Die(6);

    public Mugwump() {
        hitPoints = rollHitPoints();

    }

    /**
     * This method handles the attack logic
     *
     * @return
     */

    private int rollHitPoints() {
        maxHitPoints = 0;
        for (int i = 0; i < 10; i++) {
            d10.roll();
            maxHitPoints =maxHitPoints+ d10.getCurrentValue();
        }
        return maxHitPoints;
    }

    public void takeDamage(int hp) {

        hitPoints =hitPoints- hp;
    }

    public int attack() {
        int attack = ai();

        int damage = 0;
        switch (attack) {
            case 1:
                d20.roll();


                if (d20.getCurrentValue() >= 12 ) {
                    for (int i = 0; i < 2; i++) {
                        d6.roll();
                        damage =damage+ d6.getCurrentValue();
                    }
                    System.out.print("Mugwump uses razor-sharp claws at the warrior for " + damage
                            + " points of damage!");
                } else {
                    System.out.print("Mugwump uses razor-sharp claws at the warrior and misses!");
                }
                break;
            case 2:
                d20.roll();

                if (d20.getCurrentValue()>=16) {
                    for (int i = 0; i < 3; i++) {
                        d6.roll();
                        damage =damage+ d6.getCurrentValue();
                    }
                    System.out.print("Mugwump bites the warrior for " + damage
                            + " points of damage!");
                } else {
                    System.out.print("Mugwump snaps at the warrior and misses!");
                }
                break;
            default:
                d6.roll();

                if (hitPoints + d6.getCurrentValue() > maxHitPoints) {
                    System.out.print("The Mugwump licks his wound to heal himself for " + (maxHitPoints - hitPoints)
                            + " hit points!");
                    hitPoints = maxHitPoints;
                } else {

                    System.out.print("The Mugwump licks his wound to heal himself for " + d6.getCurrentValue()
                            + " hit points!");
                    hitPoints =hitPoints + d6.getCurrentValue();
                }
                break;
        }
        System.out.println();
        return damage;

    }


    /**
     * getter to getr hitPouints
     * @return
     */
    public int getHitPoints() {

        return hitPoints;
    }

    /**
     * Decides which attack
     *
     * @return different attack numbers for the mugwump
     */
    private int ai() {
        d100.roll();
        //start with the instance
        if (d100.getCurrentValue() <= 75) {
            return 1;
        }
        //somewhere in Lab7-hold an integer
        if (d100.getCurrentValue() <= 90) {
            return 2;
        }
        return 3;
    }
}

