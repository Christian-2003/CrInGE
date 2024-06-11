package game_editor.view.project_editor.map_editor;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import game_editor.model.map.Asset;

/**
 * Represents an Cell within the Map Editor Table
 * 
 * @author Tim Schnur
 */
public class MapEditorCell extends JButton{

    private Asset asset;

    private final int x,y;

    public MapEditorCell(int x, int y, Dimension preferredSize, Color backgroundColor){
        this(x, y, preferredSize, backgroundColor, null);
    }
    public MapEditorCell(int x, int y, Dimension preferedSize, Color backgroundColor, Asset asset){
        super();
        this.x = x;
        this.y = y;
        this.asset = asset;
        this.setPreferredSize(preferedSize);
        this.setBackground(backgroundColor);

        if(asset != null){
            asset.setX(x);
            asset.setY(y);
            this.setIcon(asset.getImage());
        }
    }

    public void setAsset(Asset asset) {
        if (this.asset != null) {
            this.removeAsset();
        }
        this.asset = asset;
        this.asset.setX(this.x);
        this.asset.setY(this.y);
        this.setIcon(asset.getImage());
    }
    public void removeAsset(){
        asset.delete();
        this.asset = null;
        this.setIcon(null);
    }
    public Asset getAsset() {
        return asset;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}
