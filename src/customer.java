import java.util.Scanner;

public class customer {
    public void DisplayMenu() {
        System.out.println("\n");
        System.out.println ("________________________________________________________________________________________");
        System.out.println ("| No  |                         Main Menu                                              |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  1  |             Registration                                                       |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  2  |             Sign In                                                            |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  3  |             Check-In A Shop                                                    |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  4  |             History of Shops Visited                                           |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println ("|  5  |             Check Status (Normal,Case,Close)                                   |");
        System.out.println ("|_____|________________________________________________________________________________|");
        System.out.println("\n");
        System.out.print("Please enter your choice (1-5): ");
    }
    public void question()
    {
        System.out.println("Would you like to proceed or exit to Main Menu?");
        System.out.println("To proceed enter 9.");
        System.out.println("If you wish to quit enter 0.");

        Scanner q = new Scanner(System.in);

        switch (q.nextInt())
        {
            case 0:
                System.out.println ("Thank you and goodbye.");
                new customer();

            case 9:

                new Regs();
                break;

            default:
                System.err.println ( "Unrecognized option" );
                question();
        }
    }

    public void question2()
    {
        System.out.println("Would you like to proceed or exit to Main Menu?");
        System.out.println("To proceed enter 9.");
        System.out.println("If you wish to quit enter 0.");

        Scanner q = new Scanner(System.in);

        switch (q.nextInt())
        {
            case 0:
                System.out.println ("Thank you and goodbye.");
                new customer();

            case 9:

                new Login();
                break;

            default:
                System.err.println ( "Unrecognized option" );
                question2();
        }
    }

    public void question3()
    {
        System.out.println("Would you like to proceed or exit to Main Menu?");
        System.out.println("To proceed enter 9.");
        System.out.println("If you wish to quit enter 0.");

        Scanner q = new Scanner(System.in);

        switch (q.nextInt())
        {
            case 0:
                System.out.println ("Thank you and goodbye.");
                new customer();

            case 9:
                System.out.println("nanti letak function check in");
                break;

            default:
                System.err.println ( "Unrecognized option" );
                question3();
        }
    }
    public void question4()
    {
        System.out.println("Would you like to proceed or exit to Main Menu?");
        System.out.println("To proceed enter 9.");
        System.out.println("If you wish to quit enter 0.");

        Scanner q = new Scanner(System.in);

        switch (q.nextInt())
        {
            case 0:
                System.out.println ("Thank you and goodbye.");
                new customer();

            case 9:
                System.out.println("nanti letak function history");
                break;

            default:
                System.err.println ( "Unrecognized option" );
                question4();
        }
    }
    public void question5()
    {
        System.out.println("Would you like to proceed or exit to Main Menu?");
        System.out.println("To proceed enter 9.");
        System.out.println("If you wish to quit enter 0.");

        Scanner q = new Scanner(System.in);

        switch (q.nextInt())
        {
            case 0:
                System.out.println ("Thank you and goodbye.");
                new customer();

            case 9:
                System.out.println("nanti letak function status");
                break;

            default:
                System.err.println ( "Unrecognized option" );
                question5();
        }
    }

    private customer() {
        Scanner input = new Scanner ( System.in );

        DisplayMenu();
        switch ( input.nextInt() ) {
            case 1:
                System.out.println ( "Registration" );
                question();
                break;
            case 2:
                System.out.println ( "Sign In" );
                question2();
                break;
            case 3:
                System.out.println ( "Check in  A Shop" );
                question3();
                break;
            case 4:
                System.out.println("View your history");
                question4();
                break;
            case 5:
                System.out.println("Check Status");
                question5();
                break;
            default:
                System.err.println ( "Please enter 1-5" );
                new customer();
        }
    }

    public void haha(){
        System.out.println("babi");

    }

    public static void main ( String[] args ) {

        new customer();
    }
}
