package games.stendhal.client.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import games.stendhal.client.ClientSingletonRepository;
import games.stendhal.client.MockStendhalClient;
import games.stendhal.client.StendhalClient;
import games.stendhal.client.gui.MockUserInterface;
import games.stendhal.client.gui.UserInterface;
import games.stendhal.client.scripting.ChatLineParser;
import games.stendhal.client.util.UserInterfaceTestHelper;
import marauroa.common.game.RPAction;

/**
 * Test the XMLAction class.
 *
 * @author Petar Artinov
 */
public class XMLActionTest {
	
	private static SlashAction action;
	
	@BeforeClass
	public static void init() {
		UserInterfaceTestHelper.resetUserInterface();
		
		new MockStendhalClient() {
			@Override
			public void send(final RPAction action) {
				assertEquals("tellall", action.get("type"));
				assertEquals("Hello", action.get("text"));

			}
		};
		action = SlashActionRepository.get("tellall");
	}

	@After
	public void tearDown() throws Exception {
		StendhalClient.resetClient();
	}

	private static MockUserInterface getInterface() {
		// Check the message
		UserInterface ui = ClientSingletonRepository.getUserInterface();
		// sanity check
		if (ui instanceof MockUserInterface) {
			return (MockUserInterface) ui;
		}
		fail();
		// just for the compiler
		return null;
	}

    
	/**
	 * Tests for execute.
	 * @throws throws SAXException, IOException 
	 */
	@Test
	public void testExecute() {
		// create client
		new MockStendhalClient() {
			@Override
			public void send(final RPAction action) {
				assertEquals("tellall", action.get("type"));
				assertEquals("Hello", action.get("text"));

			}
		};
		
		assertTrue(action.execute(null, "Hello"));

	}

	/**
	 * Tests for getMaximumParameters.
	 */
	@Test
	public void testGetMaximumParameters() {
		assertEquals(0, action.getMaximumParameters());
	}

	/**
	 * Tests for getMinimumParameters.
	 */
	@Test
	public void testGetMinimumParameters() {
		assertEquals(0, action.getMinimumParameters());
	}

	/**
	 * Tests for fromChatline.
	 * @throws IOException 
	 * @throws SAXException 
	 */
	@Test
	public void testFromChatline() throws SAXException, IOException {
		// create client
		new MockStendhalClient() {
			@Override
			public void send(final RPAction action) {
				assertEquals("tellall", action.get("type"));
				assertEquals("", action.get("text"));

			}
		};
		SlashActionRepository.register();
		ChatLineParser.parseAndHandle("/tellall");
	}
}
