package net.runelite.client.plugins.zflaxspinner.Tasks;

import net.runelite.api.GameObject;
import net.runelite.api.ObjectID;
import net.runelite.api.QueryResults;
import net.runelite.api.WallObject;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.GameObjectQuery;
import net.runelite.api.queries.WallObjectQuery;
import net.runelite.client.plugins.zflaxspinner.Zones;
import net.runelite.client.plugins.zflaxspinner.ZFlaxSpinnerPlugin;
import net.runelite.client.plugins.zflaxspinner.Task;

public class FlaxRoomDoorTask extends Task
{

	@Override
	public boolean validate()
	{

		if (!utils.isInRegion(client, Zones.seers))
			return false;

		//check
		if (!this.utils.inventoryContains(client, "energy") && !utils.inventoryFull())
			return false;

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "opening flax Room door";
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
				.idEquals(ObjectID.DOOR_25819).atWorldLocation(Zones.closeddoor)
				.result(client);

		QueryResults<WallObject> wallObjects1 = new WallObjectQuery()
				.idEquals(ObjectID.DOOR_25820).atWorldLocation(Zones.openeddoor)
				.result(client);

		QueryResults<GameObject> gameObjects = new GameObjectQuery()
				.idEquals(25938).atWorldLocation(Zones.ladder)
				.result(client);

		if (wallObjects != null && !wallObjects.isEmpty())
		{
			WallObject wallObject = wallObjects.first();

			utils.clickwallobject(wallObject);
			ZFlaxSpinnerPlugin.delay = plugin.tickDelay();
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
			ZFlaxSpinnerPlugin.delay = plugin.tickDelay();
		}

		}
	}

