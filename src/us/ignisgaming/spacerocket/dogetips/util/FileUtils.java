package us.ignisgaming.spacerocket.dogetips.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FileUtils
{
	/**
	 * Loads and parses a CSV file containing two entries per line into a Map.
	 * @param f
	 * @param map
	 * @return
	 */
	public static Map<Object, Object> loadMap(File f, boolean number)
	{
		Map<Object, Object> map = new HashMap<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(f)))
		{
			while(true)	//continuously read and parse each line until br.readLine() is null.
			{
				String line;
				
				if((line = br.readLine()) == null)
					break;
				
				String[] split = line.split(",");
				
				if(split.length == 1)
				{
					map.put(split[0], 0D);
				}
				else if(split.length > 1)
				{
					Object value = number ? Utils.parseDouble(split[1]) : split[1];
					map.put(split[0], value);
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * Writes the map as a CSV file over the specified file.
	 * @param f
	 * @param map
	 */
	public static void saveMap(File f, Map<Object, Object> map)
	{
		StringBuilder sb = new StringBuilder();

		for(Entry<Object, Object> entry : map.entrySet())
		{
			sb.append(entry.getKey() + "," + entry.getValue() + "\n");
		}

		try(FileOutputStream outStream = new FileOutputStream(f))
		{
			outStream.write(sb.toString().getBytes());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the specified file if it does not already exist.
	 * Prints IOException stack trance upon failure.
	 */
	public static void createFile(File f)
	{
		if(!f.exists())
		{
			try
			{
				f.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
