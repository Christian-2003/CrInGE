package game_engine;

import game_engine.controller.GameLoop;
import game_engine.controller.data_handler.exceptions.GameDataFileSyntaxException;
import game_engine.controller.data_handler.exceptions.NotFoundException;
import game_engine.controller.data_handler.Loader;
import game_engine.model.map.GameChunk;
import game_engine.model.map.GameMap;
import game_engine.model.map.objects.MapObject;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;


/**
 * Class tests the functionalities of the <i>Game Engine</i>.
 *
 * @author  Christian-2003
 */
public class Engine {
    
    public static void main(String[] args) throws NullPointerException, NotFoundException, GameDataFileSyntaxException {
        //Add all entities:
        Loader load = new Loader();
        ImageIcon[] icons = load.getImgSources();
        GameLoop gameLoop = new GameLoop(generateGameMap(5, 3, icons));
    }


    /**
     * Method generates a new random GameMap that can be used for testing.
     *
     * @param width     Width for the GameMap.
     * @param height    Height for the GameMap.
     * @return          Generated GameMap.
     */
    private static GameMap generateGameMap(final int width, final int height) {
        return new GameMap(width, height, createChunks(width, height, 100), loadTextures(), 13);
    }
    /**
     * Method generates a new random GameMap with additional entity's.
     *
     * @param width     Width for the GameMap.
     * @param height    Height for the GameMap.
     * @param icons     A ImageIcon List with all the necessary icons
     * @return          Generated GameMap.
     */
    private static GameMap generateGameMap(int width, int height, ImageIcon[] icons) {
        return new GameMap(width, height, createChunks(width, height, 100), icons, 13);
    }

    /**
     * Method generates an array of GameChunks of the specified size with
     * random MapObjects. The method does not test, whether the arguments are
     * valid, as this method is only meant to be used for testing!
     * The returned array can be passed to the constructor of {@link GameMap}
     * directly.
     *
     * @param width             With (in chunks) for the map.
     * @param height            Width (in chunks) for the map.
     * @param objectPercentage  Probability (number between 0 and 100) for a
     *                          MapObject to be generated.
     * @return                  Array of GameChunks.
     */
    private static GameChunk[] createChunks(final int width, final int height, final int objectPercentage) {
        GameChunk[] chunks = new GameChunk[width * height];
        Random random = new Random();
        for (int i = 0; i < chunks.length; i++) {
            ArrayList<MapObject> mapObjects = new ArrayList<>();
            int chunkHeight = i / width;
            //Generate mapObjects:
            for (int x = 0; x < GameChunk.WIDTH; x++) {
                for (int y = 0; y < GameChunk.HEIGHT; y++) {
                    if (random.nextInt(100) < objectPercentage) {
                        int texture = MapObject.NO_TEXTURE;
                        if (chunkHeight == 0) {
                            //Top chunk:
                            if (y == 0) {
                                texture = 8;
                            }
                            else if (y <= 5) {
                                texture = random.nextInt(3) + 9;
                            }
                            else {
                                texture = random.nextInt(5);
                            }
                        }
                        else if (chunkHeight > height / 2) {
                            texture = random.nextInt(3) + 5;
                        }
                        else {
                            texture = random.nextInt(5);
                        }
                        mapObjects.add(new MapObject(true, false, x, y, texture));
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
     * Static method loads the minecraft textures from the "textures" directory
     * from the resources and returns them as array of {@linkplain ImageIcon}s.
     *
     * @return  Array of textures.
     */
    private static ImageIcon[] loadTextures() {
        final int numberOfTextures = 14;
        ImageIcon[] icons = new ImageIcon[numberOfTextures];
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
        URL cobbledDeepslate = Engine.class.getResource("/textures/cobbled_deepslate.png");
        if (cobbledDeepslate != null) {
            icons[6] = new ImageIcon(cobbledDeepslate);
        }
        URL tuff = Engine.class.getResource("/textures/tuff.png");
        if (tuff != null) {
            icons[7] = new ImageIcon(tuff);
        }
        URL grass = Engine.class.getResource("/textures/grass.png");
        if (grass != null) {
            icons[8] = new ImageIcon(grass);
        }
        URL dirt = Engine.class.getResource("/textures/dirt.png");
        if (dirt != null) {
            icons[9] = new ImageIcon(dirt);
        }
        URL coarseDirt = Engine.class.getResource("/textures/coarse_dirt.png");
        if (coarseDirt != null) {
            icons[10] = new ImageIcon(coarseDirt);
        }
        URL rootedDirt = Engine.class.getResource("/textures/rooted_dirt.png");
        if (rootedDirt != null) {
            icons[11] = new ImageIcon(rootedDirt);
        }
        URL creeper = Engine.class.getResource("/textures/creeper.png");
        if (creeper != null) {
            icons[12] = new ImageIcon(creeper);
        }
        URL skybox = Engine.class.getResource("/textures/skybox.png");
        if (skybox != null) {
            icons[13] = new ImageIcon(skybox);
        }
        return icons;
    }

}
