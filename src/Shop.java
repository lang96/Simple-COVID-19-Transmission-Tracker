import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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




