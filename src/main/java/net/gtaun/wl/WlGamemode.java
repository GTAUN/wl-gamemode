/**
 * Copyright (C) 2012 MK124
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package net.gtaun.wl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.gtaun.shoebill.resource.Gamemode;

/**
 * 新未来世界的主类。
 * 
 * @author MK124
 */
public class WlGamemode extends Gamemode
{
	public static final Logger LOGGER = LoggerFactory.getLogger(WlGamemode.class);
	
	
	public WlGamemode()
	{
		
	}
	
	@Override
	protected void onEnable() throws Throwable
	{
		LOGGER.info(getDescription().getName() + " " + getDescription().getVersion() + " Enabled.");
	}
	
	@Override
	protected void onDisable() throws Throwable
	{
		LOGGER.info(getDescription().getName() + " " + getDescription().getVersion() + " Disabled.");
	}
}
