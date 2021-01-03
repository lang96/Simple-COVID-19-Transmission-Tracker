import javax.swing.*;
import java.awt.*;

class Shuframe extends JFrame {

    JLabel logname, logphone;
    JTextField t1, t2;
    JButton submit,reset;



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

        t2 = new JTextField();
        t2.setBounds(200, 100, 150, 20);
        e.add(t2);

        submit=new JButton("Login");
        submit.setBounds(220,150,80,40);
        e.add(submit);

        reset=new JButton("Reset");
        reset.setBounds(90,150,80,40);
        e.add(reset);

        setVisible(true);

    }
}

class Login {
    public static void main(String[] args) {

        Shuframe frame = new Shuframe();
    }
}