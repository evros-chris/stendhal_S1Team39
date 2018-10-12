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
package games.stendhal.server.entity.creature;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.creature.impl.DropItem;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.Log4J;
import marauroa.server.game.db.DatabaseFactory;
import utilities.RPClass.CreatureTestHelper;


public class MageGnomeTest {

	@Before
	public void setUp() {
		MockStendlRPWorld.get();
		CreatureTestHelper.generateRPClasses();
		Log4J.init();
		new DatabaseFactory().initializeDatabase();
	}

	@After
	public void tearDown() {
		MockStendlRPWorld.reset();
	}

	@Test
	public void test() {;
		Creature gnome = SingletonRepository.getEntityManager().getCreature("mage gnome");
		assertTrue(gnome != null);
		boolean flag = false;
		
		for (DropItem item : gnome.dropsItems)
		{
			if (item.name.equals("potion"))
				flag = true;
			System.out.println(item.name);
			System.out.println(flag);
		}
		assertTrue(flag == true);
		
		//fail("Not yet implemented");
		
	}

}