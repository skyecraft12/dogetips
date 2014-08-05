package us.ignisgaming.spacerocket.dogetips;

import org.bukkit.command.CommandSender;

public interface SubCommand
{
	String label();
	String usage();
	boolean handle(CommandSender sender, String[] args);
}
