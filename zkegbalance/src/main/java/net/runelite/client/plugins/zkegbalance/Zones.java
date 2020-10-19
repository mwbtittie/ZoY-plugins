package net.runelite.client.plugins.zkegbalance;

import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;

public class Zones {
    public static int WARRIOR_GUILD_BANK_REGION = 11319;
    public static WorldArea upperstairs = new WorldArea(new WorldPoint(2838, 3536, 1), new WorldPoint(2842, 3540, 1));
    public static WorldArea heavydoor = new WorldArea(new WorldPoint(2853, 3551, 1), new WorldPoint(2854, 3552, 1));
    public static WorldArea kegroom = new WorldArea(new WorldPoint(2860, 3539, 1), new WorldPoint(2867, 3544, 1));
    public static WorldArea climbingdown = new WorldArea(new WorldPoint(2851, 3550, 1), new WorldPoint(2853, 3552, 1));
    public static WorldArea pickbarrel = new WorldArea(new WorldPoint(2852, 3550, 1), new WorldPoint(2854, 3552, 1));
    public static WorldPoint closeddoor = new WorldPoint(2860, 3542, 1);
    public static WorldPoint openeddoor = new WorldPoint(2861, 3542, 1);
    public static WorldPoint kegbarrel = new WorldPoint(2866, 3539, 1);
}
