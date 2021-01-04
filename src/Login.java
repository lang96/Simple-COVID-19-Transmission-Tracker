import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Shuframe extends JFrame implements ActionListener {

    JLabel logname, logphone;
    JTextField t1, t3;
    JPasswordField t2;
    JButton login,reset;
    JLabel msg;

    Shuframe() {
        setTitle("Login Menu (KUTS)");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container e = getContentPane();
        e.setLayout(null);

        logname = new JLabel("Name :");
        logname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        logname.setBounds(20, 50, 150, 20);
        e.add(logname);

        t1 = new JTextField();
        t1.setBounds(200, 50, 150, 20);
        e.add(t1);

        logphone = new JLabel("PhoneNum :");
        logphone.setFont(new Font("Tahoma", Font.PLAIN, 20));
        logphone.setBounds(20, 100, 150, 20);
        e.add(logphone);

        t2 = new JPasswordField();
        t2.setBounds(200, 100, 150, 20);
        e.add(t2);

        login=new JButton(new ImageIcon("D:\\Download\\regis.png"));
        login.setBounds(220,150,75,20);
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

    public void actionPerformed(ActionEvent e) {

        String firstName = t1.getText();
        String mobileNumber = t2.getText();
        int len = mobileNumber.length();

        String msg = "" + firstName;
        msg += " \n";
        if (e.getSource() == login) {
                if (len != 10 && len !=11) {
                    JOptionPane.showMessageDialog(login, "Enter a valid mobile number");
                } else {
//                    Cust myCust = new Cust();
                    JOptionPane.showMessageDialog(login,
                            "Welcome, " + msg);
                }

        } else if (e.getSource() == reset) {
            String def = "";
            t1.setText(def);
            t2.setText(def);
        }
    }
}

class Login {
    public static void main(String[] args) {

        Shuframe frame = new Shuframe();
    }
}