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

import java.io.File;

import net.gtaun.shoebill.constant.PlayerMarkerMode;
import net.gtaun.shoebill.object.Pickup;
import net.gtaun.shoebill.object.Server;
import net.gtaun.shoebill.object.World;
import net.gtaun.shoebill.resource.Gamemode;
import net.gtaun.shoebill.service.Service;
import net.gtaun.wl.lang.LanguageService;
import net.gtaun.wl.lang.LocalizedStringSet;

import org.slf4j.Logger;

/**
 * 新未来世界的主类。
 *
 * @author MK124
 */
public class WlGamemode extends Gamemode
{
	private static Logger logger;
	public static Logger logger()
	{
		return logger;
	}


	private LocalizedStringSet localizedStringSet;
	private PlayerHandler playerManager;


	public WlGamemode()
	{

	}

	@Override
	protected void onEnable() throws Throwable
	{
		logger = getLogger();

		LanguageService languageService = Service.get(LanguageService.class);
		localizedStringSet = languageService.createStringSet(new File(getDataDir(), "text"));

		Server server = Server.get();
		World world = World.get();

		server.setGamemodeText("The New WL-World");
		world.showPlayerMarkers(PlayerMarkerMode.GLOBAL);
		world.showNameTags(true);
		world.enableStuntBonusForAll(false);

		Pickup.create(371, 15, 1710.3359f, 1614.3585f, 10.1191f, 0);
		Pickup.create(371, 15, 1964.4523f, 1917.0341f, 130.9375f, 0);
		Pickup.create(371, 15, 2055.7258f, 2395.8589f, 150.4766f, 0);
		Pickup.create(371, 15, 2265.0120f, 1672.3837f, 94.9219f, 0);
		Pickup.create(371, 15, 2265.9739f, 1623.4060f, 94.9219f, 0);

		playerManager = new PlayerHandler(this, getEventManager());

		File playerClassFile = new File(getDataDir(), "class.txt");
		SampDataLoader.loadClass(world, playerClassFile);
	}

	@Override
	protected void onDisable() throws Throwable
	{
		playerManager.destroy();
		playerManager = null;
	}

	public LocalizedStringSet getLocalizedStringSet()
	{
		return localizedStringSet;
	}
}
