package net.gtaun.wl.gamemode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

import org.apache.commons.lang3.StringUtils;

import net.gtaun.shoebill.object.World;

public class SampDataLoader
{
	public static void loadClass(World world, File file)
	{
		WlGamemode.logger().info("loading " + file);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8"))))
		{
			int count = 0;
			while (reader.ready())
			{
				String line = reader.readLine().trim();
				if (StringUtils.isBlank(line)) continue;

				line = StringUtils.split(line, ';')[0];

				String[] paramArray = line.split("[, ]");
				if (paramArray.length != 11) continue;

				Queue<String> params = new ArrayDeque<>();
				Collections.addAll(params, paramArray);

				try
				{
					int modelId = Integer.parseInt(params.poll());
					float x = Float.parseFloat(params.poll());
					float y = Float.parseFloat(params.poll());
					float z = Float.parseFloat(params.poll());
					float angle = Float.parseFloat(params.poll());
					int weapon1 = Integer.parseInt(params.poll());
					int ammo1 = Integer.parseInt(params.poll());
					int weapon2 = Integer.parseInt(params.poll());
					int ammo2 = Integer.parseInt(params.poll());
					int weapon3 = Integer.parseInt(params.poll());
					int ammo3 = Integer.parseInt(params.poll());
					World.get().addPlayerClass(modelId, x, y, z, angle, weapon1, ammo1, weapon2, ammo2, weapon3, ammo3);

					count++;
				}
				catch (NumberFormatException e)
				{
					WlGamemode.logger().info("Skip: " + line);
				}
			}

			WlGamemode.logger().info("Created {} classes.", count);
		}
		catch (IOException e)
		{
			WlGamemode.logger().info("Can't initialize classes, please check your " + file);
		}
	}
}
