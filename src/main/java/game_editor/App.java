package game_editor;

import game_editor.view.frame.Frame;

/**
 * Startet den Game Editor und öffnet die Projektauswahl
 * TODO Startzeiten MockUp Implementation 11:40-12:20 12:50-13:10 13:25-14:00 14:45-14:50 16:10-17:00
 * @author Tim Schnur
 */
public class App {

    private static boolean demo = true;

    private static final int w = 500, h = 500;
    public static void main(String[] args) {
        if(demo) new Frame(w, h).showCard(Frame.editorCard); // TODO Nur Temorär für Demo
        else new Frame(w, h).showCard(Frame.selectionCard);

        //TODO speichern, ob Projekt geöffnet war und automatisch öffnen oder nachfragen, ob es geöffnet werden soll, auch Größe, Language, standart path for projects
        //TODO extra Tab für Menüs
        //TODO Scripts in Editor schreiben ermöglichen
    }
}
