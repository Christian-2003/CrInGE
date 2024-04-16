package game_editor.view.project_selecter;

import javax.swing.*;
import java.awt.*;


/**
 * TODO: Add description.
 *
 * @author Tim Schnur
 */
public class ProjectSelectorPanel extends JPanel {

    public ProjectSelectorPanel(){
        super(new BorderLayout());
        this.add(new JLabel("Search Bar"), BorderLayout.NORTH);
        this.add(new ProjectList());
    }

    private void initUI(){
        JPanel searchBar = new JPanel(), projectList = new JPanel(), project = new JPanel();

        searchBar.add(new JButton("sortByName")); searchBar.add(new TextField("Search By Name")); searchBar.add(new JButton("Search Now")); //TODO Sort by changDate, Label, Path

        projectList.add(project); // TODO for every found Project

        project.add(new JLabel("Name")); project.add(new JLabel("Path")); project.add(new JButton("Export")); project.add(new JLabel("Open in Editor")); project.add(new JButton("Delete"));

        //TODO within Project Editor: rename, change path location, delete, export save;
        //TODO on delete: delete on disk or just in editor
    }
}
