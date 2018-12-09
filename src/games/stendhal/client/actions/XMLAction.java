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
import games.stendhal.client.ClientSingletonRepository;
import marauroa.common.game.RPAction;


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
		min = minParams;
		max = maxParams;
	}
	
	@Override
	public boolean execute(String[] params, String remainder)
	{
		if (actionImplemented == true)
		{
		  RPAction serverAction = new RPAction();
		  for (String datakey : actionMap.keySet())
		  {
			  String dataValue = actionMap.get(datakey);
			  
			  if(dataValue.substring(0,6).equals("params"))
			  {
				  int para_num = Integer.parseInt(dataValue.substring(6));
				  dataValue = params[para_num];
			  }
			  else if (dataValue.equals("remainder"))
			  {
				  dataValue = remainder;
			  }
			  
			  serverAction.put(datakey, dataValue);
		  }

		  // Execute
	      ClientSingletonRepository.getClientFramework().send(serverAction);

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
