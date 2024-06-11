package game_engine.model.map.objects;

import game_engine.model.map.GameMap;


/**
 * Class models a checkpoint which can be used as a MapObject.
 *
 * @author  Christian-2003
 */
public class Checkpoint extends MapObject {

    /**
     * Constructor instantiates a new {@link Checkpoint} with the passed
     * arguments.
     *
     * @param visible                   Whether the Checkpoint is visible.
     * @param tangible                  Whether the Checkpoint is tangible.
     * @param x                         X-coordinate of the Checkpoint within
     *                                  the chunk.
     * @param y                         Y-coordinate of the Checkpoint within
     *                                  the chunk.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public Checkpoint(final boolean visible, final boolean tangible, final int x, final int y) throws IllegalArgumentException {
        super(visible, tangible, x, y);
    }

    /**
     * Constructor instantiates a new {@link Checkpoint} with the passed
     * arguments.
     *
     * @param visible                   Whether the Checkpoint is visible.
     * @param tangible                  Whether the Checkpoint is tangible.
     * @param x                         X-coordinate of the Checkpoint within
     *                                  the chunk.
     * @param y                         Y-coordinate of the Checkpoint within
     *                                  the chunk.
     * @param texture                   Index (within {@link GameMap#textures})
     *                                  of the MapObject's texture.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public Checkpoint(final boolean visible, final boolean tangible, final int x, final int y, final int texture) throws IllegalArgumentException {
        super(visible, tangible, x, y, texture);
    }

    /**
     * Constructor instantiates a new {@link Checkpoint} and copies the
     * attributes of the passed Checkpoint to the generated instance.
     *
     * @param checkpoint            Checkpoint whose attributes shall be copied
     *                              to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public Checkpoint(final Checkpoint checkpoint) throws NullPointerException {
        super(checkpoint);
    }

}
