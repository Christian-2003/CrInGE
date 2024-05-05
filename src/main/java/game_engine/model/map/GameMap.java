package game_engine.model.map;


import game_engine.model.map.objects.MapObject;

import javax.swing.*;

/**
 * Class models a GameMap which contains an array of {@link GameChunk}s. Chunks are stored in a one-dimensional array
 * (see {@link #chunks}).
 * Furthermore, the class contains an array of {@linkplain ImageIcon}s, which resemble the {@link #textures} for the
 * {@link MapObject}s of this GameMap.
 *
 * @author  Christian-2003
 */
public class GameMap {

    /**
     * Attribute stores all {@link GameChunk}s of the {@link GameMap}. Chunks are stored in one dimension, the following
     * formula applies to convert two-dimensional chunk-coordinates {@code (x, y)} to an index within this array:
     * {@code i = (y * width + x)}.
     */
    private final GameChunk[] chunks;

    /**
     * Attribute stores all available textures for the {@link MapObject}s. Each MapObject only stores the index to
     * its respective texture within this array.
     */
    private final ImageIcon[] textures;

    /**
     * Attribute stores the ID of the background image. The ID corresponds to the index of the background within
     * {@link #textures}.
     */
    private final int background;

    /**
     * Attributes store the width and height (in chunks) for the {@link GameMap}.
     */
    private final int width, height;


    /**
     * Constructor instantiates a new {@link GameMap}, which contains an array of {@link GameChunk}s. The number of
     * passed chunks must match the map size ({@code width * height}). If a specific chunk does not contain anything,
     * {@code null} may be passed for the respective chunk.
     *
     * @param width                     Total width of the GameMap (in chunks).
     * @param height                    Total height of the GameMap (in chunks).
     * @param chunks                    Array of chunks for the GameMap.
     * @param textures                  Array of ImageIcons resembling the textures for the MapObjects.
     * @throws NullPointerException     The passed array is {@code null}.
     * @throws IllegalArgumentException The passed with or height is less than 0 or the number of passed chunks does not
     *                                  match the map size.
     */
    public GameMap(int width, int height, GameChunk[] chunks, ImageIcon[] textures, int background) throws NullPointerException, IllegalArgumentException {
        if (chunks == null) {
            throw new NullPointerException("Null is invalid array for chunks");
        }
        if (textures == null) {
            throw new NullPointerException("Null is invalid array for textures");
        }
        if (width < 0) {
            throw new IllegalArgumentException("Width (" + width + ") illegal");
        }
        if (height < 0) {
            throw new IllegalArgumentException("Height (" + height + ") illegal");
        }
        if (width * height != chunks.length) {
            throw new IllegalArgumentException("Number of chunks (" + chunks.length + ") does not match map size (" + width + " * " + height + " = " + width * height + ")");
        }
        this.width = width;
        this.height = height;
        this.chunks = new GameChunk[chunks.length];
        System.arraycopy(chunks, 0, this.chunks, 0, chunks.length);
        this.textures = new ImageIcon[textures.length];
        System.arraycopy(textures, 0, this.textures, 0, textures.length);
        this.background = background;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBackground() {
        return background;
    }


    /**
     * Method returns the number of available textures.
     *
     * @return  Number of available textures.
     */
    public int getNumberOfTextures() {
        return textures.length;
    }

    /**
     * Method returns the texture at the specified index.
     *
     * @param index                         Index whose texture shall be returned.
     * @return                              Texture at the specified index.
     * @throws IndexOutOfBoundsException    The passed index is out of bounds.
     */
    public ImageIcon getTexture(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > textures.length) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + textures.length);
        }
        return textures[index];
    }


    /**
     * Method returns the {@link GameChunk} at the specified index. The returned chunk may be {@code null} if there is
     * nothing inside the corresponding chunk.
     *
     * @param index                         Index of the corresponding GameChunk.
     * @return                              GameChunk at the specified index.
     * @throws IndexOutOfBoundsException    The passed index is out of bounds.
     */
    public GameChunk get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= chunks.length) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + chunks.length);
        }
        return chunks[index];
    }


    /**
     * Method returns the chunk at the specified chunk-coordinates {@code (x, y)}.
     *
     * @param x                             X-coordinate for the chunk.
     * @param y                             Y-coordinate for the chunk.
     * @return                              Chunk at the specified coordinates.
     * @throws IndexOutOfBoundsException    The passed coordinates are out of bounds.
     */
    public GameChunk get(int x, int y) throws IndexOutOfBoundsException {
        if (x < 0 || x >= width) {
            throw new IndexOutOfBoundsException("Index " + x + " out of bounds for width " + width);
        }
        if (y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Index " + y + " out of bounds for height " + height);
        }
        return chunks[convertCoordinatesToIndex(x, y)];
    }


    /**
     * Method converts the passed coordinates {@code (x, y)} into an index of the corresponding chunk within
     * {@link #chunks}.
     *
     * @param x                             X-coordinate for the chunk.
     * @param y                             Y-coordinate for the chunk.
     * @return                              Index of the corresponding chunk.
     * @throws IndexOutOfBoundsException    The passed coordinates are out of bounds.
     */
    private int convertCoordinatesToIndex(int x, int y) throws IndexOutOfBoundsException {
        if (x < 0 || x >= width) {
            throw new IndexOutOfBoundsException("Index " + x + " out of bounds for width " + width);
        }
        if (y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Index " + y + " out of bounds for height " + height);
        }
        return y * width + x;
    }

}
