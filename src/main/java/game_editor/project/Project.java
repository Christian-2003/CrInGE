package game_editor.project;

import java.awt.Color;

import javax.swing.JPanel;

public class Project {
    private final String name;
    public Project(final String name){
        this.name = name;
        // TODO get Name and everything from path
    }

    public JPanel getPanel(){
        JPanel panel = new JPanel();

        panel.setBackground(Color.BLUE);
        // TODO create Panel

        return panel;
    }
}
