package game_editor.view.frame;

import javax.swing.*;

import game_editor.view.project_editor.ProjectEditorPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * TODO: Add description.
 * CardLayout -> BorderLayout -> GridBagLayout / GridLayout
 * JTabbedPane -> addTap
 *
 * @author Tim Schnur
 */
public class Frame extends JFrame {

    public static enum cards{
        project_editor,
        project_selecter
    }

    private final CardLayout layout;

    public Frame(final int width, final int height){
        super("CrInGE - Editor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.layout = new CardLayout();
        this.setLayout(this.layout);
        this.setIconImage(new ImageIcon(this.getClass().getResource("logo.png")).getImage());
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setResizable(false); //TODO Fenstergröße anpassbar
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // TODO this.setJMenuBar(new MenuBar());
        initCards();

        this.setVisible(true);

    }

    private void initCards(){
        //this.add(new ProjectSelectorPanel(), cards.project_selecter.toString()); TODO add if not demo
        this.add(new ProjectEditorPanel(), cards.project_editor.toString());
    }

    public void showCard(cards card){
        this.layout.show(this.getContentPane(), card.toString());
    }
}
