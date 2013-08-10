package net.gtaun.wl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.common.AbstractShoebillContext;
import net.gtaun.shoebill.constant.WeaponModel;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.data.Vector3D;
import net.gtaun.shoebill.event.PlayerEventHandler;
import net.gtaun.shoebill.event.player.PlayerCommandEvent;
import net.gtaun.shoebill.event.player.PlayerConnectEvent;
import net.gtaun.shoebill.event.player.PlayerDeathEvent;
import net.gtaun.shoebill.event.player.PlayerDisconnectEvent;
import net.gtaun.shoebill.event.player.PlayerRequestClassEvent;
import net.gtaun.shoebill.event.player.PlayerSpawnEvent;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;
import net.gtaun.util.event.EventManager.HandlerPriority;

import org.apache.commons.lang3.math.NumberUtils;

public class PlayerHandler extends AbstractShoebillContext
{
	private static final Vector3D[] RANDOM_SPAWN_POS = 
	{
		new Vector3D(1958.3783f, 1343.1572f, 15.3746f).immutable(),
		new Vector3D(2199.6531f, 1393.3678f, 10.8203f).immutable(),
		new Vector3D(2483.5977f, 1222.0825f, 10.8203f).immutable(), 
		new Vector3D(2637.2712f, 1129.2743f, 11.1797f).immutable(),
		new Vector3D(2000.0106f, 1521.1111f, 17.0625f).immutable(),
		new Vector3D(2024.8190f, 1917.9425f, 12.3386f).immutable(),
		new Vector3D(2261.9048f, 2035.9547f, 10.8203f).immutable(),
		new Vector3D(2262.0986f, 2398.6572f, 10.8203f).immutable(),
		new Vector3D(2244.2566f, 2523.7280f, 10.8203f).immutable(),
		new Vector3D(2335.3228f, 2786.4478f, 10.8203f).immutable(),
		new Vector3D(2150.0186f, 2734.2297f, 11.1763f).immutable(),
		new Vector3D(2158.0811f, 2797.5488f, 10.8203f).immutable(),
		new Vector3D(1969.8301f, 2722.8564f, 10.8203f).immutable(),
		new Vector3D(1652.0555f, 2709.4072f, 10.8265f).immutable(),
		new Vector3D(1564.0052f, 2756.9463f, 10.8203f).immutable(),
		new Vector3D(1271.5452f, 2554.0227f, 10.8203f).immutable(),
		new Vector3D(1441.5894f, 2567.9099f, 10.8203f).immutable(),
		new Vector3D(1480.6473f, 2213.5718f, 11.0234f).immutable(),
		new Vector3D(1400.5906f, 2225.6960f, 11.0234f).immutable(),
		new Vector3D(1598.8419f, 2221.5676f, 11.0625f).immutable(),
		new Vector3D(1318.7759f, 1251.3580f, 10.8203f).immutable(),
		new Vector3D(1558.0731f, 1007.8292f, 10.8125f).immutable(),
		new Vector3D(-857.0551f, 1536.6832f, 22.5870f).immutable(),		// Out of Town Spawns
		new Vector3D(817.3494f, 856.5039f, 12.7891f).immutable(),
		new Vector3D(116.9315f, 1110.1823f, 13.6094f).immutable(),
		new Vector3D(-18.8529f, 1176.0159f, 19.5634f).immutable(),
		new Vector3D(-315.0575f, 1774.0636f, 43.6406f).immutable(),
		new Vector3D(1705.2347f, 1025.6808f, 10.8203f).immutable()
	};
	
	
	private Random random;
	
	
	public PlayerHandler(Shoebill shoebill, EventManager rootEventManager)
	{
		super(shoebill, rootEventManager);
		random = new Random();
	}
	
	@Override
	protected void onInit()
	{
		eventManager.registerHandler(PlayerConnectEvent.class, playerEventHandler, HandlerPriority.NORMAL);
		eventManager.registerHandler(PlayerDisconnectEvent.class, playerEventHandler, HandlerPriority.NORMAL);
		
		eventManager.registerHandler(PlayerSpawnEvent.class, playerEventHandler, HandlerPriority.NORMAL);
		eventManager.registerHandler(PlayerDeathEvent.class, playerEventHandler, HandlerPriority.NORMAL);
		eventManager.registerHandler(PlayerRequestClassEvent.class, playerEventHandler, HandlerPriority.NORMAL);
		
		eventManager.registerHandler(PlayerCommandEvent.class, playerEventHandler, HandlerPriority.NORMAL);
		eventManager.registerHandler(PlayerCommandEvent.class, playerEventHandlerBottom, HandlerPriority.BOTTOM);
	}
	
	@Override
	protected void onDestroy()
	{
		
	}
	
	private PlayerEventHandler playerEventHandler = new PlayerEventHandler()
	{
		private Random random = new Random();
		
		
		public void onPlayerConnect(PlayerConnectEvent event)
		{
			Player player = event.getPlayer();
			player.sendGameText(5000, 5, "Welcome to ~r~The New WL-World~w~!!");
			player.sendMessage(Color.PURPLE, "欢迎来到新未来世界服务器。");
			
			player.sendDeathMessage(null, WeaponModel.CONNECT);
			
			Color color = new Color(random.nextInt() << 8 | 0xFF);
			while (color.getY()<128) color = new Color(random.nextInt() << 8 | 0xFF);
			player.setColor(color);
		}
		
		protected void onPlayerDisconnect(PlayerDisconnectEvent event)
		{
			Player player = event.getPlayer();
			player.sendDeathMessage(null, WeaponModel.DISCONNECT);
		}
		
		public void onPlayerSpawn(PlayerSpawnEvent event)
		{
			Player player = event.getPlayer();
			player.toggleClock(false);
			player.setTime(12, 0);
			player.setWeather(0);
			setRandomLocation(player);
		}
		
		public void onPlayerDeath(PlayerDeathEvent event)
		{
			Player player = event.getPlayer();
			Player killer = event.getKiller();
			
			player.sendDeathMessage(killer, event.getReason());
		}
		
		public void onPlayerRequestClass(PlayerRequestClassEvent event)
		{
			Player player = event.getPlayer();
			setupForClassSelection(player);
		}
		
		public void onPlayerCommand(PlayerCommandEvent event)
		{
			Player player = event.getPlayer();
			
			String command = event.getCommand();
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
			
			case "/tp":
				if (args.size() < 3)
				{
					player.sendMessage(Color.WHITE, "Usage: /tp [x] [y] [z]");
					event.setProcessed();
					return;
				}
				
				float x = NumberUtils.toFloat(args.poll());
				float y = NumberUtils.toFloat(args.poll());
				float z = NumberUtils.toFloat(args.poll());
				player.setLocation(x, y, z);
				event.setProcessed();
				return;
				
			case "/world":
				if (args.size() < 1)
				{
					player.sendMessage(Color.WHITE, "Usage: /world [id]");
					event.setProcessed();
					return;
				}
				
				int worldId = NumberUtils.toInt(args.poll());
				player.setWorld(worldId);
				event.setProcessed();
				return;
				
			case "/interior":
				if (args.size() < 1)
				{
					player.sendMessage(Color.WHITE, "Usage: /interior [id]");
					event.setProcessed();
					return;
				}
				
				int interior = NumberUtils.toInt(args.poll());
				player.setInterior(interior);
				event.setProcessed();
				return;
				
			case "/kill":
				player.setHealth(0.0f);
				event.setProcessed();
				return;
				
			case "/codepage":
				if (args.size() < 1)
				{
					player.sendMessage(Color.WHITE, "Usage: /codepage [val]");
					event.setProcessed();
					return;
				}
				
				int codepage = NumberUtils.toInt(args.poll());
				player.setCodepage(codepage);
				event.setProcessed();
				return;
			}
		}
	};
	
	private PlayerEventHandler playerEventHandlerBottom = new PlayerEventHandler()
	{
		public void onPlayerCommand(PlayerCommandEvent event)
		{
			Player player = event.getPlayer();
			player.sendMessage(Color.RED, "未知命令。");
			event.setProcessed();
		}
	};
	
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
