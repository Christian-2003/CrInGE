package game_editor.view.project_editor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;


/**
 * TODO: Add description.
 *
 * @author Schnur Tim
 */
public class ProjectEditorPanel extends JPanel {

    private JTextArea editor;
    private File file;

    public ProjectEditorPanel(){
        this.initDemo(); // TODO Demo entfernen
    }

    private void initDemo(){
        this.file = null;
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        JPanel editorPanel = new JPanel(new GridLayout(2,1));
        this.editor = new JTextArea(20, 50);
        editorPanel.add(this.editor);
        JPanel buttonPanel = new JPanel();
        JButton save = new JButton("Speicher");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileFilter() {
                   @Override
                    public String getDescription() {
                        return "txt - Text Dokumente";
                    }
                    @Override
                    public boolean accept(File f) {
                        if(f.isDirectory()) return true;
                        else return f.getName().toLowerCase().endsWith(".txt");
                    }
                });
                if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                    file = chooser.getSelectedFile();
                    if(JOptionPane.showConfirmDialog(null, "This will override the current content!\nContinue?", "Continue?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                        try (FileWriter writer = new FileWriter(file)) {
                            writer.write(editor.getText());
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "IO Error occured", "IO Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        buttonPanel.add(save);
        JButton open = new JButton("Öffnen");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileFilter() {
                    @Override
                    public String getDescription() {
                        return "txt - Text Dokumente";
                    }
                    @Override
                    public boolean accept(File f) {
                        if(f.isDirectory()) return true;
                        else return f.getName().toLowerCase().endsWith(".txt");
                    }
                });
                if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    file = chooser.getSelectedFile();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        StringBuilder content = new StringBuilder();
                        while (reader.ready()) {
                            content.append(reader.readLine());
                            if(reader.ready()) content.append(System.lineSeparator());
                        }
                        editor.setText(content.toString());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "IO Error occured", "IO Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonPanel.add(open);
        JButton clear = new JButton("Leeren");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.setText("");
                file = null;
            }
        });
        buttonPanel.add(clear);
        editorPanel.add(buttonPanel);
        tabs.add("Editor", editorPanel);

        this.add(tabs);
    }

//TODO wie implementiert man selbst eine @Annotaion


    private void testTaps(){
        JTabbedPane tp = new JTabbedPane();
        for(int i=1; i<3; i++){
            tp.addTab(i+"", new JLabel(i+""));
        }
        this.add(tp);
    }
}
