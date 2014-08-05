package us.ignisgaming.spacerocket.dogetips.subcommands;

import org.bukkit.command.CommandSender;

import us.ignisgaming.spacerocket.dogetips.SubCommand;

public class SubDonate implements SubCommand
{
	@Override
	public String label()
	{
		return "donate";
	}

	@Override
	public String usage()
	{
		return "<Amount>";
	}

	@Override
	public boolean handle(CommandSender sender, String[] args)
	{
		return true;
	}
}
