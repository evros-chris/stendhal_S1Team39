package games.stendhal.client.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import games.stendhal.client.ClientSingletonRepository;
import games.stendhal.client.MockStendhalClient;
import games.stendhal.client.StendhalClient;
import games.stendhal.client.gui.MockUserInterface;
import games.stendhal.client.gui.UserInterface;
import games.stendhal.client.scripting.ChatLineParser;
import games.stendhal.client.util.UserInterfaceTestHelper;
import games.stendhal.server.core.config.XMLActionLoader;
import games.stendhal.server.core.config.XMLUtil;
import marauroa.common.game.RPAction;

/**
 * Test the XMLAction class.
 *
 * @author Petar Artinov
 */
public class XMLActionTest {
	
	private static XMLAction action = null;
	
	@BeforeClass
	public static void init() throws SAXException, IOException {
		UserInterfaceTestHelper.resetUserInterface();
		
		FileInputStream input_xml;
		input_xml = new FileInputStream("data/conf/slashActions.xml");
        final Document doc = XMLUtil.parse(input_xml);
		
		Element actionData = (Element) doc.getElementsByTagName("WhoAction").item(1);
		action = XMLActionLoader.readActionData(actionData);
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
				assertEquals("who", action.get("type"));
				assertNull(action.get("target"));
			}
		};
/*      NEEDS CHANGING SO IT WORKS WITH XML ACTION WHO
		final SummonAtAction action = new SummonAtAction();
		assertTrue(action.execute(new String[]{"player", "bag", "5"}, "money"));
		assertNull(getInterface().getLastEventLine());*/
		
		assertTrue(action.execute(null, null));
		assertNull(getInterface().getLastEventLine());
	}

	/**
	 * Tests for getMaximumParameters.
	 */
	@Test
	public void testGetMaximumParameters() {
		//final SummonAtAction action = new SummonAtAction();
		assertEquals(0, action.getMaximumParameters());
	}

	/**
	 * Tests for getMinimumParameters.
	 */
	@Test
	public void testGetMinimumParameters() {
		//final SummonAtAction action = new SummonAtAction();
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
				assertEquals("who", action.get("type"));
				assertNull(action.get("target"));
			}
		};
		SlashActionRepository.register();
		ChatLineParser.parseAndHandle("/who");
	}
}
