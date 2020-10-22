package net.runelite.client.plugins.zflaxspinner.Tasks;

import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.GameObjectQuery;
import net.runelite.client.plugins.zflaxspinner.Zones;
import net.runelite.client.plugins.zflaxspinner.ZFlaxSpinnerPlugin;
import net.runelite.client.plugins.zflaxspinner.Task;

public class ClimbUpTask extends Task
{
	@Override
	public boolean validate()
	{
		//check if not in bank region
		if (!utils.isInArea(client, Zones.flaxarea))
			return false;

		//check
		if (!this.utils.inventoryContains(client, "flax") || this.utils.inventoryContains(client, "string"))
			return false;

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "clicking spinning wheel";
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

		QueryResults<GameObject> gameObjects = new GameObjectQuery()
				.idEquals(ObjectID.SPINNING_WHEEL_25824)
				.result(client);

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
