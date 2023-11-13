package game_engine.controller;


import game_engine.view.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileReader;

/**
 * TODO: Add description.
 *
 * @author  TODO: Add author.
 */
public class GameLoop {

    /**
     * Attribute stores the key event manager which manages keystrokes.
     */
    private KeyEventManager keyEventManager;

    /**
     * Attribute stores the frame which resembles the main window.
     */
    private Frame frame;

    /**
     * Attribute stores the lines of the text to be displayed.
     */
    private String[] lines;


    public GameLoop(String filepath) {
        keyEventManager = new KeyEventManager();

        //Read file contents:
        try {
            FileEditor fileEditor = new FileEditor(filepath);
            lines = fileEditor.read().split("\n");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        frame = new Frame("CrInGE - Demo Game", lines);

        keyEventManager.add(KeyEvent.VK_UP, key -> frame.scrollUp());
        keyEventManager.add(KeyEvent.VK_DOWN, key -> frame.scrollDown());

    }

}
