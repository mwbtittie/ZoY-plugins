package net.runelite.client.plugins.zflaxspinner.Tasks;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.GameObject;
import net.runelite.api.ObjectID;
import net.runelite.api.QueryResults;
import net.runelite.api.WallObject;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.GameObjectQuery;
import net.runelite.api.queries.WallObjectQuery;
import net.runelite.client.plugins.zflaxspinner.ZFlaxSpinnerPlugin;
import net.runelite.client.plugins.zflaxspinner.Zones;
import net.runelite.client.plugins.zflaxspinner.ZFlaxSpinnerPlugin;
import net.runelite.client.plugins.zflaxspinner.Task;

@Slf4j
public class ExitFlaxDoorTask extends Task
{

	@Override
	public boolean validate()
	{

		if (!utils.isInRegion(client, Zones.seers))
			return false;

		if (client.getLocalPlayer().getAnimation() != -1)
			return false;

		//check
		if (this.utils.inventoryContains(client, "flax") && !utils.inventoryFull())
			return false;

		if (!this.utils.inventoryContains(client, "string") && !utils.inventoryFull())
			return false;

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "Flax Room Door";
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
				.idEquals(ObjectID.BANK_BOOTH_25808)
				.result(client);


		if (wallObjects != null && !wallObjects.isEmpty())
		{
			log.info("opening door");
			WallObject wallObject = wallObjects.first();

			utils.clickwallobject(wallObject);
			ZFlaxSpinnerPlugin.delay = plugin.tickDelay();
		}


		if (wallObjects1 != null && !wallObjects1.isEmpty())
		{
			log.info("door is opened");
			if (gameObjects == null || gameObjects.isEmpty())
			{
				return;
			}

			GameObject gameObject = gameObjects.first();

			if (gameObject == null)
				return;

			log.info("clicking bank");
			utils.openbank(gameObject);
			ZFlaxSpinnerPlugin.delay = plugin.tickDelay();
		}

	}
}

