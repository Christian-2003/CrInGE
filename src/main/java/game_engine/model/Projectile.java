package game_engine.model;

import java.awt.*;


/**
 * TODO: Add description.
 *
 * @author  TODO: Add author.
 */
public class Projectile extends Entity {

    /**
     * Constructor instantiates a new {@link Projectile} with the passed arguments.
     *
     * @param visible               Whether the Projectile is visible.
     * @param tangible              Whether the Projectile is tangible.
     * @param hitBox                Dimensions of the hit box for the Projectile.
     * @param size                  Size of the Projectile.
     * @param graphics              Graphics for the Projectile.
     * @throws NullPointerException One of the passed arguments is {@code null}.
     */
    public Projectile(boolean visible, boolean tangible, Dimension hitBox, Dimension size, Graphics graphics) throws NullPointerException {
        super(visible, tangible, hitBox, size, graphics);
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
