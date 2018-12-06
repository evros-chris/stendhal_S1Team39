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

import java.util.Map;


public class XMLAction implements SlashAction
{
	private int min = 0;
	private int max = 0;
	private boolean actionImplemented = false;
	private Map<String, String> actionMap;
	
	
	public void loadRPAction(Map<String, String> actionData, int minParams, int maxParams)
	{
		actionImplemented = true;
		actionMap = actionData;
	}
	
	@Override
	public boolean execute(String[] params, String remainder)
	{
		if (actionImplemented == true)
		{
			//RPAction newXMLAction = new RPAction();
			
		}
		return true;
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
