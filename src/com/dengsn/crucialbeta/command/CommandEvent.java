package com.dengsn.crucialbeta.command;

import com.dengsn.crucial.event.Event;

public class CommandEvent extends Event
{
  // Variables
  private final Command command;
  
  // Constructor
  public CommandEvent(Command command)
  {
    this.command = command;
  }
  
  // Management
  public Command getCommand()
  {
    return this.command;
  }
}
