package us.ignisgaming.spacerocket.dogetips.users;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.ignisgaming.spacerocket.dogetips.util.Block_ioUtils;
import us.ignisgaming.spacerocket.dogetips.util.Block_ioUtils.APIRequest;
import us.ignisgaming.spacerocket.dogetips.util.Config;
import us.ignisgaming.spacerocket.dogetips.util.FileUtils;
import us.ignisgaming.spacerocket.dogetips.util.Utils;

public class UserManager
{
	private File dataFile;
	//Maps Player ID and Address
	private Map<Object, Object> addresses = new HashMap<>();
	
	public UserManager()
	{
		loadAddresses();
	}
	
	/**
	 * Ties a player with an address (that address is returned).
	 * Will contact Block.io to get a new user with the label of playerID parameter.
	 * @param playerID
	 * @param address
	 */
	public String generateUserAddress(String playerID)
	{
		String address = null;
		
		List<String> response = Block_ioUtils.getResponse(APIRequest.CREATE_USER, Config.get(Config.API_KEY), "label", playerID);
		address = Utils.getJSONValue("address", Utils.buildStrings(response));
		
		if(address != null)
			set(playerID, address);
		
		return address;	
	}
	
	/**
	 * Fetch and address from a player's id.
	 * @param playerID
	 * @return
	 */
	public String getAddress(String playerID)
	{
		Object o = addresses.get(playerID);
		return o == null ? null : o.toString();
	}
	
	/**
	 * Set the address of a player's id.
	 * @param playerID
	 * @param d
	 */
	public void set(String playerID, String address)
	{
		addresses.put(playerID, address);
	}
	
	/**
	 * Saves user addresses.
	 */
	public void save()
	{
		FileUtils.createFile(dataFile);
		FileUtils.saveMap(dataFile, addresses);
	}
	
	/**
	 * Load all saved user addresses.
	 */
	public void loadAddresses()
	{
		dataFile = new File(Config.get(Config.ADDRESS_CSV_FILE));
		FileUtils.createFile(dataFile);
		addresses = FileUtils.loadMap(dataFile, false);
	}
}
