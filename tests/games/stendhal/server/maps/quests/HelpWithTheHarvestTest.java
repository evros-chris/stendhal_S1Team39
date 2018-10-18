package games.stendhal.server.maps.quests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.common.Direction;
import games.stendhal.server.core.engine.StendhalRPWorld;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.core.rp.StendhalRPAction;
import games.stendhal.server.entity.mapstuff.block.Block;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.server.maps.ados.forest.FarmerNPC;
import games.stendhal.server.maps.ados.forest.SickleingHalfelfNPC;
import utilities.PlayerTestHelper;
import utilities.ZonePlayerAndNPCTestImpl;

public class HelpWithTheHarvestTest extends ZonePlayerAndNPCTestImpl {
	
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
        //Creating world and zone. 
		final StendhalRPWorld world = MockStendlRPWorld.get();
		zone = new StendhalRPZone(ZONE_NAME);
		
		//Creating NPCs needed for quest then creating quest.
		new FarmerNPC().configureZone(zone, null);
		new SickleingHalfelfNPC().configureZone(zone, null);
		final AbstractQuest quest = new HelpWithTheHarvest();
		
		//Adding zone and quest to world.
		world.addRPZone(zone);
		quest.addToWorld();      		
		
		//Initializing collision map so carts know if they can mover.
		zone.collisionMap.init(110, 110);
		
		//Creating player with given direction so he can push the cart.
		player = PlayerTestHelper.createPlayer("bob");
		player.setDirection(Direction.build(2));
	}

	/**
	 * Tests if collision map is initialised properly.
	 */
	@Test
	public void testCollisionMap() {
		assertNotNull(zone.collisionMap);
		assertFalse(zone.collisionMap.getHeight() == 0);
		assertFalse(zone.collisionMap.getWidth() == 0);
	} // testCollisionMap
	
	/**
	 * Tests location of straw carts.
	 */
	@Test
	public void testCartsLocation() {
		String cartDescription = "You see a straw cart. Can you manage to push it to Karl's barn?";
		
		//Testing to see if the carts are at the right location.
        assertEquals(zone.getEntityAt(87, 100).getDescription(), cartDescription);
        assertEquals(zone.getEntityAt(79, 106).getDescription(), cartDescription);
	} // testCartsLocation
	
	/**
	 * Tests pushing of carts from outside of the zone.
	 */
	@Test
	public void testCartsPush() {;
	    //Saving the reference to one of the carts in a pointer so we can check its location later.
	    cart = (Block)zone.getEntityAt(87, 100);
	
	    //Placing player on top of cart and checking position of player and cart. Cart should move to the right.
        StendhalRPAction.placeat(zone, player, 87, 100);
        assertEquals(87, player.getX());
        assertEquals(100, player.getY());
        assertEquals(88, cart.getX());
        assertEquals(100, cart.getY());       
	} // testCartsPush
	
} // HelpWithHarvestTest
