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
public class Warrior {
    private int hitPoints;
    //private Scanner in = new Scanner(System.in);
    //private Die d20 = new Die(20); //one approach
    private Die d20 = new Die(20);
    private Die d10 = new Die(10);
    private Die d8 = new Die(8);
    private Die d4 = new Die(4);



    public Warrior() {

        hitPoints = rollHitPoints();
    }

    public int getHitPoints() {

        return hitPoints;
    }

    public void takeDamage(int hp) {
        hitPoints =hitPoints - hp; //changing the value in hit points
    }

    public int attack(int type) {
        //figure out how much damage based on the mugwump
        int damage = 0;
        if (type == 1) {
            d20.roll();
            if (d20.getCurrentValue() >= 12) {
                for (int i = 0; i < 2; i++) {
                    d8.roll();
                    damage += d8.getCurrentValue();
                }
            }
        } else {
            d20.roll();
            int hit = d20.getCurrentValue();
            if (d20.getCurrentValue() >= 8) {
                //calculate for damage
                d4.roll();
                damage = d4.getCurrentValue();
            }
        }
        return damage;
    }

    private int rollHitPoints() {
        //ask if it is the attack method
        int totalHit = 0;
        for (int i = 0; i < 6 ; i++) {
            //set the die current value
            d10.roll();
            //summing the value of the rolls
            totalHit = totalHit + d10.getCurrentValue();
        }
        return totalHit;
    }
}

