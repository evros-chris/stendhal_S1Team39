package games.stendhal.server.entity.item;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.scroll.*;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.Log4J;
import utilities.PlayerTestHelper;
import utilities.RPClass.ItemTestHelper;

public class WofolCityScrollTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Log4J.init();
		MockStendlRPWorld.get();
		ItemTestHelper.generateRPClasses();

		MockStendlRPWorld.get().addRPZone(new StendhalRPZone("-1_semos_mine_nw"));
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
		assertThat(scroll.describe(), is("You see a wofol city scroll, it will take you back to wofol city."));
	} ///testDescribe

	/**
	 * Tests for onUsed.
	 */
	@Test
	public void testOnUsed() {
		final Player bob = PlayerTestHelper.createPlayer("bob");
		final MarkedScroll scroll = (MarkedScroll) SingletonRepository.getEntityManager().getItem("wofol city scroll");
        scroll.onUsed(bob);

		assertEquals(52, bob.getX());
        assertEquals(68, bob.getY());
	} //testOnUsed

} // WofolCityScrollTest
