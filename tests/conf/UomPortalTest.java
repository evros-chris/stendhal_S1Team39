/***************************************************************************
 *                   (C) Copyright 2003-2015 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package conf;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import marauroa.common.Log4J;

/**
 * Tests for PortalMatcherTest
 */
public class UomPortalTest {

	/**
	 * Test the validation method
	 *
	 * @throws ParserConfigurationException in case of an invalid zone file
	 * @throws SAXException in case of an invalid zone file
	 * @throws IOException in case of an input/output error
	 */
	@Test
	public void testvalidate() throws ParserConfigurationException, SAXException, IOException {
		Log4J.init();
		final PortalMatchTest pmt = new PortalMatchTest();
		LinkedList<PortalTestObject> portals = new LinkedList<PortalTestObject>();
		final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		final DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmldoc = docBuilder.parse(new File("tests/conf/valid.xml"));

		portals.addAll(pmt.proceedDocument(xmldoc));
		assertThat("all portals in this test file are valid", pmt.isValid(portals), equalTo(""));
		portals = new LinkedList<PortalTestObject>();
		xmldoc = docBuilder.parse(new File("tests/conf/invalid.xml"));
		portals.addAll(pmt.proceedDocument(xmldoc));
		assertThat("there is a known bad in it", pmt.isValid(portals), not(equalTo("")));

	}
	
	// Test The UOM CAMPUS portal coordinates
	@Test
	public void testportalCoordinates() throws ParserConfigurationException, SAXException, IOException
	{
		Log4J.init();
		final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document xmldoc = docBuilder.parse(new File("data/conf/zones/ados.xml"));
	
		xmldoc.getDocumentElement().normalize();
		
		// To store the attributes of the portal
		String x = "";
		String y = "";
		String name = "";
		String zone = "";
		
		// List of portals
		final NodeList listOfPortals = xmldoc.getElementsByTagName("portal");
		
		if (listOfPortals.getLength() > 0) {

			for (int s = 0; s < listOfPortals.getLength(); s++) {
				
				zone = listOfPortals.item(s).getParentNode().getAttributes().getNamedItem(
						"name").getNodeValue();
				if(zone.equalsIgnoreCase("0_ados_city"))
				{
					name = listOfPortals.item(s).getAttributes().getNamedItem("ref").getNodeValue();
					
					//Check UOM campus left entrance
					if(name.equalsIgnoreCase("uom_campus_entrance_left"))
					{
						x = listOfPortals.item(s).getAttributes().getNamedItem("x").getNodeValue();
						y = listOfPortals.item(s).getAttributes().getNamedItem("y").getNodeValue();
						assertEquals("3",x);
						assertEquals("95",y);
					} // if
					// Check UOM campuse right entance
					else if(name.equalsIgnoreCase("uom_campus_entrance_right"))
					{
						x = listOfPortals.item(s).getAttributes().getNamedItem("x").getNodeValue();
						y = listOfPortals.item(s).getAttributes().getNamedItem("y").getNodeValue();
						assertEquals("10",x);
						assertEquals("95",y);
					} // else if
				} // if
			
			    if(zone.equalsIgnoreCase("int_uom_campus"))
			    {
					name = listOfPortals.item(s).getAttributes().getNamedItem("ref").getNodeValue();
					
					// Check left exit of uom_campus
					if(name.equalsIgnoreCase("entrance_left"))
					{
						x = listOfPortals.item(s).getAttributes().getNamedItem("x").getNodeValue();
						y = listOfPortals.item(s).getAttributes().getNamedItem("y").getNodeValue();
						assertEquals("8",x);
						assertEquals("30",y);
					} // if

					// check right exit of uom_campus
					if(name.equalsIgnoreCase("entrance_right"))
					{
						x = listOfPortals.item(s).getAttributes().getNamedItem("x").getNodeValue();
						y = listOfPortals.item(s).getAttributes().getNamedItem("y").getNodeValue();
						assertEquals("21",x);
						assertEquals("30",y);
					} // if
					
			    }// if
				
			}// end of for loop with s var
		} // if
	} // testLandingCoordinates
} // class 
