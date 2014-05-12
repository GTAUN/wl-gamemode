/**
 * WL Gamemode
 * Copyright (C) 2013 MK124
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.gtaun.wl.gamemode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;



import net.gtaun.shoebill.common.AbstractShoebillContext;
import net.gtaun.shoebill.common.dialog.AbstractDialog;
import net.gtaun.shoebill.constant.PlayerKey;
import net.gtaun.shoebill.constant.WeaponModel;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector3D;
import net.gtaun.shoebill.event.player.PlayerCommandEvent;
import net.gtaun.shoebill.event.player.PlayerConnectEvent;
import net.gtaun.shoebill.event.player.PlayerDeathEvent;
import net.gtaun.shoebill.event.player.PlayerDisconnectEvent;
import net.gtaun.shoebill.event.player.PlayerKeyStateChangeEvent;
import net.gtaun.shoebill.event.player.PlayerRequestClassEvent;
import net.gtaun.shoebill.event.player.PlayerSpawnEvent;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.PlayerKeyState;
import net.gtaun.shoebill.service.Service;
import net.gtaun.util.event.EventManager;
import net.gtaun.util.event.HandlerPriority;
import net.gtaun.wl.common.dialog.WlListDialog;
import net.gtaun.wl.gamemode.event.GameListDialogExtendEvent;
import net.gtaun.wl.gamemode.event.MainMenuDialogExtendEvent;


import net.gtaun.wl.lang.LanguageService;

import org.apache.commons.lang3.math.NumberUtils;

public class PlayerHandler extends AbstractShoebillContext
{
	private static final Vector3D[] RANDOM_SPAWN_POS = 
	{
		new Vector3D(1958.3783f, 1343.1572f, 15.3746f),
		new Vector3D(2199.6531f, 1393.3678f, 10.8203f),
		new Vector3D(2483.5977f, 1222.0825f, 10.8203f), 
		new Vector3D(2637.2712f, 1129.2743f, 11.1797f),
		new Vector3D(2000.0106f, 1521.1111f, 17.0625f),
		new Vector3D(2024.8190f, 1917.9425f, 12.3386f),
		new Vector3D(2261.9048f, 2035.9547f, 10.8203f),
		new Vector3D(2262.0986f, 2398.6572f, 10.8203f),
		new Vector3D(2244.2566f, 2523.7280f, 10.8203f),
		new Vector3D(2335.3228f, 2786.4478f, 10.8203f),
		new Vector3D(2150.0186f, 2734.2297f, 11.1763f),
		new Vector3D(2158.0811f, 2797.5488f, 10.8203f),
		new Vector3D(1969.8301f, 2722.8564f, 10.8203f),
		new Vector3D(1652.0555f, 2709.4072f, 10.8265f),
		new Vector3D(1564.0052f, 2756.9463f, 10.8203f),
		new Vector3D(1271.5452f, 2554.0227f, 10.8203f),
		new Vector3D(1441.5894f, 2567.9099f, 10.8203f),
		new Vector3D(1480.6473f, 2213.5718f, 11.0234f),
		new Vector3D(1400.5906f, 2225.6960f, 11.0234f),
		new Vector3D(1598.8419f, 2221.5676f, 11.0625f),
		new Vector3D(1318.7759f, 1251.3580f, 10.8203f),
		new Vector3D(1558.0731f, 1007.8292f, 10.8125f),
		new Vector3D(-857.0551f, 1536.6832f, 22.5870f),		// Out of Town Spawns
		new Vector3D(817.3494f, 856.5039f, 12.7891f),
		new Vector3D(116.9315f, 1110.1823f, 13.6094f),
		new Vector3D(-18.8529f, 1176.0159f, 19.5634f),
		new Vector3D(-315.0575f, 1774.0636f, 43.6406f),
		new Vector3D(1705.2347f, 1025.6808f, 10.8203f)
	};
	
	
	private Random random;
	
	
	public PlayerHandler(EventManager rootEventManager)
	{
		super(rootEventManager);
		random = new Random();
		
		init();
	}
	
	@Override
	protected void onInit()
	{
		eventManagerNode.registerHandler(PlayerConnectEvent.class, (e) ->
		{
			Player player = e.getPlayer();
			
			Color color = new Color(random.nextInt() << 8 | 0xFF);
			while (color.getY()<128) color = new Color(random.nextInt() << 8 | 0xFF);
			player.setColor(color);
			
			player.sendGameText(5000, 5, "Welcome to ~r~The New WL-World~w~!!");
			player.sendDeathMessage(null, WeaponModel.CONNECT);
			
			LanguageService langService = Service.get(LanguageService.class);
			langService.showLanguageSelectionDialog(player, (p, l) ->
			{
				player.sendMessage(Color.PURPLE, "欢迎来到新未来世界服务器。");
			});
		});
		
		eventManagerNode.registerHandler(PlayerDisconnectEvent.class, (e) ->
		{
			Player player = e.getPlayer();
			player.sendDeathMessage(null, WeaponModel.DISCONNECT);
		});
		
		eventManagerNode.registerHandler(PlayerSpawnEvent.class, (e) ->
		{
			Player player = e.getPlayer();
			player.toggleClock(false);
			player.setTime(12, 0);
			player.setWeather(0);
			setRandomLocation(player);
		});
		
		eventManagerNode.registerHandler(PlayerDeathEvent.class, (e) ->
		{
			Player player = e.getPlayer();
			Player killer = e.getKiller();
			
			player.sendDeathMessage(killer, e.getReason());
		});
		
		eventManagerNode.registerHandler(PlayerRequestClassEvent.class, (e) ->
		{
			Player player = e.getPlayer();
			setupForClassSelection(player);
		});
		
		eventManagerNode.registerHandler(PlayerKeyStateChangeEvent.class, HandlerPriority.HIGHEST, (e) ->
		{
			Player player = e.getPlayer();
			PlayerKeyState keyState = player.getKeyState();
			
			if (keyState.isAccurateKeyPressed(PlayerKey.NO))
			{
				showMainMenuDialog(player, null);
			}
			else if (keyState.isAccurateKeyPressed(PlayerKey.YES))
			{
				showGameListDialog(player, null);
			}
		});
		
		eventManagerNode.registerHandler(PlayerCommandEvent.class, (e) ->
		{
			Player player = e.getPlayer();
			
			String command = e.getCommand();
			String[] splits = command.split(" ", 2);
			
			String operation = splits[0].toLowerCase();
			Queue<String> args = new LinkedList<>();
			
			if (splits.length > 1)
			{
				String[] argsArray = splits[1].split(" ");
				args.addAll(Arrays.asList(argsArray));
			}
			
			switch (operation)
			{
			case "/pos":
				player.sendMessage(Color.WHITE, player.getLocation().toString());
				break;
			
			case "/tppos":
				if (args.size() < 3)
				{
					player.sendMessage(Color.WHITE, "Usage: /tppos [x] [y] [z]");
					e.setProcessed();
					return;
				}
				
				float x = NumberUtils.toFloat(args.poll());
				float y = NumberUtils.toFloat(args.poll());
				float z = NumberUtils.toFloat(args.poll());
				player.setLocation(x, y, z);
				e.setProcessed();
				return;
				
			case "/world":
				if (args.size() < 1)
				{
					player.sendMessage(Color.WHITE, "Usage: /world [id]");
					e.setProcessed();
					return;
				}
				
				int worldId = NumberUtils.toInt(args.poll());
				player.setWorld(worldId);
				e.setProcessed();
				return;
				
			case "/interior":
				if (args.size() < 1)
				{
					player.sendMessage(Color.WHITE, "Usage: /interior [id]");
					e.setProcessed();
					return;
				}
				
				int interior = NumberUtils.toInt(args.poll());
				player.setInterior(interior);
				e.setProcessed();
				return;
				
			case "/kill":
				player.setHealth(0.0f);
				e.setProcessed();
				return;
				
			case "/codepage":
				if (args.size() < 1)
				{
					player.sendMessage(Color.WHITE, "Usage: /codepage [val]");
					e.setProcessed();
					return;
				}
				
				int codepage = NumberUtils.toInt(args.poll());
				player.setCodepage(codepage);
				e.setProcessed();
				return;
			}
		});
		
		eventManagerNode.registerHandler(PlayerCommandEvent.class, HandlerPriority.BOTTOM, (e) ->
		{
			Player player = e.getPlayer();
			player.sendMessage(Color.RED, "未知命令。");
			e.setProcessed();
		});
	}
	
	@Override
	protected void onDestroy()
	{
		
	}
	
	public void showMainMenuDialog(Player player, AbstractDialog parentDialog)
	{
		if (parentDialog == null) player.playSound(1083);
		
		WlListDialog mainDialog = WlListDialog.create(player, rootEventManager)
			.caption("新未来世界: 主菜单")
			.build();
		
		MainMenuDialogExtendEvent dialogExtendEvent = new MainMenuDialogExtendEvent(player, eventManagerNode, mainDialog);
		eventManagerNode.dispatchEvent(dialogExtendEvent);
		
		mainDialog.show();
	}
	
	public void showGameListDialog(Player player, AbstractDialog parentDialog)
	{
		if (parentDialog == null) player.playSound(1083);

		WlListDialog mainDialog = WlListDialog.create(player, rootEventManager)
			.caption("新未来世界: 参与比赛")
			.build();
		
		GameListDialogExtendEvent dialogShowEvent = new GameListDialogExtendEvent(player, eventManagerNode, mainDialog);
		eventManagerNode.dispatchEvent(dialogShowEvent);
		
		mainDialog.show();
	}
	
	private void setRandomLocation(Player player)
	{
		int rand = random.nextInt(RANDOM_SPAWN_POS.length);
		player.setLocation(RANDOM_SPAWN_POS[rand]);
		player.setInterior(0);
	}

	private void setupForClassSelection(Player player)
	{
		player.setInterior(14);
		player.setLocation(258.4893f, -41.4008f, 1002.0234f);
		player.setAngle(270.0f);
		player.setCameraPosition(256.0815f, -43.0475f, 1004.0234f);
		player.setCameraLookAt(258.4893f, -41.4008f, 1002.0234f);
	}
}
