import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ChangeGui {

    public static void custLogin() {

        System.out.println ("                                   ---------------                                         ");
        System.out.println ("                                    Customer Login                                         ");
        System.out.println ("                                   ---------------                                         ");


        Scanner a = new Scanner(System.in);
        System.out.print("Phone Number : ");
        String phonenum = a.next(); // fix catch string only

        System.out.print("Password : ");
        String pass = a.next(); // fix catch string only

    }

    public static void custRegis() {

        System.out.println ("                                   ------------------                                         ");
        System.out.println ("                                    Customer Register                                         ");
        System.out.println ("                                   ------------------                                         ");


        Scanner a = new Scanner(System.in);
        System.out.print("Firstname : ");
        String firstname = a.nextLine(); // fix catch string only

        System.out.print("Lastname : ");
        String lastname = a.nextLine(); // fix catch string only

        System.out.print("PhoneNumber : ");
        String phonenum = a.nextLine(); // fix catch string only

        System.out.print("Password : ");
        String pass = a.nextLine(); // fix catch string only

    }

    public static void checkin() {

        System.out.println("                                   -----------------                                       ");
        System.out.println("                                    Check-In A Shop                                        ");
        System.out.println("                                   -----------------                                       ");

        System.out.println("1 - Tesco");
        System.out.println("2 - Giant");
        System.out.println("3 - Econsave");
        System.out.println("4 - Jaya Grocer");

        System.out.println("Please Enter An Integer (1-4) : ");

        LocalDateTime timeVar = LocalDateTime.now();
        DateTimeFormatter dateVar = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = timeVar.format(dateVar);

        Scanner c = new Scanner(System.in);

        switch (c.nextInt()) {

            case 1:
                System.out.println("\nChecked-In at Tesco at " + formattedDate + "\n");
                break;

            case 2:
                System.out.println("\nChecked-In at Giant at " + formattedDate + "\n");
                break;

            case 3:
                System.out.println("\nChecked-In at Econsave at " + formattedDate + "\n");
                break;

            case 4:
                System.out.println("\nChecked-In at Jaya Grocer at " + formattedDate + "\n");
                break;

        }
    }


    public static void main(String[] args) {

        //custLogin();
        //custRegis();
        checkin();
    }
}