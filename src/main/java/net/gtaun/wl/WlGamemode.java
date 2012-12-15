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
