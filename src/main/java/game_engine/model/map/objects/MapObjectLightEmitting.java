package game_engine.model.map.objects;

import game_engine.model.map.GameMap;
import java.awt.Color;


/**
 * Class models a light-emitting MapObject.
 *
 * @author  Christian-2003
 */
public class MapObjectLightEmitting extends MapObject {

    /**
     * Attribute stores the color of the light that is emitted from this
     * MapObject.
     */
    private Color lightColor;


    /**
     * Constructor instantiates a new {@link MapObjectLightEmitting} with the
     * passed arguments.
     *
     * @param visible                   Whether the MapObject is visible.
     * @param tangible                  Whether the MapObject is tangible.
     * @param x                         X-coordinate of the MapObject within
     *                                  the chunk.
     * @param y                         Y-coordinate of the MapObject within
     *                                  the chunk.
     * @param lightColor                Color of the light that is emitted from
     *                                  this MapObject.
     * @throws NullPointerException     One of the passed arguments is
     * {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public MapObjectLightEmitting(final boolean visible, final boolean tangible, final int x, final int y, final Color lightColor) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, x, y);
        if (lightColor == null) {
            throw new NullPointerException("Null is invalid lightColor.");
        }
        this.lightColor = lightColor;
    }

    /**
     * Constructor instantiates a new {@link MapObjectLightEmitting} with the
     * passed arguments.
     *
     * @param visible                   Whether the MapObject is visible.
     * @param tangible                  Whether the MapObject is tangible.
     * @param x                         X-coordinate of the MapObject within
     *                                  the chunk.
     * @param y                         Y-coordinate of the MapObject within
     *                                  the chunk.
     * @param texture                   Index (within {@link GameMap#textures})
     *                                  of the MapObject's texture.
     * @param lightColor                Color of the light that is emitted from
     *                                  this MapObject.
     * @throws NullPointerException     One of the passed arguments is
     *                                  {@code null}.
     * @throws IllegalArgumentException The passed coordinates are invalid.
     */
    public MapObjectLightEmitting(final boolean visible, final boolean tangible, final int x, final int y, final int texture, final Color lightColor) throws NullPointerException, IllegalArgumentException {
        super(visible, tangible, x, y, texture);
        if (lightColor == null) {
            throw new NullPointerException("Null is invalid lightColor.");
        }
        this.lightColor = lightColor;
    }

    /**
     * Constructor instantiates a new {@link MapObjectLightEmitting} and copies
     * the attributes of the passed MapObjectLightEmitting to the generated
     * instance.
     *
     * @param mapObject             MapObjectLightEmitting whose attributes
     *                              shall be copied to this instance.
     * @throws NullPointerException The passed instance is {@code null}.
     */
    public MapObjectLightEmitting(final MapObjectLightEmitting mapObject) throws NullPointerException {
        super(mapObject);
        this.lightColor = mapObject.getLightColor();
    }


    /**
     * Method returns the light color.
     *
     * @return  Light color.
     */
    public Color getLightColor() {
        return lightColor;
    }

    /**
     * Method changes the light color for the light emitting map object.
     *
     * @param lightColor    New light color.
     */
    public void setLightColor(final Color lightColor) {
        this.lightColor = lightColor;
    }


    /**
     * Method tests whether the attributes of the passed
     * {@link MapObjectLightEmitting} match the attributes of this instance. If
     * so, {@code true} is returned. Otherwise, {@code false} will be returned.
     *
     * @param obj   Object to be tested.
     * @return      Whether the attributes of both MapObjects match.
     */
    public boolean equals(final Object obj) {
        if (obj instanceof MapObjectLightEmitting mapObject) {
            return super.equals(mapObject) && mapObject.getLightColor().equals(lightColor);
        }
        return false;
    }

}
