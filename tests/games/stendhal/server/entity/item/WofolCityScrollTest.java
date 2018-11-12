package games.stendhal.server.entity.item;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPWorld;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.scroll.*;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.Log4J;
import marauroa.common.game.RPObject.ID;
import utilities.PlayerTestHelper;
import utilities.RPClass.ItemTestHelper;

public class WofolCityScrollTest {
	
	private static StendhalRPZone zone;
	private static final String ZONE_NAME = "0_ados_forest_w2"; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Log4J.init();
		final StendhalRPWorld world = MockStendlRPWorld.get();
		ItemTestHelper.generateRPClasses();
		zone = new StendhalRPZone(ZONE_NAME);
		world.addRPZone(zone);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		PlayerTestHelper.removeAllPlayers();
	}
		 
	/**
	 * Tests for description.
	 */
	@Test
	public void testDescribe() {
		final MarkedScroll scroll = (MarkedScroll) SingletonRepository.getEntityManager().getItem("wofol city scroll");
		assertThat(scroll.describe(), is("You see a §'wofol city scroll', it will take you back to wofol city. Upon it is written: -1_semos_mine_nw"));
	} ///testDescribe

	/**
	 * Tests for onUsed.
	 */
	@Test
	public void testOnUsed() {
		final Player bob = PlayerTestHelper.createPlayer("bob");
		PlayerTestHelper.registerPlayer(bob, zone);
		
		final TeleportScroll scroll = (TeleportScroll) SingletonRepository.getEntityManager().getItem("wofol city scroll");
		scroll.setID(new ID(1, "wofol city scroll"));
		zone.add(scroll);
        scroll.onUsed(bob);
        

		assertEquals(52, bob.getX());
        assertEquals(68, bob.getY());
	} //testOnUsed

} // WofolCityScrollTest
