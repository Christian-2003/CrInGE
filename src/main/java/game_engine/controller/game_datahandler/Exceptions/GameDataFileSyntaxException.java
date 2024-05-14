package game_engine.controller.game_dataHandler.Exceptions;

/**
 * An Exception for when the GameData File Syntax has encountered any issues
 * Like from Format or wrong Syntax
 * @author FabianDev001
 */
public class GameDataFileSyntaxException extends Exception {
    public GameDataFileSyntaxException(String error){
        super(error);
    }
}