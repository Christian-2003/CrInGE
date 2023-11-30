package game_engine.view.canvas;

import game_engine.controller.RendererManager;
import game_engine.model.GameChunk;
import game_engine.model.GameMap;
import game_engine.model.MapObject;

import javax.swing.*;
import java.awt.*;

public class SwingGameCanvas extends JComponent {

    /**
     * Attribute <i>references</i> the {@link RendererManager} which renders a {@link GameMap} to this component.
     */
    private final RendererManager rendererManager;


    public SwingGameCanvas(RendererManager rendererManager) throws NullPointerException {
        if (rendererManager == null) {
            throw new NullPointerException("Null is invalid RendererManager.");
        }
        this.rendererManager = rendererManager;
    }


    @Override
    public void paintComponent(Graphics g) {
        rendererManager.renderGameMap(this, g);
        /*
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
        */
    }

}
