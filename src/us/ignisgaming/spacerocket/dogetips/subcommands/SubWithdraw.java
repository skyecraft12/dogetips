package us.ignisgaming.spacerocket.dogetips.subcommands;

import org.bukkit.command.CommandSender;

import us.ignisgaming.spacerocket.dogetips.SubCommand;

public class SubWithdraw implements SubCommand
{
	@Override
	public String label()
	{
		return "withdraw";
	}

	@Override
	public String usage()
	{
		return "<Amount> <Address>";
	}

	@Override
	public boolean handle(CommandSender sender, String[] args)
	{
		return true;
	}
}
