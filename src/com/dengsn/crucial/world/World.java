package com.dengsn.crucial.world;

import com.dengsn.crucial.Updateable;
import com.dengsn.crucial.Drawable;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import com.dengsn.crucial.GameException;
import java.util.LinkedList;
import java.util.UUID;
import java.util.stream.Collectors;

public class World  implements Drawable, Updateable
{
  // Variables
  private final List<EntityInterface> entities = new CopyOnWriteArrayList<>();
  private final Random random;

  // Constructor
  public World(long seed)
  {
    this.random = new Random(seed);
  }
  public World()
  {
    this.random = new Random();
  }

  // Entity management
  public List<EntityInterface> getEntities()
  {
    return this.entities;
  }
  public <T extends EntityInterface> List<T> getEntities(Class<T> type)
  {
    return (List<T>)this.entities.stream()
      .filter(e -> type.isInstance(e))
      .collect(Collectors.toCollection(LinkedList::new));
  }
  public EntityInterface getEntity(UUID uuid)
  {
    return this.entities.stream()
      .filter(e -> e.getUUID().equals(uuid))
      .findFirst()
      .orElse(null);
  }
  public Random getRandom()
  {
    return this.random;
  }

  // Drawable methods
  @Override public final void draw() throws GameException
  {
    // Draw entities
    for (EntityInterface e : this.getEntities())
    {
      if (e instanceof Drawable)
        ((Drawable)e).draw();
    }
  }
  
  // Updateable methods
  @Override public final void update(long elaspedTime) throws GameException
  {
    // Update entities
    for (EntityInterface e : this.getEntities())
    {
      if (e instanceof Updateable)
        ((Updateable)e).update(elaspedTime);
    }
  }
}
