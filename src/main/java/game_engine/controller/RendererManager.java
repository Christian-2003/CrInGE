package game_engine.controller;

import game_engine.model.GameChunk;
import game_engine.model.GameMap;
import game_engine.model.MapObject;
import javax.swing.*;
import java.awt.*;


/**
 * Class implements a RendererManager which can render a {@link GameMap} onto a Java Swing component, such as
 * {@linkplain JComponent}. Call {@link #renderGameMap(JComponent, Graphics)} to render the map to the respective
 * component. If the map shall be rendered from specific absolute map coordinates, call
 * {@link #updateMapPosition(int, int)} beforehand.
 *
 * @author  Christian-2003
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
        if (absoluteWorldX < 0 || absoluteWorldX > map.getWidth() * GameChunk.WIDTH || absoluteWorldY < 0 || absoluteWorldY > map.getHeight() * GameChunk.HEIGHT) {
            //Passed coordinate out of bounds:
            throw new IndexOutOfBoundsException("Absolute world coordinate (" + absoluteWorldX + ", " + absoluteWorldY + ") out of bounds for width " + map.getWidth() + " and height " + map.getHeight());
        }
        this.absoluteWorldX = absoluteWorldX;
        this.absoluteWorldY = absoluteWorldY;
    }


    /**
     * Method renders an entire {@link GameMap} onto the {@link #component} using the provided {@linkplain Graphics}.
     * Please make sure that the passed Graphics is the instance that is passed to the component's
     * {@linkplain JComponent#paintComponent(Graphics)}-method. Any other instance will not work.
     *
     * @param component             Component onto which the map shall be rendered.
     * @param g                     Graphics to use for rendering. This must be the same instance that was passed to the
     *                              component's {@linkplain JComponent#paintComponent(Graphics)}-method.
     * @throws NullPointerException The passed component or graphics is {@code null}.
     */
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
        int topLeftChunkX = absoluteWorldX / GameChunk.WIDTH;
        int topLeftChunkY = absoluteWorldY / GameChunk.HEIGHT;
        //Find the chunk that contains the MaoObject in the bottom right corner:
        int bottomRightChunkX = topLeftChunkX + numberOfMapObjectsX / GameChunk.WIDTH + 1;
        if (bottomRightChunkX > map.getWidth()) {
            bottomRightChunkX = map.getWidth();
        }
        int bottomRightChunkY = topLeftChunkY + numberOfMapObjectsY / GameChunk.HEIGHT + 1;
        if (bottomRightChunkY > map.getHeight()) {
            bottomRightChunkY = map.getHeight();
        }
        /*
        System.out.println("=============== [ CHUNK DEBUGGING INFO ] ===============");
        System.out.println("TopLeftChunkX=" + topLeftChunkX + ", TopLeftChunkY=" + topLeftChunkY);
        System.out.println("NumberOfObjectsX=" + numberOfMapObjectsX + ", NumberOfObjectsY=" + numberOfMapObjectsY);
        System.out.println("BottomRightChunkX=" + bottomRightChunkX + ", BottomRightChunkY=" + bottomRightChunkY);
        */

        //Change background:
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, component.getWidth(), component.getHeight());

        int skippedMapObjectsX = 0;
        int skippedMapObjectsY = 0;

        //Render each chunk:
        for (int x = topLeftChunkX; x < bottomRightChunkX; x++) {
            for (int y = topLeftChunkY; y < bottomRightChunkY; y++) {
                //System.out.println("Accessing chunk (" + x + ", " + y + ")");
                GameChunk chunk = map.get(x, y);
                if (chunk == null) {
                    continue;
                }

                int startX = 0;
                int startY = 0;
                int endX = GameChunk.WIDTH;
                int endY = GameChunk.HEIGHT;
                if (x == topLeftChunkX) {
                    //Chunk is the first chunk to be rendered:
                    startX = absoluteWorldX - topLeftChunkX * GameChunk.WIDTH;
                }
                if (y == topLeftChunkY) {
                    //Chunk is the first chunk to be rendered:
                    startY = absoluteWorldY - topLeftChunkY * GameChunk.HEIGHT;
                }
                if (x == topLeftChunkX && skippedMapObjectsX == 0) {
                    //Remember how many MapObjects in columns were ignored at the beginning:
                    skippedMapObjectsX = startX;
                }
                if (y == topLeftChunkY && skippedMapObjectsY == 0) {
                    //Remember how many MapObjects in rows were ignored at the beginning:
                    skippedMapObjectsY = startY;
                }

                int chunkOffsetX = (x - topLeftChunkX) * GameChunk.WIDTH - skippedMapObjectsX;
                int chunkOffsetY = (y - topLeftChunkY) * GameChunk.HEIGHT - skippedMapObjectsY;
                renderGameChunk(chunk, chunkOffsetX, chunkOffsetY, startX, startY, endX, endY);


            }
        }
    }


    /**
     * Method renders a single {@link GameChunk}. The chunk offset indicates the offset (in map coordinates) for the
     * top left MapObject of the chunk. The start indicates the coordinates (within the chunk) of the top left MapObject
     * to be rendered. All MapObjects whose coordinates are greater than the start coordinates are rendered. The end
     * indicates the coordinates (within the chunk) of the bottom right MapObject to be rendered. All MapObjects whose
     * coordinates are less than the end coordinates are rendered.
     *
     * @param chunk                 The chunk to be rendered.
     * @param chunkOffsetX          X offset for the chunk (in map coordinates).
     * @param chunkOffsetY          Y offset for the chunk (in map coordinates).
     * @param startX                X coordinate of the first MapObject to be rendered.
     * @param startY                Y coordinate of the first MapObject to be rendered.
     * @param endX                  X coordinate of the last MapObject to be rendered.
     * @param endY                  Y coordinate of the last MapObject to be rendered.
     * @throws NullPointerException The passed chunk is {@code null}.
     */
    private void renderGameChunk(GameChunk chunk, int chunkOffsetX, int chunkOffsetY, int startX, int startY, int endX, int endY) throws NullPointerException {
        chunk.resetMapObjectIterator();
        while (chunk.hasNextMapObject()) {
            MapObject mapObject = chunk.nextMapObject();
            int x = mapObject.getX();
            int y = mapObject.getY();
            if (x < startX || y < startY || x >= endX || y >= endY) {
                //Ignore current mapObject, as it does not need to be rendered...
                continue;
            }
            //Render mapObject:
            renderMapObject(mapObject, chunkOffsetX, chunkOffsetY);
        }
        //Draw chunk borders:
        int chunkX = (chunkOffsetX < 0 ? 0: chunkOffsetX) * MAP_OBJECT_SIZE;
        int chunkY = (chunkOffsetY < 0 ? 0: chunkOffsetY) * MAP_OBJECT_SIZE;
        int chunkWidth = (endX - startX) * MAP_OBJECT_SIZE;
        int chunkHeight = (endY - startY) * MAP_OBJECT_SIZE;
        g.setColor(Color.RED);
        g.drawLine(chunkX, chunkY, chunkX + chunkWidth, chunkY); //Top line
        g.drawLine(chunkX, chunkY, chunkX, chunkY + chunkHeight); //Left line
        g.drawLine(chunkX + chunkWidth, chunkY, chunkX + chunkWidth, chunkY + chunkHeight); //Right line
        g.drawLine(chunkX, chunkY + chunkHeight, chunkX + chunkWidth, chunkY + chunkHeight); //Bottom line
    }


    /**
     * Method renders the passed {@link MapObject} according to its coordinates as if it were in a chunk in the top
     * left corner of the canvas. The passed offset indicates how far (in map coordinates) the rendered MapObject shall
     * be offset.
     *
     * @param mapObject                 MapObject to be rendered.
     * @param chunkOffsetX              X offset for the chunk of the rendered MapObject (in map coordinates).
     * @param chunkOffsetY              Y offset for the chunk of the rendered MapObject (in map coordinates).
     * @throws NullPointerException     The passed MapObject is {@code null}.
     */
    private void renderMapObject(MapObject mapObject, int chunkOffsetX, int chunkOffsetY) throws NullPointerException {
        if (mapObject == null) {
            throw new NullPointerException("Null is invalid MapObject");
        }

        //Prepare for rendering:
        int x = (mapObject.getX() + chunkOffsetX) * MAP_OBJECT_SIZE;
        int y = (mapObject.getY() + chunkOffsetY) * MAP_OBJECT_SIZE;
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
