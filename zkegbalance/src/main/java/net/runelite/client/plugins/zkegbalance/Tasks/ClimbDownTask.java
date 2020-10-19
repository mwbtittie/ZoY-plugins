package net.runelite.client.plugins.zkegbalance.Tasks;

import net.runelite.api.GameObject;
import net.runelite.api.ObjectID;
import net.runelite.api.QueryResults;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.GameObjectQuery;
import net.runelite.client.plugins.zkegbalance.Zones;
import net.runelite.client.plugins.zkegbalance.ZKegBalancePlugin;
import net.runelite.client.plugins.zkegbalance.Task;

public class ClimbDownTask extends Task
{
	@Override
	public boolean validate()
	{
		//check if not in bank region
		if (!utils.isInArea(client, Zones.climbingdown))
			return false;

		//check
		if (this.utils.inventoryContains(client, "energy"))
			return false;

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "Climbing Down";
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

		QueryResults<GameObject> gameObjects = new GameObjectQuery()
				.idEquals(ObjectID.STAIRCASE_16672)
				.result(client);

		if (gameObjects == null || gameObjects.isEmpty())
		{
			return;
		}

		GameObject gameObject = gameObjects.first();

		if (gameObject == null)
			return;

		utils.climbdown(gameObject);
		ZKegBalancePlugin.delay = plugin.tickDelay();
	}

}
