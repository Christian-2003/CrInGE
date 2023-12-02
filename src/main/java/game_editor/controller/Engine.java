package game_editor.controller;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import game_engine.controller.RendererManager;
import game_engine.controller.math_engine.MathException;
import game_engine.model.GameChunk;
import game_engine.model.GameMap;
import game_engine.model.MapObject;
import game_engine.view.canvas.GameCanvas;
import game_engine.view.canvas.SwingGameCanvas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Engine {
    
    public static void main(String[] args) throws NullPointerException, MathException {

        int width = 3, height = 2;
        GameMap map = new GameMap(width, height, createChunks(width, height, 20));
        RendererManager renderer = new RendererManager(map);
        renderer.updateMapPosition(5, 10);
        SwingGameCanvas canvas = new SwingGameCanvas(renderer);

        JFrame frame = new JFrame("Game Test");
        frame.add(canvas);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public static GameChunk[] createChunks(int width, int height, int objectPercentage) {
        GameChunk[] chunks = new GameChunk[width * height];
        Random random = new Random();
        for (int i = 0; i < chunks.length; i++) {
            ArrayList<MapObject> mapObjects = new ArrayList<>();
            //Generate mapObjects:
            for (int x = 0; x < GameChunk.WIDTH; x++) {
                for (int y = 0; y < GameChunk.HEIGHT; y++) {
                    if (random.nextInt(100) < objectPercentage) {
                        int objectWidth = random.nextInt(2) + 1;
                        int objectHeight = random.nextInt(2) + 1;
                        mapObjects.add(new MapObject(true, false, new Dimension(objectWidth, objectHeight), new Dimension(objectWidth, objectHeight), x, y, false, false));
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
    
}
