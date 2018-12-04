package com.zomato.apitests;
import org.hamcrest.Matcher;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zomato.dto.responses.Categories;
import com.zomato.dto.responses.CategoriesResponse;
import com.zomato.dto.responses.Category;
import com.zomato.generic.UtilityManager;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class Tests 
{
	@Test
	public void verify_status_for_category_api()
	{
		int status = UtilityManager.responseStatusFromGetRequest("User-key", "c695a7633da355fb79bb09c87380000c", "categories");
		Assert.assertEquals(status, 200);
	}
	@Test
	public void verify_the_contents_of_categories_api() throws Exception
	{
		String responseBody = UtilityManager.responseBodyFromGetRequest("User-key", "c695a7633da355fb79bb09c87380000c", "categories");
		Gson gson = new Gson();//Creating an instance of GSON for getting the response in model structure
		CategoriesResponse catResp=gson.fromJson(responseBody, CategoriesResponse.class); //Fetching the model
		List<Category> categories = catResp.getCategories();
		HashMap map = new HashMap();//Creating instance for map to verify the presence of objects using map.
		for(int i=0;i<categories.size();i++)
		{
			int id = categories.get(i).getCategories().getId();
			String name = categories.get(i).getCategories().getName();
			map.put(id, name);
		}	
		//Check the contents fetched from the get request
		verify_presence_of_id_and_names(map);
	}
	
	@Test
	public void verify_the_structure_for_the_list_of_names()
	{
		String responseBody = UtilityManager.responseBodyFromGetRequest("User-key", "c695a7633da355fb79bb09c87380000c", "categories");
		Gson gson = new Gson();//Creating an instance of GSON for getting the response in model structure
		CategoriesResponse catResp=gson.fromJson(responseBody, CategoriesResponse.class); //Fetching the model
		List<Category> categories = catResp.getCategories();
		ArrayList actualListOfIds = new ArrayList();
		ArrayList actualListOfNames = new ArrayList();
		for(int i=0;i<categories.size();i++)
		{
			int id = categories.get(i).getCategories().getId();
			String name = categories.get(i).getCategories().getName();
			actualListOfIds.add(id); 
			actualListOfNames.add(name); 
		}	
		//Check the names based on the ID's
		verify_presence_of_id_and_names_using_list(actualListOfIds,actualListOfNames);	
		
	}
	
	
	public void verify_presence_of_id_and_names(Map map)
	{
		Set keys = map.keySet();
        Iterator itr = keys.iterator();
        int key;
        String value;
        while(itr.hasNext())
        {
            key = (int) itr.next();
            value = (String)map.get(key);
            System.out.println(key + " - "+ value);
        }
        assertTrue(map.containsKey(1) && map.containsValue("Delivery"));
        assertTrue(map.containsKey(2) && map.containsValue("Dine-out"));
        assertTrue(map.containsKey(3) && map.containsValue("Nightlife"));
        assertTrue(map.containsKey(4) && map.containsValue("Catching-up"));
        assertTrue(map.containsKey(5) && map.containsValue("Takeaway"));
        assertTrue(map.containsKey(6) && map.containsValue("Cafes"));
        assertTrue(map.containsKey(7) && map.containsValue("Daily Menus"));
        assertTrue(map.containsKey(8) && map.containsValue("Breakfast"));
        assertTrue(map.containsKey(9) && map.containsValue("Lunch"));
        assertTrue(map.containsKey(10) && map.containsValue("Dinner"));
        assertTrue(map.containsKey(11) && map.containsValue("Pubs & Bars"));
        assertTrue(map.containsKey(13) && map.containsValue("Pocket Friendly Delivery"));
        assertTrue(map.containsKey(14) && map.containsValue("Clubs & Lounges"));
      //assertTrue(map.containsKey("22"));//Test Case will get failed as the ID 22 is not present
        }
	
	public void verify_presence_of_id_and_names_using_list(ArrayList actualListOfIds,ArrayList actualListOfNames)
	{
		ArrayList expectedListOfIds = new ArrayList();
		expectedListOfIds.add(1);
		expectedListOfIds.add(2);
		expectedListOfIds.add(3);
		expectedListOfIds.add(4);
		expectedListOfIds.add(5);
		expectedListOfIds.add(6);
		expectedListOfIds.add(7);
		expectedListOfIds.add(8);
		expectedListOfIds.add(9);
		expectedListOfIds.add(10);
		expectedListOfIds.add(11);
		expectedListOfIds.add(13);
		expectedListOfIds.add(14);
		
		ArrayList expectedListOfNames = new ArrayList();
		expectedListOfNames.add("Delivery");
		expectedListOfNames.add("Dine-out");
		expectedListOfNames.add("Nightlife");
		expectedListOfNames.add("Catching-up");
		expectedListOfNames.add("Takeaway");
		expectedListOfNames.add("Cafes");
		expectedListOfNames.add("Daily Menus");
		expectedListOfNames.add("Breakfast");
		expectedListOfNames.add("Lunch");
		expectedListOfNames.add("Dinner");
		expectedListOfNames.add("Pubs & Bars");
		expectedListOfNames.add("Pocket Friendly Delivery");
		expectedListOfNames.add("Clubs & Lounges");
		Assert.assertEquals( actualListOfIds,expectedListOfIds);
		Assert.assertEquals(actualListOfNames,expectedListOfNames);
		
	}
}
	


