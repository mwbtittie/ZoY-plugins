package net.runelite.client.plugins.zkegbalance.Tasks;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.MenuEntry;
import net.runelite.api.Skill;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.plugins.zkegbalance.Task;
import net.runelite.client.plugins.zkegbalance.ZKegBalancePlugin;
import net.runelite.client.plugins.zkegbalance.Zones;

@Slf4j
public class EatingFoodTask extends Task
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

		if (!this.utils.inventoryContains( client, config.foodid()))
			return false;

		if(client.getBoostedSkillLevel(Skill.HITPOINTS) >= config.threshold())
			return false;

		return true;
	}

	@Override
	public String getTaskDescription()
	{
		return "Eating food";
	}

	@Override
	public void onGameTick(GameTick event)
	{
		if(ZKegBalancePlugin.WithdrawDelay >0){
			ZKegBalancePlugin.WithdrawDelay--;
			return;
		}
		if(this.utils.inventoryContains(client, config.foodid()) && client.getBoostedSkillLevel(Skill.HITPOINTS) <= config.threshold()){
			utils.eatfood(utils.getInventoryWidgetItem(config.foodid()));
		}
	}

}
