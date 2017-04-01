package com.dengsn.crucialbeta.console;

import com.dengsn.crucial.core.event.Event;
import com.dengsn.crucialbeta.console.Console;
import java.util.Date;

public abstract class ConsoleEvent extends Event
{
  // Variables
  private final Console console;
  private final Date date;
  private final String message;
  
  // Constructor
  public ConsoleEvent(Console console, String message)
  {
    this.console = console;
    this.date = new Date();
    this.message = message;
  }
  
  // Returns the parent console
  public Console getConsole()
  {
    return this.console;
  }
  
  // Returns the date the message was sent
  public Date getDate()
  {
    return this.date;
  }
  
  // Returns the message
  public String getMessage()
  {
    return this.message;
  }
}
