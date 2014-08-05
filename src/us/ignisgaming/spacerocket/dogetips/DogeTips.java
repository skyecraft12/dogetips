package us.ignisgaming.spacerocket.dogetips;

import static us.ignisgaming.spacerocket.dogetips.util.Config.*;

import java.util.logging.Level;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import us.ignisgaming.spacerocket.dogetips.eco.DogeEco;
import us.ignisgaming.spacerocket.dogetips.eco.DogeEcoHandler;
import us.ignisgaming.spacerocket.dogetips.eco.StandardEco;
import us.ignisgaming.spacerocket.dogetips.util.Block_ioUtils;
import us.ignisgaming.spacerocket.dogetips.util.Config;

public class DogeTips extends JavaPlugin
{
	public static DogeTips instance;
	
	private StandardEco econ;
	
	/**
	 * Called by server to disable plugin.
	 */
	@Override
	public void onDisable()
	{
		saveConfig();
		
		if(econ instanceof DogeEco)
		{
			((DogeEco) econ).getHandler().save();
			log("disable", "Saved economy data to disk.");
		}
		//TODO Save all user data.
	}
	
	/**
	 * Called by server to enable plugin.
	 */
	@Override
	public void onEnable()
	{
		instance = this;

		getConfig();
		saveConfig();
		
		setupEconomy();
		
		Block_ioUtils.setup();
		Config.setupColors();
		
		getCommand("doge").setExecutor(new CommandDoge());
		
		log("enable", "Plugin enabled. Report any errors on the Bukkit page.");
	}
	
	/**
	 * Returns the economy instance.
	 * @return
	 */
	public StandardEco getEconomy()
	{
		return econ;
	}
	
	/**
	 * Figures out if the plugin will use a Vault-based economy or its own.
	 */
	private void setupEconomy()
	{
		//Load Vault dependency if it is available and configuration allows it.
		boolean vaultSuccess = false;
		boolean useVault = vaultPluginExists() && getBool(USE_VAULT_ECONOMY);
		
		if(useVault);
		{
			Economy vaultEcon;
			RegisteredServiceProvider<Economy> registeredServiceProvider =
				getServer().getServicesManager().getRegistration(Economy.class);
			
			if(registeredServiceProvider != null)
			{
				vaultEcon = registeredServiceProvider.getProvider();
				econ = new StandardEco(vaultEcon);
				vaultSuccess = true;
			}
		}
		
		//Setup DogeEco if vault is not to be used or it failed to initialize.
		if(!useVault || !vaultSuccess)
		{
			DogeEcoHandler dogeEconHandler = new DogeEcoHandler();
			econ = new DogeEco(dogeEconHandler);
			((DogeEco) econ).getHandler().load();
		}
	}
	
	/**
	 * Checks if the plugin Vault exists.
	 * If Vault is null, the plugin has not loaded leading this method to return false.
	 * @return
	 */
	private boolean vaultPluginExists()
	{
		return getServer().getPluginManager().getPlugin("Vault") != null;
	}
	
	/**
	 * Logs to console.
	 * @param s
	 */
	private void log(String s0, String s1)
	{
		getServer().getLogger().log(Level.INFO, "[DogeTips::" + s0 + "] " + s1);
	}
}
