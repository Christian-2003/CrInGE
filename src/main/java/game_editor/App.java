package game_editor;

import game_editor.view.frame.Frame;

/**
 * Startet den Game Editor und öffnet die Projektauswahl
 *
 * @author Tim Schnur
 */
public class App {

    private static boolean demo = true;

    private static final int w = 550, h = 550;
    
    public static void main(String[] args) {
        if(demo) new Frame(w, h).showCard(Frame.cards.project_editor); // TODO Nur Temorär für Demo
        else new Frame(w, h).showCard(Frame.cards.project_selecter);

        //TODO speichern, ob Projekt geöffnet war und automatisch öffnen oder nachfragen, ob es geöffnet werden soll, auch Größe, Language, standart path for projects
        //TODO extra Tab für Menüs
        //TODO Scripts in Editor schreiben ermöglichen
    }
}
