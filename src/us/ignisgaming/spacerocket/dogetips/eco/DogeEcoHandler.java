package us.ignisgaming.spacerocket.dogetips.eco;

import static us.ignisgaming.spacerocket.dogetips.util.Config.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import us.ignisgaming.spacerocket.dogetips.DogeTips;
import us.ignisgaming.spacerocket.dogetips.util.Auth;
import us.ignisgaming.spacerocket.dogetips.util.Config;

public class DogeEcoHandler
{
	private File dir, dataFile;
	private Map<String, Double> balances = new HashMap<>();
	
	/**
	 * DogeEcoHandler constructor.
	 * Initializes directory and balancesFile.
	 * The balance file contains all saved points.
	 */
	public DogeEcoHandler()
	{
		dir = Bukkit.getPluginManager().getPlugin(DogeTips.instance.getName()).getDataFolder();
		dataFile = new File(dir, Config.get(BALANCE_CSV_FILE));
		dir.mkdirs();
	}
	
	/**
	 * Reads balances from disk and sets it as the value of the balances Map.
	 * Does not save current balances.
	 */
	public void load()
	{
		balances = getBalances();
	}
	
	/**
	 * Writes all balances in memory to disk.
	 */
	public void save()
	{
		createDataFile();
		
		StringBuilder sb = new StringBuilder();

		for(Entry<String, Double> entry : balances.entrySet())
		{
			sb.append(entry.getKey() + "," + entry.getValue() + "\n");
		}

		try(FileOutputStream outStream = new FileOutputStream(dataFile))
		{
			outStream.write(sb.toString().getBytes());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Modifies a player's balance.
	 * @param p
	 * @param d
	 */
	public void set(Player p, double d)
	{
		balances.put(Auth.getId(p), d);
	}
	
	/**
	 * Returns the balance of a player.
	 * @param p
	 * @return
	 */
	public double get(Player p)
	{
		return balances.get(Auth.getId(p));
	}
	
	/**
	 * Reads the balance CSV file and stores each value in a Map.
	 * @return
	 */
	private Map<String, Double> getBalances()
	{
		createDataFile();
		Map<String, Double> data = new HashMap<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(dataFile)))
		{
			while(true)	//continuously read and parse each line until br.readLine() is null.
			{
				String line;
				
				if((line = br.readLine()) == null)
					break;
				
				String[] split = line.split(",");
				
				if(split.length == 1)
				{
					data.put(split[0], 0D);
				}
				else if(split.length > 1)
				{
					Double value = 0D;
					
					try
					{
						value = Double.parseDouble(split[1]);
					}
					catch(NumberFormatException ignored)
					{

					}
					
					data.put(split[0], value);
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}
	
	/**
	 * Creates the balances file if it does not already exist.
	 * Prints IOException stack trance upon failure.
	 */
	private void createDataFile()
	{
		if(!dataFile.exists())
		{
			try
			{
				dataFile.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
