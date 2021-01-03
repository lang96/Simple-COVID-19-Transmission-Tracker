import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Myframe extends JFrame implements ActionListener{

    JLabel firstname,lastname,phonenum,email,icno;
    JTextField t1,t2,t3,t4,t5;
    JButton submit;
    JButton reset;
    JCheckBox confirm;
    JLabel msg;

    Myframe() {
        setTitle("Registration Menu (KUTS)");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c=getContentPane();
        c.setLayout(null);
//name
        firstname=new JLabel("First Name :");
        firstname.setFont(new Font("Tahoma",Font.PLAIN,20));
        firstname.setBounds(20,50,150,20);
        c.add(firstname);

        t1=new JTextField();
        t1.setBounds(200,50,150,20);
        c.add(t1);

        lastname=new JLabel("Last Name :");
        lastname.setFont(new Font("Tahoma",Font.PLAIN,20));
        lastname.setBounds(400,50,150,20);
        c.add(lastname);


        t2=new JTextField();
        t2.setBounds(520,50,150,20);
        c.add(t2);
//number
        phonenum = new JLabel("Phone\r\n Number :");
        phonenum.setFont(new Font("Tahoma", Font.PLAIN, 20));
        phonenum.setBounds(20, 100, 150, 20);
        c.add(phonenum);

        t3=new JTextField();
        t3.setBounds(200,100,150,20);
        c.add(t3);

//email address
        email = new JLabel("Email:");
        email.setFont(new Font("Tahoma", Font.PLAIN, 20));
        email.setBounds(400, 100, 150, 20);
        c.add(email);

        t4 = new JTextField();
        t4.setBounds(520, 100, 150, 20);
        c.add(t4);


//icno

        icno = new JLabel("IC No. :");
        icno.setFont(new Font("Tahoma", Font.PLAIN, 20));
        icno.setBounds(200, 150, 150, 20);
        c.add(icno);

        t5 = new JTextField();
        t5.setBounds(280, 150, 160, 20);
        c.add(t5);

//submit button

        submit=new JButton("Submit");
        submit.setBounds(350,260,80,40);
        submit.addActionListener(this);
        c.add(submit);

//reset button

        reset=new JButton("Reset");
        reset.setBounds(200,260,80,40);
        reset.addActionListener(this);
        c.add(reset);

//confirmation

        confirm = new JCheckBox("Please make sure all details inserted are correct.");
        confirm.setFont(new Font("Arial", Font.PLAIN, 15));
        confirm.setSize(400, 20);
        confirm.setLocation(160, 200);
        c.add(confirm);

//message

        msg=new JLabel("");
        msg.setBounds(280,260,250,20);
        c.add(msg);


        setVisible(true);
    }

//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == submit) {
//            if (confirm.isSelected()) {
//                String data1;
//                String data
//                        = "Name : "
//                        + firstname.getText() + "\n"
//                        + "Mobile : "
//                        + phonenum.getText() + "\n";
//            }
//        else {
//                reset.setText("Please accept the"
//                        + " terms & conditions..");
//            }
//        }
//
//        else if (e.getSource() == reset) {
//            String def = "";
//            t1.setText(def);
//            t2.setText(def);
//            t3.setText(def);
//            t4.setText(def);
//            reset.setText(def);
//            t5.setText(def);
//            confirm.setSelected(false);
//
//        }
//    }


    public void actionPerformed(ActionEvent e) {

        String firstName = t1.getText();
        String mobileNumber = t3.getText();
        int len = mobileNumber.length();

        String msg = "" + firstName;
        msg += " \n";
        if (e.getSource() == submit) {
            if (confirm.isSelected()) {
                if (len != 10) {
                    JOptionPane.showMessageDialog(submit, "Enter a valid mobile number");
                } else {
                    JOptionPane.showMessageDialog(submit,
                            "Welcome, " + msg + "Your account is successfully created");


                }
            } else if (e.getSource() == reset) {
                String def = "";
                t1.setText(def);
                t2.setText(def);
                t3.setText(def);
                t4.setText(def);
                t5.setText(def);
                confirm.setSelected(false);
            }
        }
    }
}



class Regs {
    //     public static void main(String[] args) {
    Myframe frame  = new Myframe();
}
//}
