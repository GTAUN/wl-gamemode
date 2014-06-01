package net.gtaun.wl.gamemode.command;

import net.gtaun.shoebill.common.command.Command;
import net.gtaun.shoebill.data.Color;
import net.gtaun.shoebill.object.Player;

public class TestCommands
{
	public TestCommands()
	{

	}

	@Command
	public boolean pos(Player p)
	{
		p.sendMessage(Color.WHITE, p.getLocation().toString());
		return true;
	}

	@Command
	public boolean tp(Player p, float x, float y, float z)
	{
		p.setLocation(x, y, z);
		return true;
	}

	@Command
	public boolean angle(Player p, float angle)
	{
		p.setAngle(angle);
		return true;
	}

	@Command
	public boolean interior(Player p, int interior)
	{
		p.setInterior(interior);
		return true;
	}

	@Command
	public boolean world(Player p, int world)
	{
		p.setWorld(world);
		return true;
	}

	@Command
	public boolean codepage(Player p, int code)
	{
		p.setCodepage(code);
		return true;
	}
}
