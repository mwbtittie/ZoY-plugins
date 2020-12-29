package net.runelite.client.plugins.zfoodeater;

import net.runelite.client.config.*;


@ConfigGroup("zfoodeater")
public interface zfoodeaterConfiguration extends Config
{

	@ConfigItem(
			keyName = "minEatHP",
			name = "Minimum Eat HP",
			description = "Minimum HP to eat at. i.e. will always eat",
			position = 0
	)
	default int minEatHP()
	{
		return 10;
	}

	@ConfigItem(
			keyName = "maxEatHP",
			name = "Maximum Eat HP",
			description = "Highest HP to consider eating. Value MUST be higher than minimum HP config. If HP drops below this value bot may randomly decide to eat.",
			position = 1
	)
	default int maxEatHP()
	{
		return 20;
	}

	@ConfigItem(
			position = 2,
			keyName = "food",
			name = "Food to Eat",
			description = "Put in the IDs seperate with a comma. EX, 373,3144"
	)
	default String food()
	{
		return "0";
	}

	@ConfigItem(
		keyName = "randLow",
		name = "Minimum Delay",
		description = "Delays between actions.",
		position = 3
	)
	default int randLow()
	{
		return 70;
	}

	@ConfigItem(
		keyName = "randLower",
		name = "Maximum Delay",
		description = "Delays between actions.",
		position = 4
	)
	default int randHigh()
	{
		return 80;
	}

}
