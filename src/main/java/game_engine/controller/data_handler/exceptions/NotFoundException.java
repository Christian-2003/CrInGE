/**
 * This package contains every exception.
 */
package game_engine.controller.data_handler.exceptions;

/**
 * An Exception for when in a List an items it searched.
 * ...by its ID (like asset or group) and couldn't be found.
 * @author FabianDev001
 */
public class NotFoundException extends Exception {
    /**
     * Throws Exception.
     * @param message
     */
    public NotFoundException(final String message) {
        super(message);
    }

}
