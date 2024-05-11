package game_engine.controller.game_dataHandler.Exceptions;

import java.nio.file.Path;

/**
 * An Exception for when the GameData File Syntax has encountered any issues
 * Like from Format or wrong Syntax
 *
 * @author FabianDev001
 */
public class GameDataFileSyntaxException extends Exception {

    public GameDataFileSyntaxException(String error){
        super(error);
    }

    /**
     * If a Strace needs to be added to the Exception
     * @param error | String
     * @param path | Path as String
     */
    public GameDataFileSyntaxException(String error, String path){
        super(error, new Throwable(path));
    }
}
