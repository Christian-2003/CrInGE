package game_engine.model;

import java.awt.*;


/**
 * TODO: Add description.
 *
 * @author  TODO: Add author.
 */
public abstract class GameObject {

    /**
     * Attribute stores whether the GameObject is visible,
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
     * Attribute stores the coordinates of the MapObject within the {@link GameChunk}.
     */
    protected int x, y;


    /**
     * Constructor instantiates a new {@link GameObject} with the passed arguments.
     *
     * @param visible                   Whether the GameObject is visible.
     * @param tangible                  Whether the GameObject is tangible.
     * @param hitBox                    Dimensions of the hit box for the GameObject.
     * @param size                      Size of the GameObject.
     * @param x                         X-coordinate of the GameObject within the chunk.
     * @param y                         Y-coordinate of the GameObject within the chunk.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public GameObject(boolean visible, boolean tangible, Dimension hitBox, Dimension size, int x, int y) throws NullPointerException, IllegalArgumentException {
        if (hitBox == null || size == null) {
            throw new NullPointerException("Null is invalid argument.");
        }
        if (x < 0 || x >= GameChunk.WIDTH || y < 0 || y >= GameChunk.HEIGHT) {
            throw new IllegalArgumentException("Invalid coordinate for MapObject: (" + x + ", " + y + ")");
        }
        this.visible = visible;
        this.tangible = tangible;
        this.hitBox = hitBox;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor instantiates a new {@link GameObject} and copies the attributes of the passed GameObject to the
     * generated instance.
     *
     * @param gameObject            GameObject whose attributes shall be copied to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public GameObject(GameObject gameObject) throws NullPointerException {
        if (gameObject == null) {
            throw new NullPointerException("Null is invalid GameObject.");
        }
        visible = gameObject.isVisible();
        tangible = gameObject.isTangible();
        hitBox = gameObject.getHitBox();
        size = gameObject.getSize();

    }


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isTangible() {
        return tangible;
    }

    public void setTangible(boolean tangible) {
        this.tangible = tangible;
    }

    public Dimension getHitBox() {
        return hitBox;
    }

    public void setHitBox(Dimension hitBox) throws NullPointerException {
        if (hitBox == null) {
            throw new NullPointerException("Null is invalid Dimension.");
        }
        this.hitBox = hitBox;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) throws NullPointerException {
        if (size == null) {
            throw new NullPointerException("Null is invalid size.");
        }
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) throws IllegalArgumentException {
        if (x < 0 || x >= GameChunk.WIDTH) {
            throw new IllegalArgumentException("Invalid coordinate for MapObject: (" + x + ", " + y + ")");
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) throws IllegalArgumentException {
        if (y < 0 || y >= GameChunk.HEIGHT) {
            throw new IllegalArgumentException("Invalid coordinate for MapObject: (" + x + ", " + y + ")");
        }
        this.y = y;
    }

    /**
     * Method tests whether the attributes of the passed {@link GameObject} match the attributes of
     * this instance. If so, {@code true} is returned. Otherwise, {@code false} will be returned.
     *
     * @param obj   Object to be tested.
     * @return      Whether the attributes of both GameObjects match.
     */
    public boolean equals(Object obj) {
        if (obj instanceof GameObject gameObject) {
            return gameObject.isVisible() == visible &&
                    gameObject.isTangible() == tangible &&
                    gameObject.getHitBox().equals(hitBox) &&
                    gameObject.getSize().equals(size);
        }
        return false;
    }

}
