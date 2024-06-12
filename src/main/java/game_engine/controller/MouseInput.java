package game_engine.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener{

    private static final int BUTTON_COUNT = 3;
    //polled Position of the curser
    private Point mousePos = null;
    //current Position of the cursor
    private Point currentPos = null;    
    //current state of the buttons
    private boolean[] state = null;
    //polled state of the buttons
    private MouseState[]poll = null;

    private enum MouseState{
        RELEASED,       //Used when a KEy is not Pressed
        PRESSED,        //Used when a key is Pressed but already has the Keystate.ONCE
        ONCE            //Used when a Key is Pressed for the first time
    }

    public MouseInput() {
        // Create default mouse positions
        mousePos = new Point( 0, 0 );
        currentPos = new Point( 0, 0 );
        // Setup initial button states
        state = new boolean[ BUTTON_COUNT ];
        poll = new MouseState[ BUTTON_COUNT ];
        for( int i = 0; i < BUTTON_COUNT; ++i ) {
          poll[ i ] = MouseState.RELEASED;
        }
    }

    /**
     * syncronises the current mouse position and keystates with the polled ones
     */
    public synchronized void poll() {
        //position polling
        mousePos = new Point( currentPos );
        //button polling
        int index = 0;
        for( boolean b : state) {
          if( b ) {
            if( poll[ index ] == MouseState.RELEASED )
              poll[ index ] = MouseState.ONCE;
            else
              poll[ index ] = MouseState.PRESSED;
          } else {
              poll[ index ] = MouseState.RELEASED;
          }
          index++;
        }
    }

    public Point getPosition() {
        return mousePos;
    }

    /**
     * 
     * @param button    int Number of the Button 1 to 3
     * @return          true if the requestet Button has been pressed once
     */
    public boolean buttonDownOnce( int button ) {
        return poll[ button-1 ] == MouseState.ONCE;
    }

    /**
     * 
     * @param button    int Number of the Button 1 to 3
     * @return          true if the requestet Button is beeing pressed
     */
    public boolean buttonDown( int button ) {
        return poll[ button-1 ] == MouseState.ONCE ||
                poll[ button-1 ] == MouseState.PRESSED;
    }

    public synchronized void mousePressed( MouseEvent e ) {
        state[ e.getButton()-1 ] = true;
    }

    public synchronized void mouseReleased( MouseEvent e ) {
        state[ e.getButton()-1 ] = false;
    }

    public synchronized void mouseEntered( MouseEvent e ) {
        mouseMoved( e );
    }

    public synchronized void mouseExited( MouseEvent e ) {
        mouseMoved( e );
    }

    public synchronized void mouseDragged( MouseEvent e ) {
        mouseMoved( e );
    }

    public synchronized void mouseMoved( MouseEvent e ) {
        currentPos = e.getPoint();
    }

    public void mouseClicked( MouseEvent e ) {
        // Not needed
    }
}
