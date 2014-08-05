package us.ignisgaming.spacerocket.dogetips.subcommands;

import org.bukkit.command.CommandSender;

import us.ignisgaming.spacerocket.dogetips.SubCommand;

public class SubJar implements SubCommand
{
	@Override
	public String label()
	{
		return "jar";
	}

	@Override
	public String usage()
	{
		return null;
	}

	@Override
	public boolean handle(CommandSender sender, String[] args)
	{
		return true;
	}
}
