package game_editor.view.project_editor.map_editor;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import game_editor.model.map.*;

/**
 * The Editor for an Map
 * 
 * @author Tim Schnur
 */
public class MapEditor extends JPanel {

    private static int cellcounts = 32; // TODO dynamischer anpassen
    private final int width, height;
    private final Dimension preferedCellSize;
    private final Color backgroundColor = new Color(204,220,236);

    private enum buttons{
        newGroup("Neue Gruppe erstellen"),
        addToGroup("Einer Gruppe hinzufügen"),
        delete("Löschen"),
        move("Bewegen"),
        copy("Kopieren");

        private final String displayText;

        buttons(String displayText){
            this.displayText = displayText;
        }
        public String getDisplayText(){
            return this.displayText;
        }
    }

    private Map map;

    private ArrayList<ArrayList<MapEditorCell>> cells;
    private int offset;
    private JPanel table;

    private String pressedButton = "", pressedAsset = "";
    private MapEditorCell pressedCell = null;

    private final ActionListener cellClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditorCell cell = ((MapEditorCell)e.getSource());

                if(pressedAsset != ""){
                    boolean exists = false;
                    for(ImageSource imgSource : map.getImgSources()){
                        if(imgSource.getName() == pressedAsset){
                            exists = true;
                            cell.setAsset(new Asset(map.getMain(), imgSource, cell.getX(), cell.getY()));
                            break;
                        }
                    }
                    if(!exists){
                        try{
                            ImageSource imgSource = new ImageSource(new File("res/textures/"+pressedAsset));
                            cell.setAsset(new Asset(map.getMain(), imgSource, cell.getX(), cell.getY()));
                        }
                         catch (Exception ex){ex.printStackTrace();}
                    }
                }
                else if(pressedButton == buttons.newGroup.toString()) ; //TODO new Group Button click
                else if(pressedButton == buttons.addToGroup.toString()) ; //TODO addToGroup Button click
                else if(pressedButton == buttons.delete.toString()) cell.removeAsset();
                else if(pressedButton == buttons.move.toString()){
                    if(pressedCell != null){
                        cell.setAsset(pressedCell.getAsset());
                        pressedCell.removeAsset();
                        pressedCell = null;
                    }
                    else pressedCell = cell;
                }
                else if(pressedButton == buttons.copy.toString()){
                    if(pressedCell != null) cell.setAsset(new Asset(pressedCell.getAsset(), cell.getX(), cell.getY()));
                    else pressedCell = cell;
                }
            }
        };

    public MapEditor(){
        super();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        width = ((int)screen.getWidth())/(cellcounts+2+10);
        height = ((int)screen.getHeight())/(cellcounts+2);
        preferedCellSize = new Dimension(width, height);

        cells = new ArrayList<>();
        offset = 0;
        this.map = new Map(); //TODO Load Map from project
        
        this.setLayout(new BorderLayout());

        initHeadline();
        initTable();
        initGivenAssets();
    }

    private void initHeadline(){
        JPanel headline = new JPanel();
        ActionListener buttonPressedListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedAsset = "";
                JButton source = (JButton)e.getSource();
                if(pressedButton == ""){
                    pressedButton = source.getName();
                    source.setBackground(Color.GRAY);
                }
                else if(pressedButton == source.getName()){
                    pressedButton = "";
                    source.setBackground(backgroundColor);
                }
                else{
                    Component[] components = headline.getComponents();
                    for(Component component : components){
                        if(component.getName() == pressedButton) component.setBackground(backgroundColor);
                    }
                    pressedButton = source.getName();
                    source.setBackground(Color.GRAY);
                }
            }
        };
        JButton buttonL = new JButton("Nach Links blättern");
        buttonL.setName("left");
        buttonL.setBackground(backgroundColor);
        buttonL.setEnabled(false);
        buttonL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(--offset == 0) ((JButton)e.getSource()).setEnabled(false);

                ArrayList<MapEditorCell> lastCol = cells.get(cells.size()-1);
                boolean emptyCol = true;
                for (MapEditorCell cell : lastCol) {
                    if (cell.getAsset() != null) {
                        emptyCol = false;
                        break;
                    }
                }
                if(emptyCol) cells.remove(cells.size()-1);

                showTable();
            }
        });
        headline.add(buttonL);
        JButton button;
        for(buttons buttonName : buttons.values()){
            button = new JButton(buttonName.getDisplayText());
            button.setName(buttonName.toString());
            button.setBackground(backgroundColor);
            button.addActionListener(buttonPressedListener);
            headline.add(button);
        }
        button = new JButton("Nach Rechts blättern");
        button.setName("right");
        button.setBackground(backgroundColor);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonL.setEnabled(true);

                int lastCol = ++offset + cellcounts;
                if ((lastCol) > cells.size()) {
                    ArrayList<MapEditorCell> cellCol = new ArrayList<>();
                    cells.add(cellCol);
                    for (int y=0; y<cellcounts; y++){
                        cellCol.add(new MapEditorCell(lastCol, y, preferedCellSize, new Color(238, 238, 238)));
                        cellCol.get(y).addActionListener(cellClickListener);
                    }
                }
                showTable();
            }
        });
        headline.add(button);

        this.add(headline, BorderLayout.NORTH);
    }

    private void initGivenAssets(){
        JPanel AssetList = new JPanel(new GridLayout(-1, 3)); //TODO anzahl Elemente?
        
        URI texturesUri = null;
        try{
            texturesUri = this.getClass().getResource("/textures").toURI();
        } catch (URISyntaxException ex){
            ex.printStackTrace();
        }
        File textures = new File(texturesUri);
        File[] pictures = textures.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".png");
            }
        });

        Component[] buttons = ((JPanel)this.getComponents()[0]).getComponents();
        final ActionListener pressedAssetListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedButton = "";
                for(Component button : buttons){
                    button.setBackground(backgroundColor);
                }
                pressedCell = null;
                JButton source = (JButton)e.getSource();
                pressedAsset = source.getName();
                //source.setBackground(Color.GRAY);
            }
        };

        for(File picture : pictures){
            try{
                JButton asset = new JButton(new ImageIcon(ImageIO.read(picture)));
                asset.setName(picture.getName());
                asset.addActionListener(pressedAssetListener);
                AssetList.add(asset);
            }
            catch (Exception ex){ex.printStackTrace();}
        }

        this.add(AssetList, BorderLayout.EAST);
    }

    private void initTable(){
        int xcells = cellcounts, ycells = cellcounts;
        for(int x=0; x<xcells; x++){
            cells.add(new ArrayList<>());
            for(int y=0; y<ycells; y++){
                ArrayList<MapEditorCell> cellCol = cells.get(x);
                cellCol.add(new MapEditorCell(x, y, preferedCellSize, new Color(238, 238, 238)));
                cellCol.get(y).addActionListener(cellClickListener);
            }
        }

        table = new JPanel(new GridLayout(ycells, xcells));
        this.add(table, BorderLayout.CENTER);

        showTable();
    }

    private void showTable(){
        table.removeAll();
        for (int y=0; y<cellcounts; y++) {
            for (int x=0+offset; x<cellcounts+offset; x++){
                table.add(cells.get(x).get(y));
            }
        }

        this.repaint();
    }
    
}
