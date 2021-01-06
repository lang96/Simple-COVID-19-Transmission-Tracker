


public class Shop {
    int phone;
    String status, manager, name;

    public Shop(int phoneNum, String shopstatus, String shopmanager, String shopname) {
        phone = phoneNum;
        status = shopstatus;
        manager = shopmanager;
        name = shopname;
    }

    public void getinfo() {
        System.out.println(this.name);
        System.out.println(this.phone);
        System.out.println(this.status);
        System.out.println(this.manager);

    }

//    Shop Tesco = new Shop(1300131313 ,"Normal","Ali","Tesco");
//        Tesco.getinfo();
//    Shop Giant = new Shop( 1884694426,"Case","Lee","Giant");
//        Giant.getinfo();
//    Shop Econsave = new Shop(1800883311,"Case","Kumar","Econsave");
//        Econsave.getinfo();
//    Shop Jaya = new Shop(067360001,"Close","Henry","Jaya Grocer");
//        Jaya.getinfo();

}






