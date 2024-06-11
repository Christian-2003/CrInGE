package editor;

import javax.swing.JOptionPane;

import editor.model.EditorConfig;
import editor.view.frame.Frame;

/**
 * Loads the Editor.conifig File and starts the Game Editor.
 *
 * @author Tim Schnur
 */
public final class App {

    private App() { }

    /**
     * starts the Game Editor.
     * @param args are not used
     */
    public static void main(final String[] args) {
        Frame frame = new Frame();

        // loading Editor.config
        try {
            EditorConfig.load();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
            "Editor.config Loading Error", JOptionPane.ERROR_MESSAGE);
        }

        // opening the correct card due to config
        if (EditorConfig.get(EditorConfig.Attributes.recentlyOpened) == "") {
            frame.showCard(Frame.Cards.project_selecter);
        } else {
            frame.showCard(Frame.Cards.project_editor);
        }

//TODO speichern, ob Projekt geöffnet war und automatisch öffnen
//TODO standart path for projects
//TODO extra Tab für Menüs
    }
}
