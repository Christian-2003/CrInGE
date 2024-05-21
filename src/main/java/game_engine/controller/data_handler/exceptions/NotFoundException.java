package game_engine.controller.data_handler.exceptions;

/**
 * An Exception for when in a List an items it searched by its ID (like asset or group) and couldn't be found
 * @see game_engine.controller.data_handler.game_data_format.Group
 * @see game_engine.controller.data_handler.game_data_format.Asset
 * @author FabianDev001
 */
public class NotFoundException extends Exception {

    public NotFoundException(String message){
        super(message);
    }

}
