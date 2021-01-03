import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSONExample
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\clubberlang96\\IdeaProjects\\OOPDS_Assignment_1\\res\\customerData.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray customerList = (JSONArray) obj;
            System.out.println(customerList);

            //Iterate over employee array
            //customerList.forEach( emp -> parseCustomerObject( (JSONObject) emp ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseCustomerObject(JSONObject customer)
    {
        //Get employee object within list
        JSONObject customerObject = (JSONObject) customer.get("employee");

        //Get employee first name
        String firstName = (String) customerObject.get("firstName");
        System.out.println(firstName);

        //Get employee last name
        String lastName = (String) customerObject.get("lastName");
        System.out.println(lastName);

        //Get employee website name
        String website = (String) customerObject.get("website");
        System.out.println(website);
    }
}
