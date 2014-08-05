package us.ignisgaming.spacerocket.dogetips.eco;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.entity.Player;

public class StandardEco
{
	private Economy econ;
	
	/**
	 * The default economy system, which uses the Vault API.
	 * @param econ
	 */
	public StandardEco(Economy econ)
	{
		this.econ = econ;
	}
	
	/**
	 * Check if the difference between the balance and price is greater than or equal to zero.
	 * @param balance
	 * @param price
	 * @return
	 */
	protected static boolean canAfford(double balance, double price)
	{
		return balance - price >= 0D;
	}
	
	/**
	 * Deposits an amount to this player's account.
	 * @param p
	 * @param amount
	 */
	public void payPlayer(Player p, double amount)
	{
		econ.depositPlayer(p, amount);
	}
	
	/**
	 * Get the balance of this player.
	 * @param p
	 * @return
	 */
	public double getBalance(Player p)
	{
		return econ.getBalance(p);
	}
	
	/**
	 * Withdraws the specified amount (price) from the player only if they can afford it.
	 * This method returns false if the player could not complete the transaction because they could not afford it.
	 * @param p
	 * @param price
	 * @return
	 */
	public boolean chargePlayer(Player p, double price)
	{
		if(canAfford(getBalance(p), price))
		{
			econ.withdrawPlayer(p, price);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Withdraws an amount from the player without checking to see if they player can afford it.
	 * @param p
	 * @param price
	 */
	public void chargePlayerNoCheck(Player p, double price)
	{
		econ.withdrawPlayer(p, price);
	}
}
