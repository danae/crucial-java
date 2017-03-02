package com.dengsn.crucialbeta.console.event;

import com.dengsn.crucialbeta.console.Console;

public class ConsoleOutEvent extends ConsoleEvent
{
  // Constructor
  public ConsoleOutEvent(Console console, String message)
  {
    super(console,message);
  }
}
