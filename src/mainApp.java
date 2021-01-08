import java.util.Scanner;
import java.util.InputMismatchException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class mainApp {

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
        menu.startMenu();

    }

}

class menu { // Shu & Arif

    public static String loggedInPhone;

    public static void startMenu() {

        System.out.println("\n");
        System.out.println ("________________________________________________________________________________________");
        System.out.println ("| No  |                         Main Menu                                              |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  1  |             Customer                                                           |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  2  |             Shop                                                               |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  3  |             Administrator                                                      |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  4  |             Exit                                                               |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println("\n");
        System.out.print("Please enter your choice (1-4): ");

        options.option1();

    }

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

        options.option2();

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

        options.option3();

    }

    public static void shopMenu() {

        System.out.println("\n");
        System.out.println ("________________________________________________________________________________________");
        System.out.println ("| No  |                         Main Menu                                              |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  1  |             View Status                                                        |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  2  |             Sign Out                                                           |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println("\n");
        System.out.print("Please enter your choice (1-2): ");

        options.option4();

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

        options.option5();

    }

}

class options {

    public static int confirmChoice() {

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
                confirmChoice();
            }

        } while (choice==false);

        while (opt != 0 && opt != 9) {
            System.err.println ("\nUnrecognized option!\n");
            confirmChoice();
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
                        System.out.println("\nLaunch as Customer\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.startMenu();
                        } else {
                            menu.loginMenu();
                        }
                        break;

                    case 2:
                        System.out.println("\nLaunch as Shop\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.startMenu();
                        } else {
                            // shop login or just select shop in shopmenu - depends on time
                            menu.shopMenu();
                        }
                        break;

                    case 3:
                        System.out.println("\nLaunch as Administrator\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.startMenu();
                        } else {
                            // admin login
                            menu.adminMenu();
                        }
                        break;

                    case 4:
                        System.out.println("\nExit the program\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.startMenu();
                        } else {
                            System.exit(0);
                        }
                        break;

                    default:
                        System.err.println("\nUnrecognized option\n");
                        System.out.print("Please enter your choice (1-4): ");
                        option1();

                }

            } catch (InputMismatchException ex) {

                checkOpt = false;
                System.out.println("\nInvalid Input!\n");
                System.out.print("Please enter your choice (1-4): ");
                option1();

            }

        } while (checkOpt==false);

    }

    public static void option2() {

        Scanner input2 = new Scanner(System.in);
        int opt = -1;
        boolean checkOpt = false;

        do {
            try {
                switch (input2.nextInt()) {

                    case 1:
                        System.out.println("\nRegister Account\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.loginMenu();
                        } else {
                            Register.custRegister();
                        }
                        break;

                    case 2:
                        System.out.println("\nSign In\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.loginMenu();
                        } else {
                            menu.loggedInPhone = Login.custLogin();
                        }
                        break;

                    default:
                        System.err.println("\n\nUnrecognized option\n");
                        System.out.print("Please enter your choice (1-2): ");
                        option2();

                }

            } catch (InputMismatchException ex) {

                checkOpt = false;
                System.out.println("\nInvalid Input!\n");
                System.out.print("Please enter your choice (1-2): ");
                option2();

            }

        } while (checkOpt==false);

    }

    public static void option3() {

        Scanner input3 = new Scanner(System.in);
        int opt = -1;
        boolean checkOpt = false;

        do {
            try {
                switch (input3.nextInt()) {

                    case 1:
                        System.out.println("\nShop Check-In\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.userMenu();
                        } else {
                            System.out.println("Check-in");
                        }
                        break;

                    case 2:
                        System.out.println("\nView Visit History\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.userMenu();
                        } else {
                            // visit history
                        }
                        break;

                    case 3:
                        System.out.println("\nCheck Status\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.userMenu();
                        } else {
                            // check status
                        }
                        break;

                    case 4:
                        System.out.println("\nSign Out\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.userMenu();
                        } else {
                            // sign out
                        }
                        break;

                    default:
                        System.err.println("\n\nUnrecognized option\n");
                        System.out.print("Please enter your choice (1-4): ");
                        option3();

                }

            } catch (InputMismatchException ex) {

                checkOpt = false;
                System.out.println("\nInvalid Input!\n");
                System.out.print("Please enter your choice (1-4): ");
                option3();

            }

        } while (checkOpt==false);

    }

    public static void option4() {

        Scanner input4 = new Scanner(System.in);
        int opt = -1;
        boolean checkOpt = false;

        do {
            try {
                switch (input4.nextInt()) {

                    case 1:
                        System.out.println("\nView Status\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.shopMenu();
                        } else {
                            // view shop status
                        }
                        break;

                    case 2:
                        System.out.println("\nSign Out\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.shopMenu();
                        } else {
                            // return to menu.startmenu
                        }
                        break;

                    default:
                        System.err.println("\n\nUnrecognized option\n");
                        System.out.print("Please enter your choice (1-2): ");
                        option4();

                }

            } catch (InputMismatchException ex) {

                checkOpt = false;
                System.out.println("\nInvalid Input!\n");
                System.out.print("Please enter your choice (1-2): ");
                option4();

            }

        } while (checkOpt==false);

    }

    public static void option5() {

        Scanner input5 = new Scanner(System.in);
        int opt = -1;
        boolean checkOpt = false;

        do {
            try {
                switch (input5.nextInt()) {

                    case 1:
                        System.out.println("\nView All Customers\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.adminMenu();
                        } else {
                            // view all customers - fetch function
                        }
                        break;

                    case 2:
                        System.out.println("\nView All Shops\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.adminMenu();
                        } else {
                            // view all shops - function not created
                        }
                        break;

                    case 3:
                        System.out.println("\nView Master History\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.adminMenu();
                        } else {
                            // view master history - function incomplete
                        }
                        break;

                    case 4:
                        System.out.println("\nFlag Customer\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.adminMenu();
                        } else {
                            // flag customer - just fetch function
                        }
                        break;

                    case 5:
                        System.out.println("\nAdd 30 Random Visits\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.adminMenu();
                        } else {
                            // add 30 random visits - function not created
                        }
                        break;

                    case 6:
                        System.out.println("\nSign Out\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.adminMenu();
                        } else {
                            // sign out
                        }
                        break;

                    default:
                        System.err.println("\n\nUnrecognized option!\n");
                        System.out.print("Please enter your choice (1-6): ");
                        option5();

                }

            } catch (InputMismatchException ex) {

                checkOpt = false;
                System.out.println("\nInvalid Input!\n");
                System.out.print("Please enter your choice (1-6): ");
                option5();

            }

        } while (checkOpt==false);

    }

}