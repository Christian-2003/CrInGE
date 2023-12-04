package game_editor.controller;

import game_engine.controller.RendererManager;
import game_engine.controller.math_engine.MathException;
import game_engine.model.GameChunk;
import game_engine.model.GameMap;
import game_engine.model.GameObject;
import game_engine.model.MapObject;
import game_engine.view.canvas.SwingGameCanvas;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;


/**
 * Class tests the functionalities of the <i>Game Engine</i>.
 *
 * @author  Christian-2003
 */
public class Engine {
    
    public static void main(String[] args) throws NullPointerException, MathException {

        int width = 5, height = 3;
        GameMap map = new GameMap(width, height, createChunks(width, height, 100), loadTextures());
        RendererManager renderer = new RendererManager(map);
        //renderer.setDebugRendering(true);
        //renderer.updateMapPosition(5, 10);
        SwingGameCanvas canvas = new SwingGameCanvas(renderer);

        JFrame frame = new JFrame("Game Test");
        frame.add(canvas);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    /**
     * Method generates an array of GameChunks of the specified size with random MapObjects.
     * The method does not test, whether the arguments are valid, as this method is only meant to be used for
     * testing!
     * The returned array can be passed to the constructor of {@link GameMap} directly.
     *
     * @param width             With (in chunks) for the map.
     * @param height            Width (in chunks) for the map.
     * @param objectPercentage  Probability (number between 0 and 100) for a MapObject to be generated.
     * @return                  Array of GameChunks.
     */
    public static GameChunk[] createChunks(int width, int height, int objectPercentage) {
        GameChunk[] chunks = new GameChunk[width * height];
        Random random = new Random();
        for (int i = 0; i < chunks.length; i++) {
            ArrayList<MapObject> mapObjects = new ArrayList<>();
            int chunkHeight = i / width;
            //Generate mapObjects:
            for (int x = 0; x < GameChunk.WIDTH; x++) {
                for (int y = 0; y < GameChunk.HEIGHT; y++) {
                    if (random.nextInt(100) < objectPercentage) {
                        //int objectWidth = random.nextInt(1) + 1;
                        //int objectHeight = random.nextInt(1) + 1;
                        int objectWidth = 1;
                        int objectHeight = 1;
                        int texture = MapObject.NO_TEXTURE;
                        if (chunkHeight > height / 2) {
                            texture = random.nextInt(3) + 5;
                        }
                        else {
                            texture = random.nextInt(5);
                        }
                        mapObjects.add(new MapObject(true, false, new Dimension(objectWidth, objectHeight), new Dimension(objectWidth, objectHeight), x, y, texture, false, false));
                    }
                }
            }
            //Convert to Array:
            MapObject[] mo = new MapObject[mapObjects.size()];
            for (int j = 0; j < mo.length; j++) {
                mo[j] = mapObjects.get(j);
            }
            chunks[i] = new GameChunk(mo, null);
        }
        return chunks;
    }


    /**
     * Static method loads the minecraft textures from the "textures" directory from
     * the resources and returns them as array of {@linkplain ImageIcon}s.
     *
     * @return  Array of textures.
     */
    public static ImageIcon[] loadTextures() {
        ImageIcon[] icons = new ImageIcon[8];
        URL cobblestone = Engine.class.getResource("/textures/cobblestone.png");
        if (cobblestone != null) {
            icons[0] = new ImageIcon(cobblestone);
        }
        URL stone = Engine.class.getResource("/textures/stone.png");
        if (stone != null) {
            icons[1] = new ImageIcon(stone);
        }
        URL andesite = Engine.class.getResource("/textures/andesite.png");
        if (andesite != null) {
            icons[2] = new ImageIcon(andesite);
        }
        URL diorite = Engine.class.getResource("/textures/diorite.png");
        if (diorite != null) {
            icons[3] = new ImageIcon(diorite);
        }
        URL granite = Engine.class.getResource("/textures/granite.png");
        if (granite != null) {
            icons[4] = new ImageIcon(granite);
        }
        URL deepslate = Engine.class.getResource("/textures/deepslate.png");
        if (deepslate != null) {
            icons[5] = new ImageIcon(deepslate);
        }
        URL cobbled_deepslate = Engine.class.getResource("/textures/cobbled_deepslate.png");
        if (cobbled_deepslate != null) {
            icons[6] = new ImageIcon(cobbled_deepslate);
        }
        URL tuff = Engine.class.getResource("/textures/tuff.png");
        if (tuff != null) {
            icons[7] = new ImageIcon(tuff);
        }
        return icons;
    }

}
