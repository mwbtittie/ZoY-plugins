/*
 * Copyright (c) 2019-2020, ganom <https://github.com/Ganom>
 * All rights reserved.
 * Licensed under GPL3, see LICENSE for the full scope.
 */
package net.runelite.client.plugins.cursealch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.widgets.WidgetInfo;

@Getter
@AllArgsConstructor
public enum Spells
{
	BIND("Bind", WidgetInfo.SPELL_BIND),
	CRUMBLE("Crumble Undead", WidgetInfo.SPELL_CRUMBLE_UNDEAD),
	ENTANGLE("Entangle", WidgetInfo.SPELL_ENTANGLE),
	SNARE("Snare", WidgetInfo.SPELL_SNARE),

	//Curses
	CHARGE("Charge", WidgetInfo.SPELL_CHARGE),
	CONFUSE("Confuse", WidgetInfo.SPELL_CONFUSE),
	CURSE("Curse", WidgetInfo.SPELL_CURSE),
	ENFEEBLE("Enfeeble", WidgetInfo.SPELL_ENFEEBLE),
	STUN("Stun", WidgetInfo.SPELL_STUN),
	VULNERABILITY("Vulnerability", WidgetInfo.SPELL_VULNERABILITY),
	WEAKEN("Weaken", WidgetInfo.SPELL_WEAKEN);

	private String name;
	private WidgetInfo spell;

	@Override
	public String toString()
	{
		return getName();
	}
}
