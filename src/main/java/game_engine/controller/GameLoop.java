package game_engine.controller;

import game_engine.model.GameMap;
import game_engine.view.GameFrame;


/**
 * Class implements the GameLoop, which controls the entire game engine. The class is responsible for everything that
 * needs to be done repeatedly.
 *
 * @author  Christian-2003
 */
public class GameLoop {

    /**
     * Attribute stores the {@link GameFrame} which resembles the main window for the video game.
     */
    private final GameFrame gameFrame;

    /**
     * Attribute stores the {@link GameMap}.
     */
    private GameMap map;

    /**
     * Attribute stores the {@link RendererManager} that is used to render the {@link #map} to the {@link #gameFrame}.
     */
    private final RendererManager rendererManager;


    /**
     * Constructor instantiates a new {@link GameLoop} for the passed {@link GameMap}.
     *
     * @param map                   GameMap to be used for the game.
     * @throws NullPointerException The passed GameMap is {@code null}.
     */
    public GameLoop(GameMap map) throws NullPointerException {
        if (map == null) {
            throw new NullPointerException("Null is invalid GameMap");
        }
        this.map = map;
        this.rendererManager = new RendererManager(map);
        gameFrame = new GameFrame(rendererManager);
    }

}
