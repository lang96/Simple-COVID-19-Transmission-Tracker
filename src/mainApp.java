import java.util.Scanner;
import java.util.InputMismatchException;

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

        Customer.initializeCustomerList(); // Initialize CustomerList at the start of program
        menu.loginMenu();

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
        System.out.println ("|  3  |             Administrator Mode                                                 |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println("\n");
        System.out.print("Please enter your choice (1-3): ");

        option1();

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
        System.out.println ("|  4  |             Sign Out                                                           |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println("\n");
        System.out.print("Please enter your choice (1-4): ");

        option2();

    }

    public static void adminMenu() {

        System.out.println("\n");
        System.out.println ("________________________________________________________________________________________");
        System.out.println ("| No  |                         Main Menu                                              |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  1  |             View All Customers                                                 |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  2  |             View All Shops                                                     |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  3  |             View Master Visit History                                          |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  4  |             Flag Customer                                                      |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  5  |             Add 30 Random Visits                                               |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  6  |             Exit Administrator Mode                                            |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println("\n");
        System.out.print("Please enter your choice (1-6): ");

        option3();

    }

    public static int choice() {

        int opt = -1;
        Scanner s = new Scanner(System.in);
        boolean choice = false;

        do {
            try {

                System.out.println("Would you like to proceed or return to the previous menu?\n");
                System.out.println("To proceed, enter 9.");
                System.out.println("If you wish to return, enter 0.\n");
                opt = s.nextInt();
                choice = true;

            } catch (InputMismatchException ex) {
                choice = false;
                System.out.println("\nInvalid Input!\n");
                s.nextLine();
            }

        } while (choice==false);

        while (opt != 0 && opt != 9) {
            System.err.println ("Unrecognized option!\n");
            choice();
        }

        return opt;

    }

    public static int afterChoice() {

        int opt = -1;
        Scanner s = new Scanner(System.in);
        boolean choice = false;

        do {
            try {

                System.out.println("Would you like to return to the previous menu or quit to login menu?\n");
                System.out.println("To return to the previous menu, enter 9.");
                System.out.println("If you wish to quit to login menu, enter 0.\n");
                opt = s.nextInt();
                choice = true;

            } catch (InputMismatchException ex) {
                choice = false;
                System.out.println("\nInvalid Input!\n");
                s.nextLine();
            }

        } while (choice==false);

        while (opt != 0 && opt != 9) {
            System.err.println ("Unrecognized option!\n");
            choice();
        }

        return opt;

    }

    public static void option1() {

        Scanner input = new Scanner(System.in);
        int opt = -1;
        boolean checkOpt = false;

        do {
            try {
                switch (input.nextInt()) {

                    case 1:
                        System.out.println("\nRegister Account\n");
                        opt = choice();
                        if (opt == 0) {
                            loginMenu();
                        } else {
                            Register.custRegister();
                        }
                        break;

                    case 2:
                        System.out.println("\nSign In\n");
                        opt = choice();
                        if (opt == 0) {
                            loginMenu();
                        } else {
                            loginStat = Login.custLogin();
                        }
                        break;

                    case 3:
                        System.out.println("\nAdministrator Mode\n");
                        opt = choice();
                        if (opt == 0) {
                            loginMenu();
                        } else {
                            adminMenu();
                        }
                        break;

                    default:
                        System.err.println("Unrecognized option");
                        System.out.println("\n");
                        System.out.print("Please enter your choice (1-3): ");
                        option1();

                }
            } catch (InputMismatchException ex) {
                checkOpt = false;
                System.out.println("\nInvalid Input!");
                input.nextLine();
                loginMenu();
            }

        } while (checkOpt==false);

    }

    public static void option2() {

        Scanner input = new Scanner ( System.in );
        int opt;
        boolean checkOpt2=false;

        do {
            try {
                switch (input.nextInt()) {

                    case 1:
                        System.out.println("\nShop Check-in\n");
                        opt = choice();
                        if (opt == 0) {
                            userMenu();
                        } else {
                            Check_In.custCheck_In();
                            // either quit to login or return to user menu
                        }
                        break;

                    case 2:
                        System.out.println("\nView Visit History\n");
                        opt = choice();
                        if (opt == 0) {
                            userMenu();
                        } else {
                            ViewHistory.viewHistory();
                            // either quit to login or return to user menu
                        }
                        break;

                    case 3:
                        System.out.println("\nCheck Status\n");
                        opt = choice();
                        if (opt == 0) {
                            userMenu();
                        } else {
                            System.out.println("\ncheck status\n"); // check status
                            // either quit to login or return to user menu
                        }
                        break;

                    case 4:
                        System.out.println("\nSign Out\n");
                        opt = choice();
                        if (opt == 0) {
                            userMenu();
                        } else {
                            loginStat = false;
                            System.out.println("clear out login status");
                            loginMenu();
                        }
                        break;

                    default:
                        System.err.println("Unrecognized option\n");
                        System.out.print("Please enter your choice (1-4): ");
                        option2();

                }
            } catch (InputMismatchException ex) {
                checkOpt2 = false;
                System.out.println("\nInvalid Input!");
                input.nextLine();
                userMenu();
            }

        } while (checkOpt2 ==false);

    }

    public static void option3() {

        Scanner input = new Scanner ( System.in );
        int opt;
        boolean checkOpt3=false;

        do {
            try {
        switch (input.nextInt()) { // fix catch digit only

            case 1:
                System.out.println("\nView All Customers\n");
                opt = choice();
                if (opt == 0) {
                    adminMenu();
                } else {
                    Admin.viewCustomer();
                    // either quit to login or return to admin menu
                }
                break;

            case 2:
                System.out.println("\nView All Shops\n");
                opt = choice();
                if (opt == 0) {
                    adminMenu();
                } else {
                    // view all shops
                    // either quit to login or return to admin menu
                }
                break;

            case 3:
                System.out.println("\nView Master Visit History\n");
                opt = choice();
                if (opt == 0) {
                    adminMenu();
                } else {
                    Admin.viewMaster();
                    // either quit to login or return to admin menu
                }
                break;

            case 4:
                System.out.println("\nFlag Customer\n");
                opt = choice();
                if (opt == 0) {
                    adminMenu();
                } else {
                    // flag customer
                    // either quit to login or return to admin menu
                }
                break;

            case 5:
                System.out.println("\nAdd 30 Random Visits\n");
                opt = choice();
                if (opt == 0) {
                    adminMenu();
                } else {
                    // add random visits
                    // either quit to login or return to admin menu
                }
                break;

            case 6:
                System.out.println("\nExit Administrator Mode\n");
                opt = choice();
                if (opt == 0) {
                    adminMenu();
                } else {
                    loginMenu();
                }
                break;

            default:
                System.err.println("Unrecognized option!\n");
                System.out.print("Please enter your choice (1-6): ");
                option3();

        }
            } catch (InputMismatchException ex) {
                checkOpt3 = false;
                System.out.println("\nInvalid Input!");
                input.nextLine();
                adminMenu();
            }

        } while (checkOpt3 ==false);

    }

}