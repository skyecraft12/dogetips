package us.ignisgaming.spacerocket.dogetips.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Block_ioUtils
{
	//default URL settings, will be changed if found in configuration.
	public static String baseURL = "https://block.io/api/v1/";
	public static String apiKeyRequest = "/?api_key=";
	
	public enum APIRequest
	{
		//Address
		NEW_ADDRESS,
		GET_BALANCE,
		WITHDRAW,
		GET_MY_ADDRESSES,
		GET_ADDRESS_BALANCE,
		GET_ADDRESS_RECEIVED,
		GET_ADDRESS_BY_LABEL,
		
		//User
		CREATE_USER,
		GET_USER_BALANCE,
		WITHDRAW_FROM_USER,
		GET_USER_ADDRESS,
		GET_USER_RECEIVED,
		
		//Miscellaneous
		GET_CURRENT_PRICE
	}
	
	/**
	 * Reads from plugin configuration and sets up base URLs.
	 */
	public static void setup()
	{
		String baseURL_temp = Config.get(Config.BASE_URL);
		String apiKeyRequest_temp = Config.get(Config.API_KEY_QUERY);
		
		if(baseURL_temp != null)
			baseURL = baseURL_temp;
		
		if(apiKeyRequest_temp != null)
			apiKeyRequest = apiKeyRequest_temp;
	}
	
	/**
	 * Attempts to get a response from the server and returns a String List of each line.
	 * Forms a URL with the give parameters and then returns what is read line by line.
	 * @param rt
	 * @param apiKey
	 * @param args
	 * @return
	 */
	public static List<String> getResponse(APIRequest rt, String apiKey, String... args)
	{
		String requestType = rt.toString().toLowerCase();
		URL url = null;
		
		switch(rt)	//TODO omit some of these unused APIRequest, otherwise this switch is pointless
		{
			case NEW_ADDRESS:
			case GET_BALANCE:
			case WITHDRAW:
			case GET_MY_ADDRESSES:
			case GET_ADDRESS_BALANCE:
			case GET_ADDRESS_RECEIVED:
			case GET_ADDRESS_BY_LABEL:
			case CREATE_USER:
			case GET_USER_BALANCE:
			case WITHDRAW_FROM_USER:
			case GET_USER_ADDRESS:
			case GET_USER_RECEIVED:
			case GET_CURRENT_PRICE:
				url = formURL(requestType, apiKey, args);
		}
		
		if(url != null)
		{
			try
			{
				List<String> list = new ArrayList<String>();
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
				while(true)
				{
					String s = br.readLine();
					
					if(s == null)
						break;
					else
						list.add(s);
				}
				
				br.close();
				return list;
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return null;
	}

	/**
	 * Creates a URL based off of the type of API request and query arguments.
	 * Example: formURL("withdraw", "x", "amount", "7"): new URL("https://block.io/api/v1/withdraw/?api_key=x&amount=7")
	 * @param type
	 * @param apiKey
	 * @param args
	 * @return
	 */
	private static URL formURL(String type, String apiKey, String... args)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(baseURL);
		sb.append(type);
		sb.append(apiKeyRequest);
		sb.append(apiKey);
		sb.append(formQuery(args));
		
		try
		{
			return new URL(sb.toString());
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Creates a query string minus the initial argument as that is the API key.
	 * Example: formQuery("amount", "50", "test", "true"): "&amount=50&test=true"
	 * @param args
	 * @return
	 */
	private static String formQuery(String... args)
	{
		if(args.length == 0)
			return "";
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < args.length; i++)
		{
			boolean even = i % 2 == 0;
			
			if(even)
				sb.append("&" + args[i] + "=");
			else
				sb.append(args[i]);
		}
		
		return sb.toString();
	}
}
