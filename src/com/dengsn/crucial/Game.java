package com.dengsn.crucial;

import com.dengsn.crucial.event.EventManager;
import com.dengsn.crucial.event.CloseEvent;
import com.dengsn.crucial.window.Window;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Game extends Window implements Drawable, Updateable, Resource
{
  // Static game instance
  private static Game instance;
  
  // Returns the static game instance
  public static Game getInstance()
  {
    return Game.instance;
  }
  
  // Timer class
  public static final class Timer
  {
    // Variables
    private long lastLoop = this.getTime();
    private long currentLoop = this.getTime();
   
    // Get time
    public long getTime()
    {
      return System.currentTimeMillis();
    }
  
    // Get delta time
    public long getDeltaTime()
    {
      this.lastLoop = this.currentLoop;
      this.currentLoop = this.getTime();
    
      return this.currentLoop - this.lastLoop;
    }
    public long getFPS()
    {
      return Math.round(1000.0 / (this.currentLoop - this.lastLoop));
    }
  }
  
  // Variables
  private final EventManager eventManager;
  private final Timer timer;
  private final Map<String,Object> services;

  // Constructor
  public Game(int width, int height, String title) throws GameException
  {
    super(width,height,title);
    
    this.eventManager = new EventManager();
    this.timer = new Timer();
    this.services = new LinkedHashMap<>();
    
    Game.instance = this;
  }
  public Game(int width, int height) throws GameException
  {
    this(width,height,"Game");
  }
  
  // Returns the FPS
  public long getFPS()
  {
    return this.timer.getFPS();
  }
  
  // Service management
  public Object getService(String name)
  {
    return this.services.get(name);
  }
  public <T> T getService(String name, Class<T> type)
  {
    return (T)this.services.get(name);
  }
  public <T> void addService(String name, T service)
  {
    this.services.put(name,service);
  }
  public void removeService(String name)
  {
    this.services.remove(name);
  }
  
  // Run the game
  public final void run() throws GameException
  {
    // Main loop
    while (!this.isClosing())
    {
      // Update game logic
      long elapsedTime = this.timer.getDeltaTime();
      
      this.update(elapsedTime);
      this.executeEvents();

      // Draw
      this.getGraphics().clear();
      this.draw();
      this.getGraphics().swap();
    }
    
    // Generate close event
    this.executeEvent(new CloseEvent());
    
    // Destroy
    this.close();
  }
  
  // Draw and update
  @Override public void draw() throws GameException
  {
    // Implementation left for the user
  }
  @Override public void update(long elaspedTime) throws GameException
  {
    // Implementation left for the user
  }
}
