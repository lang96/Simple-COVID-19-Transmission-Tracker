import java.util.Scanner;

// Member 1 & 2

public class mainApp {

    public static void main(String[] args) {

        Customer.initializeCustomerList(); // Initialize CustomerList at the start of program
        Shop.initializeShopList(); // Initialize ShopList at the start of program
        Visit.initializeVisitList(); // Initialize VisitList at the start of program
        Case.initializeCaseVisit(); // Initialize CaseList at the start of program
        menu.startMenu(); // Start of entire program's menu system

    }

}

class menu { // Member 1 & 2

    public static String loggedInPhone = "";
    public static String loggedInShop = "";
    public static boolean registerStat = false;

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
        System.out.println ("|  3  |             Exit to Main Menu                                                  |");
        System.out.println ("|_____|________________________________________________________________________________|");

        System.out.println("\n");
        System.out.print("Please enter your choice (1-3): ");

        options.option2();

    }

    public static void userMenu() {

        String name = "";

        for(int i=0; i < Customer.CustomerList.size(); i++) {

            String checkPhone = Customer.CustomerList.get(i).getPhoneNum();
            if (checkPhone.equals(menu.loggedInPhone)) {

                name = Customer.CustomerList.get(i).getFName();

            }

        }

        System.out.println("\nCurrently logged in user : " + name);

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

        int opt;
        Scanner s = new Scanner(System.in);

        System.out.println("Would you like to proceed or return to the previous menu?\n");
        System.out.println("To proceed, enter 9.");
        System.out.println("If you wish to return, enter 0.\n");
        opt = s.nextInt();

        while (opt != 0 && opt != 9) {
            System.err.println("\nUnrecognized option!\n");
            confirmChoice();
        }

        return opt;

    }

    public static int afterChoice() {

        int opt;
        Scanner s = new Scanner(System.in);

        System.out.println("\nWould you like to return to the previous menu or sign out and quit to the start menu?\n");
        System.out.println("To return to the previous menu, enter 9.");
        System.out.println("If you wish to quit to the start menu, enter 0.\n");
        opt = s.nextInt();

        while (opt != 0 && opt != 9) {
            System.err.println("\nUnrecognized option!\n");
            confirmChoice();
        }

        return opt;

    }

    public static void option1() {

        Scanner input = new Scanner(System.in);
        int opt = -1;
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
                    int shopSelection = Shop.selectShop();
                    menu.loggedInShop = "" + Shop.ShopList.get(shopSelection).getShopName();
                    menu.shopMenu();
                }
                break;

            case 3:
                System.out.println("\nLaunch as Administrator\n");
                opt = confirmChoice();
                if (opt == 0) {
                    menu.startMenu();
                } else {
                    Admin_Login.adminLogin();
                }
                break;

            case 4:
                System.out.println("\nExit the program\n");
                opt = confirmChoice();
                if (opt == 0) {
                    menu.startMenu();
                } else {
                    System.out.println("Thank you for using our program and have a nice day!");
                    System.exit(0);
                }
                break;

            default:
                System.err.println("\nUnrecognized option\n" + "Please enter your choice (1-4): \n");
                option1();

        }

    }

    public static void option2() {

        Scanner input2 = new Scanner(System.in);
        int opt = -1;

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
                    Login.custLogin();
                }
                break;

            case 3:
                System.out.println("\nExit to Main Menu\n");
                opt = confirmChoice();
                if (opt == 0) {
                    menu.loginMenu();
                } else {
                    menu.startMenu();
                }
                break;

            default:
                System.err.println("\n\nUnrecognized option\n");
                System.out.print("Please enter your choice (1-3): ");
                option2();

        }

    }

    public static void option3() {

        Scanner input3 = new Scanner(System.in);
        int opt = -1;

        switch (input3.nextInt()) {

            case 1:
                System.out.println("\nShop Check-In\n");
                opt = confirmChoice();
                if (opt == 0) {
                    menu.userMenu();
                } else {
                    Check_In.custCheck_In(menu.loggedInPhone);
                }
                break;

            case 2:
                System.out.println("\nView Visit History\n");
                opt = confirmChoice();
                if (opt == 0) {
                    menu.userMenu();
                } else {
                    ViewHistory.viewHistory(menu.loggedInPhone);
                }
                break;

            case 3:
                System.out.println("\nCheck Status\n");
                opt = confirmChoice();
                if (opt == 0) {
                    menu.userMenu();
                } else {
                    CheckStatus.checkStatus(menu.loggedInPhone);
                }
                break;

            case 4:
                System.out.println("\nSign Out\n");
                opt = confirmChoice();
                if (opt == 0) {
                    menu.userMenu();
                } else {
                    menu.loggedInPhone = "";
                    menu.startMenu();
                }
                break;

            default:
                System.err.println("\n\nUnrecognized option\n");
                System.out.print("Please enter your choice (1-4): ");
                option3();

        }
    }

    public static void option4() {

        Scanner input4 = new Scanner(System.in);
        int opt = -1;
        switch (input4.nextInt()) {

            case 1:
                System.out.println("\nView Status\n");
                opt = confirmChoice();
                if (opt == 0) {
                    menu.shopMenu();
                } else {
                    Shop.viewShopStatus(menu.loggedInShop);
                }
                break;

            case 2:
                System.out.println("\nSign Out\n");
                opt = confirmChoice();
                if (opt == 0) {
                    menu.shopMenu();
                } else {
                    menu.loggedInShop = "";
                    menu.startMenu();
                }
                break;

            default:
                System.err.println("\n\nUnrecognized option\n");
                System.out.print("Please enter your choice (1-2): ");
                option4();

        }
    }

    public static void option5() {

        Scanner input5 = new Scanner(System.in);
        int opt = -1;

                switch (input5.nextInt()) {

                    case 1:
                        System.out.println("\nView All Customers\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.adminMenu();
                        } else {
                            Admin.viewCustomer();
                        }
                        break;

                    case 2:
                        System.out.println("\nView All Shops\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.adminMenu();
                        } else {
                            Admin.viewShop();
                        }
                        break;

                    case 3:
                        System.out.println("\nView Master History\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.adminMenu();
                        } else {
                            Admin.viewMaster();
                        }
                        break;

                    case 4:
                        System.out.println("\nFlag Customer\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.adminMenu();
                        } else {
                            Admin.flagCustomer();
                        }
                        break;

                    case 5:
                        System.out.println("\nAdd 30 Random Visits\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.adminMenu();
                        } else {
                            Admin.add30Visits();
                        }
                        break;

                    case 6:
                        System.out.println("\nSign Out\n");
                        opt = confirmChoice();
                        if (opt == 0) {
                            menu.adminMenu();
                        } else {
                            menu.startMenu();
                        }
                        break;

                    default:
                        System.err.println("\n\nUnrecognized option!\n");
                        System.out.print("Please enter your choice (1-6): ");

                        option5();

                }

            }

        }