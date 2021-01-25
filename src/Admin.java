import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.List;

// Member 1

public class Admin {

    // methods

    // program feature methods
    public static void viewMaster() {

        // This function allows admin to view the master visit history of the entire system.

        Visit.initializeVisitList();
        List<Visit.DateItem> sortedHistory;
        sortedHistory = Visit.sortVisitHistory();

        String sortedDate;
        String sortedTime;
        String sortedCustomer;
        String sortedShop;

        System.out.printf("\n%-2s ", "No");
        System.out.printf("%-10s  ", "Date");
        System.out.printf("%-8s  ", "Time");
        System.out.printf("%-10s  ", "Customer");
        System.out.printf("%-10s  \n\n", "Shop");

        for(int i = 0; i < sortedHistory.size(); i++) {

            String temp = String.valueOf(sortedHistory.get(i).getDateTime());
            String[] tempList = temp.split(" ");

            sortedDate = "" + tempList[0];
            sortedTime = "" + tempList[1];
            sortedCustomer = "" + tempList[2];
            sortedShop = "" + tempList[3];

            System.out.printf("%-2s ", i+1);
            System.out.printf("%-10s  ", sortedDate);
            System.out.printf("%-8s  ", sortedTime);
            System.out.printf("%-10s  ", sortedCustomer);
            System.out.printf("%-10s  \n\n", sortedShop);

        }

        int opt = options.afterChoice();

        if(opt == 9) {
            menu.adminMenu();
        } else {
            menu.startMenu();
        }


    }

    public static void viewCustomer() {

        // This function allows admin to view the details of all customers registered in the system.
        // Details of customers follows from Customer.java
        // The details of each customerData which are generated from registration are stored in a json file and are -
        // - retrieved in this java module.

        // Reading JSON file \\

        JSONParser jsonParser = new JSONParser();

        // Parsing the contents of the JSON file

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\Desktop\\SourceCode\\res\\data\\customerData.json")) {

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

            int opt = options.afterChoice();

            if(opt == 9) {
                menu.adminMenu();
            } else {
                menu.startMenu();
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

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\Desktop\\SourceCode\\res\\data\\shopData.json")) {

            Object obj = jsonParser.parse(reader);

            JSONObject shop = (JSONObject) obj;

            JSONArray name = (JSONArray) shop.get("name");
            JSONArray phoneNum = (JSONArray) shop.get("phoneNum");
            JSONArray manager = (JSONArray) shop.get("manager");
            JSONArray status = (JSONArray) shop.get("status");

            // Table output

            System.out.printf("\n%-2s ", "No");
            System.out.printf("%-15s ", "Name");
            System.out.printf("%-11s  ", "Phone");
            System.out.printf("%-15s  ", "Manager");
            System.out.printf("%-6s \n\n", "Status");

            for (int i = 0;i < phoneNum.size();i++) {

                System.out.printf("%-2s ", (i+1));
                System.out.printf("%-15s ", name.get(i));
                System.out.printf("%-11s  ", phoneNum.get(i));
                System.out.printf("%-15s  ", manager.get(i));
                System.out.printf("%-6s \n\n", status.get(i));

            }

            int opt = options.afterChoice();

            if(opt == 9) {
                menu.adminMenu();
            } else {
                menu.startMenu();
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

        // timestamp for close contact flagging
        LocalDateTime timeVar = LocalDateTime.now();
        DateTimeFormatter dateVar = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = timeVar.format(dateVar);

        String[] parts = formattedDate.split(" ");
        String flagDate = "" + parts[0];
        String flagTime = "" + parts[1];

        ArrayList<String> shopList = new ArrayList<>();
        String shop = "";
        String phone = "";

        // access customer and retrieve index
        int flagIndex = 0;
        ArrayList<Integer> flaggable = Customer.displayCustomerListToFlag();

        if(flaggable.isEmpty()) {

            System.out.println("\nThere are no customers available to flag since the customers are all either marked as Case or Close.\n");

            int opt = options.afterChoice();

            if(opt == 9) {
                menu.adminMenu();
            } else {
                menu.startMenu();
            }

        } else {

            flagIndex = choice(flaggable, flagIndex);

            // retrieve flagged customer's phone
            phone = "" + Customer.CustomerList.get(flagIndex).getPhoneNum();

            // retrieve shops visited by customer
            Visit.initializeVisitList();

            for(int i = 0; i < Visit.VisitList.size(); i++) {

                if(Customer.CustomerList.get(flagIndex).getFName().equals(Visit.VisitList.get(i).getVisitCustomer())) {
                    shop = "" + Visit.VisitList.get(i).getVisitShop();
                    shopList.add(shop);
                }

            }

            // flag customer
            String newStat = "Case";
            Customer.CustomerList.get(flagIndex).setStatus(newStat);

            // flag close contacts
            autoFlag.autoFlagFromCustomer(phone, flagDate, flagTime,shopList);

            int opt = options.afterChoice();

            if(opt == 9) {
                menu.adminMenu();
            } else {
                menu.startMenu();
            }

        }

    }

    public static void add30Visits() {

        // This function allows admin to deploy an additional feature in which 30 random visits are added into the -
        // - master visit history. These visits are generated using the existing list of customers and shops from -
        // - application data files (json).

        for(int i = 0; i < 30; i++) {

            addVisit();

        }

        int opt = options.afterChoice();

        if(opt == 9) {
            menu.adminMenu();
        } else {
            menu.startMenu();
        }

    }

    // accompanying methods
    public static void addVisit() {

        // get name, shop arrays

        ArrayList<String> nameArr = new ArrayList<>();
        ArrayList<String> shopArr = new ArrayList<>();

        // loop through customers to get customer names
        for (int i = 0; i < Customer.CustomerList.size(); i++) {

            nameArr.add(Customer.CustomerList.get(i).getFName());

        }

        // loop through shops to get shop names
        for (int i = 0; i < Shop.ShopList.size(); i++) {

            shopArr.add(Shop.ShopList.get(i).getShopName());

        }

        // get random element from arrays
        String randomName = getRandomString(nameArr);
        String randomShop = getRandomString(shopArr);

        // get random time
        String dateTime = "";

        try {
            dateTime = getRandomDate();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        String[] splitDateTime = dateTime.split(" ");

        String outputDate = "" + splitDateTime[0];
        String outputTime = "" + splitDateTime[1];

        appendVisit(randomName, outputDate, outputTime, randomShop);

    }

    public static void clearVisitJSON() {

        // Test feature just to clear VisitHistory.json for ease of access

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\Desktop\\SourceCode\\res\\data\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject visit = (JSONObject) obj;

            visit.remove("date");
            visit.remove("time");
            visit.remove("customerName");
            visit.remove("shop");
            visit.remove("caseLog");

            JSONArray visitDate = new JSONArray();
            JSONArray visitTime = new JSONArray();
            JSONArray visitName = new JSONArray();
            JSONArray visitShop = new JSONArray();

            JSONObject caseLog = new JSONObject();
            JSONArray caseDateArr = new JSONArray();
            JSONArray caseTimeArr = new JSONArray();
            JSONArray caseShopArr = new JSONArray();

            caseLog.put("caseDateLog",caseDateArr);
            caseLog.put("caseTimeLog",caseTimeArr);
            caseLog.put("caseShopLog",caseShopArr);

            visit.put("date", visitDate);
            visit.put("time", visitTime);
            visit.put("customerName", visitName);
            visit.put("shop", visitShop);
            visit.put("caseLog", caseLog);

            try (FileWriter fileWrite = new FileWriter("C:\\Users\\clubberlang96\\Desktop\\SourceCode\\res\\data\\visitHistory.json")) {

                fileWrite.write(visit.toJSONString());
                fileWrite.flush();

            } catch (IOException ef) {
                ef.printStackTrace();
            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    public static void appendToMasterHistory(String loginPhone, String check_InDate, String check_InTime, String check_InShop) {

        // add check-in details into visitHistory.json

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\Desktop\\SourceCode\\res\\data\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject visit = (JSONObject) obj;

            JSONArray visitDate = (JSONArray) visit.get("date");
            JSONArray visitTime = (JSONArray) visit.get("time");
            JSONArray visitName = (JSONArray) visit.get("customerName");
            JSONArray visitShop = (JSONArray) visit.get("shop");

            try (FileReader reader2 = new FileReader("C:\\Users\\clubberlang96\\Desktop\\SourceCode\\res\\data\\customerData.json")) {

                Object obj2 = jsonParser.parse(reader2);
                JSONObject customer = (JSONObject) obj2;

                JSONArray phoneNum = (JSONArray) customer.get("phoneNum");
                JSONArray name = (JSONArray) customer.get("fName");

                String appendName = "";
                for(int i = 0; i < phoneNum.size(); i++) {

                    if(loginPhone.equals(phoneNum.get(i))) {
                        appendName = "" + name.get(i);
                        break;
                    }

                }

                visitDate.add(check_InDate);
                visitTime.add(check_InTime);
                visitName.add(appendName);
                visitShop.add(check_InShop);

                visit.put("date", visitDate);
                visit.put("time", visitTime);
                visit.put("customerName", visitName);
                visit.put("shop", visitShop);

                try (FileWriter fileWrite = new FileWriter("C:\\Users\\clubberlang96\\Desktop\\SourceCode\\res\\data\\visitHistory.json")) {

                    fileWrite.write(visit.toJSONString());
                    fileWrite.flush();

                } catch (IOException ef) {
                    ef.printStackTrace();
                }

            } catch (FileNotFoundException f) {
                f.printStackTrace();
            } catch (IOException f) {
                f.printStackTrace();
            } catch (ParseException f) {
                f.printStackTrace();
            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    public static void appendVisit(String name, String check_InDate, String check_InTime, String check_InShop) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\Desktop\\SourceCode\\res\\data\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject visit = (JSONObject) obj;

            JSONArray visitDate = (JSONArray) visit.get("date");
            JSONArray visitTime = (JSONArray) visit.get("time");
            JSONArray visitName = (JSONArray) visit.get("customerName");
            JSONArray visitShop = (JSONArray) visit.get("shop");

            visitDate.add(check_InDate);
            visitTime.add(check_InTime);
            visitName.add(name);
            visitShop.add(check_InShop);

            visit.put("date", visitDate);
            visit.put("time", visitTime);
            visit.put("customerName", visitName);
            visit.put("shop", visitShop);

            try (FileWriter fileWrite = new FileWriter("C:\\Users\\clubberlang96\\Desktop\\SourceCode\\res\\data\\visitHistory.json")) {

                fileWrite.write(visit.toJSONString());
                fileWrite.flush();

            } catch (IOException ef) {
                ef.printStackTrace();
            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    public static int choice(ArrayList<Integer> flagIndex, int index) { // For flagCustomer()

        int opt = -1;
        Scanner s = new Scanner(System.in);
        boolean choice = false;

        do {
            try {

                System.out.println("\nWhich customer would you like to flag?\n");
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

    public static String getRandomDate() throws java.text.ParseException { // For add30Visits()

        LocalDateTime timeVar = LocalDateTime.now();
        DateTimeFormatter dateVar = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        String formattedDate = timeVar.format(dateVar);

        LocalDateTime yesterdayDateTime = timeVar.minusDays(1);
        String formattedYesterday = yesterdayDateTime.format(dateVar);

        long startPoint = new SimpleDateFormat("dd-MMM-yyyy").parse(formattedYesterday).getTime();
        long endPoint = new SimpleDateFormat("dd-MMM-yyyy").parse(formattedDate).getTime();

        Random randomTime = new Random();
        long timePeriod = endPoint - startPoint;
        long randomTimeStamp = startPoint + (long) (randomTime.nextDouble() * timePeriod);

        String outputTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(randomTimeStamp));

        String[] splitDateTime = outputTimeStamp.split(" ");
        String outputDate = "" + splitDateTime[0];
        String outputTime = "" + splitDateTime[1];

        return outputDate + " " + outputTime;

    }

    public static String getRandomString(ArrayList<String> paramArr) { // For add30Visits()

        Random r = new Random();
        int randomIndex = r.nextInt(paramArr.size());
        String random = paramArr.get(randomIndex);

        return random;

    }

}

class Visit {

    // private members
    private String visitDate, visitTime, visitCustomer, visitShop;

    // public members
    public static ArrayList<Visit> VisitList = new ArrayList<>();

    // constructor
    Visit(String visitDate, String visitTime, String visitCustomer, String visitShop) {

        this.visitDate = visitDate;
        this.visitTime = visitTime;
        this.visitCustomer = visitCustomer;
        this.visitShop = visitShop;

    }

    // methods

    // getters
    public String getVisitDate() { return this.visitDate; }

    public String getVisitTime() {
        return this.visitTime;
    }

    public String getVisitCustomer() {
        return this.visitCustomer;
    }

    public String getVisitShop() {
        return this.visitShop;
    }

    // accompanying methods
    public static void initializeVisitList() { // At the start of program, the VisitList is initialized from JSON file

        if (!VisitList.isEmpty()) {
            VisitList.clear();
        }

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\Desktop\\SourceCode\\res\\data\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject visit = (JSONObject) obj;

            JSONArray visitDate = (JSONArray) visit.get("date");
            JSONArray visitTime = (JSONArray) visit.get("time");
            JSONArray visitName = (JSONArray) visit.get("customerName");
            JSONArray visitShop = (JSONArray) visit.get("shop");

            for(int i = 0; i < visitDate.size(); i++) {

                String appendDate = "" + visitDate.get(i);
                String appendTime = "" + visitTime.get(i);
                String appendName = "" + visitName.get(i);
                String appendShop = "" + visitShop.get(i);


                VisitList.add(new Visit(appendDate, appendTime, appendName, appendShop));

            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    public static List<DateItem> sortVisitHistory() {

        String visitLog = "";

        List<DateItem> visitLogArr = new ArrayList<>();

        for(int i = 0; i < VisitList.size(); i++) {

            visitLog = "" + VisitList.get(i).getVisitDate() + " " + VisitList.get(i).getVisitTime() + " " +
                    VisitList.get(i).getVisitCustomer() + " " + VisitList.get(i).getVisitShop();

            visitLogArr.add(new DateItem(visitLog));

        }

        Collections.sort(visitLogArr, new SortByDate());

        return visitLogArr;

    }

    static class DateItem {

        String datetime;

        DateItem(String date) {
            this.datetime = date;
        }

        String getDateTime() {return this.datetime;}

    }

    static class SortByDate implements Comparator<DateItem> {
        @Override
        public int compare(DateItem a, DateItem b) {
            return a.datetime.compareTo(b.datetime);
        }
    }

}

// Member 2

class Admin_Login {

    public static void adminLogin() {
        AdminFrame adminLogin = new AdminFrame();
    }

    static class AdminFrame extends JFrame implements ActionListener {

        JLabel AdminID, AdminPass;
        JTextField t1;
        JPasswordField t2;
        JButton login, reset, show, hide;
        JLabel msg;

        AdminFrame() { // Shu

            setTitle("Admin Menu");
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            Container e = getContentPane();
            e.setLayout(null);

            AdminID = new JLabel("ID :");
            AdminID.setFont(new Font("Tahoma", Font.PLAIN, 20));
            AdminID.setBounds(20, 50, 150, 20);
            e.add(AdminID);

            t1 = new JTextField();
            t1.setBounds(200, 50, 150, 20);
            e.add(t1);

            AdminPass = new JLabel("Password :");
            AdminPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
            AdminPass.setBounds(20, 100, 150, 20);
            e.add(AdminPass);

            t2 = new JPasswordField();
            t2.setBounds(200, 100, 150, 20);
            e.add(t2);

            login = new JButton("Login");
            login.setBounds(220, 150, 80, 40);
            login.addActionListener(this);
            e.add(login);

            reset = new JButton("Reset");
            reset.setBounds(90, 150, 80, 40);
            reset.addActionListener(this);
            e.add(reset);

            show=new JButton(new ImageIcon("C:\\Users\\clubberlang96\\Desktop\\SourceCode\\res\\gui\\show.png")); // add dir
            show.setBounds(220,120,30,20);
            show.addActionListener(this);
            e.add(show);

            hide=new JButton(new ImageIcon("C:\\Users\\clubberlang96\\Desktop\\SourceCode\\res\\gui\\hide.png"));
            hide.setBounds(265,120,30,20);
            hide.addActionListener(this);
            e.add(hide);

            msg = new JLabel("");
            msg.setBounds(280, 260, 250, 20);
            e.add(msg);

            setVisible(true);

        }

        public void actionPerformed(ActionEvent e) { // Arif

            String id = t1.getText();
            String pass = new String(t2.getPassword());

            // Reading JSON file - To check with login data

            JSONParser jsonParser = new JSONParser();

            // Parsing the contents of the JSON file

            try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\Desktop\\SourceCode\\res\\data\\loginData.json")) {

                Object obj = jsonParser.parse(reader);
                JSONObject loginData = (JSONObject) obj;

                Object admin = loginData.get("admin");
                JSONObject adminData = (JSONObject) admin;

                JSONArray ID = (JSONArray) adminData.get("ID");
                JSONArray adminPass = (JSONArray) adminData.get("pass");

                boolean found = false;
                int foundIndex = 0;

                for (int i = 0; i < ID.size(); i++) { // To check if phone number entered is registered

                    if (ID.get(i).equals(id)) {
                        found = true;
                        foundIndex = i;
                    }

                }

                String message = "Welcome, " + ID.get(foundIndex) + "!";

                if (e.getSource() == login) {

                    if (!found) {
                        JOptionPane.showMessageDialog(login, "Invalid ID!");
                    }

                    else {

                        if (pass.equals(adminPass.get(foundIndex))) {
                            JOptionPane.showMessageDialog(login, message);
                            dispose();
                            menu.adminMenu();
                        } else {
                            JOptionPane.showMessageDialog(login, "Incorrect password!");
                        }

                    }

                } else if (e.getSource() == reset) {
                    String def = "";
                    t1.setText(def);
                    t2.setText(def);

                } else if (e.getSource() == show) {
                    t2.setEchoChar((char)0);
                }
                else if (e.getSource() == hide) {
                    t2.setEchoChar('*');
                }

            } catch (FileNotFoundException f) {
                f.printStackTrace();
            } catch (IOException f) {
                f.printStackTrace();
            } catch (ParseException f) {
                f.printStackTrace();
            }

        }

    }

}
