package com.dengsn.crucial.world;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.Updateable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class World implements Drawable, Updateable
{
  // Variables
  private final List<Effect> effects;
  private final List<Entity> entities;
  private final Random random;

  // Constructor
  public World()
  {
    this.effects = new CopyOnWriteArrayList<>();
    this.entities = new CopyOnWriteArrayList<>();
    this.random = new Random();
  }
  public World(long seed)
  {
    this();
    this.random.setSeed(seed);
  }
  
  // Returns all effects
  public final List<Effect> getEffects()
  {
    return this.effects;
  }
  
  // Returns effects of this type
  public final <T extends Effect> List<T> getEffects(Class<T> type)
  {
    return (List<T>)this.effects.stream()
      .filter(f -> type.isInstance(f))
      .collect(Collectors.toCollection(LinkedList::new));
  }
  
  // Returns an effect by a predicate
  public final Effect getEffect(Predicate<Effect> predicate)
  {
    return this.effects.stream()
      .filter(predicate)
      .findFirst()
      .orElse(null);
  }
  
  // Returns an effect by a predicate of this type
  public final <T extends Effect> T getEffect(Predicate<T> predicate, Class<T> type)
  {
    return this.effects.stream()
      .filter(f -> type.isInstance(f))
      .map(f -> (T)f)
      .filter(predicate)
      .findFirst()
      .orElse(null);
  }

  // Returns all entities
  public final List<Entity> getEntities()
  {
    return this.entities;
  }
  
  // Returns entities of this type
  public final <T extends Entity> List<T> getEntities(Class<T> type)
  {
    return (List<T>)this.entities.stream()
      .filter(e -> type.isInstance(e))
      .map(e -> (T)e)
      .collect(Collectors.toCollection(LinkedList::new));
  }
  
  // Returns an entity by a predicate
  public final Entity getEntity(Predicate<Entity> predicate)
  {
    return this.entities.stream()
      .filter(predicate)
      .findFirst()
      .orElse(null);
  }
  
  // Returns an entity by a predicate of this type
  public final <T extends Entity> T getEntity(Predicate<T> predicate, Class<T> type)
  {
    return this.entities.stream()
      .filter(e -> type.isInstance(e))
      .map(e -> (T)e)
      .filter(predicate)
      .findFirst()
      .orElse(null);
  }
  
  // Returns the randomizer
  public Random getRandom()
  {
    return this.random;
  }

  // Draws this world
  @Override public final void draw() throws GameException
  {
    // Draw entities
    for (Entity e : this.getEntities())
      e.draw();
  }
  
  // Updateable methods
  @Override public final void update(long elaspedTime) throws GameException
  {
    // Update effects
    for (Effect f : this.getEffects())
      f.update(elaspedTime);
    
    // Update entities
    for (Entity e : this.getEntities())
      e.update(elaspedTime);
  }
}
