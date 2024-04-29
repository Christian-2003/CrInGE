package game_editor.model.project;

import java.awt.Color;
import java.io.File;

import javax.swing.JPanel;

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

    public JPanel getPanel(){
        JPanel panel = new JPanel();

        panel.setBackground(Color.BLUE);
        // TODO create Panel

        return panel;
    }
}
