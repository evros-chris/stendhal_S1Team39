package games.stendhal.server.maps.ados.uomcampus;

import java.util.Map;

import games.stendhal.common.Direction;
import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.Outfit;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.npc.SpeakerNPC;
/**
 * <p>Creates a normal version of mr ross in the ross house.
 */
public class LonJathamNPC implements ZoneConfigurator {

	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	@Override
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
		createLonJathamNPC(zone);

	}

	public void createLonJathamNPC(final StendhalRPZone zone) {
	
		// No Path No moving NPC
		final SpeakerNPC npc = new SpeakerNPC("Lon Jatham") {
			@Override
			protected void createPath() {
				setPath(null);

			}

			// Create Dialog
			@Override
			protected void createDialog() {
			
					addGreeting("GOOOOOOOD MORRRRNINGG!");
					addJob("I'm a lecturer in University of Manchester.");
					addHelp("If you have question about studying Computer Science in Unversity of Manchester. Please ask ME!");
					addReply("firstyearcourse",
                        "We offer Java, Comp Architecture, Comp Engineering, distributing system, Discrete Maths etc, for first year students");
					addReply("secondyearcourse",
                        "We offer Algorithms, Machine learning, Graphics, Distributed computing, mobile system, logic etc for second year students");
					addReply("thirdyearcourse",
                		"We offer a range of courses for you to choose, please visit our website for more details.");
					addOffer("Sorry I do not have anything to offer you, but there are a place to eat in UoM - the Byte Cafe.");
					addGoodbye("Bye, nice to meet you.");
			}

			/*
			 * (non-Javadoc)
			 * @see games.stendhal.server.entity.npc.SpeakerNPC#onGoodbye(games.stendhal.server.entity.RPEntity)
			 */
			@Override
			protected void onGoodbye(RPEntity player) {
				setDirection(Direction.DOWN);
			}

		};

		// SET NPC outfit and direction
		npc.setOutfit(new Outfit(0, 9, 7, 48, 1));
		npc.setPosition(15, 26);
		npc.setDirection(Direction.DOWN);
		npc.initHP(100);
		// Add Description
		npc.setDescription("Lecturer teaching Java in University of Manchester School of Computer Science.");
		zone.add(npc);
	}

}