import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class mainApp { // Arif

    /* NOTE : DELETE PRIOR TO ZIPPING AND SUBMISSION

    Program Requirements :

    1. Never clear screen - Accomplished using line-by-line console action or GUI (preferably the latter) [/]

    2. Pre-load with 5 customers (when the program starts) - Accomplished using json file to store data []

    3. Pre-load with 4 shops - Accomplished using json file to store data []

    4. Have a feature to add 30 random visits to the master visit history (random shops, random
       visitors, time ranging from yesterday until current system time). Sort the master visit history
       by date and time. - Accomplished with a function []

    Final Submission Requirement :

    1. Java source code. Make sure the code can be compiled and run. [X]

    2. Java documentation (Javadoc) for a class. Choose a class that have methods with parameter
    and return type. [X]

    3. A file named Members.txt that lists down the group members’ ID, name, and contribution. [~]

     */

    // methods

    public static void main(String[] args) {

        menu.loginMenu();
        // menu.userMenu();

    }

}

class menu { // Shu & Arif

    public static boolean loginStat;

    // methods
    public static void loginMenu() {

        System.out.println("\n");
        System.out.println ("________________________________________________________________________________________");
        System.out.println ("| No  |                         Main Menu                                              |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  1  |             Register Account                                                   |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  2  |             Sign In                                                            |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println("\n");
        System.out.print("Please enter your choice (1-2): ");

        option1();

    }

    public static void pauseForLogin() {



    }

    public static void userMenu() {

        System.out.println("\n");
        System.out.println ("________________________________________________________________________________________");
        System.out.println ("| No  |                         Main Menu                                              |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  1  |             Shop Check-in                                                      |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  2  |             View Visit History                                                 |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  3  |             Check Status                                                       |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  4  |             Administrator Mode                                                 |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println("\n");
        System.out.print("Please enter your choice (1-4): ");

        option2();

    }

    public static int choice() {

        System.out.println("Would you like to proceed or exit to Main Menu?\n");
        System.out.println("To proceed enter 9.");
        System.out.println("If you wish to quit enter 0.\n");

        Scanner s = new Scanner(System.in);

        int opt = s.nextInt(); // fix catch digit only

        while (opt != 0 && opt != 9) {
            System.err.println ("Unrecognized option");
            choice();
        }

        return opt;

    }

    public static void option1() {

        Scanner input = new Scanner ( System.in );
        int opt;

        switch (input.nextInt()) { // fix catch digit only

            case 1:
                System.out.println ("\nRegister Account\n");
                opt = choice();
                if (opt == 0) {
                    loginMenu();
                } else {
                    Register.custRegister();
                }
                break;

            case 2:
                System.out.println ("\nSign In\n");
                opt = choice();
                if (opt == 0) {
                    loginMenu();
                } else {
                    loginStat = Login.custLogin();
                }
                break;

            default:
                System.err.println ("Unrecognized option");
                System.out.println("\n");
                System.out.print("Please enter your choice (1-2): ");
                option1();

        }

    }

    public static void option2() {

        Scanner input = new Scanner ( System.in );
        int opt;

        switch (input.nextInt()) { // fix catch digit only

            case 1:
                System.out.println("\nShop Check-in\n");
                opt = choice();
                if (opt == 0) {
                    userMenu();
                } else {
                    Check_In.custCheck_In();
                }
                break;

            case 2:
                System.out.println("\nView Visit History\n");
                opt = choice();
                if (opt == 0) {
                    userMenu();
                } else {
                    ViewHistory.viewHistory();
                }
                break;

            case 3:
                System.out.println("\nCheck Status\n");
                opt = choice();
                if (opt == 0) {
                    userMenu();
                } else {
                    System.out.println("\ncheck status\n"); // check status
                }
                break;

            case 4:
                System.out.println("\nAdministrator Mode\n");
                opt = choice();
                if (opt == 0) {
                    userMenu();
                } else {
                    System.out.println("admin mode"); // admin mode
                }
                break;

            default:
                System.err.println("Unrecognized option");
                System.out.println("\n");
                System.out.print("Please enter your choice (1-4): ");
                option2();

        }

    }

}