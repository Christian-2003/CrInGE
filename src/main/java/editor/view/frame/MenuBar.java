package editor.view.frame;

import java.util.ArrayList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import editor.model.EditorConfig;

/**
 * The Menubar used for the Editor with all Menu Items.
 * @author Tim Schnur
 */
public class MenuBar extends JMenuBar {

    /** creates an default Menu Bar for the Editor. */
    public MenuBar() {
        super();

        initMenus();
    }

    // TODO Settings, Open (Import), new ...

    /** initializes the default menu items. */
    private void initMenus() {
        JMenu m = new JMenu("File");
        m.add(new JMenuItem("New"));
        m.addSeparator();
        JMenu load = new JMenu("Load Recent");
        @SuppressWarnings("unchecked")
        ArrayList<String> knownProjects = (ArrayList<String>) EditorConfig.get(EditorConfig.Attributes.knownProjects);
        for (String known : knownProjects ) {
            JMenuItem knownItem = new JMenuItem(known);
            load.add(knownItem);
        }
        m.add(load);
        m.addSeparator();
        m.add(new JMenuItem("Save"));

        this.add(m);
        /*
        m.add(new JRadioButtonMenuItem("Wääh", false));
        m.add(new JCheckBoxMenuItem("Waah"));
        */
    }
}
