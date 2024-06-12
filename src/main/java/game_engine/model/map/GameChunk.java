package game_engine.model.map;

import game_engine.model.map.objects.MapObject;


/**
 * Class models a chunk that contains multiple {@link MapObject}s. This is used
 * for handling MapObjects within the engine.
 * Iterate through the MapObjects with {@link #nextMapObject()}, which returns
 * the next MapObject respectively, beginning with the first one
 * chronologically. The same applies to external MapObjects, which can be
 * iterated through with {@link #nextExternalMapObject()}.
 *
 * @author  Christian-2003
 */
public class GameChunk {

    /**
     * Constant stores the height of a chunk.
     */
    public static final int HEIGHT = 16;

    /**
     * Constant stores the width of a chunk.
     */
    public static final int WIDTH = 16;


    /**
     * Attribute stores all {@link MapObject}s that are located within this
     * chunk.
     */
    private MapObject[] mapObjects;

    /**
     * Attribute stores the index of the current {@link MapObject} within
     * {@link #mapObjects}.
     */
    private int currentMapObject;

    /**
     * Attribute stores <b>references</b> to all {@link MapObject}s that are
     * stored within different chunks but are large enough that they need to be
     * rendered in this chunk.
     */
    private MapObject[] externalMapObjects;

    /**
     * Attribute stores the index of the current external {@link MapObject}
     * within {@link #externalMapObjects}.
     */
    private int currentExternalMapObject;


    /**
     * Constructor instantiates a new {@link GameChunk} which contains the
     * passed {@link MapObject}s, as well as the passed references to
     * MapObjects that are large enough that they need to be rendered in this
     * chunk.
     *
     * @param mapObjects            Array of MapObjects that are stored within
     *                              this chunk.
     * @param externalMapObjects    Array of references to MapObjects from
     *                              other chunks that need to be rendered in
     *                              this chunk. Pass {@code null} if there are
     *                              no external MapObjects for this chunk.
     */
    public GameChunk(final MapObject[] mapObjects, final MapObject[] externalMapObjects) {
        if (mapObjects != null) {
            this.mapObjects = new MapObject[mapObjects.length];
            System.arraycopy(mapObjects, 0, this.mapObjects, 0, mapObjects.length);
        }
        resetMapObjectIterator();
        if (externalMapObjects != null) {
            this.externalMapObjects = new MapObject[externalMapObjects.length];
            System.arraycopy(externalMapObjects, 0, this.externalMapObjects, 0, externalMapObjects.length);
        }
        resetExternalMapObjectIterator();
    }


    /**
     * Method returns the next {@link MapObject} in {@link #mapObjects}
     * (chronologically). if this chunk does not contain any MapObjects, a
     * NullPointerException will be thrown. If this method iterated through all
     * MapObjects, an IndexOutOfBoundsException will be thrown (If this shall
     * be undone, call {@link #resetMapObjectIterator()}).
     *
     * @return                              Next MapObject chronologically.
     * @throws NullPointerException         This chunk does not contain any
     *                                      MapObjects.
     * @throws IndexOutOfBoundsException    This method has already iterated
     *                                      through all MapObjects.
     */
    public MapObject nextMapObject() throws NullPointerException, IndexOutOfBoundsException {
        if (currentMapObject == -1) {
            throw new NullPointerException("No MapObjects available in " + getClass().getSimpleName());
        }
        if (currentMapObject >= mapObjects.length) {
            throw new IndexOutOfBoundsException("No more MapObjects available in " + getClass().getSimpleName());
        }
        return mapObjects[currentMapObject++];
    }

    /**
     * Method resets the iterator for the current {@link MapObject} within
     * {@link #mapObjects}. This sets the iterator to the first MapObject
     * chronologically and allows for the {@link #nextMapObject()} to iterate
     * through all MapObjects.
     */
    public void resetMapObjectIterator() {
        currentMapObject = 0;
        if (mapObjects == null) {
            currentMapObject = -1;
        }
    }

    /**
     * Method determines whether there are more {@link MapObject}s within
     * {@link #mapObjects}.
     *
     * @return  Whether there are more MapObjects.
     */
    public boolean hasNextMapObject() {
        return currentMapObject >= 0 && currentMapObject < mapObjects.length;
    }


    /**
     * Method returns the next external {@link MapObject} in
     * {@link #externalMapObjects} (chronologically). if this chunk does not
     * contain any external MapObjects, a NullPointerException will be thrown.
     * If this method iterated through all external MapObjects, an
     * IndexOutOfBoundsException will be thrown (If this shall be undone, call
     * {@link #resetExternalMapObjectIterator()}).
     *
     * @return                              Next external MapObject
     *                                      chronologically.
     * @throws NullPointerException         This chunk does not contain any
     *                                      external MapObjects.
     * @throws IndexOutOfBoundsException    This method has already iterated
     *                                      through all external MapObjects.
     */
    public MapObject nextExternalMapObject() throws NullPointerException, IndexOutOfBoundsException {
        if (currentExternalMapObject == -1) {
            throw new NullPointerException("No external MapObjects available in " + getClass().getSimpleName());
        }
        if (currentExternalMapObject >= externalMapObjects.length) {
            throw new IndexOutOfBoundsException("No more external MapObjects available in " + getClass().getSimpleName());
        }
        return externalMapObjects[currentExternalMapObject++];
    }

    /**
     * Method resets the iterator for the current {@link MapObject} within
     * {@link #externalMapObjects}. This sets the iterator to the first
     * external MapObject chronologically and allows for the
     * {@link #nextExternalMapObject()} to iterate through all external
     * MapObjects.
     */
    public void resetExternalMapObjectIterator() {
        currentExternalMapObject = 0;
        if (externalMapObjects == null) {
            currentExternalMapObject = -1;
        }
    }

    /**
     * Method determines whether there are more {@link MapObject}s within
     * {@link #externalMapObjects}.
     *
     * @return  Whether there are more MapObjects.
     */
    public boolean hasNextExternalMapObject() {
        return currentExternalMapObject >= 0 && currentExternalMapObject < externalMapObjects.length;
    }

}
