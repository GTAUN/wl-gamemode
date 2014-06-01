package net.gtaun.wl.gamemode.command;

import net.gtaun.shoebill.common.command.Command;
import net.gtaun.shoebill.object.Player;
import net.gtaun.wl.gamemode.PlayerHandler;

public class ModeCommands
{
	private PlayerHandler playerHandler;


	public ModeCommands(PlayerHandler playerHandler)
	{
		this.playerHandler = playerHandler;
	}

	@Command
	public boolean m(Player p)
	{
		playerHandler.showMainMenuDialog(p, null);
		return true;
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
