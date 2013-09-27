package net.gtaun.wl.gamemode.event;

import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.common.dialog.AbstractDialog;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;
import net.gtaun.wl.common.dialog.ExtensibleListDialogShowEvent;

public class MainMenuDialogShowEvent extends ExtensibleListDialogShowEvent
{
	public MainMenuDialogShowEvent(Player player, Shoebill shoebill, EventManager eventManager, AbstractDialog parentDialog)
	{
		super(player, shoebill, eventManager, parentDialog);
	}
}
