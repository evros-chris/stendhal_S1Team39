/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.client.actions;

import java.util.HashMap;

//
//

/*
 * Eventually move these out from inner classes, then make them
 * dynamically configurable/loadable.
 */

/**
 * A chat command.
 */
public abstract class NewTypeAction implements SlashAction
{
	private int min;
	private int max;
	protected HashMap<String, String> values = new HashMap<>();
	protected static final String ATTR_TYPE = "type";
	protected static final String ATTR_TARGET = "target";
	protected static final String ATTR_TEXT = "text";

	public NewTypeAction(int min, int max, String type, String target, String text)
	{
		this.min = min;
		this.max = max;
		values.put(ATTR_TYPE, type);
		values.put(ATTR_TARGET, target);
		values.put(ATTR_TEXT, text);
	}
	
	public boolean execute()
	{
		return false;
	}
	
	@Override
	public int getMaximumParameters()
	{
		return max;
	}
	
	@Override
	public int getMinimumParameters()
	{
		return min;
	}
}
