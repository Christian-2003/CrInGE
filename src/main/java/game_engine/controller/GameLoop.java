package game_engine.controller;

import game_engine.controller.events.CollisionEventDetector;
import game_engine.controller.events.MoveEventDetector;
import game_engine.model.entities.Entity;
import game_engine.model.entities.Player;
import game_engine.model.events.EventTypes;
import game_engine.model.map.GameMap;
import game_engine.view.GameFrame;

import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;


/**
 * Class implements the GameLoop, which controls the entire game engine.
 * The class is responsible for everything that needs to be done repeatedly.
 *
 * @author  Christian-2003
 */
public class GameLoop implements Runnable {

    /**
     * Attribute stores the {@link GameFrame} which resembles the main window
     * for the video game.
     */
    private final GameFrame gameFrame;

    /**
     * Attribute stores the {@link GameMap}.
     */
    private final GameMap map;

    /**
     * Attribute stores the {@link RendererManager} that is used to render
     * the {@link #map} to the {@link #gameFrame}.
     */
    private final RendererManager rendererManager;

    /**
     * Attribute stores the {@link Thread} which runs the game loop.
     */
    private Thread loop;

    /**
     * Attribute stores a flag which indicates whether the game loop shall
     * continue running. Set this to {@code false} to stop the game loop.
     */
    private volatile boolean continueLoop;


    /**
     * Constructor instantiates a new {@link GameLoop} for the passed
     * {@link GameMap}.
     *
     * @param map                   GameMap to be used for the game.
     * @throws NullPointerException The passed GameMap is {@code null}.
     */
    public GameLoop(final GameMap map) throws NullPointerException {
        if (map == null) {
            throw new NullPointerException("Null is invalid GameMap");
        }
        this.map = map;
        this.rendererManager = new RendererManager(map);
        //rendererManager.setDebugRendering(true);
        gameFrame = new GameFrame(rendererManager);
        //Set "continueLoop" initially to false. In case anyone tries to make
        //the GameLoop-instance a thread, the loop will not do anything! The
        //loop will therefore only work when the thread is created through this
        //instance with proper instantiation beforehand.
        continueLoop = false;
    }


    /**
     * Method starts the game loop.
     *
     * @throws GameStateException   The game loop is already running!
     */
    public void startLoop() throws GameStateException {
        if (loop != null && !continueLoop) {
            //Game loop already running:
            throw new GameStateException("Game loop already running");
        }
        continueLoop = true;
        loop = new Thread(this);
        loop.start();
    }


    /**
     * Method stops the game loop.
     */
    public void stopLoop() {
        continueLoop = false;
    }


    /**
     * Method contains the game loop which allows the game loop to run in
     * another thread. The thread will be initialized automatically once
     * the classes {@link #startLoop()}-method is invoked. Stop the execution
     * of the thread by calling {@link #stopLoop()}.
     */
    public void run() {
        //Game loop:
        Player player = (Player) EntityManager.getInstance().getAllEntities().stream().filter(entity -> entity instanceof Player).findFirst().orElse(null);
        if(player == null) {
            throw new NullPointerException("No player found");
        }

        KeyInput keyInput = new KeyInput();
        gameFrame.addKeyListener(keyInput);

        while (continueLoop) {
            //TODO: Implement game loop.
            keyInput.poll();

            boolean playerMoveLeft = keyInput.keyDown(KeyEvent.VK_A) || keyInput.keyDown(KeyEvent.VK_LEFT);
            boolean playerMoveRight = keyInput.keyDown(KeyEvent.VK_D) || keyInput.keyDown(KeyEvent.VK_RIGHT);

            if (playerMoveLeft && !playerMoveRight) {
                player.setPosition(player.getX() - 0.05, player.getY());
            } else if (playerMoveRight && !playerMoveLeft) {
                player.setPosition(player.getX() + 0.05, player.getY());
            }

            // Event detection
            for (Entity entity : EntityManager.getInstance().getAllEntities().stream().toList()) {
                Set<EventTypes> entityEvents = entity.getAllEvents().keySet();

                if (entityEvents.contains(EventTypes.COLLISION)) {
                    CollisionEventDetector.getInstance().detect(entity);
                }
                if (entityEvents.contains(EventTypes.MOVE)) {
                    MoveEventDetector.getInstance().detect(entity);
                }
            }
            this.gameFrame.repaintCanvas();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
