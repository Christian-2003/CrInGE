package game_engine.model;

import java.awt.*;


/**
 * TODO: Add description.
 *
 * @author  TODO: Add author.
 */
public class MapObjectLightEmitting extends MapObject {

    /**
     * Attribute stores the color of the light that is emitted from this MapObject.
     */
    private Color lightColor;


    /**
     * Constructor instantiates a new {@link MapObjectLightEmitting} with the passed arguments.
     *
     * @param visible                   Whether the MapObject is visible.
     * @param tangible                  Whether the MapObject is tangible.
     * @param hitBox                    Dimensions of the hit box for the MapObject.
     * @param size                      Size of the MapObject.
     * @param x                         X-coordinate of the MapObject within the chunk.
     * @param y                         Y-coordinate of the MapObject within the chunk.
     * @param moving                    Whether the MapObject is moving.
     * @param movable                   Whether the MapObject is movable.
     * @param lightColor                Color of the light that is emitted from this MapObject.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public MapObjectLightEmitting(boolean visible, boolean tangible, Dimension hitBox, Dimension size, int x, int y, boolean moving, boolean movable, Color lightColor) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, hitBox, size, x, y, moving, movable);
        if (lightColor == null) {
            throw new NullPointerException("Null is invalid lightColor.");
        }
        this.lightColor = lightColor;
    }

    /**
     * Constructor instantiates a new {@link MapObjectLightEmitting} with the passed arguments.
     *
     * @param visible                   Whether the MapObject is visible.
     * @param tangible                  Whether the MapObject is tangible.
     * @param hitBox                    Dimensions of the hit box for the MapObject.
     * @param size                      Size of the MapObject.
     * @param x                         X-coordinate of the MapObject within the chunk.
     * @param y                         Y-coordinate of the MapObject within the chunk.
     * @param texture                   Index (within {@link GameMap#textures}) of the MapObject's texture.
     * @param moving                    Whether the MapObject is moving.
     * @param movable                   Whether the MapObject is movable.
     * @param lightColor                Color of the light that is emitted from this MapObject.
     * @throws NullPointerException     One of the passed arguments is {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public MapObjectLightEmitting(boolean visible, boolean tangible, Dimension hitBox, Dimension size, int x, int y, int texture, boolean moving, boolean movable, Color lightColor) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, hitBox, size, x, y, texture, moving, movable);
        if (lightColor == null) {
            throw new NullPointerException("Null is invalid lightColor.");
        }
        this.lightColor = lightColor;
    }

    /**
     * Constructor instantiates a new {@link MapObjectLightEmitting} and copies the attributes of the passed MapObjectLightEmitting to the
     * generated instance.
     *
     * @param mapObject             MapObjectLightEmitting whose attributes shall be copied to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public MapObjectLightEmitting(MapObjectLightEmitting mapObject) throws NullPointerException {
        super(mapObject);
        this.lightColor = mapObject.getLightColor();
    }


    public Color getLightColor() {
        return lightColor;
    }

    public void setLightColor(Color lightColor) {
        this.lightColor = lightColor;
    }


    /**
     * Method tests whether the attributes of the passed {@link MapObjectLightEmitting} match the attributes of
     * this instance. If so, {@code true} is returned. Otherwise, {@code false} will be returned.
     *
     * @param obj   Object to be tested.
     * @return      Whether the attributes of both MapObjects match.
     */
    public boolean equals(Object obj) {
        if (obj instanceof MapObjectLightEmitting mapObject) {
            return super.equals(mapObject) && mapObject.getLightColor().equals(lightColor);
        }
        return false;
    }

}
