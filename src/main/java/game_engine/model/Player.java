package game_engine.model;

import java.awt.*;


/**
 * TODO: Add description.
 *
 * @author  TODO: Add author.
 */
public class Player extends Entity {

    /**
     * Constructor instantiates a new {@link Player} with the passed arguments.
     *
     * @param visible                   Whether the Player is visible.
     * @param tangible                  Whether the Player is tangible.
     * @param hitBox                    Dimensions of the hit box for the Player.
     * @param size                      Size of the Player.
     * @param x                         X-coordinate of the Player within the chunk.
     * @param y                         Y-coordinate of the Player within the chunk.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public Player(boolean visible, boolean tangible, Dimension hitBox, Dimension size, int x, int y) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, hitBox, size, x, y);
    }

    /**
     * Constructor instantiates a new {@link Player} and copies the attributes of the passed Player to the
     * generated instance.
     *
     * @param gameObject            Player whose attributes shall be copied to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public Player(Player gameObject) throws NullPointerException {
        super(gameObject);
    }

}
