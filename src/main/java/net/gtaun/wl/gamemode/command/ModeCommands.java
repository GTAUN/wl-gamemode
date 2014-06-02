package net.gtaun.wl.gamemode.command;

import net.gtaun.shoebill.common.command.Command;
import net.gtaun.shoebill.object.Player;
import net.gtaun.wl.gamemode.PlayerHandler;
import net.gtaun.wl.gamemode.WlGamemode;
import net.gtaun.wl.gamemode.dialog.HelpDialog;

public class ModeCommands
{
	private final WlGamemode gamemode;
	private final PlayerHandler playerHandler;


	public ModeCommands(WlGamemode gamemode, PlayerHandler playerHandler)
	{
		this.gamemode = gamemode;
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
		HelpDialog.create(p, playerHandler.getEventManager(), gamemode, null).show();
		return true;
	}
}
