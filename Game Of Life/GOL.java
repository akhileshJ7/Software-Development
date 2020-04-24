/**
 * Course: CS-1011
 * Term: Fall 2018
 * Assignment: Lab 7
 * Author: Akhilesh Janaswamy
 * Date: 09/12/18
 */
package janaswamya;
import java.util.Scanner;

/**
 * Class containing the part of program for lab 7 in fall 2018 CS-1011 course which performs
 * a battle simulator game.
 * @version 2018-10-22
 */
public class GOL {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        intro();
        // game loop
        boolean repeat = true;
        //loop to run depending on the user
        while (repeat){
            // initialize game
            Warrior warrior = new Warrior();
            Mugwump mugwump = new Mugwump();


            // while neither combatant has lost all of their hit points, battle!

            String winner = "none";
            while (winner.equals("none")) {
                report(warrior, mugwump);
                winner = battle(warrior, mugwump, in);
            }
            // declare the winner
            victory(winner);

            // ask to play again
            System.out.println("Would you like to play again?");
            String option = in.nextLine();

            if(option.equalsIgnoreCase("y") || option.equalsIgnoreCase("Yes")){
                repeat= true;
            }
            else{
                repeat=false;
            }
        }
        // Thank the user for playing your game
        System.out.print("Thank you for playing the BAttle Simulator!");
    }


    /**
     * This method prints the introduction
     */
    private static void intro() {
        String intro="Welcome to Battle Simulator 3000! The world's more low tech battle simulator!"
       +"You are a Valiant Warrior defending your humble village"+
        "from an evil Mugwump! Fight bravely,You have your Trusty Sword, which deals decent damage,"+
        "but can be tough to hit with sometimesYou also have your Shield of Light,"+
        "which is not as strong as your sword, but is easier to deal damage with.Let the epic battle begin!";

        System.out.println(intro);
    }

    /**
     * This method handles the battle logic for the game.
     *
     * @param warrior
     * @param mugwump
     * @return String name of the winner
     */
    private static String battle(Warrior warrior, Mugwump mugwump, Scanner in) {
        // determine who attacks first
        int attack = initiative();
        int countAttack = 0;
        do {
            switch (attack) {
                case 1:
                    attack = 2;
                    System.out.println("\nThe Warrior attacks first!");
                    int attackChoice = attackChoice(in);
                    if (attackChoice == 1) {
                        int damage = warrior.attack(attackChoice);
                        if (damage == 0) {
                            System.out.println("You swing your  Sword and miss the foul creature!");
                        } else {
                            System.out.println("You hit the Mugwump with your Sword for "
                                    + damage + " points of damage!");
                            mugwump.takeDamage(damage);
                        }
                    } else {
                        int damage = warrior.attack(attackChoice);
                        if (damage == 0) {
                            System.out.println("You miss the Mugwump with your Shield of Light!");
                        } else {
                            System.out.println("You hit the Mugwump with your Shield of Light for "
                                    + damage + " points of damage!");
                            mugwump.takeDamage(damage);
                        }
                    }
                    if (mugwump.getHitPoints() <= 0) {
                        return "warrior";
                    }
                    break;
                case 2:
                    attack = 1;
                    System.out.println("\nThe Mugwump attacks first!");
                    warrior.takeDamage(mugwump.attack());
                    if (mugwump.getHitPoints() <= 0) {
                        return "warrior";
                    }
            }
            countAttack++;
        } while (countAttack < 2);
        return "none";
    }

    /**
     * shows the hp
     * @param warrior object to warrior
     * @param mugwump  object to Mugwump
     */
    private static void report(Warrior warrior, Mugwump mugwump) {
        System.out.println("Warrior Hp: " + warrior.getHitPoints());
        System.out.println("Mugwump Hp: " + mugwump.getHitPoints());
    }

    /**
     * Asks for which ype of attack
     *
     * @return option
     */
    private static int attackChoice(Scanner in) {
        int options;
        do {
            System.out.println("Choose your weapon");
            System.out.println("1. Trusty Sword");
            System.out.println("2. Shield of Light");
            System.out.print("Enter your choice: ");
            options = in.nextInt();
        } while (!(options == 1 || options == 2));
        return options;
    }


    /**
     *decides which attacks first
     *
     * @return the worrior attack
     */
    private static int initiative() {
        Die d10 = new Die(10);
        int warriorDie;
        int mugwumpDie;
        do {
            d10.roll();
            warriorDie = d10.getCurrentValue();
            d10.roll();
            mugwumpDie = d10.getCurrentValue();
            if (warriorDie > mugwumpDie) {
                return 1;
            } else if (mugwumpDie > warriorDie) {
                return 2;
            }
        } while (warriorDie == mugwumpDie);
        return 0;
    }

    /**
     * Decides the winner
     *
     * @param winner
     */
    private static void victory(String winner) {
        if (winner.equals("warrior")) {
            System.out.println("The Warrior is the winner and dances around the Mugwump's dead body "
                    + "and set the body on fire!");

        } else {
            System.out.println("The Mugwump is the winner. Afterwards the Mugwump laughs at the dead human"
                    + "and throws the body into a hole!");
        }
        System.out.println();
    }
}
