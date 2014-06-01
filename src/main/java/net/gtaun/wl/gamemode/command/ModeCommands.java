package net.gtaun.wl.gamemode.command;

import net.gtaun.shoebill.common.command.Command;
import net.gtaun.shoebill.object.Player;

public class ModeCommands
{
	public ModeCommands()
	{

	}

	@Command
	public boolean kill(Player p)
	{
		p.setHealth(0.0f);
		return true;
	}

	@Command
	public boolean help(Player p)
	{
		return true;
	}
}
