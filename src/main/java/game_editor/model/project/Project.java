package game_editor.model.project;

import java.awt.Color;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * this class represents an Project at runtime
 * 
 * @author Tim Schnur
 */
public class Project {
    private String name, path;

    public Project(String path) {
        this.path = path;
        load();
    }

    private void load() {
        // TODO get Name and everything from path
        File project = new File(this.path);
        this.name = project.getName();
    }

    /**
     * returns an JPanel with all informations for the usage in the Project List
     */
    public JPanel getPanel(){
        JPanel panel = new JPanel();

        panel.setBackground(Color.BLUE);
        panel.add(new JLabel(this.name));
        // TODO create Panel

        return panel;
    }
}
