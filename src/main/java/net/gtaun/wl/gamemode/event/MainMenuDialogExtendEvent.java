package net.gtaun.wl.gamemode.event;

import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;
import net.gtaun.wl.common.dialog.ListDialogExtendEvent;
import net.gtaun.wl.common.dialog.WlListDialog;

public class MainMenuDialogExtendEvent extends ListDialogExtendEvent
{
	public MainMenuDialogExtendEvent(Player player, EventManager eventManager, WlListDialog dialog)
	{
		super(player, eventManager, dialog);
	}
}
