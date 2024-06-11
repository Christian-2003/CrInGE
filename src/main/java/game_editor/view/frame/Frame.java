package game_editor.view.frame;

import javax.swing.*;

import game_editor.view.project_editor.ProjectEditor;

import java.awt.*;


/**
 * <p>The Frame used for the Editor</p>
 * The following Layout structure is used within: CardLayout -> BorderLayout -> GridBagLayout / GridLayout
 *
 * @author Tim Schnur
 */
public class Frame extends JFrame {

    /**
     * These are the cards that can be displayed within the main Frame
     */
    public static enum cards{
        project_editor,
        project_selecter
    }

    private final CardLayout layout;

    public Frame(){
        super("CrInGE - Editor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.layout = new CardLayout();
        this.setLayout(this.layout);
        this.setIconImage(new ImageIcon(this.getClass().getResource("logo.png")).getImage());
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setJMenuBar(new MenuBar());
        initCards();

        this.setVisible(true);

    }

    private void initCards(){
        //this.add(new ProjectSelectorPanel(), cards.project_selecter.toString()); TODO add if not demo
        this.add(new ProjectEditor(), cards.project_editor.toString());
    }

    /**
     * shows the card within the main Frame
     * @param card that should be shown can be one of the following {@link cards}
     */
    public void showCard(cards card){
        this.layout.show(this.getContentPane(), card.toString());
    }
}
