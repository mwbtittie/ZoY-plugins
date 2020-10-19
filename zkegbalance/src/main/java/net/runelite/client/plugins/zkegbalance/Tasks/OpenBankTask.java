package net.runelite.client.plugins.zkegbalance.Tasks;

import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.GameObjectQuery;
import net.runelite.client.plugins.zkegbalance.Zones;
import net.runelite.client.plugins.zkegbalance.ZKegBalancePlugin;
import net.runelite.client.plugins.zkegbalance.Task;

public class OpenBankTask extends Task
{
	@Override
	public boolean validate()
	{
		//not in the bank region
		if (!utils.isInRegion(client, Zones.WARRIOR_GUILD_BANK_REGION))
			return false;

		//check if bank is open
		if (utils.isBankOpen(client))
			return false;

		if (this.utils.inventoryContains( client, "energy"))
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
		if(ZKegBalancePlugin.OpenBankDelay >0){
			ZKegBalancePlugin.OpenBankDelay--;
			return;
		}

		if(utils.isMoving(plugin.lastlocalpoint))
		{
			return;
		}

		QueryResults<GameObject> gameObjects = new GameObjectQuery()
			.idEquals(ObjectID.BANK_BOOTH_10355)
			.result(client);

		if (gameObjects == null || gameObjects.isEmpty())
		{
			return;
		}

		GameObject gameObject = gameObjects.first();

		if (gameObject == null)
			return;

		utils.openbank(gameObject);
		ZKegBalancePlugin.OpenBankDelay = plugin.tickDelay();
	}

}
