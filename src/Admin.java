import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

public class Admin {

    public static void adminLogin() {

        System.out.println ("                                   -------------                                        ");
        System.out.println ("                                    Admin Login                                         ");
        System.out.println ("                                   -------------                                        ");

        Scanner s = new Scanner(System.in);
        String adminID = s.next(); // fix catch string only


        String pass = s.next(); // fix catch string only

    } // incomplete

    public static void viewMaster() {

        // This function allows admin to view the master visit history of the entire system.

        // Reading JSON file \\

        JSONParser jsonParser = new JSONParser();

        // Parsing the contents of the JSON file

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject visit = (JSONObject) obj;

            JSONArray date = (JSONArray) visit.get("date");
            JSONArray time = (JSONArray) visit.get("time");
            JSONArray customer = (JSONArray) visit.get("customerName");
            JSONArray shop = (JSONArray) visit.get("shop");

            // Table output

            System.out.printf("%-2s ", "No");
            System.out.printf("%-10s  ", "Date");
            System.out.printf("%-8s  ", "Time");
            System.out.printf("%-15s ", "Customer");
            System.out.printf("%-15s \n", "Shop");

            for (int i = 0;i < customer.size();i++) {

                System.out.printf("%-2s ", (i+1));
                System.out.printf("%-10s  ", date.get(i));
                System.out.printf("%-8s  ", time.get(i));
                System.out.printf("%-15s ", customer.get(i));
                System.out.printf("%-15s \n", shop.get(i));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    } // incomplete

    public static void viewCustomer() {

        // This function allows admin to view the details of all customers registered in the system.
        // Details of customers follows from Customer.java
        // The details of each customerData which are generated from registration are stored in a json file and are -
        // - retrieved in this java module.

        // Reading JSON file \\

        JSONParser jsonParser = new JSONParser();

        // Parsing the contents of the JSON file

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\customerData.json")) {

            Object obj = jsonParser.parse(reader);

            JSONObject customer = (JSONObject) obj;

            JSONArray fName = (JSONArray) customer.get("fName");
            JSONArray lName = (JSONArray) customer.get("lName");
            JSONArray phoneNum = (JSONArray) customer.get("phoneNum");
            JSONArray status = (JSONArray) customer.get("status");

            // Table output

            System.out.printf("\n%-2s ", "No");
            System.out.printf("%-15s  ", "Name");
            System.out.printf("%-11s  ", "Phone");
            System.out.printf("%-6s \n\n", "Status");

            for (int i = 0;i < phoneNum.size();i++) {

                System.out.printf("%-2s ", (i+1));
                System.out.printf("%-15s  ", fName.get(i) + " " + lName.get(i));
                System.out.printf("%-11s  ", phoneNum.get(i));
                System.out.printf("%-6s \n\n", status.get(i));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void viewShop() {

        // This function allows admin to view the details of all shops within the system.
        // Details of customers follows from shop.java
        // The details of each shopData are stored in a json file and are retrieved in this java module.

        // Reading JSON file \\

        JSONParser jsonParser = new JSONParser();

        // Parsing the contents of the JSON file

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\shopData.json")) {

            Object obj = jsonParser.parse(reader);

            JSONObject shop = (JSONObject) obj;

            JSONArray name = (JSONArray) shop.get("name");
            JSONArray phoneNum = (JSONArray) shop.get("phoneNum");
            JSONArray manager = (JSONArray) shop.get("manager");
            JSONArray status = (JSONArray) shop.get("status");

            // Table output

            System.out.printf("| %-2s | ", "No");
            System.out.printf("%-20s | ", "Name");
            System.out.printf("%-11s | ", "Phone");
            System.out.printf("%-15s | ", "Manager");
            System.out.printf("%-6s |\n", "Status");

            for (int i = 0;i < phoneNum.size();i++) {

                System.out.printf("| %-2s | ", (i+1));
                System.out.printf("%-20s | ", name.get(i));
                System.out.printf("%-11s | ", phoneNum.get(i));
                System.out.printf("%-15s | ", manager.get(i));
                System.out.printf("%-6s |\n", status.get(i));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    } // incomplete

    public static int choice(ArrayList<Integer> flagIndex, int index) { // For flagCustomer()

        int opt = -1;
        Scanner s = new Scanner(System.in);
        boolean choice = false;

        do {
            try {

                System.out.println("Which customer would you like to flag?\n");
                System.out.println("Enter the index (no) of the customer: \n");
                opt = s.nextInt();
                choice = true;
                opt -= 1;
                if (opt < 0 || opt > flagIndex.size()) {
                    System.err.println ("\nUnrecognized option!\n");
                    choice(flagIndex, index);
                } else {
                    index = flagIndex.get(opt);
                }

            } catch (InputMismatchException ex) {
                choice = false;
                System.out.println("\nInvalid Input!\n");
                s.nextLine();
            }

        } while (choice==false);

        return index;

    }

    public static void flagCustomer() {

        // This function allows admin to flag a customer as a positive case of CoViD-19.
        // With this function deployed, the flagShop() function automatically deploys as well.

        // access customer and retrieve index
        int flagIndex = 0;
        ArrayList<Integer> flaggable = Customer.displayCustomerListToFlag();
        flagIndex = choice(flaggable, flagIndex);

        // flag customer
        String newStat = "Case";
        Customer.CustomerList.get(flagIndex).setStatus(newStat);

    }

    public static void flagShop() {

        // This function is an extension of the function flagCustomer(). NOTE : DO NOT ATTEMPT TO RUN ON ITS OWN!
        // This function allows admin to flag a shop as a zone of CoViD-19 positive cases, thus automatically -
        // - flagging anyone who are considered close contacts (within the regulations set in customerData class) as -
        // - CoViD-19 positive cases.

    } // not created

    public static void addVisit() {

        // This function allows admin to deploy an additional feature in which 30 random visits are added into the -
        // - master visit history. These visits are generated using the existing list of customers and shops from -
        // - application data files (json).

    } // not created

    // Test main module only
    public static void main(String[] args) {
        viewCustomer();
        Customer.initializeCustomerList();
        flagCustomer();
        Customer.updateCustomerList();
    }

}