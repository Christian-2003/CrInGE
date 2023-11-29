package game_engine.view.canvas;

import game_engine.model.GameChunk;
import game_engine.model.GameMap;
import game_engine.model.MapObject;

import javax.swing.*;
import java.awt.*;

public class SwingGameCanvas extends JComponent {

    /**
     * Constant stores the height and width for {@link game_engine.model.MapObject}s in pixels.
     */
    private static final int OBJECT_HEIGHT = 16;

    /**
     * Constant stores how far the game shall be offset from the top left corner in pixels.
     */
    private static final int OFFSET = 1;

    /**
     * Attribute references the {@link GameMap} which shall be rendered.
     */
    private GameMap map;

    /**
     * Attribute stores the position of the first component (upper left corner) to be painted.
     */
    private int x, y;


    public SwingGameCanvas(GameMap map) throws NullPointerException {
        if (map == null) {
            throw new NullPointerException("Null is invalid map.");
        }
        this.map = map;
    }


    public void setCoordinates(int x, int y) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (x < 0 || y < 0 || x >= map.getWidth() * GameChunk.DIMENSION || y >= map.getHeight() * GameChunk.DIMENSION) {
            //Passed coordinate out of bounds:
            throw new IndexOutOfBoundsException("Coordinate (" + x + ", " + y + ") out of bounds for map width=" + map.getWidth() + " and height=" + map.getHeight());
        }
        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        //Fill background:
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getSize().width, getSize().height);

        //Select chunk to be rendered:
        GameChunk chunk = map.get(x, y);
        chunk.resetMapObjectIterator();
        while (chunk.hasNextMapObject()) {
            MapObject mapObject = chunk.nextMapObject();
            int x = mapObject.getX() * OBJECT_HEIGHT + OFFSET;
            int y = mapObject.getY() * OBJECT_HEIGHT + OFFSET;
            int width = mapObject.getSize().width * OBJECT_HEIGHT;
            int height = mapObject.getSize().height * OBJECT_HEIGHT;
            //Draw back of the map object:
            g.setColor(Color.BLACK);
            g.fillRect(x, y, width, height);
            //Draw border of map object:
            g.setColor(Color.WHITE);
            g.drawLine(x, y, x + width, y); //Top line
            g.drawLine(x, y, x, y + height); //Left line
            g.drawLine(x + width, y, x + width, y + height); //Right line
            g.drawLine(x, y + height, x + width, y + height); //Bottom line
            g.drawLine(x, y, x + width, y + height); //Diagonal line 1
            g.drawLine(x + width, y, x, y + height); //Diagonal line 2
        }
        //Draw chunk borders:
        int x = OFFSET;
        int y = OFFSET;
        int width = GameChunk.DIMENSION * OBJECT_HEIGHT;
        g.setColor(Color.RED);
        g.drawLine(x, y, x + width, y); //Top line
        g.drawLine(x, y, x, y + width); //Left line
        g.drawLine(x + width, y, x + width, y + width); //Right line
        g.drawLine(x, y + width, x + width, y + width); //Bottom line
    }

}
