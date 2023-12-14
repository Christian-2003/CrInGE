package game_editor.view.frame;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * TODO add description
 * 
 * @author Tim Schnur
 */
public class MenuBar extends JMenuBar{
    public MenuBar(){
        super();

        initMenus();
    }

    // TODO Settings, Open (Import), new ...

    private void initMenus(){
        JMenu m = new JMenu("Temp");
        m.add(new JMenuItem("Test"));
        m.addSeparator();
        m.add(new JRadioButtonMenuItem("Wääh", false));
        m.add(new JCheckBoxMenuItem("Waah"));
        this.add(m);
    }
}
