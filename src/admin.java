public class admin {

    public void viewMaster() {

        // This function allows admin to view the master visit history of the entire system
        // The format is as below :
        // No |    Date    |   Time   | Customer |  Shop  |
        // 1. | 2020-12-01 | 15:10:15 |   Ali    |  Tesco |

    }

    public void viewCustomer() {

        // This function allows admin to view the details of all customers registered in the system.
        // Details of customers follows from customer.java
        // The details of each customer which are generated from registration are stored in a json file and are -
        // - retrieved in this java module.

    }

    public void viewShop() {

        // This function allows admin to view the details of all shops within the system.
        // Details of customers follows from shop.java
        // The details of each shop are stored in a json file and are retrieved in this java module.

    }

    public void flagCustomer() {

        // This function allows admin to flag a customer as a positive case of CoViD-19.
        // With this function deployed, the flagShop() function automatically deploys as well.

    }

    public void flagShop() {

        // This function is an extension of the function flagCustomer(). NOTE : DO NOT ATTEMPT TO RUN ON ITS OWN!
        // This function allows admin to flag a shop as a zone of CoViD-19 positive cases, thus automatically -
        // - flagging anyone who are considered close contacts (within the regulations set in customer class) as -
        // - CoViD-19 positive cases.

    }

    public void addVisit() {

        // This function allows admin to deploy an additional feature in which 30 random visits are added into the -
        // - master visit history. These visits are generated using the existing list of customers and shops from -
        // - application data files (json).

    }


    // Test main module only
    public static void main(String[] args) {
        System.out.println("this is just to test branch commit and push");
    }

}
