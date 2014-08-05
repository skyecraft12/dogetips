package us.ignisgaming.spacerocket.dogetips;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import us.ignisgaming.spacerocket.dogetips.subcommands.SubCheck;
import us.ignisgaming.spacerocket.dogetips.subcommands.SubDeposit;
import us.ignisgaming.spacerocket.dogetips.subcommands.SubDonate;
import us.ignisgaming.spacerocket.dogetips.subcommands.SubHelp;
import us.ignisgaming.spacerocket.dogetips.subcommands.SubJar;
import us.ignisgaming.spacerocket.dogetips.subcommands.SubTip;
import us.ignisgaming.spacerocket.dogetips.subcommands.SubWithdraw;
import us.ignisgaming.spacerocket.dogetips.util.Config;

public class CommandDoge implements CommandExecutor
{
	/**
	 * Register all SubCommands on class initialization.
	 */
	private static ArrayList<SubCommand> subCommands = new ArrayList<SubCommand>()
	{
		private static final long serialVersionUID = -73012991749083899L;

		{
			add(new SubHelp());
	
			add(new SubDeposit());
			add(new SubWithdraw());
			add(new SubCheck());
			add(new SubDonate());
			add(new SubJar());
			add(new SubTip());
		}
	};

	/**
	 * Returns all registered SubCommands.
	 * @return
	 */
	public static ArrayList<SubCommand> getSubCommands()
	{
		return subCommands;
	}
	
	/**
	 * Formats a String of the SubCommand's usage.
	 * Will ignore null usages.
	 * @param sub
	 * @return
	 */
	public static String subUsage(SubCommand sub)
	{
		String u = sub.usage();
		return sub.label() + (u == null ? "" : " " + sub.usage());
	}
	
	/**
	 * Called when the command '/doge' is sent to the server.
	 * If at least one argument exists, a corresponding SubCommand will be handled.
	 * Otherwise, the SubCommand 'help' is handled.
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(args.length > 0)
		{
			for(SubCommand sub : subCommands)
			{
				if(args[0].equalsIgnoreCase(sub.label()))
				{
					boolean b = sub.handle(sender, Arrays.copyOfRange(args, 1, args.length - 1));
					
					if(!b)
						sender.sendMessage(Config.error + "Usage: " + Config.primary + subUsage(sub));
					
					return true;
				}
			}
			
			sender.sendMessage(Config.error + "Bad argument: " + Config.primary + "Try '/doge help'");
		}
		else
		{
			//Handles help SubCommand. Probably need a more way than 'get(0)'.
			//For now there is no need to loop through all SubCommands searching
			//(SubCommand) sub.label().equals("help") since SubHelp() is registered first.
			subCommands.get(0).handle(sender, new String[0]);
		}
		
		return true;
	}
}
