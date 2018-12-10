package games.stendhal.client.actions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.client.ClientSingletonRepository;
import games.stendhal.client.MockStendhalClient;
import games.stendhal.client.StendhalClient;
import games.stendhal.client.gui.MockUserInterface;
import games.stendhal.client.gui.UserInterface;
import games.stendhal.client.util.UserInterfaceTestHelper;
import marauroa.common.game.RPAction;
import static org.hamcrest.core.StringContains.containsString;

public class ClientInfoActionTest {

	private final String response = "\n" + 
			"Stendhal: 1.29uom\n" + 
			"OS: Mac OS X  10.14.1 x86_64\n" + 
			"Java-Runtime: Java(TM) SE Runtime Environment 9+181 from /Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home\n" + 
			"Java-VM: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 9+181\n";	
	
	@BeforeClass
	public static void init() {
		UserInterfaceTestHelper.resetUserInterface();
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
	 */
	@Test
	public void testExecute() {
		new MockStendhalClient() {
			@Override
			public void send(final RPAction action) {
				assertEquals("support", action.get("type"));

			}
		};
		
		final ClientInfoAction action = new ClientInfoAction();
		action.execute(new String[] {null}, null);
		assertThat(getInterface().getLastEventLine(), containsString(response));
		
		
	}

	/**
	 * Tests for getMaximumParameters().
	 */
	@Test
	public void testGetMaximumParameters() {
		final ClientInfoAction action = new ClientInfoAction();
		assertThat(action.getMaximumParameters(), is(0));
	}

	/**
	 * Tests for getMinimumParameters.
	 */
	@Test
	public void testGetMinimumParameters() {
		final ClientInfoAction action = new ClientInfoAction();
		assertThat(action.getMinimumParameters(), is(0));
	}
	
}
