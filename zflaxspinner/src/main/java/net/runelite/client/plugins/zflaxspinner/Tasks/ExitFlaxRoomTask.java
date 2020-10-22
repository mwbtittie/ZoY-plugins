package net.runelite.client.plugins.zflaxspinner.Tasks;

import net.runelite.api.ObjectID;
import net.runelite.api.QueryResults;
import net.runelite.api.WallObject;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.WallObjectQuery;
import net.runelite.client.plugins.zflaxspinner.ZFlaxSpinnerPlugin;
import net.runelite.client.plugins.zflaxspinner.Zones;
import net.runelite.client.plugins.zflaxspinner.ZFlaxSpinnerPlugin;
import net.runelite.client.plugins.zflaxspinner.Task;

public class ExitFlaxRoomTask extends Task
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
		if(ZFlaxSpinnerPlugin.delay >0){
			ZFlaxSpinnerPlugin.delay--;
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
			ZFlaxSpinnerPlugin.delay = plugin.tickDelay();
		}
	}

