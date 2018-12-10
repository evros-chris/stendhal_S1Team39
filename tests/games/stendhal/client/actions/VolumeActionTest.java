package games.stendhal.client.actions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.client.ClientSingletonRepository;
import games.stendhal.client.StendhalClient;
import games.stendhal.client.gui.MockUserInterface;
import games.stendhal.client.gui.UserInterface;
import games.stendhal.client.util.UserInterfaceTestHelper;

public class VolumeActionTest {

	private final String[] response1 = {"master -> 0"};	
	private final String[] response2 = {"Please type \"/volume show\" for a valid list of groups"};
	private final String[] response3 = {"Please use /volume for help."};
	
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
		final VolumeAction action = new VolumeAction();
		action.execute(new String[] {null}, null);
		assertEquals(response1[0], getInterface().getLastEventLine());
		
		action.execute(new String[] {"music", "5"}, null);
		assertEquals(response2[0], getInterface().getLastEventLine());
		
		action.execute(new String[] {"ambient", null}, null);
		assertEquals(response3[0], getInterface().getLastEventLine());
	}

	/**
	 * Tests for getMaximumParameters().
	 */
	@Test
	public void testGetMaximumParameters() {
		final VolumeAction action = new VolumeAction();
		assertThat(action.getMaximumParameters(), is(2));
	}

	/**
	 * Tests for getMinimumParameters.
	 */
	@Test
	public void testGetMinimumParameters() {
		final VolumeAction action = new VolumeAction();
		assertThat(action.getMinimumParameters(), is(0));
	}
	
}
