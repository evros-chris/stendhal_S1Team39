package games.stendhal.server.entity.item;


import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertSame;


import static utilities.SpeakerNPCTestHelper.getReply;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.creature.Creature;

import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.server.maps.fado.tavern.MaidNPC;
import games.stendhal.server.maps.quests.AbstractQuest;
import games.stendhal.server.maps.quests.Soup;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.RPClass.CreatureTestHelper;
import utilities.RPClass.ItemTestHelper;


public class InvisibilityRingTest {

	// Initialising player and NPC objects
    private Player player = null;
    private SpeakerNPC npc = null;
    private Engine en = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        QuestHelper.setUpBeforeClass();
        ItemTestHelper.generateRPClasses();
       
        MockStendlRPWorld.get();
        CreatureTestHelper.generateRPClasses();
    }

    @Before
    public void setUp() {
        final StendhalRPZone zone = new StendhalRPZone("int_fado_tavern");
        MockStendlRPWorld.get().addRPZone(zone);
        new MaidNPC().configureZone(zone, null);


        AbstractQuest quest = new Soup();
        quest.addToWorld();

        player = PlayerTestHelper.createPlayer("bob");
    }
   
    @Test
    public void testInvisibileToNPC()
    {
        npc = SingletonRepository.getNPCList().get("Old Mother Helena");
        en = npc.getEngine();
        player.setXP(100);
        // Loading player and NPC
       
        InvisibilityRing invisibilityring = new InvisibilityRing();
        player.equip("bag", invisibilityring);
        // Player equips invisibility ring
        
        en.step(player, "hi");
        // Player tries to talk to the NPC
        
        assertEquals(npc.getAttending(), null);
        // Test to see if the NPC replies, passes if no reply is given when wearing the invisibility ring
        
        player.drop("invisibility ring", 1);
        // Player drops the invisibility ring
        
        en.step(player, "hi");
        // PLayer tries to talk to the NPC
        
        assertEquals("Hello, stranger. You look weary from your travels. I know what would #revive you.", getReply(npc));
        // Test to see if the NPC replies, passes if there is a reply as player is not wearing the ring
    }
   
    /**
     * Tests for getNearestEnemy.
     */
    @Test
    public void testInvisibleToCreatures() {

    	// Initialise two players
        final Player first = PlayerTestHelper.createPlayer("bob");
        final Player second = PlayerTestHelper.createPlayer("tom");
        first.setPosition(6, 0);
        second.setPosition(7, 0);
        
        // Initialising a creature object
        final MockCreature creature1 = new MockCreature();
        
        // Setting up the zone
        final StendhalRPZone zone = new StendhalRPZone("test", 20 , 20);
        zone.add(creature1);
        zone.add(first);
        zone.add(second);
        enemies.add(second);
        enemies.add(first);
       
        // Test to see which player the creature attacks, passes if it is the first player
        // The creature attacks the closest player, and the first player is closer to it than the second
        assertSame(first, creature1.getNearestEnemy(6));
        
        // The first player equips the invisibility ring
        InvisibilityRing invisibilityring = new InvisibilityRing();
        first.equip("bag", invisibilityring);
       
        // Test to see which player the creature attacks, passes if it is the second player
        // due to the fact that the first player is wearing the ring, even though he is closer than the second
        assertSame(second, creature1.getNearestEnemy(6));
       
    }

    private static List<RPEntity> enemies  = new LinkedList<RPEntity>();
    private static class MockCreature extends Creature {

        @Override
        public List<RPEntity> getEnemyList() {

            return enemies;
        }
    }
}