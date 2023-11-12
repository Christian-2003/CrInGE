package game_editor.controller;

import game_engine.controller.GameKeyEvent;
import game_engine.controller.KeyEventManager;
import game_engine.controller.math_engine.EMatrix;
import game_engine.controller.math_engine.MathException;
import game_engine.view.Frame;

import java.awt.event.KeyEvent;

public class Engine {
    
    public static void main(String[] args) throws NullPointerException, MathException {
        Frame frame = new Frame("Main Frame");
        KeyEventManager keyEventManager = new KeyEventManager();
        keyEventManager.add(KeyEvent.VK_UP, keyCode -> {
            System.out.println("UP");
        });
        keyEventManager.add(KeyEvent.VK_DOWN, keyCode -> {
            System.out.println("DOWN");
        });
        keyEventManager.add(KeyEvent.VK_DOWN, keyCode -> {
            System.out.println("DOWN2");
        });
    }
    
}
