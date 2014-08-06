package us.ignisgaming.spacerocket.dogetips.eco;

import static us.ignisgaming.spacerocket.dogetips.util.Config.BALANCE_CSV_FILE;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;

import us.ignisgaming.spacerocket.dogetips.DogeTips;
import us.ignisgaming.spacerocket.dogetips.util.Config;
import us.ignisgaming.spacerocket.dogetips.util.FileUtils;
import us.ignisgaming.spacerocket.dogetips.util.Utils;

public class DogeEcoHandler
{
	private File dir, dataFile;
	//Maps Account and Balance
	private Map<Object, Object> balances = new HashMap<>();
	
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
		FileUtils.createFile(dataFile);;
		FileUtils.saveMap(dataFile, balances);
	}
	
	/**
	 * Modifies a player's balance.
	 * @param p
	 * @param d
	 */
	public void set(String playerID, double d)
	{
		balances.put(playerID, d);
	}
	
	/**
	 * Returns the balance of a player.
	 * @param p
	 * @return
	 */
	public double get(String playerID)
	{
		return Utils.parseDouble(balances.get(playerID));
	}
	
	/**
	 * Reads the balance CSV file and stores each value in a Map.
	 * @return
	 */
	private Map<Object, Object> getBalances()
	{
		FileUtils.createFile(dataFile);
		return FileUtils.loadMap(dataFile, true);
	}
}
