/*
 * @(#) games/stendhal/client/entity/Chest2DView.java
 *
 * $Id$
 */

package games.stendhal.client.entity;

//
//

import games.stendhal.client.Sprite;
import games.stendhal.client.SpriteStore;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;


/**
 * The 2D view of a chest.
 */
public class Ring2DView extends Item2DView {
	private Ring ring;
	private Sprite working;
	private Sprite broken;
	
	/**
	 * Create a 2D view of a chest.
	 *
	 * @param	ring		The entity to render.
	 */
	public Ring2DView(final Ring ring) {
		super(ring);
		this.ring=ring;
	}


	//
	// AnimatedStateEntity2DView
	//

	/**
	 * Populate named state sprites.
	 * @param	map		The map to populate.
	 */
	@Override
	protected void buildRepresentation() {
		String name = entity.getEntityClass();
		String subclass = entity.getEntitySubClass();

		if (subclass != null) {
			name += "/" + subclass;
		}
		
		String resource = "data/sprites/items/" + name + ".png";
		SpriteStore store = SpriteStore.get();

		working=store.getAnimatedSprite(resource, 0, 1, 1, 1, 0L, false);
		broken=store.getAnimatedSprite(resource, 1, 1, 1, 1, 0L, false);
	}

	@Override
	public Sprite getSprite() {
		if(ring.isWorking()) {
			return working;
		} else {
			return broken;			
		}
	}

	//
	// Entity2DView
	//

	/**
	 * Get the 2D area that is drawn in.
	 *
	 * @return	The 2D area this draws in.
	 */
	@Override
	public Rectangle2D getDrawnArea() {
		return new Rectangle.Double(getX(), getY(), 1.0, 1.0);
	}


	/**
	 * Determines on top of which other entities this entity should be
	 * drawn. Entities with a high Z index will be drawn on top of ones
	 * with a lower Z index.
	 * 
	 * Also, players can only interact with the topmost entity.
	 * 
	 * @return	The drawing index.
	 */
	@Override
	public int getZIndex() {
		return 5000;
	}
}
