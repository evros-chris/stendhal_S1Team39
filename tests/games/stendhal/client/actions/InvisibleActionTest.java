package games.stendhal.client.actions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import games.stendhal.client.MockStendhalClient;
import games.stendhal.client.StendhalClient;
import marauroa.common.game.RPAction;

// Test the Invisible Action
public class InvisibleActionTest {

	@Before
	public void setUpClass() throws Exception {
		SlashActionRepository.register();
	}


	@After
	public void tearDown() throws Exception {
		StendhalClient.resetClient();
	}
	/**
	 * Tests for execute.
	 */
	@Test
	public void testExecute() {
		new MockStendhalClient() {
			@Override
			public void send(final RPAction action) {
				assertEquals("invisible", action.get("type"));
			}
		};
		final SlashAction action = SlashActionRepository.get("invisible");
		assertTrue(action.execute(null, null));
	}

	/**
	 * Tests for getMaximumParameters.
	 */
	@Test
	public void testGetMaximumParameters() {
		final SlashAction action = SlashActionRepository.get("invisible");
		assertEquals(action.getMaximumParameters(), 0);
	}

	/**
	 * Tests for getMinimumParameters.
	 */
	@Test
	public void testGetMinimumParameters() {
		final SlashAction action = SlashActionRepository.get("invisible");
		assertEquals(action.getMinimumParameters(), 0);
	}
}