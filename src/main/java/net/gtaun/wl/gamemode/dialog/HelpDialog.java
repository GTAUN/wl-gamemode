package net.gtaun.wl.gamemode.dialog;

import net.gtaun.shoebill.common.dialog.AbstractDialog;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;
import net.gtaun.wl.common.dialog.WlMsgboxDialog;
import net.gtaun.wl.gamemode.WlGamemode;
import net.gtaun.wl.lang.LocalizedStringSet.PlayerStringSet;

public class HelpDialog
{
	public static WlMsgboxDialog create(Player player, EventManager eventManager, WlGamemode gamemode, AbstractDialog parent)
	{
		PlayerStringSet stringSet = gamemode.getLocalizedStringSet().getStringSet(player);
		return WlMsgboxDialog.create(player, eventManager)
			.parentDialog(parent)
			.caption(stringSet.get("Dialog.HelpDialog.Caption"))
			.message(stringSet.get("Dialog.HelpDialog.Message"))
			.onClickOk((i) ->
			{
				player.playSound(1083);
			})
			.build();
	}
}
