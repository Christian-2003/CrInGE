package game_editor.view.project_selecter;

import javax.swing.*;

import game_editor.model.project.Project;

import java.awt.*;

public class ProjectList extends JPanel{
    private final GridLayout layout;

    public ProjectList(){
        super();
        layout = new GridLayout(19, 1); // TODO dynamische anzahl
        this.setLayout(layout);

        this.add(new Project("Dummy").getPanel()); //TODO an richtige stelle und dynamisch
        this.add(new Project("Dummy").getPanel());
        this.add(new Project("Dummy").getPanel());
        
        SwingUtilities.invokeLater(() -> { //TODO muss in extra funktion, damit beim ansichtsswitch passieren kann
            System.out.println(this.getHeight());
            layout.setVgap(this.getHeight()/(19*4));
            this.invalidate();
            this.validate();
            this.repaint();
        });
    }
}
