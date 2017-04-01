package com.dengsn.crucial;

import com.dengsn.crucial.audio.Audio;
import com.dengsn.crucial.core.Window;
import com.dengsn.crucial.core.WindowController;

public final class Game extends WindowController
{
  // Static instance
  private static Game instance;
  
  // Returns the singleton instance of this class, create one if not yet done
  public static Game get() throws GameException
  {
    // Create a new game if no instance is created yet
    if (instance == null)
      instance = new Game();
    
    // Return the instance
    return instance;
  }
  
  // Runs a new instance with a window
  public static <T extends Window> void run(Class<T> windowClass) throws GameException
  {
    try
    {
      Game.get()
        .addWindow(windowClass.newInstance())
        .run();
    }
    catch (InstantiationException | IllegalAccessException ex)
    {
      throw new GameException("Could not create window",ex);
    }
  }
  
  // Variables
  private final Audio audio;       
  
  // Constructor
  private Game() throws GameException
  {
    this.audio = new Audio();
  }
  
  // Returns the audio context
  public Audio getAudio()
  {
    return this.audio;
  }
  
  // Adds a window
  @Override public Game addWindow(Window w)
  {
    return (Game)super.addWindow(w);
  }
  
  // Removes a window
  @Override public Game removeWindow(Window w)
  {
    return (Game)super.removeWindow(w);
  }
}
