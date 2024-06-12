package editor.view.frame;


import java.awt.CardLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import editor.view.project_editor.ProjectEditor;



/**
 * <p>The Frame used for the Editor. </p>
 * The following Layout structure is used within:
 * CardLayout -> BorderLayout -> GridBagLayout / GridLayout
 * @author Tim Schnur
 */
public class Frame extends JFrame {

    /** These are the Cards that can be displayed within the main Frame. */
    public enum Cards {
        /** crad for editing a project. */
        project_editor,
        /** card for selecting a project. */
        project_selecter
    }

    /** layout used within the main frame. */
    private final CardLayout layout;

    /** creates the main frame and sets default values. */
    public Frame() {
        super("CrInGE - Editor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.layout = new CardLayout();
        this.setLayout(this.layout);
        this.setIconImage(new ImageIcon(
            this.getClass().getResource("logo.png")).getImage());
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setJMenuBar(new MenuBar());
        initCards();

        this.setVisible(true);

    }

    /** initilizes the differnt Cards of the frame. */
    private void initCards() {
    //this.add(new ProjectSelectorPanel(), Cards.project_selecter.toString());
    //TODO add if not demo
        this.add(new ProjectEditor(), Cards.project_editor.toString());
    }

    /**
     * shows the card within the main Frame.
     * @param card that should be shown of type {@link Cards}
     */
    public void showCard(final Cards card) {
        this.layout.show(this.getContentPane(), card.toString());
    }
}
