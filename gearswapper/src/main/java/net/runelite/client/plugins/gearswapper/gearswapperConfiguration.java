package net.runelite.client.plugins.gearswapper;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Keybind;


@ConfigGroup("gearswapper")
public interface gearswapperConfiguration extends Config
{
	@ConfigSection(
		keyName = "Config",
		name = "<html><center>Instructions/Config Delays" +
			"<br></center></html>",
		description = "",
		position = 0
	)
	default boolean Config()
	{
		return false;
	}
	@ConfigItem(
		keyName = "commandssets",
		name = "",
		description = "Instructions. Don't enter anything into this field",
		position = 0,
		section = "Config"
	)
	default String commandssets()
	{
		return "ID:EQUIP || ID:REMOVE\nID:DROP || ID:EAT (Support Triple Eat)\nSPEC:ENABLE || SPEC:ENABLEON\n1:HITLASTTARGET (Player and NPC)\n1:MOVETOTARGET (Only Players)"+
			"[EX]\nProtect From Melee:PRAY\nVeng:CASTSPELL\nStun:LEFTCLICKCAST";
	}
	@ConfigItem(
		keyName = "randLow",
		name = "Minimum Delay",
		description = "Delays between actions.",
		section = "Config",
		position = 0
	)
	default int randLow()
	{
		return 70;
	}

	@ConfigItem(
		keyName = "randLower",
		name = "Maximum Delay",
		description = "Delays between actions.",
		section = "Config",
		position = 1
	)
	default int randHigh()
	{
		return 80;
	}
	@ConfigSection(
		keyName = "GearSwaps",
		name = "<html><center>Gear Swaps Beta." +
			"<br>Created by ZoY" +
			"<br></center></html>",
		description = "",
		position = 2
	)
	default boolean GearSwaps()
	{
		return false;
	}
	@ConfigItem(
		keyName = "customswapone",
		name = "Enable Custom Swap One",
		description = "",
		section = "GearSwaps",
		position = 3
	)
	default boolean GearOne()
	{
		return false;
	}
	@ConfigItem(
		keyName = "customOne",
		name = "Execute Hotkey One",
		description = "",
		position = 4,
		section = "GearSwaps",
		hidden = true,
		unhide = "customswapone"
	)
	default Keybind customOne()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			position = 5,
			keyName = "gearone",
			name = "Commands to be Executed",
			description = "Put in the IDs seperate with a comma.",
		section = "GearSwaps",
		hidden = true,
		unhide = "customswapone"
	)
	default String gearone()
	{
		return "ID:EQUIP";
	}



	@ConfigItem(
		keyName = "customswaptwo",
		name = "Enable Custom Swap Two",
		description = "",
		section = "GearSwaps",
		position = 6
	)
	default boolean GearTwo()
	{
		return false;
	}
	@ConfigItem(
		keyName = "customTwo",
		name = "Execute Hotkey Two",
		description = "",
		position = 7,
		section = "GearSwaps",
		hidden = true,
		unhide = "customswaptwo"
	)
	default Keybind customTwo()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		position = 8,
		keyName = "geartwo",
		name = "Commands to be Executed",
		description = "Put in the IDs seperate with a comma.",
		section = "GearSwaps",
		hidden = true,
		unhide = "customswaptwo"
	)
	default String geartwo()
	{
		return "ID:REMOVE";
	}


	@ConfigItem(
		keyName = "customswapthree",
		name = "Enable Custom Swap Three",
		description = "",
		section = "GearSwaps",
		position = 9
	)
	default boolean GearThree()
	{
		return false;
	}
	@ConfigItem(
		keyName = "customThree",
		name = "Execute Hotkey Three",
		description = "",
		position = 10,
		section = "GearSwaps",
		hidden = true,
		unhide = "customswapthree"
	)
	default Keybind customThree()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		position = 11,
		keyName = "gearthree",
		name = "Commands to be Executed",
		description = "Put in the IDs seperate with a comma.",
		section = "GearSwaps",
		hidden = true,
		unhide = "customswapthree"
	)
	default String gearthree()
	{
		return "ID:REMOVE";
	}

}
