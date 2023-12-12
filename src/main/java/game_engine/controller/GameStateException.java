package game_engine.controller;


/**
 * Class models an {@link Exception} which can be thrown by the Engine on state-related exceptions.
 *
 * @author  Christian-2003
 */
public class GameStateException extends Exception {

    /**
     * Constructor instantiates a new {@link GameStateException} with the passed message.
     *
     * @param message   Message for the exception.
     */
    public GameStateException(String message) {
        super(message);
    }

}
