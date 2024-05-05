package game_engine.controller;

import game_engine.model.entities.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;


/**
 * Class manages all {@link Entity}-instances for the game engine and is implemented through singleton-pattern.
 * Access the class' instance through {@link #getInstance()}.
 *
 * @author  Christian-2003
 */
public class EntityManager {

    /**
     * Field stores the singleton-instance of the entity manager.
     */
    private static EntityManager singleton;


    /**
     * Attribute stores all entities that are being managed by the entity manager.
     */
    private final HashMap<UUID, Entity> entities;


    /**
     * Constructor instantiates a new entity manager.
     * The constructor is private to comply with singleton pattern. Get the singleton instance through
     * {@link #getInstance()}.
     */
    private EntityManager() {
        entities = new HashMap<>();
    }


    /**
     * Method adds the specified entity to the manager.
     *
     * @param entity                Entity to be added to the manager.
     * @throws NullPointerException The passed entity is {@code null}.
     */
    public void put(Entity entity) throws NullPointerException {
        if (entity == null) {
            throw new NullPointerException();
        }
        entities.put(entity.getUuid(), entity);
    }


    /**
     * Method returns the entity with the specified UUID from the entity manager.
     *
     * @param uuid                  UUID of the entity to be returned.
     * @return                      Entity with the specified UUID.
     * @throws NullPointerException The passed UUID is {@code null}.
     */
    public Entity get(UUID uuid) throws NullPointerException {
        if (uuid == null) {
            throw new NullPointerException();
        }
        return entities.get(uuid);
    }

    /**
     * Method removes the specified entity from the manager. If {@code null} is passed, nothing happens.
     *
     * @param entity    Entity to be removed.
     * @return          Whether the entity was removed from the manager.
     */
    public boolean remove(Entity entity) {
        if (entity != null) {
            return entities.remove(entity.getUuid()) != null;
        }
        return false;
    }

    /**
     * Method removes the specified entity from the manager. If {@code null} is passed, nothing happens.
     *
     * @param uuid      UUID of the entity to be removed.
     * @return          Whether the entity was removed from the manager.
     */
    public boolean remove(UUID uuid) {
        if (uuid != null) {
            return entities.remove(uuid) != null;
        }
        return false;
    }


    /**
     * Method tests whether the passed entity is managed by the entity manager. If {@code null} is passed, {@code false}
     * is returned.
     *
     * @param entity    Entity to be tested.
     * @return          Whether the specified entity is being managed.
     */
    public boolean contains(Entity entity) {
        if (entity != null) {
            return entities.containsKey(entity.getUuid());
        }
        return false;
    }

    /**
     * Method tests whether the entity with the specified UUID is managed by the entity manager. If {@code null} is
     * passed, {@code false} is returned.
     *
     * @param uuid      UUID of the entity to be tested.
     * @return          Whether the entity with the specified UUID is being managed.
     */
    public boolean contains(UUID uuid) {
        if (uuid != null) {
            return entities.containsKey(uuid);
        }
        return false;
    }


    /**
     * Method removes all managed entities.
     */
    public void clear() {
        entities.clear();
    }


    /**
     * Method returns the number of entities being managed.
     *
     * @return  Number of managed entitiés.
     */
    public int size() {
        return entities.size();
    }


    /**
     * Method returns all available entities within a collection.
     * Use this method if all entities need to be processed (e.g. by the {@link RendererManager}).
     *
     * @return  Collection of all entities.
     */
    public Collection<Entity> getAllEntities() {
        return entities.values();
    }


    /**
     * Method returns the singleton-instance of the entity manager.
     *
     * @return  Entity manager.
     */
    public static EntityManager getInstance() {
        if (singleton == null) {
            singleton = new EntityManager();
        }
        return singleton;
    }

}
