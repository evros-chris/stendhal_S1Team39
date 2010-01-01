package games.stendhal.server.events;

import games.stendhal.server.entity.item.Item;

import java.util.Collection;

import marauroa.common.game.Definition;
import marauroa.common.game.RPClass;
import marauroa.common.game.RPEvent;
import marauroa.common.game.RPSlot;
import marauroa.common.game.SyntaxException;
import marauroa.common.game.Definition.DefinitionClass;
import marauroa.common.game.Definition.Type;

import org.apache.log4j.Logger;
/**
 * shows a list of items with image and stats
 *
 * @author hendrik
 */
public class ShowItemListEvent extends RPEvent {
	private static final String RPCLASS_NAME = "show_item_list";
	private static final String CONTENT_SLOT = "content";
	private static final String CAPTION = "caption";
	private static final String TITLE = "title";
	

	/** the logger instance. */
	private static final Logger logger = Logger.getLogger(ShowItemListEvent.class);

	/**
	 * Creates the rpclass.
	 */
	public static void generateRPClass() {
		try {
			final RPClass rpclass = new RPClass(RPCLASS_NAME);
			rpclass.add(DefinitionClass.ATTRIBUTE, TITLE, Type.STRING, Definition.PRIVATE);
			rpclass.add(DefinitionClass.ATTRIBUTE, CAPTION, Type.STRING, Definition.PRIVATE);
			rpclass.addRPSlot(CONTENT_SLOT, 999);
		} catch (final SyntaxException e) {
			logger.error("cannot generateRPClass", e);
		}
	}

	/**
	 * Creates a new ShowItemListEvent.
	 *
	 * @param title title of image viewer
	 * @param caption caption to display above the table 
	 * @param items items
	 */
	public ShowItemListEvent(final String title, String caption, final Collection<Item> items) {
		super(RPCLASS_NAME);
		super.put(TITLE, title);
		if (caption != null) {
			super.put(CAPTION, caption);
		}
		super.addSlot(CONTENT_SLOT);
		RPSlot slot = super.getSlot(CONTENT_SLOT);
		for (Item item : items) {
			slot.add(item);
		}
	}
}
