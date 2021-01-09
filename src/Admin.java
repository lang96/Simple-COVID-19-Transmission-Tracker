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

public class Admin {

    // methods

    // program feature methods
    public static void viewMaster() {

        // This function allows admin to view the master visit history of the entire system.

        List<Visit.DateItem> sortedHistory;

        Visit.initializeVisitList();
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

            System.out.printf("\n%-2s ", "No");
            System.out.printf("%-10s  ", "Name");
            System.out.printf("%-11s  ", "Phone");
            System.out.printf("%-15s  ", "Manager");
            System.out.printf("%-6s \n\n", "Status");

            for (int i = 0;i < phoneNum.size();i++) {

                System.out.printf("%-2s ", (i+1));
                System.out.printf("%-10s  ", name.get(i));
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

    } // complete

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

        Customer.updateCustomerList();

        //LocalDateTime timeVar = LocalDateTime.now();
        //DateTimeFormatter dateVar = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //String formattedDate = timeVar.format(dateVar);

        int opt = options.afterChoice();

        if(opt == 9) {
            menu.adminMenu();
        } else {
            menu.startMenu();
        }

        // above 1 am and below 11 pm


        // between 11 pm and 1 am


    } // fix

    public static String flagShop(String shop, String date, String time) {

        // This function is an extension of the function flagCustomer().
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
        long minsDiff = 0;


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

                minsDiff = findDifference(fullDateTime, dateTimeIndex.get(i));
                if (minsDiff >= 0 && minsDiff <= 60) {
                    if (fullDateTime.equals(dateTimeIndex.get(i)))
                        continue;
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

    } // incomplete & fix

    public static void add30Visits() {

        // This function allows admin to deploy an additional feature in which 30 random visits are added into the -
        // - master visit history. These visits are generated using the existing list of customers and shops from -
        // - application data files (json).

        for(int i = 0; i < 30; i++) {

            addVisit();

        }

//        int opt = options.afterChoice();
//
//        if(opt == 9) {
//            menu.adminMenu();
//        } else {
//            menu.startMenu();
//        }

    } // complete

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

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject visit = (JSONObject) obj;

            visit.remove("date");
            visit.remove("time");
            visit.remove("customerName");
            visit.remove("shop");

            JSONArray visitDate = new JSONArray();
            JSONArray visitTime = new JSONArray();
            JSONArray visitName = new JSONArray();
            JSONArray visitShop = new JSONArray();

            visit.put("date", visitDate);
            visit.put("time", visitTime);
            visit.put("customerName", visitName);
            visit.put("shop", visitShop);

            try (FileWriter fileWrite = new FileWriter("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                    "\\res\\data\\visitHistory.json")) {

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

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject visit = (JSONObject) obj;

            JSONArray visitDate = (JSONArray) visit.get("date");
            JSONArray visitTime = (JSONArray) visit.get("time");
            JSONArray visitName = (JSONArray) visit.get("customerName");
            JSONArray visitShop = (JSONArray) visit.get("shop");

            try (FileReader reader2 = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                    "\\res\\data\\customerData.json")) {

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

                try (FileWriter fileWrite = new FileWriter("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                        "\\res\\data\\visitHistory.json")) {

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

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\visitHistory.json")) {

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

            try (FileWriter fileWrite = new FileWriter("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                    "\\res\\data\\visitHistory.json")) {

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

    public static String getRandomDate() throws java.text.ParseException {

        long startPoint = new SimpleDateFormat("dd-MMM-yyyy").parse("10-Jan-2021").getTime();
        long endPoint = new SimpleDateFormat("dd-MMM-yyyy").parse("10-Dec-2020").getTime();

        Random randomTime = new Random();
        long timePeriod = endPoint - startPoint;
        long randomTimeStamp = startPoint + (long) (randomTime.nextDouble() * timePeriod);

        String outputTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(randomTimeStamp));

        String[] splitDateTime = outputTimeStamp.split(" ");
        String outputDate = "" + splitDateTime[0];
        String outputTime = "" + splitDateTime[1];

        return outputDate + " " + outputTime;

    }

    public static String getRandomString(ArrayList<String> paramArr) {

        Random r = new Random();
        int randomIndex = r.nextInt(paramArr.size());
        String random = paramArr.get(randomIndex);

        return random;

    }

    public static void main(String[] args) {

        Customer.initializeCustomerList();
        Shop.initializeShopList();
        viewMaster();
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

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\visitHistory.json")) {

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

            show=new JButton(new ImageIcon("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1\\res\\gui\\show.png")); // add dir
            show.setBounds(220,120,30,20);
            show.addActionListener(this);
            e.add(show);

            hide=new JButton(new ImageIcon("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1\\res\\gui\\hide.png"));
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

            try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                    "\\res\\data\\loginData.json")) {

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
