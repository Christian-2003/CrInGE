package game_engine.model;

import java.awt.*;


/**
 * Class models a projectile, which can be used as Entity.
 *
 * @author  Christian-2003
 */
public class Projectile extends Entity {

    /**
     * Constructor instantiates a new {@link Projectile} with the passed arguments.
     *
     * @param visible                   Whether the Projectile is visible.
     * @param tangible                  Whether the Projectile is tangible.
     * @param hitBox                    Dimensions of the hit box for the Projectile.
     * @param size                      Size of the Projectile.
     * @param x                         X-coordinate of the Projectile within the chunk.
     * @param y                         Y-coordinate of the Projectile within the chunk.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public Projectile(boolean visible, boolean tangible, Dimension hitBox, Dimension size, int x, int y) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, hitBox, size, x, y);
    }

    /**
     * Constructor instantiates a new {@link Projectile} and copies the attributes of the passed Projectile to the
     * generated instance.
     *
     * @param gameObject            Projectile whose attributes shall be copied to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public Projectile(Projectile gameObject) throws NullPointerException {
        super(gameObject);
    }

}
