package net.runelite.client.plugins.zflaxspinner.Tasks;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.GameObjectQuery;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.plugins.zflaxspinner.Zones;
import net.runelite.client.plugins.zflaxspinner.ZFlaxSpinnerPlugin;
import net.runelite.client.plugins.zflaxspinner.Task;

@Slf4j
public class SpinWheelTask extends Task
{
	Boolean spin;

	@Override
	public boolean validate()
	{
		//check if not in bank region
		if (!utils.isInArea(client, Zones.flaxarea))
			return false;

		if (client.getLocalPlayer().getAnimation() != -1)
			return false;

		//check
		if (!this.utils.inventoryContains(client, "flax"))
			return false;

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "Spin Wheel";
	}

	@Override
	public void onGameTick(GameTick event)
	{

		Widget Skills = client.getWidget(WidgetInfo.MULTI_SKILL_MENU);
		Widget levelup = client.getWidget(WidgetInfo.LEVEL_UP);

		if(Skills != null){
			utils.spinflax();
			spin = false;
			log.info("stringing");
			return;
		}

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

		utils.clickwheel(gameObject);
		log.info("clicking wheel");
		ZFlaxSpinnerPlugin.delay = plugin.tickDelay();
	}

}
