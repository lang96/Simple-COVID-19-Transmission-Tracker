import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class Customer { // Arif

    // private members
    private String custFName, custLName, custPhone, custPass, custStatus;

    // public members
    public static final String defStatus = "Normal";
    public static ArrayList<Customer> CustomerList = new ArrayList<>();

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

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\customerData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject customerData = (JSONObject) obj;

            JSONArray fNameArr = (JSONArray) customerData.get("fName");
            JSONArray lNameArr = (JSONArray) customerData.get("lName");
            JSONArray phoneNumArr = (JSONArray) customerData.get("phoneNum");
            JSONArray statusArr = (JSONArray)  customerData.get("status");

            try (FileReader reader2 = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                    "\\res\\data\\loginData.json")) {

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

                    for (int j = 0; j < loginPhoneArr.size(); j++) { // To check if phone number entered is registered

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

        String status = Customer.defStatus;

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\customerData.json")) {

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
            try (FileWriter fileWrite = new FileWriter("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                    "\\res\\data\\customerData.json")) {

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

        try (FileReader reader2 = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\loginData.json")) {

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
            try (FileWriter fileWrite2 = new FileWriter("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                    "\\res\\data\\loginData.json")) {

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

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\customerData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject customerData = (JSONObject) obj;

            customerData.remove("fName");
            customerData.remove("lName");
            customerData.remove("phoneNum");
            customerData.remove("status");

            try (FileReader reader2 = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                    "\\res\\data\\loginData.json")) {

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

                try (FileWriter fileWrite = new FileWriter("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                        "\\res\\data\\loginData.json")) {

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

            try (FileWriter fileWrite2 = new FileWriter("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                    "\\res\\data\\customerData.json")) {

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

    public static ArrayList<Integer>displayCustomerListToFlag()  {

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
class Register { // Shu & edit name

    public static boolean custRegister() {

        registerFrame frame = new registerFrame();
        return registerFrame.regStatus;

    }

    static class registerFrame extends JFrame implements ActionListener{

        public static boolean regStatus = false;
        JLabel firstName,lastName,phoneNumber,pass;
        JTextField t1,t2,t3,t4;
        JButton submit;
        JButton reset;
        JCheckBox confirm;
        JLabel msg;

        registerFrame() {

            setTitle("Registration Menu (KUTS)");
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
                        regStatus = true;
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

} // buffer issue

class Login { // Shu & Arif

    public static String custLogin() {
        loginFrame frame = new loginFrame();
        return loginFrame.loggedInUserPhone;
    }

    static class loginFrame extends JFrame implements ActionListener {

        public static String loggedInUserPhone;
        JLabel loginPhone, loginPass;
        JTextField t1;
        JPasswordField t2;
        JButton login,reset;
        JLabel msg;

        loginFrame() { // Shu

            setTitle("Login Menu (KUTS)");
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

            try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                    "\\res\\data\\loginData.json")) {

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

                try (FileReader reader2 = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                        "\\res\\data\\customerData.json")) {

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
                                loggedInUserPhone = "" + phone;
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

} // buffer issue & edit name

class Check_In { // Shu

    public static String getStatus(String loginPhone) {

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

    public static boolean checkLastFlag(String lastFlagTime) {

        boolean flag = false;
        long diff = 0;

        lastFlagTime = menu.lastFlagTime;

        String date = check_InFrame.check_InDate;
        String time = check_InFrame.check_InTime;
        String fullDateTime = date + " " + time;

        diff = Admin.findDifference(fullDateTime, lastFlagTime);

        if (diff >= 0 && diff <= 60) {
            flag = true;
        }

        return flag;

    }

    public static String custCheck_In(String loginPhone) {

        check_InFrame frame = new check_InFrame();

        String status = getStatus(loginPhone);
        boolean flag = checkLastFlag(check_InFrame.lastFlag);

        if(status.equals("Case")) {

            check_InFrame.lastFlag = Admin.flagShop(check_InFrame.check_InShop, check_InFrame.check_InDate, check_InFrame.check_InTime);

        } else {

            if(flag) {

                check_InFrame.lastFlag = Admin.flagShop(check_InFrame.check_InShop, check_InFrame.check_InDate, check_InFrame.check_InTime);

            }

        }

        return check_InFrame.check_InShop + " " + check_InFrame.check_InDate + " "
                + check_InFrame.check_InTime + check_InFrame.loginPhone + " " + check_InFrame.lastFlag;

    }

    static class check_InFrame extends JFrame implements ActionListener {

        public static String check_InShop;
        public static String check_InDate;
        public static String check_InTime;
        public static String lastFlag;
        public static String loginPhone;
        JLabel Title;
        JButton Tesco, Giant, Econsave, Jaya;
        JLabel msg;

        check_InFrame() {

            setTitle("CHECK IN SHOP (KUTS)"); // fix
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            Container e = getContentPane();
            e.setLayout(null);

            Title = new JLabel("PLEASE SELECT A SHOP"); // fix
            Title.setFont(new Font("Tahoma", Font.PLAIN, 20));
            Title.setBounds(80, 30, 300, 20);
            e.add(Title);

            Tesco = new JButton(new ImageIcon("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1\\res\\gui\\tesco.jpg"));
            Tesco.setBounds(50, 80, 140, 35);
            Tesco.addActionListener(this);
            e.add(Tesco);

            Giant = new JButton(new ImageIcon("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1\\res\\gui\\Giant.jpg"));
            Giant.setBounds(200, 80, 140, 35);
            Giant.addActionListener(this);
            e.add(Giant);

            Econsave = new JButton(new ImageIcon("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1\\res\\gui\\Econsave.png"));
            Econsave.setBounds(50, 150, 140, 35);
            Econsave.addActionListener(this);
            e.add(Econsave);

            Jaya = new JButton(new ImageIcon("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1\\res\\gui\\jaya.jpg"));
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

            if (e.getSource() == Tesco) {
                JOptionPane.showMessageDialog(Tesco,
                        "Checked-In Tesco at :" + "\n" + formattedDate);
                check_InShop = "" + "Tesco";
                Admin.appendToMasterHistory(check_InFrame.loginPhone,check_InFrame.check_InDate,
                        check_InFrame.check_InTime,check_InFrame.check_InShop);
                dispose();
                menu.userMenu();
            }
            if (e.getSource() == Giant) {
                JOptionPane.showMessageDialog(Giant,
                        "Checked-In Giant at :" + "\n" + formattedDate);
                check_InShop = "" + "Giant";
                Admin.appendToMasterHistory(check_InFrame.loginPhone,check_InFrame.check_InDate,
                        check_InFrame.check_InTime,check_InFrame.check_InShop);
                dispose();
                menu.userMenu();
            }
            if (e.getSource() == Econsave) {
                JOptionPane.showMessageDialog(Econsave,
                        "Checked-In Econsave at :" + "\n" + formattedDate);
                check_InShop = "" + "Econsave";
                Admin.appendToMasterHistory(check_InFrame.loginPhone,check_InFrame.check_InDate,
                        check_InFrame.check_InTime,check_InFrame.check_InShop);
                dispose();
                menu.userMenu();
            }
            if (e.getSource() == Jaya) {
                JOptionPane.showMessageDialog(Jaya,
                        "Checked-In Jaya Grocer at :" + "\n" + formattedDate);
                check_InShop = "" + "Jaya Grocer";
                Admin.appendToMasterHistory(check_InFrame.loginPhone,check_InFrame.check_InDate,
                        check_InFrame.check_InTime,check_InFrame.check_InShop);
                dispose();
                menu.userMenu();
            }

        }

    }

} // buffer issue & edit name

class ViewHistory { // Arif

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

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\visitHistory.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject customerVisit = (JSONObject) obj;

            JSONArray date = (JSONArray) customerVisit.get("date");
            JSONArray time = (JSONArray) customerVisit.get("time");
            JSONArray name = (JSONArray) customerVisit.get("customerName");
            JSONArray shop = (JSONArray) customerVisit.get("shop");

            System.out.printf("\n%-2s ", "No");
            System.out.printf("%-10s  ", "Date");
            System.out.printf("%-8s  ", "Time");
            System.out.printf("%-15s \n", "Shop");

            int taggedIndex = 0;

            for (int i = 0; i < date.size(); i++) {

                if (name.get(i).equals(checkName)) {

                    taggedIndex++;
                    System.out.printf("%-2s ", (taggedIndex));
                    System.out.printf("%-10s  ", date.get(i));
                    System.out.printf("%-8s  ", time.get(i));
                    System.out.printf("%-15s \n", shop.get(i));

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

} // complete

class CheckStatus { // Arif

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

    }

} // complete

class autoFlag {

    public static void flagShop(String shop, String date, String time) {

        LocalDateTime timeVar = LocalDateTime.now();
        DateTimeFormatter dateVar = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = timeVar.format(dateVar);

        for (int i = 0; i < Shop.ShopList.size(); i++) {

            if(shop.equals(Shop.ShopList.get(i).getShopName())) {

                Shop.ShopList.get(i).setStatus("Case");

            }

        }

        // access master visit history and change individual

    }

}