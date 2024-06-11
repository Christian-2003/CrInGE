package editor.view.project_selecter;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import editor.model.Project;

/**
 * TODO add description.
 * @author Tim Schnur
 */
public class ProjectList extends JPanel {
    /** Layout used on this panel. */
    private final GridLayout layout;

    /** initializes the panel. */
    public ProjectList() {
        super();
        layout = new GridLayout(19, 1); // TODO dynamische anzahl
        this.setLayout(layout);

        //TODO an richtige stelle und dynamisch
        this.add(new Project("Dummy").getPanel());
        this.add(new Project("Dummy").getPanel());
        this.add(new Project("Dummy").getPanel());

        //TODO muss in extra funktion, damit beim ansichtsswitch passieren kann
        SwingUtilities.invokeLater(() -> {
            System.out.println(this.getHeight());
            layout.setVgap(this.getHeight() / (19 * 4));
            this.invalidate();
            this.validate();
            this.repaint();
        });
    }
}
