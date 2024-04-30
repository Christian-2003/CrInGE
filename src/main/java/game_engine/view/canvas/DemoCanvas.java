package game_engine.view.canvas;

import javax.swing.*;


/**
 * Class models a DemoCanvas which can display a String.
 *
 * @author  Christian-2003
 */
public class DemoCanvas extends JTextArea {

    /**
     * Attribute stores the lines of text to be displayed within the canvas.
     */
    private String[] lines;

    /**
     * Attribute stores the first line which is displayed.
     */
    private int firstDisplayedLine;

    /**
     * Constant stores the number of lines to be displayed on the canvas.
     */
    private final int NUMBER_OF_LINES = 3;


    /**
     * Constructor instantiates a new {@link DemoCanvas} which displays the passed text within this component.
     *
     * @param lines                 Text which shall be displayed within this component.
     * @throws NullPointerException The passed text is {@code null}.
     */
    public DemoCanvas(String[] lines) throws NullPointerException {
        super();
        if (lines == null) {
            throw new NullPointerException("Null is invalid text.");
        }
        this.lines = lines;
        firstDisplayedLine = 0;
        displayLines(firstDisplayedLine, NUMBER_OF_LINES);
        setEditable(false);
        setFocusable(false);
    }


    /**
     * Method scrolls down one line.
     */
    public void scrollDown() {
        displayLines(firstDisplayedLine + 1, firstDisplayedLine + 1 + NUMBER_OF_LINES);
    }


    /**
     * Method scrolls up one line.
     */
    public void scrollUp() {
        displayLines(firstDisplayedLine - 1, firstDisplayedLine - 1 + NUMBER_OF_LINES);
    }


    /**
     * Method displays the lines between the indices {@code begin} (inclusive) abd {@code end} (exclusive).
     *
     * @param begin First line to be displayed.
     * @param end   Last line to be displayed.
     */
    private void displayLines(int begin, int end) {
        if (begin < 0) {
            begin = 0;
        }
        if (end > lines.length) {
            end = lines.length;
        }
        StringBuilder text = new StringBuilder();
        for (int i = begin; i < end; i++) {
            text.append(lines[i]);
            text.append(System.lineSeparator());
        }
        setText(text.toString());
        firstDisplayedLine = begin;
    }

}
