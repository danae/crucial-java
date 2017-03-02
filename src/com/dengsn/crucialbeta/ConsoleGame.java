package com.dengsn.crucialbeta;

import com.dengsn.crucialbeta.command.CommandException;
import com.dengsn.crucialbeta.command.CommandManager;
import com.dengsn.crucialbeta.console.InteractiveConsole;
import com.dengsn.crucialbeta.console.event.ConsoleInEvent;
import com.dengsn.crucial.Game;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.color.Color;
import com.dengsn.crucial.event.ExceptionEvent;
import com.dengsn.crucial.event.Listener;

public class ConsoleGame extends Game
{
  // Variables
  private final CommandManager commands;
  private final InteractiveConsole console;
  
  // Constructor
  public ConsoleGame(int width, int height, String title) throws GameException
  {
    super(width,height,title);
    
    this.commands = new CommandManager(this);
    this.commands.registerCommand("help",() -> this.getConsole().log(this.commands.toString()));
    this.commands.registerCommand("quit",this::setClosing);

    this.console = new InteractiveConsole(this,this.getCommands());
    
    this.registerListener(this::onConsoleIn,ConsoleInEvent.class,Listener.Priority.HIGHEST);
    this.registerListener(this::onException,ExceptionEvent.class);
  }
  
  // Management
  public final CommandManager getCommands()
  {
    return this.commands;
  }
  public final InteractiveConsole getConsole()
  {
    return this.console;
  }
  
  // Draw
  @Override public void draw() throws GameException
  {
    //super.draw();
    this.console.draw();
  }
  
  // Update
  @Override public void update(long elaspedTime) throws GameException
  {
    this.console.update(elaspedTime);
  }
  
  // Events
  public final void onConsoleIn(ConsoleInEvent e)
  {
    try
    {
      if (this.getCommands().executeCommand(e.getMessage()));
        e.setCancelled(true);
    }
    catch (CommandException ex)
    {
      this.registerEvent(new ExceptionEvent(ex));
      e.setCancelled(true);
    }
  }
  public final void onException(ExceptionEvent e)
  {
    this.console.log(e.getThrowable().getMessage(),Color.RED);
  }
}
