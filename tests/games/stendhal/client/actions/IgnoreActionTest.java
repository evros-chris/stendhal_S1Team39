/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.client.actions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertThat;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.client.MockStendhalClient;

import games.stendhal.client.StendhalClient;

import marauroa.common.game.RPAction;

public class IgnoreActionTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
				assertEquals("ignore", action.get("type"));
				assertEquals("schnick", action.get("target"));
				assertEquals(null, action.get("list"));
				assertEquals("6", action.get("duration"));
				assertEquals("poop", action.get("reason"));
			}
		};
		final IgnoreAction action = new IgnoreAction();
		
		assertTrue(action.execute(new String[] {"schnick", "6"}, "poop"));
	}


	/**
	 * Tests for getMaximumParameters.
	 */
	@Test
	public void testGetMaximumParameters() {
		final IgnoreAction action = new IgnoreAction();
		assertThat(action.getMaximumParameters(), is(2));
	}

	/**
	 * Tests for getMinimumParameters.
	 */
	@Test
	public void testGetMinimumParameters() {
		final IgnoreAction action = new IgnoreAction();
		assertThat(action.getMinimumParameters(), is(0));
	}

}
