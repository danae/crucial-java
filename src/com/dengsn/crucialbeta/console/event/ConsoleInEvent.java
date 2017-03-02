package com.dengsn.crucialbeta.console.event;

import com.dengsn.crucial.event.Event;
import com.dengsn.crucialbeta.console.Console;

public class ConsoleInEvent extends ConsoleEvent
{
  // Constructor
  public ConsoleInEvent(Console console, String message)
  {
    super(console,message);
  }
}
