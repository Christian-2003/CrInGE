package game_editor;

import javax.swing.JOptionPane;

import game_editor.model.EditorConfig;
import game_editor.view.frame.Frame;

/**
 * Loads the Editor.conifig File and starts the Game Editor.
 *
 * @author Tim Schnur
 */
public class App {
    
    public static void main(String[] args) {
        Frame frame = new Frame();
        
        // loading Editor.config
        try {
            EditorConfig.load();   
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Editor.config Loading Error", JOptionPane.ERROR_MESSAGE);
        }

        // opening the correct card due to config
        if (EditorConfig.get(EditorConfig.attributes.recentlyOpened) == "") frame.showCard(Frame.cards.project_selecter);
        else frame.showCard(Frame.cards.project_editor);

        //TODO speichern, ob Projekt geöffnet war und automatisch öffnen oder nachfragen, ob es geöffnet werden soll, auch Größe, Language, standart path for projects
        //TODO extra Tab für Menüs
    }
}
