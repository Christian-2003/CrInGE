package editor.view.project_editor;

import javax.swing.JPanel;

import editor.view.project_editor.map_editor.MapEditor;


/**
 * TODO Add description.
 * @author Schnur Tim
 */
public class ProjectEditor extends JPanel {

    /** creates an projet editor panel for an demo project. */
    public ProjectEditor() {
        this.add(new MapEditor());
    }

    //TODO add project information

}
