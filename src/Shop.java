import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Member 2

public class Shop {

    // private members
    private String shopName, shopPhone, shopStatus, shopManager;

    // public members
    public static ArrayList<Shop> ShopList = new ArrayList<>();

    // constructor

    /**
     * A Shop representing multiple strings (shopName,shopPhone,shopStatus,shopManager) where all of them are string.
     *
     * @param shopName    the shop name of Shop
     * @param shopPhone   the shop phone number of Shop
     * @param shopStatus  the shop status of Shop
     * @param shopManager the shop manager name of Shop
     *
     */
    public Shop(String shopName, String shopPhone, String shopStatus, String shopManager) {

        this.shopName = shopName;
        this.shopPhone = shopPhone;
        this.shopStatus = shopStatus;
        this.shopManager = shopManager;

    }

    // methods

    // getters

    /**
     * Returns the Shop's Name
     * @return (shopName)
     */
    public String getShopName() {
        return this.shopName;
    }
    /**
     * Returns the Shop's Phone Number
     * @return (shopPhone)
     */
    public String getShopPhone() {
        return this.shopPhone;
    }
    /**
     * Returns the Shop's Status
     * @return (shopStatus)
     */
    public String getShopStatus() {
        return this.shopStatus;
    }
    /**
     * Returns the Shop's Manager Name
     * @return (shopManager)
     */
    public String getShopManager() {
        return this.shopManager;
    }

    // setters

    /**
     * Set Status of a Shop
     * @param shopStatus the status of a Shop
     */
    public void setStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    // list and JSON methods

    /**
     * Returns the Shop's attributes in a predetermined format by passing an index as a parameter
     * @param shopIndex the Shop instance index
     * @return the Shop attributes
     */
    public String toString(int shopIndex) {

        return String.format("%-2s %-15s  %-15s  \n", shopIndex+1, shopName, shopManager);

    }

    // program feature methods

    /**
     * Selecting Shop for shop manager to sign in by choosing from index
     * @return index of selected Shop
     */
    public static int selectShop() {

        int opt = 0;

        Scanner s = new Scanner(System.in);

        System.out.println("\nWhich shop would you like to sign in as?\n");

        for(int i = 0; i < Shop.ShopList.size(); i++) {

            String output = Shop.ShopList.get(i).toString(i);
            System.out.println(output);

        }

        System.out.println("Enter the index (no) of the shop: \n");

        opt = s.nextInt();
        opt -= 1;

        while (opt < 0 && opt > Shop.ShopList.size()) {
            System.err.println("\nUnrecognized option!\n");
        }

        return opt;

    }

    /**
     * viewShopStatus outputs the Shop's status
     * @param shop the shop name of Shop
     */
    public static void viewShopStatus(String shop) {

        String status = "";
        String manager = "";

        for(int i = 0; i < Shop.ShopList.size(); i++) {

            if(shop.equals(Shop.ShopList.get(i).getShopName())) {

                status = "" + Shop.ShopList.get(i).getShopStatus();
                manager = "" + Shop.ShopList.get(i).getShopManager();
                break;

            }

        }

        System.out.println("\nWelcome " + manager + " !\n");
        System.out.println(shop + "'s " + "status is " + status + ".\n");

        int opt = options.afterChoice();

        if(opt == 9) {
            menu.shopMenu();
        } else {
            menu.startMenu();
        }

    }

    // accompanying methods

    /**
     * To initialize ShopList from JSON File
     */
    public static void initializeShopList() { // At the start of program, the CustomerList is initialized from JSON file

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\shopData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject shopData = (JSONObject) obj;

            JSONArray shopNameArr = (JSONArray) shopData.get("name");
            JSONArray shopPhoneArr = (JSONArray) shopData.get("phoneNum");
            JSONArray shopStatusArr = (JSONArray) shopData.get("status");
            JSONArray shopManagerArr = (JSONArray)  shopData.get("manager");

            for(int i = 0; i < shopNameArr.size(); i++) {

                String appendName = "" + shopNameArr.get(i);
                String appendPhone = "" + shopPhoneArr.get(i);
                String appendStatus = "" + shopStatusArr.get(i);
                String appendManager = "" + shopManagerArr.get(i);

                ShopList.add(new Shop(appendName, appendPhone, appendStatus, appendManager));

            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    /**
     * Updating ShopList to JSON file
     */
    public static void updateShopList() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\shopData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject shopData = (JSONObject) obj;

            shopData.remove("name");
            shopData.remove("phoneNum");
            shopData.remove("status");
            shopData.remove("manager");

            JSONArray shopNameArr = new JSONArray();
            JSONArray shopPhoneArr = new JSONArray();
            JSONArray shopStatusArr = new JSONArray();
            JSONArray shopManagerArr = new JSONArray();

            for(int i = 0; i < ShopList.size(); i++) {

                String updateName = "" + ShopList.get(i).getShopName();
                String updatePhoneNum = "" + ShopList.get(i).getShopPhone();
                String updateStatus = "" + ShopList.get(i).getShopStatus();
                String updateManager = "" + ShopList.get(i).getShopManager();

                shopNameArr.add(updateName);
                shopPhoneArr.add(updatePhoneNum);
                shopStatusArr.add(updateStatus);
                shopManagerArr.add(updateManager);

            }

            shopData.put("name",shopNameArr);
            shopData.put("phoneNum",shopPhoneArr);
            shopData.put("status",shopStatusArr);
            shopData.put("manager",shopManagerArr);

            try (FileWriter fileWrite = new FileWriter("C:\\Users\\_YourUserName_\\Desktop\\SourceCode\\res\\data\\shopData.json")) {

                fileWrite.write(shopData.toJSONString());
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

    }

}