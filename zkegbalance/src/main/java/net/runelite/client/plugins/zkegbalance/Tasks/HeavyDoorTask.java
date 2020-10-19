package net.runelite.client.plugins.zkegbalance.Tasks;

import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.WallObjectQuery;
import net.runelite.client.plugins.zkegbalance.Zones;
import net.runelite.client.plugins.zkegbalance.ZKegBalancePlugin;
import net.runelite.client.plugins.zkegbalance.Task;

public class HeavyDoorTask extends Task
{
	@Override
	public boolean validate()
	{

		if (!utils.isInArea(client, Zones.upperstairs))
			return false;

		//check
		if (!this.utils.inventoryContains( client, "energy") && !utils.inventoryFull())
			return false;

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "Opening Heavy Door";
	}

	@Override
	public void onGameTick(GameTick event)
	{
		if(ZKegBalancePlugin.delay >0){
			ZKegBalancePlugin.delay--;
			return;
		}

		if(utils.isMoving(plugin.lastlocalpoint))
		{
			return;
		}

		QueryResults<WallObject> wallObjects = new WallObjectQuery()
				.idEquals(ObjectID.HEAVY_DOOR_15660)
				.result(client);

		if (wallObjects == null || wallObjects.isEmpty())
		{
			return;
		}

		WallObject wallObject = wallObjects.first();

		if (wallObjects == null)
			return;

		utils.clickwallobject(wallObject);
		ZKegBalancePlugin.delay = plugin.tickDelay();
	}

}
