package net.runelite.client.plugins.zkegbalance.Tasks;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.plugins.zkegbalance.Zones;
import net.runelite.client.plugins.zkegbalance.ZKegBalancePlugin;
import net.runelite.client.plugins.zkegbalance.Task;

@Slf4j
public class WithdrawingPotsTask extends Task
{
	@Override
	public boolean validate()
	{
		//not in the bank region
		if (!utils.isInRegion(client, Zones.WARRIOR_GUILD_BANK_REGION))
			return false;

		//check if bank is open
		if (!utils.isBankOpen(client))
			return false;

		if (this.utils.inventoryContains( client, "energy"))
			return false;

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "Withdrawing Energy Potions";
	}

	@Override
	public void onGameTick(GameTick event)
	{
		if(ZKegBalancePlugin.WithdrawDelay >0){
			ZKegBalancePlugin.WithdrawDelay--;
			return;
		}
		Widget widget = client.getWidget(WidgetInfo.BANK_DEPOSIT_INVENTORY);
		if(this.utils.inventoryContains(client, "vial")){
			utils.deposit(widget);
			return;
		}
		if(!this.utils.inventoryContains(client, "vial")) {
		if(!utils.bankContainsAnyOf(3008)){
			utils.withdrawAllItem(3010);
		}
		if(utils.bankContainsAnyOf(3008)){
			utils.withdrawAllItem(3008);
		}
			ZKegBalancePlugin.WithdrawDelay = plugin.tickDelay();
		}
	}

}
