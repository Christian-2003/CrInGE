package game_engine.model;

import game_engine.model.entities.Entity;
import game_engine.model.map.GameMap;
import game_engine.model.map.objects.MapObject;
import java.awt.Dimension;
import java.util.Objects;


/**
 * Class models a GameObject and represents the <i>abstract</i> superclass for
 * all {@link MapObject}- and {@link Entity}-classes.
 *
 * @author  Christian-2003
 */
public abstract class GameObject {

    /**
     * Constant indicates that the specific GameObject shall not have any texture.
     */
    public static final int NO_TEXTURE = -1;


    /**
     * Attribute stores whether the GameObject is visible.
     */
    protected boolean visible;

    /**
     * Attribute stores whether the GameObject is tangible.
     */
    protected boolean tangible;

    /**
     * Attribute stores the dimensions of the hitBox of the GameObject.
     */
    protected Dimension hitBox;

    /**
     * Attribute stores the size of the GameObject.
     */
    protected Dimension size;

    /**
     * Attribute stores the index for {@link GameMap#textures} at which the
     * texture can be found.
     */
    protected int texture;


    /**
     * Constructor instantiates a new {@link GameObject} with the passed
     * arguments.
     *
     * @param visible                   Whether the GameObject is visible.
     * @param tangible                  Whether the GameObject is tangible.
     * @param hitBox                    Dimensions of the hit box for the
     *                                  GameObject.
     * @param size                      Size of the GameObject.
     * @throws NullPointerException     One of the passed arguments is
     *                                  {@code null}.
     */
    public GameObject(final boolean visible, final boolean tangible, final Dimension hitBox, final Dimension size) throws NullPointerException {
        if (hitBox == null || size == null) {
            throw new NullPointerException("Null is invalid argument.");
        }
        this.visible = visible;
        this.tangible = tangible;
        this.hitBox = hitBox;
        this.size = size;
        this.texture = NO_TEXTURE;
    }

    /**
     * Constructor instantiates a new {@link GameObject} with the passed
     * arguments.
     *
     * @param visible                   Whether the GameObject is visible.
     * @param tangible                  Whether the GameObject is tangible.
     * @param hitBox                    Dimensions of the hit box for the
     *                                  GameObject.
     * @param size                      Size of the GameObject.
     * @param texture                   Index (within {@link GameMap#textures})
     *                                  of the GameObject's texture.
     * @throws NullPointerException     One of the passed arguments is
     *                                  {@code null}.
     */
    public GameObject(final boolean visible, final boolean tangible, final Dimension hitBox, final Dimension size, final int texture) throws NullPointerException {
        if (hitBox == null || size == null) {
            throw new NullPointerException("Null is invalid argument.");
        }
        this.visible = visible;
        this.tangible = tangible;
        this.hitBox = hitBox;
        this.size = size;
        this.texture = texture;
    }

    /**
     * Constructor instantiates a new {@link GameObject} and copies the
     * attributes of the passed GameObject to the generated instance.
     *
     * @param gameObject            GameObject whose attributes shall be copied
     *                              to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public GameObject(final GameObject gameObject) throws NullPointerException {
        if (gameObject == null) {
            throw new NullPointerException("Null is invalid GameObject.");
        }
        visible = gameObject.isVisible();
        tangible = gameObject.isTangible();
        hitBox = gameObject.getHitBox();
        size = gameObject.getSize();
        texture = gameObject.getTexture();
    }


    /**
     * Method returns whether the map obejct is visible.
     *
     * @return  Whether the map object is visible.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Method changes whether the map object is visible.
     *
     * @param visible   Whether the map object is visible.
     */
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }

    /**
     * Method returns whether the map object is tangible.
     *
     * @return  Whether the map object is tangible.
     */
    public boolean isTangible() {
        return tangible;
    }

    /**
     * Method changes whether the map object is tangible.
     *
     * @param tangible  Whether the map object is tangible.
     */
    public void setTangible(final boolean tangible) {
        this.tangible = tangible;
    }

    /**
     * Method returns the hit box of the game object.
     *
     * @return  Hit box of the game object.
     */
    public Dimension getHitBox() {
        return hitBox;
    }

    /**
     * Method changes the size of the hit box of the game object.
     *
     * @param hitBox                New size for the hit box.
     * @throws NullPointerException The passed hit box is {@code null}.
     */
    public void setHitBox(final Dimension hitBox) throws NullPointerException {
        if (hitBox == null) {
            throw new NullPointerException("Null is invalid Dimension.");
        }
        this.hitBox = hitBox;
    }

    /**
     * method returns the size of the game object.
     *
     * @return  Size of the game object.
     */
    public Dimension getSize() {
        return size;
    }

    /**
     * Method changes the size of the game object.
     *
     * @param size                  New size for the game object.
     * @throws NullPointerException The passed size is {@code null}.
     */
    public void setSize(final Dimension size) throws NullPointerException {
        if (size == null) {
            throw new NullPointerException("Null is invalid size.");
        }
        this.size = size;
    }

    /**
     * Method returns the texture of the game object.
     *
     * @return  Texture of the game object.
     */
    public int getTexture() {
        return texture;
    }

    /**
     * Method changes the texture for the game object.
     *
     * @param texture   New texture.
     */
    public void setTexture(final int texture) {
        this.texture = texture;
    }


    /**
     * Method tests whether the attributes of the passed {@link GameObject}
     * match the attributes of this instance. If so, {@code true} is returned.
     * Otherwise, {@code false} will be returned.
     *
     * @param obj   Object to be tested.
     * @return      Whether the attributes of both GameObjects match.
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof GameObject gameObject) {
            return gameObject.isVisible() == visible
                    && gameObject.isTangible() == tangible
                    && gameObject.getHitBox().equals(hitBox)
                    && gameObject.getSize().equals(size);
        }
        return false;
    }

    /**
     * Method calculates a hash code for the game object.
     *
     * @return  Generated hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(visible, tangible, hitBox, size, texture);
    }

}
