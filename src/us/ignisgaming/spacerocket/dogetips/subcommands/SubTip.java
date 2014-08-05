package us.ignisgaming.spacerocket.dogetips.subcommands;

import org.bukkit.command.CommandSender;

import us.ignisgaming.spacerocket.dogetips.SubCommand;

public class SubTip implements SubCommand
{
	@Override
	public String label()
	{
		return "tip";
	}

	@Override
	public String usage()
	{
		return "<Player> <Amount>";
	}

	@Override
	public boolean handle(CommandSender sender, String[] args)
	{
		return true;
	}
}
