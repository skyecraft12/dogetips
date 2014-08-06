package us.ignisgaming.spacerocket.dogetips.eco;

import org.bukkit.entity.Player;

import us.ignisgaming.spacerocket.dogetips.util.Auth;

public class DogeEco extends StandardEco
{
	private DogeEcoHandler dogeEcoHandler;
	
	/**
	 * The non-Vault API economy system for this plugin.
	 * Uses a DogeEcoHandler for its base.
	 */
	public DogeEco(DogeEcoHandler separateEH)
	{
		super(null);
		this.dogeEcoHandler = separateEH;
	}
	
	/**
	 * Modified from superclass to use a DogeEcoHandler.
	 * Deposits an amount to this player's account.
	 * @param p
	 * @param amount
	 */
	@Override
	public void payPlayer(Player p, double amount)
	{
		dogeEcoHandler.set(Auth.getId(p), amount);
	}
	
	/**
	 * Modified from superclass to use a DogeEcoHandler.
	 * Get the balance of this player.
	 * @param p
	 * @return
	 */
	@Override
	public double getBalance(Player p)
	{
		return dogeEcoHandler.get(Auth.getId(p));
	}
	
	/**
	 * Modified from superclass to use a DogeEcoHandler.
	 * Withdraws the specified amount (price) from the player only if they can afford it.
	 * This method returns false if the player could not complete the transaction because they could not afford it.
	 * @param p
	 * @param price
	 * @return
	 */
	@Override
	public boolean chargePlayer(Player p, double price)
	{
		if(canAfford(getBalance(p), price))
		{
			dogeEcoHandler.set(Auth.getId(p), price);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Modified from superclass to use a DogeEcoHandler.
	 * Withdraws an amount from the player without checking to see if they player can afford it.
	 * @param p
	 * @param price
	 */
	@Override
	public void chargePlayerNoCheck(Player p, double price)
	{
		dogeEcoHandler.set(Auth.getId(p), price);
	}
	
	/**
	 * Returns the DogeEcoHandler instance.
	 * @return
	 */
	public DogeEcoHandler getHandler()
	{
		return dogeEcoHandler;
	}
}
