package game_engine.controller;


import game_engine.model.GameChunk;
import game_engine.model.GameMap;
import game_engine.model.MapObject;

import javax.swing.*;
import java.awt.*;

/**
 * TODO: Add description.
 *
 * @author  TODO: Add author.
 */
public class RendererManager {

    /**
     * Constant stores the size (width and height) for each MapObject in pixels.
     */
    private static final int MAP_OBJECT_SIZE = 16;


    /**
     * Attribute stores the {@link GameMap} that shall be rendered by this {@link RendererManager}.
     */
    private final GameMap map;

    /**
     * Attribute stores the Java swing {@link JComponent} onto which the {@link #map} shall be rendered.
     */
    private JComponent component;

    /**
     * Attribute stores the {@link Graphics} that are used to draw onto {@link #component}.
     */
    private Graphics g;

    /**
     * Attributes store the absolute world coordinates of the {@link game_engine.model.MapObject} that shall be rendered
     * in the top left corner of {@link #component}.
     */
    private int absoluteWorldX, absoluteWorldY;


    /**
     * Constructor instantiates a new {@link RendererManager}.
     *
     * @param map                   Map, which shall be rendered.
     * @throws NullPointerException The passed map is {@code null}.
     */
    public RendererManager(GameMap map) throws NullPointerException {
        if (map == null) {
            throw new NullPointerException("Null is invalid map");
        }
        this.map = map;
        absoluteWorldX = 0;
        absoluteWorldY = 0;
    }


    /**
     * Method updates the coordinates of the {@link game_engine.model.MapObject} in the top left corner to be rendered.
     * The map must be updated manually.
     *
     * @param absoluteWorldX                X-coordinate of the MapObject in the upper left corner.
     * @param absoluteWorldY                Y-coordinate of the MapObject in the upper left corner.
     * @throws IndexOutOfBoundsException    The passed coordinate is out of bounds.
     */
    public void updateMapPosition(int absoluteWorldX, int absoluteWorldY) throws IndexOutOfBoundsException {
        if (absoluteWorldX < 0 || absoluteWorldX > map.getWidth() * GameChunk.DIMENSION || absoluteWorldY < 0 || absoluteWorldY > map.getHeight() * GameChunk.DIMENSION) {
            //Passed coordinate out of bounds:
            throw new IndexOutOfBoundsException("Absolute world coordinate (" + absoluteWorldX + ", " + absoluteWorldY + ") out of bounds for width " + map.getWidth() + " and height " + map.getHeight());
        }
        this.absoluteWorldX = absoluteWorldX;
        this.absoluteWorldY = absoluteWorldY;
    }


    public void renderGameMap(JComponent component, Graphics g) throws NullPointerException {
        if (component == null) {
            throw new NullPointerException("Null is invalid JComponent");
        }
        if (g == null) {
            throw new NullPointerException("Null is invalid Graphics");
        }
        this.component = component;
        this.g = g;
        //Define how many MapObjects fit into the window:
        int numberOfMapObjectsX = component.getWidth() / MAP_OBJECT_SIZE + 1;
        int numberOfMapObjectsY = component.getHeight() / MAP_OBJECT_SIZE + 1;
        //Find the chunk that contains the MapObject in the top left corner:
        int topLeftChunkX = absoluteWorldX / GameChunk.DIMENSION;
        int topLeftChunkY = absoluteWorldY / GameChunk.DIMENSION;
        //Find the chunk that contains the MaoObject in the bottom right corner:
        int bottomRightChunkX = topLeftChunkX + numberOfMapObjectsX / GameChunk.DIMENSION + 1;
        if (bottomRightChunkX > map.getWidth()) {
            bottomRightChunkX = map.getWidth();
        }
        int bottomRightChunkY = topLeftChunkY + numberOfMapObjectsY / GameChunk.DIMENSION + 1;
        if (bottomRightChunkY > map.getHeight()) {
            bottomRightChunkY = map.getHeight();
        }
        //System.out.println("TopLeftChunkX=" + topLeftChunkX + ", TopLeftChunkY=" + topLeftChunkY);
        //System.out.println("NumberOfObjectsX=" + numberOfMapObjectsX + ", NumberOfObjectsY=" + numberOfMapObjectsY);
        //System.out.println("BottomRightChunkX=" + bottomRightChunkX + ", BottomRightChunkY=" + bottomRightChunkY);

        //Change background:
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, component.getWidth(), component.getHeight());

        //Render each chunk:
        for (int x = topLeftChunkX; x < bottomRightChunkX; x++) {
            for (int y = topLeftChunkY; y < bottomRightChunkY; y++) {
                System.out.println("Accessing chunk (" + x + ", " + y + ")");
                GameChunk chunk = map.get(x, y);
                if (chunk == null) {
                    continue;
                }

                int startX = 0;
                int startY = 0;
                int endX = GameChunk.DIMENSION;
                int endY = GameChunk.DIMENSION;
                if (x == topLeftChunkX) {
                    //Chunk is the first chunk to be rendered:
                    startX = x - x / GameChunk.DIMENSION;
                }
                if (y == topLeftChunkY) {
                    //Chunk is the first chunk to be rendered:
                    startY = y - y / GameChunk.DIMENSION;
                }

                int offsetX = (x - topLeftChunkX) * GameChunk.DIMENSION * MAP_OBJECT_SIZE;
                int offsetY = (y - topLeftChunkY) * GameChunk.DIMENSION * MAP_OBJECT_SIZE;
                renderGameChunk(chunk, offsetX, offsetY, startX, startY, endX, endY);
            }
        }
    }


    /**
     * Method renders a single chunk. The offset indicates the coordinates (within {@link #component}) for the top left
     * MapObject of the chunk. The start indicates the coordinates (within the chunk) of the top left MapObject to be
     * rendered. All MapObjects whose coordinates are greater than the start coordinates are rendered. The end indicates
     * the coordinates (within the chunk) of the bottom right MapObject to be rendered. All MapObjects whose coordinates
     * are less than the end coordinates are rendered.
     *
     * @param chunk                 The chunk to be rendered.
     * @param offsetX               X coordinate of the swing component at which to begin rendering.
     * @param offsetY               Y coordinate of the swing component at which to begin rendering.
     * @param startX                X coordinate of the first MapObject to be rendered.
     * @param startY                Y coordinate of the first MapObject to be rendered.
     * @param endX                  X coordinate of the last MapObject to be rendered.
     * @param endY                  Y coordinate of the last MapObject to be rendered.
     * @throws NullPointerException The passed chunk is {@code null}.
     */
    private void renderGameChunk(GameChunk chunk, int offsetX, int offsetY, int startX, int startY, int endX, int endY) throws NullPointerException {
        chunk.resetMapObjectIterator();
        while (chunk.hasNextMapObject()) {
            MapObject mapObject = chunk.nextMapObject();
            int x = mapObject.getX();
            int y = mapObject.getY();
            if (x < startX || y < startY || x >= endX || y >= endY) {
                //Ignore current mapObject, as it does not need to be rendered..
                continue;
            }
            //Render mapObject:
            renderMapObject(mapObject, x * MAP_OBJECT_SIZE + offsetX, y * MAP_OBJECT_SIZE + offsetY);
        }
        //Draw chunk borders:
        int width = (endX - startX) * MAP_OBJECT_SIZE;
        int height = (endX - startX) * MAP_OBJECT_SIZE;
        g.setColor(Color.RED);
        g.drawLine(offsetX, offsetY, offsetX + width, offsetY); //Top line
        g.drawLine(offsetX, offsetY, offsetX, offsetY + height); //Left line
        g.drawLine(offsetX + width, offsetY, offsetX + width, offsetY + height); //Right line
        g.drawLine(offsetX, offsetY + height, offsetX + width, offsetY + height); //Bottom line
    }


    /**
     * Method renders the passed {@link MapObject} to the {@link #component}. The passed coordinates indicate the
     * position to which the MapObject shall be rendered to the Swing component.
     *
     * @param mapObject                 MapObject to be rendered.
     * @param x                         X coordinate of the upper left corner to which the MapObject shall be rendered.
     * @param y                         Y coordinate of the upper left corner to which the MapObject shall be rendered.
     * @throws NullPointerException     The passed MapObject is {@code null}.
     * @throws IllegalArgumentException The passed coordinate is illegal.
     */
    private void renderMapObject(MapObject mapObject, int x, int y) throws NullPointerException, IllegalArgumentException {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinate (" + x + ", " + y + ") is illegal");
        }
        if (mapObject == null) {
            throw new NullPointerException("Null is invalid MapObject");
        }

        //Prepare for rendering:
        int width = mapObject.getSize().width * MAP_OBJECT_SIZE;
        int height = mapObject.getSize().height * MAP_OBJECT_SIZE;

        //Render MapObject:
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height); //Fill background color
        g.setColor(Color.WHITE);
        g.drawLine(x, y, x + width, y); //Top line
        g.drawLine(x, y, x, y + height); //Left line
        g.drawLine(x + width, y, x + width, y + height); //Right line
        g.drawLine(x, y + height, x + width, y + height); //Bottom line
        g.drawLine(x, y, x + width, y + height); //Diagonal line 1
        g.drawLine(x + width, y, x, y + height); //Diagonal line 2
    }

}
