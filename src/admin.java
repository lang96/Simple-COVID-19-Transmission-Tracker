import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class admin {

    public static void viewHistory() { // Shu's

        // Reading JSON file \\

        JSONParser jsonParser = new JSONParser();

        // Parsing the contents of the JSON file

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);

            JSONObject customerVisit = (JSONObject) obj;

            JSONArray date = (JSONArray) customerVisit.get("date");
            JSONArray time = (JSONArray) customerVisit.get("time");
            JSONArray shop = (JSONArray) customerVisit.get("shopName");

            // Table output

            System.out.printf("| %-2s | ", "No");
            System.out.printf("%-10s | ", "Date");
            System.out.printf("%-8s | ", "Time");
            System.out.printf("%-15s |\n", "Shop");

            for (int i = 0;i < date.size();i++) {

                System.out.printf("| %-2s | ", (i+1));
                System.out.printf("%-10s | ", date.get(i));
                System.out.printf("%-8s | ", time.get(i));
                System.out.printf("%-15s |\n", shop.get(i));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void loginRead() { // Just to practice accessing JSON file contents

        // Reading JSON file \\

        JSONParser jsonParser = new JSONParser();

        // Parsing the contents of the JSON file

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\loginData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject loginData = (JSONObject) obj;

            Object admin = loginData.get("admin");
            JSONObject adminData = (JSONObject) admin;

            JSONArray adminID = (JSONArray) adminData.get("id");
            JSONArray adminPass = (JSONArray) adminData.get("pass");

            Object customer = loginData.get("customer");
            JSONObject customerData = (JSONObject) customer;

            JSONArray phoneNum = (JSONArray) customerData.get("phoneNum");
            JSONArray email = (JSONArray) customerData.get("email");
            JSONArray customerPass = (JSONArray) customerData.get("pass");

            // Admin table output

            System.out.printf("| %-2s | ", "No");
            System.out.printf("%-5s | ", "ID");
            System.out.printf("%-15s |\n", "Password");

            for (int i = 0;i < adminID.size();i++) {

                System.out.printf("| %-2s | ", (i+1));
                System.out.printf("%-5s | ", adminID.get(i));
                System.out.printf("%-15s |\n", adminPass.get(i));

            }

            System.out.println("\n");

            // Customer table output

            System.out.printf("| %-2s | ", "No");
            System.out.printf("%-11s | ", "Phone");
            System.out.printf("%-30s | ", "Email");
            System.out.printf("%-15s |\n", "Password");

            for (int i = 0;i < phoneNum.size();i++) {

                System.out.printf("| %-2s | ", (i+1));
                System.out.printf("%-11s | ", phoneNum.get(i));
                System.out.printf("%-30s | ", email.get(i));
                System.out.printf("%-15s |\n", customerPass.get(i));

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void viewMaster() {

        // This function allows admin to view the master visit history of the entire system.

        // Reading JSON file \\

        JSONParser jsonParser = new JSONParser();

        // Parsing the contents of the JSON file

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);

            JSONObject visit = (JSONObject) obj;

            JSONArray date = (JSONArray) visit.get("date");
            JSONArray time = (JSONArray) visit.get("time");
            JSONArray customer = (JSONArray) visit.get("customerName");
            JSONArray shop = (JSONArray) visit.get("shopName");

            // Table output

            System.out.printf("| %-2s | ", "No");
            System.out.printf("%-10s | ", "Date");
            System.out.printf("%-8s | ", "Time");
            System.out.printf("%-15s | ", "Customer");
            System.out.printf("%-15s |\n", "Shop");

            for (int i = 0;i < customer.size();i++) {

                System.out.printf("| %-2s | ", (i+1));
                System.out.printf("%-10s | ", date.get(i));
                System.out.printf("%-8s | ", time.get(i));
                System.out.printf("%-15s | ", customer.get(i));
                System.out.printf("%-15s |\n", shop.get(i));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void viewCustomer() {

        // This function allows admin to view the details of all customers registered in the system.
        // Details of customers follows from customer.java
        // The details of each customerData which are generated from registration are stored in a json file and are -
        // - retrieved in this java module.

        // Reading JSON file \\

        JSONParser jsonParser = new JSONParser();

        // Parsing the contents of the JSON file

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\customerData.json")) {

            Object obj = jsonParser.parse(reader);

            JSONObject customer = (JSONObject) obj;

            JSONArray fName = (JSONArray) customer.get("fName");
            JSONArray lName = (JSONArray) customer.get("lName");
            JSONArray phoneNum = (JSONArray) customer.get("phoneNum");
            JSONArray status = (JSONArray) customer.get("status");

            // Table output

            System.out.printf("| %-2s | ", "No");
            System.out.printf("%-20s | ", "Name");
            System.out.printf("%-11s | ", "Phone");
            System.out.printf("%-6s |\n", "Status");

            for (int i = 0;i < phoneNum.size();i++) {

                System.out.printf("| %-2s | ", (i+1));
                System.out.printf("%-20s | ", fName.get(i) + " " + lName.get(i));
                System.out.printf("%-11s | ", phoneNum.get(i));
                System.out.printf("%-6s |\n", status.get(i));

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
                "\\res\\shopData.json")) {

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

    }

    public static void flagCustomer() {

        // This function allows admin to flag a customer as a positive case of CoViD-19.
        // With this function deployed, the flagShop() function automatically deploys as well.

    }

    public static void flagShop() {

        // This function is an extension of the function flagCustomer(). NOTE : DO NOT ATTEMPT TO RUN ON ITS OWN!
        // This function allows admin to flag a shop as a zone of CoViD-19 positive cases, thus automatically -
        // - flagging anyone who are considered close contacts (within the regulations set in customerData class) as -
        // - CoViD-19 positive cases.

    }

    public static void addVisit() {

        // This function allows admin to deploy an additional feature in which 30 random visits are added into the -
        // - master visit history. These visits are generated using the existing list of customers and shops from -
        // - application data files (json).

    }

    // Test main module only
    public static void main(String[] args) {
        viewCustomer();
        System.out.println("\n");
        viewShop();
        System.out.println("\n");
        viewMaster();
        System.out.println("\n");
        viewHistory();
        System.out.println("\n");
        loginRead();
        System.out.println("\n");
    }

}