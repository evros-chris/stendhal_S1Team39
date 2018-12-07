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
package games.stendhal.server.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import games.stendhal.client.actions.SlashAction;
import games.stendhal.client.actions.XMLAction;

public final class XMLActionLoader {
	public static HashMap<String,SlashAction> load(InputStream in) throws SAXException, IOException
	{
		final Document doc = XMLUtil.parse(in);
		
		Element actionRepository = (Element) doc.getFirstChild();
		
		HashMap<String,SlashAction> actions = new HashMap<String,SlashAction>();
		
		NodeList allactions = actionRepository.getChildNodes();
		for (int i = 0; i < allactions.getLength(); i++)
		{
			Node node = allactions.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element element = (Element)allactions.item(i);
				final String actionName = element.getAttribute("name");
				XMLAction testAction = readActionData(null);
				actions.put(actionName,testAction);

			}
		}
		return actions;
	}
	
	private static XMLAction readActionData(final Element element)
	{
		// Test for adding a new XMLaction
		// Message Action
		XMLAction xml_action = new XMLAction();
		Map<String, String>test_action_data = new HashMap<String, String>();
		test_action_data.put("type","tell");
		test_action_data.put("target","params0");
		test_action_data.put("text","remainder");
	    xml_action.loadRPAction(test_action_data, 1, 1);
	    return xml_action;
	}


}
