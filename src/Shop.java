import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop { // Shu

    // private members
    private String shopName, shopPhone, shopStatus, shopManager;

    // public members
    public static ArrayList<Shop> ShopList = new ArrayList<>();

    // constructor
    public Shop(String shopName, String shopPhone, String shopStatus, String shopManager) {

        this.shopName = shopName;
        this.shopPhone = shopPhone;
        this.shopStatus = shopStatus;
        this.shopManager = shopManager;

    }

    // methods

    // getters
    public String getShopName() { return this.shopName; }

    public String getShopPhone() {
        return this.shopPhone;
    }

    public String getShopStatus() {
        return this.shopStatus;
    }

    public String getShopManager() {
        return this.shopManager;
    }

    // setters
    public void setStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    // list and JSON methods
    public String toString(int shopIndex) {

        return String.format("%-2s %-15s  %-15s  \n", shopIndex+1, shopName, shopManager);

    }

    // program feature methods
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
    public static void initializeShopList() { // At the start of program, the CustomerList is initialized from JSON file

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\shopData.json")) {

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

    public static void updateShopList() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                "\\res\\data\\shopData.json")) {

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

            try (FileWriter fileWrite = new FileWriter("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1" +
                    "\\res\\data\\shopData.json")) {

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






