package net.runelite.client.plugins.zkegbalance.Tasks;

import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.GameObjectQuery;
import net.runelite.client.plugins.zkegbalance.Zones;
import net.runelite.client.plugins.zkegbalance.ZKegBalancePlugin;
import net.runelite.client.plugins.zkegbalance.Task;

public class ClimbUpTask extends Task
{
	@Override
	public boolean validate()
	{
		//check if not in bank region
		if (!utils.isInRegion(client, Zones.WARRIOR_GUILD_BANK_REGION))
			return false;

		//check
		if (!this.utils.inventoryContains(client, "energy") || this.utils.inventoryContains(client, "vial"))
			return false;

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "Climbing Up";
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
				.idEquals(ObjectID.STAIRCASE_16671)
				.result(client);

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
