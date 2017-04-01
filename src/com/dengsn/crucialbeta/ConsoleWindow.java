package com.dengsn.crucialbeta;

import com.dengsn.crucialbeta.command.CommandList;
import com.dengsn.crucialbeta.console.InteractiveConsole;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.core.Window;
import com.dengsn.crucial.core.event.ListenerPriority;
import com.dengsn.crucialbeta.console.ConsoleEvent;
import com.dengsn.crucialbeta.console.ExceptionEvent;

public class ConsoleWindow extends Window
{
  // Variables
  private final CommandList commands;
  private final InteractiveConsole console;
  
  // Constructor
  public ConsoleWindow(int width, int height) throws GameException
  {
    super(width,height);
    
    this.commands = new CommandList(this);
    this.commands.registerCommand("help",() -> this.getConsole().log(this.commands.toString()));
    this.commands.registerCommand("quit",() -> this.setClosing(true));

    this.console = new InteractiveConsole(this,this.getCommands());
    
    this.registerListener(this::onConsole,ConsoleEvent.class,ListenerPriority.HIGHEST);
    this.registerListener(this::onException,ExceptionEvent.class);
  }
  
  // Management
  public final CommandList getCommands()
  {
    return this.commands;
  }
  public final InteractiveConsole getConsole()
  {
    return this.console;
  }
  
  // Draws the console window
  @Override public void draw() throws GameException
  {
    //super.draw();
    this.console.draw();
  }
  
  // Updates the console window
  @Override public void update(long elaspedTime) throws GameException
  {
    this.console.update(elaspedTime);
  }
  
  // Checks for incoming messages
  public final void onConsole(ConsoleEvent e)
  {
    try
    {
      if (this.getCommands().executeCommand(e.getMessage()));
        e.setCancelled(true);
    }
    catch (Exception ex)
    {
      this.registerEvent(new ExceptionEvent(ex));
      e.setCancelled(true);
    }
  }
  
  // Checks for thrown exceptions
  public final void onException(ExceptionEvent e)
  {
    this.console.log(e.getException().getMessage(),Color.RED);
  }
}
