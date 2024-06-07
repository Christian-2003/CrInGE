package game_engine.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyInput implements KeyListener{

    private static final int KEY_COUNT = 256;

    private enum KeyState{
        RELEASED,       //Used when a KEy is not Pressed
        PRESSED,        //Used when a key is Pressed but already has the Keystate.ONCE
        ONCE            //Used when a Key is Pressed for the first time
    }
    
    //current state of Keyboard
    private boolean[] current = null;

    // Polled Keyboardstate
    private KeyState[] keys = null;

    public KeyInput(){
        current = new boolean[KEY_COUNT];
        keys = new KeyState[KEY_COUNT];
        for (int i = 0; i < KEY_COUNT;i++) {
            keys[i] = KeyState.RELEASED;
        }
    }

    /**
     * syncronises the current Keystates with the Polled Keystates
     */
    public synchronized void poll(){
        int index = 0;
        for (boolean b : current) {
            if (b) {
                if (keys[index] == KeyState.RELEASED) {
                    keys[index] = KeyState.ONCE;
                }else{
                    keys[index] = KeyState.PRESSED;
                }
            }else{
                keys[index] = KeyState.RELEASED;
            }
            index++;
        }
    }

    /**
     * Method that checks if a Key with a spezific Keycode is pressed
     * 
     * @param Keycode of the Key to be checked
     * @return Boolean that is true if the Key is pressed
     */
    public boolean keyDown(int Keycode){
        return keys[Keycode] == KeyState.ONCE || keys[Keycode] == KeyState.PRESSED;
    }

    /**
     * Method that checks if a Key with a spezific Keycode has been Pressed Once
     * 
     * @param Keycode of the Key to be checked
     * @return Boolean that is true if the Key has been pressed once
     */
    public boolean keyDownOnce(int Keycode){
        return keys[Keycode] == KeyState.ONCE;
    }

    /**
     * Method that tracks if a Key is currently pressed
     */
    public synchronized void keyPressed( KeyEvent e ) {
        int keyCode = e.getKeyCode();
        if( keyCode >= 0 && keyCode < KEY_COUNT ) {
            current[ keyCode ] = true;
        }
    }

    /**
     * Method that tracks if a Key has been released
     */
    public synchronized void keyReleased( KeyEvent e ) {
        int keyCode = e.getKeyCode();
        if( keyCode >= 0 && keyCode < KEY_COUNT ) {
            current[ keyCode ] = false;
        }
    }

    
    public void keyTyped( KeyEvent e ) {
        // Not needed
    }


}
