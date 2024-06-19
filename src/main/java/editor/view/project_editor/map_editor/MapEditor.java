package editor.view.project_editor.map_editor;

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
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import editor.model.map.Asset;
import editor.model.map.ImageSource;
import editor.model.map.Map;


/**
 * The Editor for an Map.
 * @author Tim Schnur
 */
public class MapEditor extends JPanel {

    /** number of cells in the width and height. */
    private static int cellcounts = 32; // TODO dynamischer anpassen
    /** with of an Table Cell. */
    private final int width;
    /** height of an Table Cell. */
    private final int height;
    /** Dimension of a table cell. */
    private final Dimension preferedCellSize;
    /** Background color of the buttons. */
    private final Color backgroundColor = new Color(204, 220, 236);

    /** Buttons in the header line of the Map editor. */
    private enum Buttons {
        /** button to make a new group. */
        newGroup("Neue Gruppe erstellen"),
        /** button to add an asset to a group. */
        addToGroup("Einer Gruppe hinzufügen"),
        /** button to delete an asset. */
        delete("Löschen"),
        /** button to move an asset. */
        move("Bewegen"),
        /** button to copy an asset. */
        copy("Kopieren");

        /** displayed text of the button. */
        private final String displayText;

        Buttons(final String displayText) {
            this.displayText = displayText;
        }
        public String getDisplayText() {
            return this.displayText;
        }
    }

    /** Map object represented by the editor. */
    private Map map;

    /** list of cells on the editors grid. */
    private ArrayList<ArrayList<MapEditorCell>> cells;
    /** offset on x of the shown cells from zero. */
    private int offset;
    /** panel for the editable cells. */
    private JPanel table;

    /** currently pressed button. */
    private String pressedButton = "";
    /** currently pressed Asset. */
    private String pressedAsset = "";
    /** currently pressed cell. */
    private MapEditorCell pressedCell = null;

    /** Action Listener for clicking on cells. */
    private final ActionListener cellClickListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MapEditorCell cell = ((MapEditorCell) e.getSource());

                if (pressedAsset != "") {
                    boolean exists = false;
                    for (ImageSource imgSource : map.getImgSources()) {
                        if (imgSource.getName() == pressedAsset) {
                            exists = true;
                            cell.setAsset(new Asset(map.getMain(),
                            imgSource, cell.getX(), cell.getY()));
                            break;
                        }
                    }
                    if (!exists) {
                        try {
                            ImageSource imgSource = new ImageSource(
                                new File("res/textures/" + pressedAsset));
                            cell.setAsset(new Asset(map.getMain(),
                            imgSource, cell.getX(), cell.getY()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (pressedButton == Buttons.newGroup.toString()) {
                    //TODO new Group Button click
                } else if (pressedButton == Buttons.addToGroup.toString()) {
                    //TODO addToGroup Button click
                } else if (pressedButton == Buttons.delete.toString()) {
                    cell.removeAsset();
                } else if (pressedButton == Buttons.move.toString()) {
                    if (pressedCell != null) {
                        cell.setAsset(pressedCell.getAsset());
                        pressedCell.removeAsset();
                        pressedCell = null;
                    } else {
                        pressedCell = cell;
                    }
                } else if (pressedButton == Buttons.copy.toString()) {
                    if (pressedCell != null) {
                        cell.setAsset(new Asset(
                        pressedCell.getAsset(), cell.getX(), cell.getY()));
                    } else {
                        pressedCell = cell;
                    }
                }
            }
        };

    /** initializes the map editor panel. */
    public MapEditor() {
        super();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        width = ((int) screen.getWidth()) / (cellcounts + 2 + 10);
        height = ((int) screen.getHeight()) / (cellcounts + 2);
        preferedCellSize = new Dimension(width, height);

        cells = new ArrayList<>();
        offset = 0;
        this.map = new Map(); //TODO Load Map from project

        this.setLayout(new BorderLayout());

        initHeadline();
        initTable();
        initGivenAssets();
    }

    /** inits the headline of the panel with the buttons for e.g. copying.  */
    private void initHeadline() {
        JPanel headline = new JPanel();
        ActionListener buttonPressedListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                pressedAsset = "";
                JButton source = (JButton) e.getSource();
                if (pressedButton == "") {
                    pressedButton = source.getName();
                    source.setBackground(Color.GRAY);
                } else if (pressedButton == source.getName()) {
                    pressedButton = "";
                    source.setBackground(backgroundColor);
                } else {
                    Component[] components = headline.getComponents();
                    for (Component component : components) {
                        if (component.getName() == pressedButton) {
                            component.setBackground(backgroundColor);
                        }
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
            public void actionPerformed(final ActionEvent e) {
                if (--offset == 0) {
                    ((JButton) e.getSource()).setEnabled(false);
                }

                ArrayList<MapEditorCell> lastCol = cells.get(cells.size() - 1);
                boolean emptyCol = true;
                for (MapEditorCell cell : lastCol) {
                    if (cell.getAsset() != null) {
                        emptyCol = false;
                        break;
                    }
                }
                if (emptyCol) {
                    cells.remove(cells.size() - 1);
                }

                showTable();
            }
        });
        headline.add(buttonL);
        JButton button;
        for (Buttons buttonName : Buttons.values()) {
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
            public void actionPerformed(final ActionEvent e) {
                buttonL.setEnabled(true);

                int lastCol = ++offset + cellcounts;
                if ((lastCol) > cells.size()) {
                    ArrayList<MapEditorCell> cellCol = new ArrayList<>();
                    cells.add(cellCol);
                    for (int y = 0; y < cellcounts; y++) {
                        cellCol.add(new MapEditorCell(lastCol, y,
                        preferedCellSize, new Color(238, 238, 238)));
                        cellCol.get(y).addActionListener(cellClickListener);
                    }
                }
                showTable();
            }
        });
        headline.add(button);

        this.add(headline, BorderLayout.NORTH);
    }

    /** initializes the given assets list. */
    private void initGivenAssets() { //TODO refactoring into other class?
        // TODO Hardcoded pictures for Demo
        String[] blocks = {"cobblestone.png", "stone.png", "andesite.png", "diorite.png", "granite.png", "deepslate.png", "cobbled_deepslate.png", "tuff.png", "grass.png", "dirt.png", "coarse_dirt.png", "rooted_dirt.png"};
        String[] objects = {"creeper.png", "player.png", "iron_golem.png"};

        //TODO Anzahl Elemente?
        JPanel assetList = new JPanel(new GridLayout(-1, 3));
        JPanel entityList = new JPanel(new GridLayout(-1, 3));

        /* TODO wrong textures source for demo
        URI texturesUri = null;
        try {
            texturesUri = this.getClass().getResource("/textures").toURI();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        File textures = new File(texturesUri);
        */
        File textures = new File("res/textures");
        File[] assets = textures.listFiles(new FileFilter() {
            @Override
            public boolean accept(final File pathname) {
                for (String name : blocks) {
                    if (pathname.getName().endsWith(name)) {
                        return true;
                    }
                }
                return false;
            }
        });
        File[] entitys = textures.listFiles(new FileFilter() {
            @Override
            public boolean accept(final File pathname) {
                for (String name : objects) {
                    if (pathname.getName().endsWith(name)) {
                        return true;
                    }
                }
                return false;            }
        });

        Component[] buttons =
        ((JPanel) this.getComponents()[0]).getComponents();
        final ActionListener pressedAssetListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                pressedButton = "";
                for (Component button : buttons) {
                    button.setBackground(backgroundColor);
                }
                pressedCell = null;
                JButton source = (JButton) e.getSource();
                pressedAsset = source.getName();
System.out.println(pressedAsset);
                //source.setBackground(Color.GRAY);
            }
        };

        JSplitPane givenBox = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, assetList, entityList);

        for (File file : assets) {
            try {
                JButton asset = new JButton(
                    new ImageIcon(ImageIO.read(file)));
                asset.setName(file.getName());
                asset.setPreferredSize(preferedCellSize);
                asset.addActionListener(pressedAssetListener);
                assetList.add(asset);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        for (File file : entitys) {
            try {
                JButton asset = new JButton(
                    new ImageIcon(ImageIO.read(file)));
                asset.setName(file.getName());
                asset.setPreferredSize(preferedCellSize);
                asset.addActionListener(pressedAssetListener);
                entityList.add(asset);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        this.add(givenBox, BorderLayout.EAST);
    }

    /** initializes the table for the edibale cells. */
    private void initTable() {
        int xcells = cellcounts;
        int ycells = cellcounts;
        for (int x = 0; x < xcells; x++) {
            cells.add(new ArrayList<>());
            for (int y = 0; y < ycells; y++) {
                ArrayList<MapEditorCell> cellCol = cells.get(x);
                cellCol.add(new MapEditorCell(x, y, preferedCellSize,
                new Color(238, 238, 238)));
                cellCol.get(y).addActionListener(cellClickListener);
            }
        }

        table = new JPanel(new GridLayout(ycells, xcells));
        this.add(table, BorderLayout.CENTER);

        showTable();
    }

    /** shows the table of edibale cells on the panel. */
    private void showTable() {
        table.removeAll();
        for (int y = 0; y < cellcounts; y++) {
            for (int x = 0 + offset; x < cellcounts + offset; x++) {
                table.add(cells.get(x).get(y));
            }
        }

        this.repaint();
    }

}
