/**
 * This package contains exceptions that can be dropped by the data_handler.
 */
package game_engine.controller.data_handler.exceptions;

/**
 * An Exception for when the GameData File Syntax has encountered any issues.
 * Like from Format or wrong Syntax
 *
 * @author FabianDev001
 */
public class GameDataFileSyntaxException extends Exception {
    /**
     * Error-Handler for GameData File Syntax Exceptions.
     * @param error
     */
    public GameDataFileSyntaxException(final String error) {
        super(error);
    }

    /**
     * If a Strace needs to be added to the Exception.
     * @param error | String
     * @param path | Path as String
     */
    public GameDataFileSyntaxException(final String error, final String path) {
        super(error, new Throwable(path));
    }

}
