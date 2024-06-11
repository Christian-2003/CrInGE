package game_editor.view.project_editor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import game_editor.view.project_editor.map_editor.MapEditor;


/**
 * TODO: Add description.
 *
 * @author Schnur Tim
 */
public class ProjectEditor extends JPanel {

    public ProjectEditor(){
        this.add(new MapEditor());
    }

    //TODO add project information

}
