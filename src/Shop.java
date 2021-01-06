public class Shop { // Shu

    // private members
    private String shopName, shopPhone, shopStatus, shopManager;

    // constructor
    public Shop(String shopName, String shopPhone, String shopStatus, String shopManager) {

        this.shopName = shopName;
        this.shopPhone = shopPhone;
        this.shopStatus = shopStatus;
        this.shopManager = shopManager;

    }

    // methods
    public void getInfo() {

    }

    Shop Tesco = new Shop("Tesco", "1300131313", "Normal", "Ali");
    Shop Giant = new Shop("Giant", "1884694426", "Case", "Lee");
    Shop Econsave = new Shop("Econsave", "1800883311", "Case", "Kumar");
    Shop Jaya = new Shop("Jaya Grocer", "067360001", "Close", "Henry");


}




