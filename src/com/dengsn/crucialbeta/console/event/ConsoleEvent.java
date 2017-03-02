package com.dengsn.crucialbeta.console.event;

import com.dengsn.crucial.event.Event;
import com.dengsn.crucialbeta.console.Console;

public abstract class ConsoleEvent extends Event
{
  // Variables
  private transient final Console console;
  private final String message;
  
  // Constructor
  public ConsoleEvent(Console console, String message)
  {
    this.console = console;
    this.message = message;
  }
  
  // Management
  public Console getConsole()
  {
    return this.console;
  }
  public String getMessage()
  {
    return this.message;
  }
}
