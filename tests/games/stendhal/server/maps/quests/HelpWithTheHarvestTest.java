package games.stendhal.server.maps.quests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.common.Direction;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.core.rp.StendhalRPAction;
import games.stendhal.server.entity.mapstuff.block.Block;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.server.maps.ados.forest.FarmerNPC;
import games.stendhal.server.maps.ados.forest.SickleingHalfelfNPC;
import utilities.PlayerTestHelper;
import utilities.ZonePlayerAndNPCTestImpl;

public class HelpWithTheHarvestTest extends ZonePlayerAndNPCTestImpl {

	private static String questSlot = "helpwiththeharvest";

	private SpeakerNPC npc = null;
	private Engine en = null;
	
	private Player player = null;
	
	private StendhalRPZone zone;
	private static final String ZONE_NAME = "0_ados_forest_w2";
	private Block cart;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
		setupZone(ZONE_NAME);
	}

	public HelpWithTheHarvestTest() {
		super(ZONE_NAME, "Eheneumniranin");
	}

	@Override
	@Before
	public void setUp() {
		zone = new StendhalRPZone(ZONE_NAME);
		MockStendlRPWorld.get().addRPZone(zone);
		
		new FarmerNPC().configureZone(zone, null);
		new SickleingHalfelfNPC().configureZone(zone, null);
		final AbstractQuest quest = new HelpWithTheHarvest();
		quest.addToWorld();
		
		
		cart = new Block(true, "hay_cart");
		cart.setPosition(10, 10);
		cart.setDescription("You see a straw cart. Can you manage to push it to Karl's barn?");
		zone.add(cart);
       
		player = PlayerTestHelper.createPlayer("bob");
		player.setDirection(Direction.build(2));
	}

	/**
	 * Tests location of carts.
	 */
	@Test
	public void testCartsLocation() {
		String cartDescription = "You see a straw cart. Can you manage to push it to Karl's barn?";
        assertEquals(zone.getEntityAt(87, 100).getDescription(), cartDescription);
        assertEquals(zone.getEntityAt(79, 106).getDescription(), cartDescription);
	} // testCartsPush
	
	/**
	 * Tests pushing of carts from outside of the zone.
	 */
	@Test
	public void testCartsPush() {
        assertEquals(cart, zone.getEntityAt(10, 10));;
        StendhalRPAction.placeat(zone, player, 10, 10);
        assertEquals(10, player.getX());
        assertEquals(10, player.getY());
        assertEquals(11, cart.getX());
        assertEquals(10, cart.getY());       
	} // testCartsPush
	
} // HelpWithHarvestTest
