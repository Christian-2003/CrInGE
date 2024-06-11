package game_editor.view.project_editor.MapEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import game_editor.model.map.Asset;
import game_editor.model.map.ImageSource;
import game_editor.model.map.Map;

/**
 * TODO add descriptiom
 * 
 * @author Tim Schnur
 */
public class MapEditor extends JPanel {

    private Map mapRepository;

    private ArrayList<ArrayList<MapEditorCell>> cells;
    private int offset;
    private static int cellcounts = 32; // TODO dynamischer anpassen
    private final int width, height;
    private final Dimension preferedCellSize;

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
    private String pressedButton = "", pressedAsset = "";
    private MapEditorCell pressedCell = null;
    private final Color backgroundColor = new Color(204,220,236);

    private JPanel table;
    private final ActionListener cellClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditorCell cell = ((MapEditorCell)e.getSource());

                if(pressedAsset != ""){
                    boolean exists = false;
                    for(ImageSource imgSource : mapRepository.getImgSources()){
                        if(imgSource.getName() == pressedAsset){
                            exists = true;
                            cell.setAsset(new Asset(null, imgSource, 0, 0));
                            break;
                        }
                    }
                    if(!exists){
                        try{
                            ImageSource imgSource = new ImageSource(new File("res/textures/"+pressedAsset));
                            cell.setAsset(new Asset(null, imgSource, 0, 0));
                        }
                         catch (Exception ex){ex.printStackTrace();}
                    }
                }
                else if(pressedButton == buttons.newGroup.toString()) ;          //TODO
                else if(pressedButton == buttons.addToGroup.toString()) ;   //TODO
                else if(pressedButton == buttons.delete.toString()) cell.removeAsset();
                else if(pressedButton == buttons.move.toString()){
                    if(pressedCell != null){
                        cell.setAsset(pressedCell.removeAsset());
                        pressedCell = null;
                    }
                    else pressedCell = cell;
                }
                else if(pressedButton == buttons.copy.toString()){
                    if(pressedCell != null) cell.setAsset(new Asset(pressedCell.getAsset(), 0, 0));
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
        this.mapRepository = new Map();
        
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
        JButton button = new JButton("Nach Links blättern");
        button.setName("left");
        button.setBackground(backgroundColor);
        button.setEnabled(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(--offset == 0) ((JButton)e.getSource()).setEnabled(false);
                showTable();
            }
        });
        headline.add(button);
        for(buttons buttonName : buttons.values()){
            button = new JButton(buttonName.getDisplayText());
            button.setName(buttonName.toString());
            button.setBackground(backgroundColor);
            button.addActionListener(buttonPressedListener);
            headline.add(button);
        }
        button = new JButton("Nach Reschts blättern");
        button.setName("right");
        button.setBackground(backgroundColor);
        //button.setEnabled(false);           //TODO fix bug and enable again
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(++offset == 1) headline.getComponent(0).setEnabled(true);
                showTable();
            }
        });
        headline.add(button);

        this.add(headline, BorderLayout.NORTH);
    }

    private void initGivenAssets(){
        JPanel AssetList = new JPanel(new GridLayout(-1, 3));
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
            catch (Exception ex){}
        }

        this.add(AssetList, BorderLayout.EAST);
    }

    private void initTable(){
        int xcells = cellcounts, ycells = cellcounts;
        table = new JPanel(new GridLayout(ycells, xcells));
        for(int x=0; x<xcells; x++){
            cells.add(new ArrayList<>());
            for(int y=0; y<ycells; y++){
                ArrayList<MapEditorCell> cellCol = cells.get(x);
                cellCol.add(new MapEditorCell(x, y, preferedCellSize, new Color(238, 238, 238)));
                cellCol.get(y).addActionListener(cellClickListener);
            }
        }
        showTable();
    }

    private void cleanTable(){
        int xcells = cellcounts, ycells = cellcounts;
        for(int y=0; y<ycells; y++){
             for(int x=offset-1; x<xcells+offset-1; x++){
                table.remove(cells.get(x).get(y));
             }
        }
    }

    private void showTable(){
        if(offset != 0) cleanTable();
        int xcells = cellcounts, ycells = cellcounts;
        for(int y=0; y<ycells; y++){
            for(int x=offset; x<xcells+offset; x++){
                if(x==cells.size()) cells.add(new ArrayList<>());
                ArrayList<MapEditorCell> cellCol = cells.get(x);
                if(y==cellCol.size()){
                    cellCol.add(new MapEditorCell(x, y, preferedCellSize, new Color(238, 238, 238)));
                    cellCol.get(y).addActionListener(cellClickListener);
                }
                table.add(cellCol.get(y));
            }
        }
        this.add(table, BorderLayout.CENTER);
        this.repaint();
    }
    
}
