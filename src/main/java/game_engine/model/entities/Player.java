package game_engine.model.entities;

import java.awt.Dimension;


/**
 * Class models a player character which can be used as Entity.
 *
 * @author  Christian-2003
 */
public class Player extends Entity {

    /**
     * Constructor instantiates a new {@link Player} with the passed arguments.
     *
     * @param visible                   Whether the Player is visible.
     * @param tangible                  Whether the Player is tangible.
     * @param hitBox                    Dimensions of the hit box for the
     *                                  Player.
     * @param size                      Size of the Player.
     * @param x                         X-coordinate of the Player.
     * @param y                         Y-coordinate of the Player.
     * @throws NullPointerException     One of the passed arguments is
     *                                  {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public Player(final boolean visible, final boolean tangible, final Dimension hitBox, final Dimension size, final double x, final double y) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, hitBox, size, x, y);
    }

    /**
     * Constructor instantiates a new {@link Player} and copies the attributes
     * of the passed Player to the generated instance.
     *
     * @param gameObject            Player whose attributes shall be copied
     *                              to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public Player(final Player gameObject) throws NullPointerException {
        super(gameObject);
    }

}
