package us.ignisgaming.spacerocket.dogetips.subcommands;

import org.bukkit.command.CommandSender;

import us.ignisgaming.spacerocket.dogetips.SubCommand;

public class SubHelp implements SubCommand
{
	@Override
	public String label()
	{
		return "help";
	}

	@Override
	public String usage()
	{
		return "<Page No.>";
	}

	@Override
	public boolean handle(CommandSender sender, String[] args)
	{
		return true;
	}
}
