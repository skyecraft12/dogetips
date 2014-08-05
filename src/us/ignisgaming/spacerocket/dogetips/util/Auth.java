package us.ignisgaming.spacerocket.dogetips.util;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Auth
{
	/**
	 * Returns the player's UUID if the current server is in online mode.
	 * LowerCase player names are returned if server is in offline mode.
	 * @param p
	 * @return
	 */
	public static String getId(Player p)
	{
		UUID uuid = p.getUniqueId();
		return Bukkit.getServer().getOnlineMode() ? uuid.toString() : p.getName().toLowerCase();
	}
}
