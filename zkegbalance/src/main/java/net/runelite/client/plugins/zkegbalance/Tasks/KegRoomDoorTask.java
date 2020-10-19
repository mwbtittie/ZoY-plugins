package net.runelite.client.plugins.zkegbalance.Tasks;

import net.runelite.api.GameObject;
import net.runelite.api.ObjectID;
import net.runelite.api.QueryResults;
import net.runelite.api.WallObject;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.GameObjectQuery;
import net.runelite.api.queries.WallObjectQuery;
import net.runelite.client.plugins.zkegbalance.Zones;
import net.runelite.client.plugins.zkegbalance.ZKegBalancePlugin;
import net.runelite.client.plugins.zkegbalance.Task;

public class KegRoomDoorTask extends Task
{

	@Override
	public boolean validate()
	{

		if (!utils.isInArea(client, Zones.heavydoor))
			return false;

		//check
		if (!this.utils.inventoryContains(client, "energy") && !utils.inventoryFull())
			return false;

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "opening Keg Room";
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

		QueryResults<WallObject> wallObjects1 = new WallObjectQuery()
				.idEquals(ObjectID.DOOR_1536).atWorldLocation(Zones.openeddoor)
				.result(client);

		QueryResults<GameObject> gameObjects = new GameObjectQuery()
				.idEquals(15672).atWorldLocation(Zones.kegbarrel)
				.result(client);

		if (wallObjects != null && !wallObjects.isEmpty())
		{
			WallObject wallObject = wallObjects.first();

			utils.clickwallobject(wallObject);
			ZKegBalancePlugin.delay = plugin.tickDelay();
		}


		if (wallObjects1 != null && !wallObjects1.isEmpty())
		{
			if (gameObjects == null || gameObjects.isEmpty())
			{
				return;
			}

			GameObject gameObject = gameObjects.first();

			if (gameObject == null)
				return;

			utils.clickgameobject(gameObject);
			ZKegBalancePlugin.delay = plugin.tickDelay();
		}

		}
	}

