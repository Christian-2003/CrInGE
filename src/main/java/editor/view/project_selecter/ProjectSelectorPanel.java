package editor.view.project_selecter;

import java.awt.BorderLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * TODO Add description.
 * @author Tim Schnur
 */
public class ProjectSelectorPanel extends JPanel {

    /** initializes Project selector panel. */
    public ProjectSelectorPanel() {
        super(new BorderLayout());
        this.add(new JLabel("Search Bar"), BorderLayout.NORTH);
        this.add(new ProjectList());
    }

    /** inits the UI components. */
    private void initUI() {
        JPanel searchBar = new JPanel();
        JPanel projectList = new JPanel();
        JPanel project = new JPanel();

        //TODO Sort by changDate, Label, Path
        searchBar.add(new JButton("sortByName"));
        searchBar.add(new TextField("Search By Name"));
        searchBar.add(new JButton("Search Now"));

        //TODO for every found Project
        projectList.add(project);

        project.add(new JLabel("Name"));
        project.add(new JLabel("Path"));
        project.add(new JButton("Export"));
        project.add(new JLabel("Open in Editor"));
        project.add(new JButton("Delete"));

        //TODO within Project Editor:
        //rename, change path location, delete, export save;
        //TODO on delete: delete on disk or just in editor
    }
}
