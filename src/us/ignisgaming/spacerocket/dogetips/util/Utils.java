package us.ignisgaming.spacerocket.dogetips.util;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils
{
	/**
	 * Attempts to parse double or returns 0.
	 * @param s
	 * @return
	 */
	public static double parseDouble(Object o)
	{
		try
		{
			return Double.parseDouble(o.toString());
		}
		catch(NumberFormatException e)
		{
			return 0;
		}
	}
	
	/**
	 * Builds each String in an Iterable<String> object into one big String.
	 * @param iterable
	 * @return
	 */
	public static String buildStrings(Iterable<String> iterable)
	{
		StringBuilder sb = new StringBuilder();
		
		//Was originally going to build it into a line-by-line string, but there's no point for this plugin.
		for(String s : iterable)
			sb.append(s);// + '\n');
		
		/*String s = sb.toString();
		
		if(s.equals("\n"))
			return "";
		
		s = s.endsWith("\n") ? s.substring(s.length() - 2) : s;*/
		
		return sb.toString();//s;
	}
	
	/**
	 * Finds and returns the value of specified variable in given json string.
	 * @param variable
	 * @param json
	 * @return
	 */
	public static String getJSONValue(String variable, String json)
	{
		JSONObject obj = new JSONObject(json);
		Object o = null;
		
		try
		{
			o = obj.get(variable);
		}
		catch(JSONException e)
		{
			e.printStackTrace();
			return null;
		}
		
		return o.toString();
	}
}
