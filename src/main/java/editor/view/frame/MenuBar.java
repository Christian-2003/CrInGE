package editor.view.frame;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * The Menubar used for the Editor with all Menu Items.
 * @author Tim Schnur
 */
public class MenuBar extends JMenuBar {

    /** creates an default Menu Bar for the Editor. */
    public MenuBar() {
        super();

        //initMenus(); TODO make Menu
    }

    // TODO Settings, Open (Import), new ...

    /** initializes the default menu items. */
    private void initMenus() {
        JMenu m = new JMenu("Temp");
        m.add(new JMenuItem("Test"));
        m.addSeparator();
        m.add(new JRadioButtonMenuItem("Wääh", false));
        m.add(new JCheckBoxMenuItem("Waah"));
        this.add(m);
    }
}
