// Written by Cheuk Nam Ryan Ho for testing the drop of mage gnome is potion not minor potion
// with quantity 2-4 probability 40.0
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
		// Create Mage Gnome
		Creature testgnome = SingletonRepository.getEntityManager().getCreature("mage gnome");
		// to ensure a mage gnome is created, no more null pointer exception!!
		assertTrue(testgnome != null);
		boolean flag = false;
		
		// check drop item
		for (DropItem item : testgnome.dropsItems)
		{
			// for each item the mage gnome drops, if one of it is potion, than check its prob, min, max
			if (item.name.equals("potion"))
			{
				flag = true;
				assertTrue(item.probability == 40.0);
				assertTrue(item.min == 2);
				assertTrue(item.max == 4);
			}// if
			// no more minor potion
			if (item.name.equals("minor potion"))
			{
				flag = false;
				
			}// if
		}// for
		assertTrue(flag == true);
		
	}// test

}// class