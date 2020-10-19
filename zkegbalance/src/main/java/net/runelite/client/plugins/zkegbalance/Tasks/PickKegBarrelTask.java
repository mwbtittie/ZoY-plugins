package net.runelite.client.plugins.zkegbalance.Tasks;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.GameObject;
import net.runelite.api.QueryResults;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.GameObjectQuery;
import net.runelite.client.plugins.zkegbalance.Zones;
import net.runelite.client.plugins.zkegbalance.ZKegBalancePlugin;
import net.runelite.client.plugins.zkegbalance.Task;

@Slf4j
public class PickKegBarrelTask extends Task
{

	Boolean shoulddrinknow;

	@Override
	public boolean validate()
	{

		//check
		if (!this.utils.inventoryContains( client, "energy"))
			return false;

		if (!utils.isInArea(client, Zones.kegroom))
			return false;

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "Picking Keg Barrel";
	}

	@Override
	public void onGameTick(GameTick event)
	{

		if(utils.isMoving(plugin.lastlocalpoint))
		{
			return;
		}

		if(ZKegBalancePlugin.potdelay >0){
			ZKegBalancePlugin.potdelay--;
			return;
		}

		if(utils.drinkStamPot(97,utils.get_random_delay(60, 80))){
			shoulddrinknow = true;
			utils.drinkStamPot(97,utils.get_random_delay(60, 80));
			ZKegBalancePlugin.potdelay = plugin.tickDelay();
		}
		if(!utils.drinkStamPot(97,utils.get_random_delay(60, 80))){
			shoulddrinknow = false;
		}
		if (client.getLocalPlayer().getPoseAnimation() == 4179)
			return;

		if(ZKegBalancePlugin.delay >0){
			ZKegBalancePlugin.delay--;
			return;
		}

		QueryResults<GameObject> gameObjects = new GameObjectQuery()
				.idEquals(15672).atWorldLocation(Zones.kegbarrel)
				.result(client);

		if (gameObjects == null || gameObjects.isEmpty())
		{
			return;
		}

		GameObject gameObject = gameObjects.first();

		if (gameObject == null)
			return;

		if(!shoulddrinknow){
			utils.clickgameobject(gameObject);
			ZKegBalancePlugin.delay = plugin.tickDelay();
			shoulddrinknow = true;
		}
	}
}
