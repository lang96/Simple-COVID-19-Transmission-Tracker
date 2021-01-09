import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    } // complete

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

    } // complete

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

            System.out.printf("%-2s ", "No");
            System.out.printf("%-15s  ", "Name");
            System.out.printf("%-11s  ", "Phone");
            System.out.printf("%-15s  ", "Manager");
            System.out.printf("%-6s \n", "Status");

            for (int i = 0;i < phoneNum.size();i++) {

                System.out.printf("%-2s ", (i+1));
                System.out.printf("%-15s  ", name.get(i));
                System.out.printf("%-11s  ", phoneNum.get(i));
                System.out.printf("%-15s  ", manager.get(i));
                System.out.printf("%-6s \n", status.get(i));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    } // complete

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

        LocalDateTime timeVar = LocalDateTime.now();
        DateTimeFormatter dateVar = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = timeVar.format(dateVar);

        // above 1 am and below 11 pm


        // between 11 pm and 1 am


    }

    public static long findDifference(String beginDate, String endDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        long timeDifference;
        long minutesDifference = 0;

        try {

            Date d1 = sdf.parse(beginDate);
            Date d2 = sdf.parse(endDate);

            int beforeAfter = d1.compareTo(d2);

            if (beforeAfter == -1) {
                timeDifference = d2.getTime() - d1.getTime();
            } else if (beforeAfter == 1) {
                timeDifference = d1.getTime() - d2.getTime();
            } else {
                timeDifference = 0;
            }

            minutesDifference = (timeDifference / (1000 * 60)) % 60;

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return minutesDifference;

    }

    public static String flagShop(String shop, String date, String time) {

        // This function is an extension of the function flagCustomer(). NOTE : DO NOT ATTEMPT TO RUN ON ITS OWN!
        // This function allows admin to flag a shop as a zone of CoViD-19 positive cases, thus automatically -
        // - flagging anyone who are considered close contacts (within the regulations set in customerData class) as -
        // - CoViD-19 positive cases.

        // find customers within flagged customer timestamp range

        String fullDateTime = date + " " + time;
        String splitDate = "";
        String splitTime = "";
        ArrayList<String> dateTimeIndex = new ArrayList<>();
        ArrayList<String> flagTimeIndex = new ArrayList<>();
        ArrayList<String> flaggedCustomers = new ArrayList<>();
        String[] splitDateTime;
        long minDiff = 0;


        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject visit = (JSONObject) obj;

            JSONArray dateArr = (JSONArray) visit.get("date");
            JSONArray timeArr = (JSONArray) visit.get("time");
            JSONArray customerArr = (JSONArray) visit.get("customerName");
            JSONArray shopArr = (JSONArray) visit.get("shop");

            String checkDateTime = "";

            for (int i = 0;i < dateArr.size();i++) {

                checkDateTime = dateArr.get(i) + " " + timeArr.get(i);
                dateTimeIndex.add(checkDateTime);

            }

            for (int i = 0;i < dateTimeIndex.size();i++) {

                minDiff = findDifference(fullDateTime, dateTimeIndex.get(i));
                if (minDiff >= 0 && minDiff <= 60) {
                    flagTimeIndex.add(dateTimeIndex.get(i));
                }

            }

            // get all close contact customers

            for (int i = 0;i < flagTimeIndex.size();i++) {

                splitDateTime = flagTimeIndex.get(i).split(" ");
                splitDate = "" + splitDateTime[0];
                splitTime = "" + splitDateTime[1];

                for (int j = 0;j < dateArr.size();j++) {

                    if (splitDate.equals(dateArr.get(j)) && splitTime.equals(timeArr.get(j))) {

                        flaggedCustomers.add(String.valueOf(customerArr.get(j)));

                    }

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // flag close contacts
        for (int i = 0; i < flaggedCustomers.size(); i++) {

            for (int j = 0; j < Customer.CustomerList.size(); j++) {

                if(flaggedCustomers.get(i).equals(Customer.CustomerList.get(j).getFName())) {

                    Customer.CustomerList.get(j).setStatus("Close");

                }

            }

        }

        Customer.updateCustomerList();

        // flag shop as case
        for (int k = 0; k < Shop.ShopList.size(); k++) {

            if(shop.equals(Shop.ShopList.get(k).getShopName())) {

                Shop.ShopList.get(k).setStatus("Case");

            }

        }

        Shop.updateShopList();

        return fullDateTime;

    } // complete

    public static void addVisit() {

        // This function allows admin to deploy an additional feature in which 30 random visits are added into the -
        // - master visit history. These visits are generated using the existing list of customers and shops from -
        // - application data files (json).

    } // not created

    // Test main module only
    public static void main(String[] args) throws java.text.ParseException {

        viewCustomer();
        viewMaster();
        viewShop();
        //Customer.initializeCustomerList();
        //flagCustomer();
        //Customer.updateCustomerList();

    }

}