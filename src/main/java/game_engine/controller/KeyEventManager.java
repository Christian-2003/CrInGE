package game_engine.controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;


/**
 * Class implements a KeyEventManager which handles {@link GameKeyEvent}s.
 * To add a GameKeyEvent, call {@link KeyEventManager#add(int, GameKeyEvent)} and pass the desired GameKeyEvent alongside
 * a keyCode, which indicates the key for which to listen (as defined in {@link KeyEvent}).
 * Example:<br>
 * {@code
 *     KeyEventManager keyEventManager = new KeyEventManager();
 *     keyEventManager.add(KeyEvent.VK_UP, key -> System.out.println("Hello World"));
 * }<br>
 * Prints "Hello World" to the console whenever the UP-Key is pressed.
 *
 * @author  Christian-2003
 */
public class KeyEventManager {

    /**
     * Record models an association which associates a {@link GameKeyEvent} with a key code. Key codes are
     * specified within {@link KeyEvent}.
     *
     * @param keyEvent Attribute stores the {@link GameKeyEvent} that shall be associated with a specific {@link #keyCode}.
     * @param keyCode  Attribute stores the keyCode (as specified in {@link KeyEvent}) for the {@link #keyEvent}.
     * @author Christian-2003
     */
    private record GameKeyEventAssociation(GameKeyEvent keyEvent, int keyCode) {

        /**
         * Constructor instantiates a new {@link GameKeyEventAssociation} that associates the passed {@link GameKeyEvent}
         * with the specified keyCode.
         *
         * @param keyEvent GameKeyEvent that shall be associated with the specified keyCode.
         * @param keyCode  KeyCode (as specified in {@link KeyEvent}) for the GameKeyEvent.
         * @throws NullPointerException The passed GameKeyEvent is {@code null}.
         */
        private GameKeyEventAssociation {
            if (keyEvent == null) {
                throw new NullPointerException("Null is invalid keyEvent");
            }
        }


        /**
         * Method returns the {@link GameKeyEvent} of this association.
         *
         * @return GameKeyEvent of this association.
         */
         @Override
         public GameKeyEvent keyEvent() {
            return keyEvent;
         }

         /**
          * Method returns the keyCode of this association.
          *
          * @return KeyCode of this association.
          */
         @Override
         public int keyCode() {
            return keyCode;
         }

    }


    /**
     * Attribute stores all registered key events.
     */
    private final LinkedList<GameKeyEventAssociation> keyEvents;


    /**
     * Constructor instantiates a new {@link KeyEventManager}.
     */
    public KeyEventManager() {
        keyEvents = new LinkedList<>();
    }


    /**
     * Method adds a new {@link GameKeyEvent} to the KeyEventManager.
     * The {@link GameKeyEvent#onKeyPressed(int)}-method is called whenever the passed {@code keyCode} is
     * pressed.
     *
     * @param keyCode               KeyCode for which to listen.
     * @param keyEvent              GameKeyEvent to call when the listened key is pressed.
     * @throws NullPointerException The passed GameKeyEvent is {@code null}.
     */
    public void add(int keyCode, GameKeyEvent keyEvent) throws NullPointerException {
        if (keyEvent == null) {
            throw new NullPointerException("Null is invalid key event");
        }
        addKeyEventDispatcher(keyCode);
        keyEvents.add(new GameKeyEventAssociation(keyEvent, keyCode));
    }


    /**
     * Method adds a new {@link KeyEventDispatcher} to the <i>KeyboardFocusManager</i> of the application.
     *
     * @param keyCode   KeyCode for which to add a new KeyEventDispatcher.
     */
    private void addKeyEventDispatcher(int keyCode) {
        //Test whether a KeyEventDispatcher already exists:
        for (GameKeyEventAssociation event : keyEvents) {
            if (event.keyCode() == keyCode) {
                return;
            }
        }

        //New KeyEventDispatcher needs to be added:
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            synchronized (KeyEventManager.class) {
                if (e.getKeyCode() == keyCode && e.getID() == KeyEvent.KEY_PRESSED) {
                    //Call all GameKeyEvents that with keyCode:
                    for (GameKeyEventAssociation event : keyEvents) {
                        if (event.keyCode() == keyCode) {
                            //Registered event found:
                            event.keyEvent().onKeyPressed(keyCode);
                        }
                    }
                }
                return false;
            }
        });
    }

}
