package game_engine.controller;


/**
 * Interface models a key event. The implemented method {@link #onKeyPressed(int)} is called by the {@link KeyEventManager}
 * whenever the specified key is pressed. The key in question must be specified when the {@link GameKeyEvent} is
 * added to the KeyEventManager.
 *
 * @author  Christian-2003
 */
public interface GameKeyEvent {

    /**
     * Method is called by the {@link KeyEventManager} whenever the specified key is pressed.
     *
     * @param keyCode   Key which was pressed as defined in {@link java.awt.event.KeyEvent}.
     */
    void onKeyPressed(int keyCode);

}
