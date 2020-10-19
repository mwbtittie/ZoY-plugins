package net.runelite.client.plugins.zkegbalance.Tasks;

import net.runelite.api.ObjectID;
import net.runelite.api.QueryResults;
import net.runelite.api.WallObject;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.WallObjectQuery;
import net.runelite.client.plugins.zkegbalance.Zones;
import net.runelite.client.plugins.zkegbalance.ZKegBalancePlugin;
import net.runelite.client.plugins.zkegbalance.Task;

public class ExitKegRoomTask extends Task
{

	@Override
	public boolean validate()
	{

		if (!utils.isInArea(client, Zones.kegroom))
			return false;

		//check
		if (this.utils.inventoryContains(client, "energy"))
			return false;

		QueryResults<WallObject> wallObjects = new WallObjectQuery()
				.idEquals(ObjectID.DOOR_1535).atWorldLocation(Zones.closeddoor)
				.result(client);

		if (wallObjects == null || wallObjects.isEmpty())
		{
			return false;
		}

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "Exiting Keg Room";
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
				.idEquals(ObjectID.DOOR_1535).atWorldLocation(Zones.closeddoor)
				.result(client);

		if (wallObjects == null || wallObjects.isEmpty())
		{
			return;
		}

		WallObject wallObject = wallObjects.first();

			utils.clickwallobject(wallObject);
			ZKegBalancePlugin.delay = plugin.tickDelay();
		}
	}

