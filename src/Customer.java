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
import java.io.FileReader;
import java.io.IOException;

public class Customer { // Arif

    // private members
    private String custName, custPhone, custPass, custStatus;

    // constructor
    public Customer(String custName, String custPhone, String custPass, String custStatus) {

        this.custName = custName;
        this.custPhone = custPhone;
        this.custPass = custPass;
        this.custStatus = custStatus;

    }

    // methods
    public void getInfo() {

    }

    // test main method
    public static void main(String[] args) {

    }

}

class Register { // Shu

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

            setTitle("Registration Menu (KUTS)");
            setSize(700, 400);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
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

//    public class Cust {
//        {
//
//            String firstname = t1.getText();
//            String lastname = t2.getText();
//            String phonenum = t3.getText();
//            String email = t4.getText();
//            String icno = t5.getText();
//
//
//            //Add employees to list
//            JSONArray firstnameList = new JSONArray();
//            JSONArray lastnameList = new JSONArray();
//            JSONArray phonenumList = new JSONArray();
//            JSONArray emailList = new JSONArray();
//            JSONArray icnoList = new JSONArray();
//
//            firstnameList.add("firstName", firstname);
//            lastnameList.add("lastName", lastname);
//            phonenumList.add("phonenum", phonenum);
//            emailList.add("email",email);
//            icnoList.add("icno",icno);
//
//
//            //Write JSON file
//            try (FileWriter file = new FileWriter("C:\\Users\\User\\IdeaProjects\\OOPDS_Assignment_1\\res\\cust.json")) {
//
//                file.write(firstnameList.toJSONString());
//                file.write(lastnameList.toJSONString());
//                file.write(phonenumList.toJSONString());
//                file.write(emailList.toJSONString());
//                file.write(icnoList.toJSONString());
//
//                file.flush();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

        public void actionPerformed(ActionEvent e) {

            String fName = t1.getText();
            String lName = t2.getText();
            String phoneNum = t3.getText();
            String pass = t4.getText();
            int len = phoneNum.length();

            String msg = "" + fName;
            msg += " \n";

            if (e.getSource() == submit) {

                if (confirm.isSelected()) {

                    if (len != 10) {
                        JOptionPane.showMessageDialog(submit, "Enter a valid mobile number");
                    } else {
//                    Cust myCust = new Cust();
                        JOptionPane.showMessageDialog(submit,
                                "Welcome, " + msg + "Your account is successfully created");
                    }

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

class Login { // Shu & Arif

    public static boolean custLogin() {
        loginFrame frame = new loginFrame();
        return loginFrame.loginStatus;
    }

    static class loginFrame extends JFrame implements ActionListener {

        public static boolean loginStatus;
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

                for (int i = 0; i < phoneNum.size(); i++) {

                    if (phoneNum.get(i).equals(phone)) {
                        found = true;
                        foundIndex = i;
                    }

                }

                /*
                try (FileReader reader2 = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                        "\\res\\data\\customerData.json")) {

                    Object obj2 = jsonParser.parse(reader);

                    JSONObject cCustomer = (JSONObject) obj;

                    JSONArray fName = (JSONArray) cCustomer.get("fName");
                    JSONArray lName = (JSONArray) cCustomer.get("lName");
                    JSONArray cPhoneNum = (JSONArray) cCustomer.get("phoneNum");
                    JSONArray status = (JSONArray) cCustomer.get("status");

                } catch (FileNotFoundException f) {
                    f.printStackTrace();
                } catch (IOException f) {
                    f.printStackTrace();
                } catch (ParseException f) {
                    f.printStackTrace();
                }
                 */

                // String msg = "" + firstName;
                // msg += " \n";

                if (e.getSource() == login) {

                    if (len != 10 && len !=11) {
                        JOptionPane.showMessageDialog(login, "Enter a valid mobile number!");
                    }

                    else if (!found) {
                        JOptionPane.showMessageDialog(login, "Phone number not registered!");
                    }

                    else {

                        if (pass.equals(customerPass.get(foundIndex))) {
                            JOptionPane.showMessageDialog(login, "Welcome!");
                            loginStatus = true;
                            dispose();
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

        }

    }

}

class Check_In { // Shu

    public static void custCheck_In() {
        check_InFrame frame = new check_InFrame();
    }

    static class check_InFrame extends JFrame implements ActionListener { // add console check in module which accommodates class if got time

        JLabel Title;
        JButton Tesco, Giant, Econsave, Jaya;
        JLabel msg;

        check_InFrame() {

            setTitle("CHECK IN SHOP (KUTS)"); // fix
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
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

            LocalDateTime masa = LocalDateTime.now(); // fix
            DateTimeFormatter hari = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // fix
            String formattedDate = masa.format(hari); // fix

            if (e.getSource() == Tesco) {
                JOptionPane.showMessageDialog(Tesco,
                        "Checked-In Tesco at :" + "\n" + formattedDate);
            }
            if (e.getSource() == Giant) {
                JOptionPane.showMessageDialog(Giant,
                        "Checked-In Giant at :" + "\n" + formattedDate);
            }
            if (e.getSource() == Econsave) {
                JOptionPane.showMessageDialog(Econsave,
                        "Checked-In Econsave at :" + "\n" + formattedDate);
            }
            if (e.getSource() == Jaya) {
                JOptionPane.showMessageDialog(Jaya,
                        "Checked-In Jaya Grocer at :" + "\n" + formattedDate);
            }

        }

    }

}

class ViewHistory { // Arif

    public static void viewHistory() { // temp

        // Reading JSON file \\

        JSONParser jsonParser = new JSONParser();

        // Parsing the contents of the JSON file

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\visitHistory.json")) {

            Object obj2 = jsonParser.parse(reader);
            JSONObject customerVisit = (JSONObject) obj2;

            Object tesco = customerVisit.get("Tesco");
            JSONObject tescoHistory = (JSONObject) tesco;

            JSONArray date = (JSONArray) tescoHistory.get("date");
            JSONArray time = (JSONArray) tescoHistory.get("time");
            JSONArray custName = (JSONArray) tescoHistory.get("customerName");

            Object giant = customerVisit.get("Giant");
            JSONObject giantHistory = (JSONObject) giant;

            JSONArray date2 = (JSONArray) giantHistory.get("date");
            JSONArray time2 = (JSONArray) giantHistory.get("time");
            JSONArray custName2 = (JSONArray) giantHistory.get("customerName");

            Object econsave = customerVisit.get("Econsave");
            JSONObject econsaveHistory = (JSONObject) econsave;

            JSONArray date3 = (JSONArray) econsaveHistory.get("date");
            JSONArray time3 = (JSONArray) econsaveHistory.get("time");
            JSONArray custName3 = (JSONArray) econsaveHistory.get("customerName");

            Object jaya = customerVisit.get("Jaya");
            JSONObject jayaHistory = (JSONObject) jaya;

            JSONArray date4 = (JSONArray) jayaHistory.get("date");
            JSONArray time4 = (JSONArray) jayaHistory.get("time");
            JSONArray custName4 = (JSONArray) jayaHistory.get("customerName");

            // Table output 1

            System.out.println("\nTesco\n");
            System.out.printf("| %-2s | ", "No");
            System.out.printf("%-10s | ", "Date");
            System.out.printf("%-8s | ", "Time");
            System.out.printf("%-15s |\n", "Customer");

            for (int i = 0;i < date.size();i++) {

                System.out.printf("| %-2s | ", (i+1));
                System.out.printf("%-10s | ", date.get(i));
                System.out.printf("%-8s | ", time.get(i));
                System.out.printf("%-15s |\n", custName.get(i));

            }

            // Table output 2

            System.out.println("\nGiant\n");
            System.out.printf("| %-2s | ", "No");
            System.out.printf("%-10s | ", "Date");
            System.out.printf("%-8s | ", "Time");
            System.out.printf("%-15s |\n", "Customer");

            for (int i = 0;i < date.size();i++) {

                System.out.printf("| %-2s | ", (i+1));
                System.out.printf("%-10s | ", date2.get(i));
                System.out.printf("%-8s | ", time2.get(i));
                System.out.printf("%-15s |\n", custName2.get(i));

            }

            // Table output 3

            System.out.println("\nEconsave\n");
            System.out.printf("| %-2s | ", "No");
            System.out.printf("%-10s | ", "Date");
            System.out.printf("%-8s | ", "Time");
            System.out.printf("%-15s |\n", "Customer");

            for (int i = 0;i < date.size();i++) {

                System.out.printf("| %-2s | ", (i+1));
                System.out.printf("%-10s | ", date3.get(i));
                System.out.printf("%-8s | ", time3.get(i));
                System.out.printf("%-15s |\n", custName3.get(i));

            }

            // Table output 4

            System.out.println("\nJaya Grocer\n");
            System.out.printf("| %-2s | ", "No");
            System.out.printf("%-10s | ", "Date");
            System.out.printf("%-8s | ", "Time");
            System.out.printf("%-15s |\n", "Customer");

            for (int i = 0;i < date.size();i++) {

                System.out.printf("| %-2s | ", (i+1));
                System.out.printf("%-10s | ", date4.get(i));
                System.out.printf("%-8s | ", time4.get(i));
                System.out.printf("%-15s |\n", custName4.get(i));

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

class CheckStatus { // Arif



}