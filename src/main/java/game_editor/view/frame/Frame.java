package game_editor.view.frame;

import javax.swing.*;

import game_editor.view.project_editor.ProjectEditorPanel;

import java.awt.*;


/**
 * TODO: Add description.
 * CardLayout -> BorderLayout -> GridBagLayout / GridLayout
 * JTabbedPane -> addTap
 *
 * @author Tim Schnur
 */
public class Frame extends JFrame {

    public static final String editorCard = "project_editor", selectionCard = "project_selecter";

    private final CardLayout layout;

    public Frame(final int width, final int height){
        super("CrInGE - Editor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.layout = new CardLayout();
        this.setLayout(this.layout);
        this.setSize(width, height);
        this.setVisible(true);

        // TODO this.setJMenuBar(new MenuBar());
        initCards();
    }

    private void initCards(){
     /*   JPanel editorPanel = new ProjectEditorPanel(), selectPanel = new ProjectSelectorPanel();
        //TODO richtige Panel zufügen
        this.add(selectPanel, this.selectionCard);
        this.add(editorPanel, this.editorCard);
        */

    }

    public void showCard(String cardName){
        this.layout.show(this.getContentPane(), cardName);
    }
}
