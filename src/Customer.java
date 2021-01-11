import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;

public class Customer { // Member 1 & 2

    // private members
    private String custFName, custLName, custPhone, custPass, custStatus;

    // public members
    public static final String defStatus = "Normal";
    public static ArrayList<Customer> CustomerList = new ArrayList<>();
    public static int check_InTotal = 0;

    // constructor
    public Customer(String custFName, String custLName, String custPhone, String custPass) {

        this.custFName = custFName;
        this.custLName = custLName;
        this.custPhone = custPhone;
        this.custPass = custPass;
        this.custStatus = defStatus;

    }

    public Customer(String custFName, String custLName, String custPhone, String custPass, String custStatus) {

        this.custFName = custFName;
        this.custLName = custLName;
        this.custPhone = custPhone;
        this.custPass = custPass;
        this.custStatus = custStatus;

    }

    // methods

    // getters
    public String getFName() { return this.custFName; }

    public String getLName() {
        return this.custLName;
    }

    public String getPhoneNum() {
        return this.custPhone;
    }

    public String getPass() {
        return this.custPass;
    }

    public String getStatus() {
        return this.custStatus;
    }

    // setters
    public void setStatus(String custStatus) {
        this.custStatus = custStatus;
    }

    // list and JSON methods
    public String toString(int index) {

        String fullName = custFName + " " + custLName;

        return String.format("%-2s %-15s  %-11s  %-6s \n", index, fullName, custPhone, custStatus);

    }

    public String toString() {

        String fullName = custFName + " " + custLName;

        return String.format("%-15s  %-11s  %-6s \n", fullName, custPhone, custStatus);

    }

    // accompanying methods
    public static void initializeCustomerList() { // At the start of program, the CustomerList is initialized from JSON file

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\customerData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject customerData = (JSONObject) obj;

            JSONArray fNameArr = (JSONArray) customerData.get("fName");
            JSONArray lNameArr = (JSONArray) customerData.get("lName");
            JSONArray phoneNumArr = (JSONArray) customerData.get("phoneNum");
            JSONArray statusArr = (JSONArray)  customerData.get("status");

            try (FileReader reader2 = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\loginData.json")) {

                Object obj2 = jsonParser.parse(reader2);
                JSONObject login = (JSONObject) obj2;

                Object customerObj = login.get("customer");
                JSONObject loginData = (JSONObject) customerObj;

                JSONArray loginPhoneArr = (JSONArray) loginData.get("phoneNum");
                JSONArray loginPassArr = (JSONArray) loginData.get("pass");

                for(int i = 0; i < fNameArr.size(); i++) {

                    String appendFName = "" + fNameArr.get(i);
                    String appendLName = "" + lNameArr.get(i);
                    String appendPhoneNum = "" + phoneNumArr.get(i);
                    String appendPass = "";
                    String appendStatus = "" + statusArr.get(i);

                    for (int j = 0; j < loginPhoneArr.size(); j++) { // To match phone number to password

                        if (loginPhoneArr.get(i).equals(appendPhoneNum)) {

                            appendPass = "" + loginPassArr.get(i);
                            break;

                        }

                    }

                    CustomerList.add(new Customer(appendFName, appendLName, appendPhoneNum, appendPass, appendStatus));

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

    public static void appendToCustomerList(String fName, String lName, String phoneNum, String pass) {

        // add Customer() object to CustomerList

        String status = Customer.defStatus;

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\customerData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject customerData = (JSONObject) obj;

            JSONArray fNameArr = (JSONArray) customerData.get("fName");
            JSONArray lNameArr = (JSONArray) customerData.get("lName");
            JSONArray phoneNumArr = (JSONArray) customerData.get("phoneNum");
            JSONArray statusArr = (JSONArray)  customerData.get("status");

            //Add details to customerData list
            fNameArr.add(fName);
            lNameArr.add(lName);
            phoneNumArr.add(phoneNum);
            statusArr.add(status);

            CustomerList.add(new Customer(fName, lName, phoneNum, pass));

            // Compile the updated lists into organised JSON format
            customerData.put("lName", lNameArr);
            customerData.put("fName", fNameArr);
            customerData.put("phoneNum", phoneNumArr);
            customerData.put("status", statusArr);

            //Write JSON file
            try (FileWriter fileWrite = new FileWriter("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\customerData.json")) {

                fileWrite.write(customerData.toJSONString());
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

        try (FileReader reader2 = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\loginData.json")) {

            Object obj2 = jsonParser.parse(reader2);

            JSONObject login = (JSONObject) obj2;

            Object adminObj = login.get("admin");
            JSONObject adminData = (JSONObject) adminObj;

            JSONArray adminIDArr = (JSONArray) adminData.get("ID");
            JSONArray adminPassArr = (JSONArray) adminData.get("pass");

            Object customerObj = login.get("customer");
            JSONObject loginData = (JSONObject) customerObj;

            JSONArray loginPhoneArr = (JSONArray) loginData.get("phoneNum");
            JSONArray loginPassArr = (JSONArray) loginData.get("pass");

            //Add details to loginData list
            loginPhoneArr.add(phoneNum);
            loginPassArr.add(pass);

            // Compile the updated lists into organised JSON format
            adminData.put("pass", adminPassArr);
            adminData.put("ID", adminIDArr);

            loginData.put("pass", loginPassArr);
            loginData.put("phoneNum", loginPhoneArr);

            login.put("admin",adminData);
            login.put("customer",loginData);

            //Write JSON file
            try (FileWriter fileWrite2 = new FileWriter("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\loginData.json")) {

                fileWrite2.write(login.toJSONString());
                fileWrite2.flush();

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

    public static void updateCustomerList() {

        // updates CustomerList after check-in to update details in JSON files

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\customerData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject customerData = (JSONObject) obj;

            customerData.remove("fName");
            customerData.remove("lName");
            customerData.remove("phoneNum");
            customerData.remove("status");

            try (FileReader reader2 = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\loginData.json")) {

                Object obj2 = jsonParser.parse(reader2);

                JSONObject login = (JSONObject) obj2;

                Object adminObj = login.get("admin");
                JSONObject adminData = (JSONObject) adminObj;

                JSONArray adminIDArr = (JSONArray) adminData.get("ID");
                JSONArray adminPassArr = (JSONArray) adminData.get("pass");

                login.remove("customer");

                JSONObject customerLogin = new JSONObject();
                JSONArray loginPhoneArr = new JSONArray();
                JSONArray loginPassArr = new JSONArray();

                for(int i = 0; i < CustomerList.size(); i++) {

                    String updatePhoneNum = "" + CustomerList.get(i).getPhoneNum();
                    String updatePass = "" + CustomerList.get(i).getPass();
                    loginPhoneArr.add(updatePhoneNum);
                    loginPassArr.add(updatePass);

                }

                customerLogin.put("pass", loginPassArr);
                customerLogin.put("phoneNum", loginPhoneArr);
                adminData.put("pass", adminPassArr);
                adminData.put("ID", adminIDArr);

                login.put("admin", adminData);
                login.put("customer", customerLogin);

                try (FileWriter fileWrite = new FileWriter("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\loginData.json")) {

                    fileWrite.write(login.toJSONString());
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

            JSONArray fNameArr = new JSONArray();
            JSONArray lNameArr = new JSONArray();
            JSONArray phoneNumArr = new JSONArray();
            JSONArray statusArr = new JSONArray();

            for(int i = 0; i < CustomerList.size(); i++) {

                String updateFName = "" + CustomerList.get(i).getFName();
                String updateLName = "" + CustomerList.get(i).getLName();
                String updatePhoneNum = "" + CustomerList.get(i).getPhoneNum();
                String updateStatus = "" + CustomerList.get(i).getStatus();

                fNameArr.add(updateFName);
                lNameArr.add(updateLName);
                phoneNumArr.add(updatePhoneNum);
                statusArr.add(updateStatus);

            }

            customerData.put("lName",lNameArr);
            customerData.put("fName",fNameArr);
            customerData.put("phoneNum",phoneNumArr);
            customerData.put("status",statusArr);

            try (FileWriter fileWrite2 = new FileWriter("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\customerData.json")) {

                fileWrite2.write(customerData.toJSONString());
                fileWrite2.flush();

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

    public static ArrayList<Integer>displayCustomerListToFlag()  { // For flagCustomer() in Admin

        System.out.printf("\n%-2s %-15s  %-11s  %-6s \n\n", "No", "Name", "Phone", "Status");

        int outputIndex = 0;
        ArrayList<Integer> flagIndex = new ArrayList<>();

        for(int i=0; i < CustomerList.size(); i++) {

            if (CustomerList.get(i).custStatus.equals("Case")) {
                continue;
            }
            else if (CustomerList.get(i).custStatus.equals("Close")) {
                continue;
            }
            else {
                outputIndex++;
                String output = CustomerList.get(i).toString(outputIndex);
                flagIndex.add(i);
                System.out.println(output);
            }

        }

        return flagIndex;

    }

}

// program feature classes with methods
class Register { // Member 2

    public static void custRegister() {
        registerFrame frame = new registerFrame();
    }

    static class registerFrame extends JFrame implements ActionListener{

        JLabel firstName,lastName,phoneNumber,pass;
        JTextField t1,t2,t3,t4;
        JButton submit;
        JButton reset;
        JCheckBox confirm;
        JLabel msg;

        registerFrame() {

            setTitle("Registration Menu");
            setSize(700, 400);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            Container c=getContentPane();
            c.setLayout(null);

            // name
            firstName=new JLabel("First Name :");
            firstName.setFont(new Font("Tahoma",Font.PLAIN,20));
            firstName.setBounds(20,50,150,20);
            c.add(firstName);

            t1=new JTextField();
            t1.setBounds(200,50,150,20);
            c.add(t1);

            lastName=new JLabel("Last Name :");
            lastName.setFont(new Font("Tahoma",Font.PLAIN,20));
            lastName.setBounds(400,50,150,20);
            c.add(lastName);

            t2=new JTextField();
            t2.setBounds(520,50,150,20);
            c.add(t2);

            // number
            phoneNumber = new JLabel("Phone\r\n Number :");
            phoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
            phoneNumber.setBounds(20, 100, 150, 20);
            c.add(phoneNumber);

            t3=new JTextField();
            t3.setBounds(200,100,150,20);
            c.add(t3);

            // pass
            pass = new JLabel("Password :");
            pass.setFont(new Font("Tahoma", Font.PLAIN, 20));
            pass.setBounds(400, 100, 150, 20);
            c.add(pass);

            t4 = new JPasswordField();
            t4.setBounds(520, 100, 150, 20);
            c.add(t4);

            // submit button
            submit=new JButton("Submit");
            submit.setBounds(350,260,80,40);
            submit.addActionListener(this);
            c.add(submit);

            // reset button
            reset=new JButton("Reset");
            reset.setBounds(200,260,80,40);
            reset.addActionListener(this);
            c.add(reset);

            // confirmation
            confirm = new JCheckBox("Please make sure all details inserted are correct.");
            confirm.setFont(new Font("Arial", Font.PLAIN, 15));
            confirm.setSize(400, 20);
            confirm.setLocation(160, 200);
            c.add(confirm);

            // message
            msg=new JLabel("");
            msg.setBounds(280,260,250,20);
            c.add(msg);

            setVisible(true);

        }

        public void actionPerformed(ActionEvent e) {

            String fName = t1.getText();
            String lName = t2.getText();
            String phoneNum = t3.getText();
            String pass = t4.getText();
            String status = Customer.defStatus;
            int len = phoneNum.length();

            String message = "Welcome, " + fName + "!\n" + "Your account is successfully created!";

            if (e.getSource() == submit) {

                if (confirm.isSelected()) {

                    if (len != 10 && len != 11) {
                        JOptionPane.showMessageDialog(submit, "Enter a valid mobile number!");
                    } else {
                        Customer.appendToCustomerList(fName, lName, phoneNum, pass);
                        JOptionPane.showMessageDialog(submit, message);
                        menu.registerStat = true;
                        dispose();
                        menu.loginMenu();
                    }

                }

                else {

                    JOptionPane.showMessageDialog(submit, "Please ensure all details have been entered " +
                            "properly and click the checkbox before submitting.");

                }

            } else if (e.getSource() == reset) {

                String def = "";
                t1.setText(def);
                t2.setText(def);
                t3.setText(def);
                t4.setText(def);
                confirm.setSelected(false);

            }

        }

    }

}

class Login { // Member 1 & 2

    public static void custLogin() {
        loginFrame frame = new loginFrame();
    }

    static class loginFrame extends JFrame implements ActionListener {

        JLabel loginPhone, loginPass;
        JTextField t1;
        JPasswordField t2;
        JButton login,reset,show,hide;
        JLabel msg;

        loginFrame() { // Shu

            setTitle("Login Menu");
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            Container e = getContentPane();
            e.setLayout(null);

            loginPhone = new JLabel("Mobile Number :");
            loginPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
            loginPhone.setBounds(20, 50, 150, 20);
            e.add(loginPhone);

            t1 = new JTextField();
            t1.setBounds(200, 50, 150, 20);
            e.add(t1);

            loginPass = new JLabel("Password :");
            loginPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
            loginPass.setBounds(20, 100, 150, 20);
            e.add(loginPass);

            t2 = new JPasswordField();
            t2.setBounds(200, 100, 150, 20);
            e.add(t2);

            login=new JButton("Login");
            login.setBounds(220,150,80,40);
            login.addActionListener(this);
            e.add(login);

            reset=new JButton("Reset");
            reset.setBounds(90,150,80,40);
            reset.addActionListener(this);
            e.add(reset);

            show=new JButton(new ImageIcon("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\gui\\show.png")); // add dir
            show.setBounds(220,120,30,20);
            show.addActionListener(this);
            e.add(show);

            hide=new JButton(new ImageIcon("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\gui\\hide.png"));
            hide.setBounds(265,120,30,20);
            hide.addActionListener(this);
            e.add(hide);

            msg=new JLabel("");
            msg.setBounds(280,260,250,20);
            e.add(msg);

            setVisible(true);

        }

        public void actionPerformed(ActionEvent e) { // Arif

            String phone = t1.getText();
            String pass = new String(t2.getPassword());
            int len = phone.length();

            // Reading JSON file - To check with login data

            JSONParser jsonParser = new JSONParser();

            // Parsing the contents of the JSON file

            try (FileReader reader = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\loginData.json")) {

                Object obj = jsonParser.parse(reader);
                JSONObject loginData = (JSONObject) obj;

                Object customer = loginData.get("customer");
                JSONObject customerData = (JSONObject) customer;

                JSONArray phoneNum = (JSONArray) customerData.get("phoneNum");
                JSONArray customerPass = (JSONArray) customerData.get("pass");

                boolean found = false;
                int foundIndex = 0;

                for (int i = 0; i < phoneNum.size(); i++) { // To check if phone number entered is registered

                    if (phoneNum.get(i).equals(phone)) {
                        found = true;
                        foundIndex = i;
                    }

                }

                try (FileReader reader2 = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\customerData.json")) {

                    Object obj2 = jsonParser.parse(reader2);

                    JSONObject cCustomer = (JSONObject) obj2;

                    JSONArray fName = (JSONArray) cCustomer.get("fName");
                    JSONArray cPhoneNum = (JSONArray) cCustomer.get("phoneNum");

                    int foundIndex2 = 0;

                    for (int i = 0; i < cPhoneNum.size(); i++) { // To get index of customer who logged in

                        if (cPhoneNum.get(i).equals(phoneNum.get(foundIndex))) {
                            foundIndex2 = i;
                        }

                    }

                    String message = "Welcome, " + fName.get(foundIndex2) + "!";

                    if (e.getSource() == login) {

                        if (len != 10 && len !=11) {
                            JOptionPane.showMessageDialog(login, "Enter a valid mobile number!");
                        }

                        else if (!found) {
                            JOptionPane.showMessageDialog(login, "Phone number not registered!");
                        }

                        else {

                            if (pass.equals(customerPass.get(foundIndex))) {
                                JOptionPane.showMessageDialog(login, message);
                                menu.loggedInPhone = "" + phone;
                                dispose();
                                menu.userMenu();
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

class Check_In { // Member 1 & 2

    public static void custCheck_In(String loginPhone) {

        check_InFrame frame = new check_InFrame();

    }

    static class check_InFrame extends JFrame implements ActionListener {

        public static String check_InShop;
        public static String check_InDate;
        public static String check_InTime;
        public static ArrayList<String> lastCaseArr;
        public static String loginPhone;
        JLabel Title;
        JButton Tesco, Giant, Econsave, Jaya;
        JLabel msg;

        check_InFrame() {

            setTitle("Check-In Shop");
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            Container e = getContentPane();
            e.setLayout(null);

            Title = new JLabel("PLEASE SELECT A SHOP"); // fix
            Title.setFont(new Font("Tahoma", Font.PLAIN, 20));
            Title.setBounds(80, 30, 300, 20);
            e.add(Title);

            Tesco = new JButton(new ImageIcon("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\gui\\tesco.jpg"));
            Tesco.setBounds(50, 80, 140, 35);
            Tesco.addActionListener(this);
            e.add(Tesco);

            Giant = new JButton(new ImageIcon("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\gui\\Giant.jpg"));
            Giant.setBounds(200, 80, 140, 35);
            Giant.addActionListener(this);
            e.add(Giant);

            Econsave = new JButton(new ImageIcon("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\gui\\Econsave.png"));
            Econsave.setBounds(50, 150, 140, 35);
            Econsave.addActionListener(this);
            e.add(Econsave);

            Jaya = new JButton(new ImageIcon("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\gui\\jaya.jpg"));
            Jaya.setBounds(200, 150, 140, 35);
            Jaya.addActionListener(this);
            e.add(Jaya);

            msg = new JLabel("");
            msg.setBounds(160, 160, 250, 20);
            e.add(msg);

            setVisible(true);

        }

        public void actionPerformed(ActionEvent e) { // fix

            LocalDateTime timeVar = LocalDateTime.now();
            DateTimeFormatter dateVar = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = timeVar.format(dateVar);

            String[] parts = formattedDate.split(" ");
            check_InDate = "" + parts[0];
            check_InTime = "" + parts[1];
            loginPhone = "" + menu.loggedInPhone;
            String status = "";
            String shopStatus = "";

            if (e.getSource() == Tesco) {
                JOptionPane.showMessageDialog(Tesco,
                        "Checked-In Tesco at :" + "\n" + formattedDate);
                check_InShop = "" + "Tesco";

                Admin.appendToMasterHistory(loginPhone, check_InDate, check_InTime, check_InShop);
                autoFlag.autoFlag(loginPhone, check_InDate, check_InTime, check_InShop);

                dispose();
                int opt = options.afterChoice();

                if (opt == 9) {
                    Customer.check_InTotal++;
                    menu.userMenu();
                } else {
                    Customer.check_InTotal++;
                    menu.loggedInPhone = "";
                    menu.startMenu();
                }

            }

            else if (e.getSource() == Giant) {
                JOptionPane.showMessageDialog(Giant,
                        "Checked-In Giant at :" + "\n" + formattedDate);
                check_InShop = "" + "Giant";

                Admin.appendToMasterHistory(loginPhone, check_InDate, check_InTime, check_InShop);
                autoFlag.autoFlag(loginPhone, check_InDate, check_InTime, check_InShop);

                dispose();
                int opt = options.afterChoice();

                if (opt == 9) {
                    Customer.check_InTotal++;
                    menu.userMenu();
                } else {
                    Customer.check_InTotal++;
                    menu.loggedInPhone = "";
                    menu.startMenu();
                }

            } else if (e.getSource() == Econsave) {
                JOptionPane.showMessageDialog(Econsave,
                        "Checked-In Econsave at :" + "\n" + formattedDate);
                check_InShop = "" + "Econsave";

                Admin.appendToMasterHistory(loginPhone, check_InDate, check_InTime, check_InShop);
                autoFlag.autoFlag(loginPhone, check_InDate, check_InTime, check_InShop);

                dispose();
                int opt = options.afterChoice();

                if (opt == 9) {
                    Customer.check_InTotal++;
                    menu.userMenu();
                } else {
                    Customer.check_InTotal++;
                    menu.loggedInPhone = "";
                    menu.startMenu();
                }

            } else if (e.getSource() == Jaya) {
                JOptionPane.showMessageDialog(Jaya,
                        "Checked-In Jaya Grocer at :" + "\n" + formattedDate);
                check_InShop = "" + "Jaya Grocer";

                Admin.appendToMasterHistory(loginPhone, check_InDate, check_InTime, check_InShop);
                autoFlag.autoFlag(loginPhone, check_InDate, check_InTime, check_InShop);

                dispose();
                int opt = options.afterChoice();

                if (opt == 9) {
                    Customer.check_InTotal++;
                    menu.userMenu();
                } else {
                    Customer.check_InTotal++;
                    menu.loggedInPhone = "";
                    menu.startMenu();
                }

            }

        }

    }

}

class ViewHistory { // Member 1

    public static String getName(String loginPhone) {

        String checkPhone = "";
        String name = "";

        for (int i = 0; i < Customer.CustomerList.size(); i++) {

            checkPhone = Customer.CustomerList.get(i).getPhoneNum();
            if (checkPhone.equals(loginPhone)) {

                name = Customer.CustomerList.get(i).getFName();

            }

        }

        return name;

    }

    public static void viewHistory(String loginPhone) { // temp

        String checkName = getName(loginPhone);

        // Reading JSON file \\

        JSONParser jsonParser = new JSONParser();

        // Parsing the contents of the JSON file

        try (FileReader reader = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject customerVisit = (JSONObject) obj;

            JSONArray date = (JSONArray) customerVisit.get("date");
            JSONArray time = (JSONArray) customerVisit.get("time");
            JSONArray name = (JSONArray) customerVisit.get("customerName");
            JSONArray shop = (JSONArray) customerVisit.get("shop");

            System.out.printf("\n%-2s ", "No");
            System.out.printf("%-10s  ", "Date");
            System.out.printf("%-8s  ", "Time");
            System.out.printf("%-10s \n\n", "Shop");

            int taggedIndex = 0;

            for (int i = 0; i < date.size(); i++) {

                if (name.get(i).equals(checkName)) {

                    taggedIndex++;
                    System.out.printf("%-2s ", (taggedIndex));
                    System.out.printf("%-10s  ", date.get(i));
                    System.out.printf("%-8s  ", time.get(i));
                    System.out.printf("%-10s \n\n", shop.get(i));

                }

            }

            int opt = options.afterChoice();

            if(opt == 9) {
                menu.userMenu();
            } else {
                menu.loggedInPhone = "";
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

}

class CheckStatus { // Member 1

    public static String getStatus_and_Name(String loginPhone) {

        String checkPhone = "";
        String status = "";
        String name = "";

        for(int i=0; i < Customer.CustomerList.size(); i++) {

            checkPhone = Customer.CustomerList.get(i).getPhoneNum();
            if (checkPhone.equals(loginPhone)) {

                status = Customer.CustomerList.get(i).getStatus();
                name = Customer.CustomerList.get(i).getFName();

            }

        }

        return status + " " + name;

    }

    public static void checkStatus(String loginPhone) {

        String checked = getStatus_and_Name(loginPhone);
        String[] parts = checked.split(" ");
        String status = parts[0];
        String name = parts[1];

        System.out.println("\nHi " + name +  " !\n" + "Your status is " + status + ".\n");

        int opt = options.afterChoice();

        if(opt == 9) {
            menu.userMenu();
        } else {
            menu.loggedInPhone = "";
            menu.startMenu();
        }

    }

}

class Case { // Member 1

    // private members
    private String date, time, shop;

    // public members
    public static ArrayList<Case> CaseList = new ArrayList<>();

    // constructor
    Case(String date, String time, String shop) {

        this.date = date;
        this.time = time;
        this.shop = shop;

    }

    // methods

    // getters

    public String getDate() { return this.date;}
    public String getTime() { return this.time;}
    public String getShop() { return this.shop;}

    public static void initializeCaseVisit() { // Whenever required, the CaseList is initialized from JSON file

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject logs = (JSONObject) obj;

            Object caseLogs = logs.get("caseLog");
            JSONObject caseLog = (JSONObject) caseLogs;

            JSONArray caseDateArr = (JSONArray) caseLog.get("caseDateLog");
            JSONArray caseTimeArr = (JSONArray) caseLog.get("caseTimeLog");
            JSONArray caseShopArr = (JSONArray) caseLog.get("caseShopLog");

            for(int i = 0; i < caseDateArr.size(); i++) {

                String appendDate = "" + caseDateArr.get(i);
                String appendTime = "" + caseTimeArr.get(i);
                String appendShop = "" + caseShopArr.get(i);

                CaseList.add(new Case(appendDate, appendTime, appendShop));

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

class autoFlag { // Member 1

    public static String getStatus(String loginPhone) {

        // get status of customer who checked in

        String checkPhone = "";
        String status = "";

        for(int i=0; i < Customer.CustomerList.size(); i++) {

            checkPhone = Customer.CustomerList.get(i).getPhoneNum();
            if (checkPhone.equals(loginPhone)) {

                status = Customer.CustomerList.get(i).getStatus();

            }

        }

        return status;

    }

    public static long findDifference(String beginDate, String endDate) {

        // finds the difference in time between two dateTimes

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

    public static String checkIfCase(String loginPhone, String date, String time, String shop) { // right after check-in

        // checks if customer who checked in is of a "Case" status

        String status = getStatus(loginPhone);

        if (status.equals("Case")) {

            addLastCaseVisit(date, time, shop);

        }

        return status;

    }

    public static String checkIfShopCase(String shopName) { // for normal customer check-in

        // checks if shop which customer checked into is of a "Case" status

        String checkName = "";
        String shopStatus = "";

        for(int i=0; i < Shop.ShopList.size(); i++) {

            checkName = Shop.ShopList.get(i).getShopName();
            if (checkName.equals(shopName)) {

                shopStatus = Shop.ShopList.get(i).getShopStatus();

            }

        }

        return shopStatus;

    }

    public static void addLastCaseVisit(String date, String time, String shop) {

        // logs any check-in of a customer of a "Case" status

        Check_In.check_InFrame.lastCaseArr = new ArrayList<>();
        String lastCaseLog = "" + date + " " + time + " " + shop;
        Check_In.check_InFrame.lastCaseArr.add(lastCaseLog);

        String[] caseSplit = lastCaseLog.split(" ");
        String caseDate = caseSplit[0];
        String caseTime = caseSplit[1];
        String caseShop = caseSplit[2];

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject visit = (JSONObject) obj;

            JSONObject caseLog = new JSONObject();
            JSONArray caseDateArr = new JSONArray();
            JSONArray caseTimeArr = new JSONArray();
            JSONArray caseShopArr = new JSONArray();

            caseDateArr.add(caseDate);
            caseTimeArr.add(caseTime);
            caseShopArr.add(caseShop);

            caseLog.put("caseDateLog", caseDateArr);
            caseLog.put("caseTimeLog", caseTimeArr);
            caseLog.put("caseShopLog", caseShopArr);

            visit.put("caseLog", caseLog);

            try (FileWriter fileWrite = new FileWriter("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\visitHistory.json")) {

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

    public static boolean checkLastFlag(String shop, String date, String time) {

        // checks if there were any visit by "Case" customer logged in the JSON file

        Check_In.check_InFrame.lastCaseArr = new ArrayList<>();
        boolean flag = false;
        long diff = 0;
        String lastFlagTime = "";
        String lastFlagShop = "";

        for(int i = 0; i < Case.CaseList.size(); i++) {

            String dateGet = Case.CaseList.get(i).getDate();
            String timeGet = Case.CaseList.get(i).getTime();
            String shopGet = Case.CaseList.get(i).getShop();
            String lastCaseLog = "" + dateGet + " " + timeGet + " " + shopGet;
            Check_In.check_InFrame.lastCaseArr.add(lastCaseLog);

        }

        if (!Check_In.check_InFrame.lastCaseArr.isEmpty()) {

            for(int i = 0; i < Check_In.check_InFrame.lastCaseArr.size(); i++) {

                String lastCase = Check_In.check_InFrame.lastCaseArr.get(i);
                String[] splitString = lastCase.split(" ");
                lastFlagTime = "" + splitString[0];
                lastFlagShop = "" + splitString[1];

                if(shop.equals(lastFlagShop)) {

                    String fullDateTime = date + " " + time;

                    diff = findDifference(fullDateTime, lastFlagTime);

                    if (diff >= 0 && diff <= 60) {
                        flag = true;
                        break;
                    }

                }

            }

        }
            return flag;
    }

    public static void autoFlag(String phone, String date, String time, String shop) {
        // all parameters are attribute belonging to person who currently checked in

        // automatically flags all close contacts when a customer checks in according to conditionals

        String checkShopName = "";
        String customerStat = "";
        String shopStat = "";
        boolean flag = false;

        customerStat = checkIfCase(phone, date, time, shop);
        shopStat = checkIfShopCase(shop);

        if (customerStat.equals("Case")) { // for case check-in

            if (shopStat.equals("Case")) {

                flagCloseContacts(phone, date, time, shop); // flag close contacts without flagging self

            } else { // since shop is normal, set status to case

                for(int i=0; i < Shop.ShopList.size(); i++) {

                    checkShopName = Shop.ShopList.get(i).getShopName();
                    if (checkShopName.equals(shop)) {

                        Shop.ShopList.get(i).setStatus("Case");

                    }

                }

                // flag close contacts without flagging self
                flagCloseContacts(phone, date, time, shop);
            }

        } else { // for normal check-in

            if (shopStat.equals("Case")) {

                flag = checkLastFlag(shop, date, time); // check last flag
                if(flag) {

                    String checkPhone = "";

                    for(int i=0; i < Customer.CustomerList.size(); i++) {

                        checkPhone = Customer.CustomerList.get(i).getPhoneNum();
                        if (checkPhone.equals(phone)) {

                            Customer.CustomerList.get(i).setStatus("Close");

                        }

                    }

                }

            }

        }

        Customer.updateCustomerList();
        Shop.updateShopList();

    }

    public static void autoFlagFromCustomer(String phone, String date, String time, ArrayList<String> shop) {
        // all parameters are attribute belonging to person who currently checked in

        // automatically flags all close contacts when a admin flags a customer according to conditionals

        String currentShop = "";
        String checkShopName = "";
        String customerStat = "";
        String shopStat = "";
        boolean flag = false;

        if(shop.isEmpty()) {
            return;
        } else {

            for(int i = 0; i < shop.size(); i++) {

                currentShop = "" + shop.get(i);

                customerStat = checkIfCase(phone, date, time, currentShop);
                shopStat = checkIfShopCase(currentShop);

                if (customerStat.equals("Case")) { // for case check-in

                    if (shopStat.equals("Case")) {

                        flagCloseContacts(phone, date, time, currentShop); // flag close contacts without flagging self

                    } else { // since shop is normal, set status to case

                        for(int j=0; j < Shop.ShopList.size(); j++) {

                            checkShopName = Shop.ShopList.get(j).getShopName();
                            if (checkShopName.equals(currentShop)) {

                                Shop.ShopList.get(j).setStatus("Case");

                            }

                        }

                        // flag close contacts without flagging self
                        flagCloseContacts(phone, date, time, currentShop);
                    }

                } else { // for normal check-in

                    if (shopStat.equals("Case")) {

                        flag = checkLastFlag(currentShop, date, time); // check last flag
                        if(flag) {

                            String checkPhone = "";

                            for(int k=0; k < Customer.CustomerList.size(); k++) {

                                checkPhone = Customer.CustomerList.get(k).getPhoneNum();
                                if (checkPhone.equals(phone)) {

                                    Customer.CustomerList.get(k).setStatus("Close");

                                }

                            }

                        }

                    }

                }

                Customer.updateCustomerList();
                Shop.updateShopList();

            }

        }

    } // works

    public static void flagCloseContacts(String phone, String date, String time, String shop) {

        // This method automatically flags a shop as a zone of CoViD-19 positive cases, thus automatically -
        // - flagging anyone who are considered close contacts (within the timeRange of the case check-in) as -
        // - CoViD-19 close contacts.

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

        try (FileReader reader = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject visit = (JSONObject) obj;

            JSONArray dateArr = (JSONArray) visit.get("date");
            JSONArray timeArr = (JSONArray) visit.get("time");
            JSONArray customerArr = (JSONArray) visit.get("customerName");
            JSONArray shopArr = (JSONArray) visit.get("shop");

            String checkDateTime = "";

            for (int i = 0;i < shopArr.size();i++) {

                if (shop.equals(shopArr.get(i))) {

                    for (int j = 0; j < dateArr.size(); j++) {

                        checkDateTime = dateArr.get(i) + " " + timeArr.get(i);
                        if (fullDateTime.equals(checkDateTime))
                            continue;
                        dateTimeIndex.add(checkDateTime);

                    }

                } else
                    continue;

            }

            if(dateTimeIndex.isEmpty()) { // no check-in within the particular shop
                return;
            } else {

                for (int i = 0;i < dateTimeIndex.size();i++) {

                    minsDiff = findDifference(fullDateTime, dateTimeIndex.get(i));
                    if (minsDiff >= 0 && minsDiff <= 60) {
                        flagTimeIndex.add(dateTimeIndex.get(i));
                    } else
                        continue;

                }

            }

            if(flagTimeIndex.isEmpty()) { // no check-in within close contact time range
                return;
            } else {

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

            }

            for (int i = 0; i < flaggedCustomers.size(); i++) {

                for (int j = 0; j < Customer.CustomerList.size(); j++) {

                    if(flaggedCustomers.get(i).equals(Customer.CustomerList.get(j).getFName())) {

                        if (Customer.CustomerList.get(j).getPhoneNum().equals(phone)) {

                            Customer.CustomerList.get(j).setStatus("Case");
                            continue;

                        }
                        Customer.CustomerList.get(j).setStatus("Close");

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

    }

}