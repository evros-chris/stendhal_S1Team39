package games.stendhal.client.actions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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

public class GMHelpActionTest {

	private final String[] response1 = {"\t\tList the jailed players and their sentences."};	
	private final String[] response2 = {"\t\t  This will make <testplayer> look like danter"};	
	private final String[] response3 = {"#/script #ResetSlot.class #player #slot : Resets the named slot such as !kills or !quests. Useful for debugging."};
	private final String[] response4 = {"$notsupport - Hi %s, sorry, but support cannot help with this issue. Please use #https://stendhalgame.org and the wiki #https://stendhalgame.org/wiki/Stendhal as information sources."};
	
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
		final GMHelpAction action = new GMHelpAction();
		action.execute(new String[] {null}, null);
		assertEquals(response1[0], getInterface().getLastEventLine());
		
		action.execute(new String[] {"alter"}, null);
		assertEquals(response2[0], getInterface().getLastEventLine());
		
		action.execute(new String[] {"script"}, null);
		assertEquals(response3[0], getInterface().getLastEventLine());
		
		action.execute(new String[] {"support"}, null);
		assertEquals(response4[0], getInterface().getLastEventLine());
		
		action.execute(new String[] {"sfajkflasf"}, null);
		assertNull(getInterface().getLastEventLine());
		
		action.execute(new String[] {"sfajkflasf", "dasds"}, null);
		assertNull(getInterface().getLastEventLine());
		/*for (int i = response1.length - 1; i >= 0; i--)
		{
          action.execute(new String[] {null}, null);
          assertEquals(response1[i], getInterface().getLastEventLine());
		} // for
		
		for (int i = response2.length - 1; i >= 0; i--)
		{
          action.execute(new String[] {"alter"}, null);
          assertEquals(response2[i], getInterface().getLastEventLine());
		} // for
		
		for (int i = response3.length - 1; i >= 0; i--)
		{
          action.execute(new String[] {"script"}, null);
          assertEquals(response3[i], getInterface().getLastEventLine());
		} // for*/
	}

	/**
	 * Tests for getMaximumParameters().
	 */
	@Test
	public void testGetMaximumParameters() {
		final GMHelpAction action = new GMHelpAction();
		assertThat(action.getMaximumParameters(), is(1));
	}

	/**
	 * Tests for getMinimumParameters.
	 */
	@Test
	public void testGetMinimumParameters() {
		final GMHelpAction action = new GMHelpAction();
		assertThat(action.getMinimumParameters(), is(0));
	}
	
}
