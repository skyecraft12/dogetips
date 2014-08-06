package us.ignisgaming.spacerocket.dogetips.util;

import org.bukkit.ChatColor;

import us.ignisgaming.spacerocket.dogetips.DogeTips;

/**
 * Configuration utilities made for easy access to the plugin's configuration file.
 * Class intended to make code cleaner and to keep track of nodes.
 */
public class Config
{
	//Nodes: Economy
	public static final String USE_VAULT_ECONOMY = "use-vault-economy";
	public static final String ADDRESS_CSV_FILE = "address-file-name";
	public static final String BALANCE_CSV_FILE = "separate-economy.balance-file-name";
	
	//Nodes: API
	public static final String API_KEY = "api.api-key";
	public static final String BASE_URL = "api.base-url";
	public static final String API_KEY_QUERY = "api.api-key-query";
	
	//Nodes: Colors
	public static final String COLOR_PRIMARY = "colors.primary";
	public static final String COLOR_SECONDARY = "colors.secondary";
	public static final String COLOR_ERROR = "colors.errors";

	//Configurable colors. Defaults to yellow, grey, red.
	private static String[] colors = {"\u00a7e", "\u00a77", "\u00a7c"};
	public static String primary = colors[0];
	public static String secondary = colors[1];
	public static String error = colors[2];
	
	/**
	 * Returns the config String value of specified node.
	 * @param s
	 * @return
	 */
	public static String get(String s)
	{
		return DogeTips.instance.getConfig().getString(s);
	}
	
	/**
	 * Returns the config boolean value of specified node.
	 * @param s
	 * @return
	 */
	public static boolean getBool(String s)
	{
		return DogeTips.instance.getConfig().getBoolean(s);
	}
	
	/**
	 * Initializes colors from config.
	 * TODO needs a better way to accomplish this.
	 */
	public static void setupColors()
	{
		String[] tempColors = new String[3];
		tempColors[0] = get(COLOR_PRIMARY);
		tempColors[1] = get(COLOR_SECONDARY);
		tempColors[2] = get(COLOR_ERROR);
		
		for(int i = 0; i < tempColors.length; i++)
			if(tempColors[i] != null)
				colors[i] = ChatColor.translateAlternateColorCodes('&', tempColors[i]);
	}
}
