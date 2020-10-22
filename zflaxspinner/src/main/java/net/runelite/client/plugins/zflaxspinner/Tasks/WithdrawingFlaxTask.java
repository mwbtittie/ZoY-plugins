package net.runelite.client.plugins.zflaxspinner.Tasks;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.plugins.zflaxspinner.Zones;
import net.runelite.client.plugins.zflaxspinner.ZFlaxSpinnerPlugin;
import net.runelite.client.plugins.zflaxspinner.Task;

@Slf4j
public class WithdrawingFlaxTask extends Task
{
	@Override
	public boolean validate()
	{
		//not in the bank region
		if (!utils.isInArea(client, Zones.seersbank))
			return false;

		//check if bank is open
		if (!utils.isBankOpen(client))
			return false;

		if (this.utils.inventoryContains( client, "flax"))
			return false;

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "Withdrawing Flax";
	}

	@Override
	public void onGameTick(GameTick event)
	{
		if(ZFlaxSpinnerPlugin.WithdrawDelay >0){
			ZFlaxSpinnerPlugin.WithdrawDelay--;
			return;
		}
		Widget widget = client.getWidget(WidgetInfo.BANK_DEPOSIT_INVENTORY);
		if(this.utils.inventoryContains(client, "string")){
			utils.deposit(widget);
			return;
		}
		if(!this.utils.inventoryContains(client, "string")) {
			utils.withdrawAllItem(1779);
			ZFlaxSpinnerPlugin.WithdrawDelay = plugin.tickDelay();
		}
	}

}
