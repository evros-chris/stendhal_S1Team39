package games.stendhal.server.maps.ados.uomcampus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.common.parser.Expression;
import games.stendhal.common.parser.ExpressionType;
import games.stendhal.common.parser.Sentence;
import games.stendhal.common.parser.SentenceImplementation;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.NPC;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.Log4J;
import utilities.PlayerTestHelper;

public class LonJathamNPCTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Log4J.init();
		MockStendlRPWorld.get();
	}

	/**
	 * Tests for configureZone.
	 */
	@Test
	public void testConfigureZone() {

		SingletonRepository.getRPWorld();
		final LonJathamNPC lonjathamConfigurator = new LonJathamNPC();

		final StendhalRPZone zone = new StendhalRPZone("testzone");
		lonjathamConfigurator.configureZone(zone, null);
		assertFalse(zone.getNPCList().isEmpty());
		final NPC lonJatham = zone.getNPCList().get(0);
		assertThat(lonJatham.getName(), is("Lon Jatham"));
		assertThat(lonJatham.getDescription(), is("Lecturer teaching Java in University of Manchester School of Computer Science."));
	}

	/**
	 * Tests for hiandBye.
	 */
	@Test
	public void testHiandBye() {
		SingletonRepository.getRPWorld();
		final LonJathamNPC lonjathamConfigurator = new LonJathamNPC();
		final StendhalRPZone zone = new StendhalRPZone("testzone");
		lonjathamConfigurator.configureZone(zone, null);
		final SpeakerNPC LonJatham = (SpeakerNPC) zone.getNPCList().get(0);
		assertThat(LonJatham.getName(), is("Lon Jatham"));
		final Engine engine = LonJatham.getEngine();
		engine.setCurrentState(ConversationStates.IDLE);

		Sentence sentence = new SentenceImplementation(new Expression("hi", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence);
		assertThat(engine.getCurrentState(), is(ConversationStates.ATTENDING));
		assertThat(getReply(LonJatham), is("GOOOOOOOD MORRRRNINGG!"));

		sentence = new SentenceImplementation(new Expression("bye", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence);
		assertThat(engine.getCurrentState(), is(ConversationStates.IDLE));
		assertThat(getReply(LonJatham), is("Bye, nice to meet you."));
	}

	@Test
	public void testReply() {
		SingletonRepository.getRPWorld();
		final LonJathamNPC lonjathamConfigurator = new LonJathamNPC();
		final StendhalRPZone zone = new StendhalRPZone("testzone");
		lonjathamConfigurator.configureZone(zone, null);
		final SpeakerNPC LonJatham = (SpeakerNPC) zone.getNPCList().get(0);
		assertThat(LonJatham.getName(), is("Lon Jatham"));
		final Engine engine = LonJatham.getEngine();
		engine.setCurrentState(ConversationStates.IDLE);
		
		Sentence sentence = new SentenceImplementation(new Expression("hi", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence);
		assertThat(engine.getCurrentState(), is(ConversationStates.ATTENDING));
		assertThat(getReply(LonJatham), is("GOOOOOOOD MORRRRNINGG!"));
		
	    sentence = new SentenceImplementation(new Expression("firstyearcourse", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence);
		assertThat(engine.getCurrentState(), is(ConversationStates.ATTENDING));
		assertThat(getReply(LonJatham), is("We offer Java, Comp Architecture, Comp Engineering, distributing system, Discrete Maths etc, for first year students"));
		
		Sentence sentence2 = new SentenceImplementation(new Expression("secondyearcourse", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence2);
		assertThat(engine.getCurrentState(), is(ConversationStates.ATTENDING));
		assertThat(getReply(LonJatham), is("We offer Algorithms, Machine learning, Graphics, Distributed computing, mobile system, logic etc for second year students"));
		
		Sentence sentence3 = new SentenceImplementation(new Expression("thirdyearcourse", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence3);
		assertThat(engine.getCurrentState(), is(ConversationStates.ATTENDING));
		assertThat(getReply(LonJatham), is("We offer a range of courses for you to choose, please visit our website for more details."));
		
	}
	
	@Test
	public void testJobHelpOffer() {
		SingletonRepository.getRPWorld();
		final LonJathamNPC lonjathamConfigurator = new LonJathamNPC();
		final StendhalRPZone zone = new StendhalRPZone("testzone");
		lonjathamConfigurator.configureZone(zone, null);
		final SpeakerNPC LonJatham = (SpeakerNPC) zone.getNPCList().get(0);
		assertThat(LonJatham.getName(), is("Lon Jatham"));
		final Engine engine = LonJatham.getEngine();
		engine.setCurrentState(ConversationStates.IDLE);
		
		Sentence sentence = new SentenceImplementation(new Expression("hi", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence);
		assertThat(engine.getCurrentState(), is(ConversationStates.ATTENDING));
		assertThat(getReply(LonJatham), is("GOOOOOOOD MORRRRNINGG!"));
		
	    sentence = new SentenceImplementation(new Expression("job", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence);
		assertThat(engine.getCurrentState(), is(ConversationStates.ATTENDING));
		assertThat("job text", getReply(LonJatham),
				is("I'm a lecturer in University of Manchester."));

		sentence = new SentenceImplementation(new Expression("help", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence);
		assertThat(engine.getCurrentState(), is(ConversationStates.ATTENDING));
		assertThat("help text", getReply(LonJatham),
				is("If you have question about studying Computer Science in Unversity of Manchester. Please ask ME!"));
		
		sentence = new SentenceImplementation(new Expression("offer", ExpressionType.VERB));
		engine.step(PlayerTestHelper.createPlayer("bob"), sentence);
		assertThat(engine.getCurrentState(), is(ConversationStates.ATTENDING));
		assertThat("offer text", getReply(LonJatham), equalTo("Sorry I do not have anything to offer you, but there are a place to eat in UoM - the Byte Cafe."));		
	}

}