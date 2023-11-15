package game_engine.controller;


import game_engine.view.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
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


    public GameLoop() {
        keyEventManager = new KeyEventManager();

        //Get a text file in the current folder:
        File folder = new File("./");
        String selectedPath = null;
        File[] files = folder.listFiles();
        if (files == null) {
            JOptionPane.showMessageDialog(null, "Did not find any file to read.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (File file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                selectedPath = file.getPath();
            }
        }
        if (selectedPath == null) {
            JOptionPane.showMessageDialog(null, "Did not find any file to read.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Read file contents:
        try {
            FileEditor fileEditor = new FileEditor(selectedPath);
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
