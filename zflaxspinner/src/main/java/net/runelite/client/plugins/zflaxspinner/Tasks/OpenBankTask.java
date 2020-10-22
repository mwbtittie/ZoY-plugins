package net.runelite.client.plugins.zflaxspinner.Tasks;

import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.GameObjectQuery;
import net.runelite.client.plugins.zflaxspinner.ZFlaxSpinnerPlugin;
import net.runelite.client.plugins.zflaxspinner.Zones;
import net.runelite.client.plugins.zflaxspinner.ZFlaxSpinnerPlugin;
import net.runelite.client.plugins.zflaxspinner.Task;

public class OpenBankTask extends Task
{
	@Override
	public boolean validate()
	{
		//not in the bank region
		if (!utils.isInArea(client, Zones.seersbank))
			return false;

		//check if bank is open
		if (utils.isBankOpen(client))
			return false;

		if (this.utils.inventoryContains( client, "flax"))
			return false;

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "Opening Bank";
	}

	@Override
	public void onGameTick(GameTick event)
	{
		if(ZFlaxSpinnerPlugin.OpenBankDelay >0){
			ZFlaxSpinnerPlugin.OpenBankDelay--;
			return;
		}

		if(utils.isMoving(plugin.lastlocalpoint))
		{
			return;
		}

		QueryResults<GameObject> gameObjects = new GameObjectQuery()
			.idEquals(ObjectID.BANK_BOOTH_25808)
			.result(client);

		if (gameObjects == null || gameObjects.isEmpty())
		{
			return;
		}

		GameObject gameObject = gameObjects.first();

		if (gameObject == null)
			return;

		utils.openbank(gameObject);
		ZFlaxSpinnerPlugin.OpenBankDelay = plugin.tickDelay();
	}

}
