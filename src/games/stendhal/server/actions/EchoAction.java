/* $Id$ */
package games.stendhal.server.actions;

import static games.stendhal.common.constants.Actions.ECHO;
import static games.stendhal.common.constants.Actions.N_TIMES;
import static games.stendhal.common.constants.Actions.VALUE;

import games.stendhal.server.core.engine.GameEvent;
import games.stendhal.server.entity.Outfit;
import games.stendhal.server.entity.player.Player;
import marauroa.common.game.RPAction;

public class EchoAction implements ActionListener {

	public static void register() {
		CommandCenter.register(ECHO, new EchoAction());
	}

	@Override
	public void onAction(final Player player, final RPAction action) {
		if (action.has(VALUE)) {
			player.sendPrivateText(stringMultiply(action.get(VALUE), action.getInt(N_TIMES)));
		}
	}

	private static String stringMultiply(String s, int n) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; ++i){
			sb.append(s + " ");
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}
}
