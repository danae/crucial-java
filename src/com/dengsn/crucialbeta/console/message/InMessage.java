package com.dengsn.crucialbeta.console.message;

import com.dengsn.crucialbeta.console.Console;
import com.dengsn.crucialbeta.console.Command;
import com.dengsn.crucialbeta.console.event.ConsoleInEvent;

public class InMessage extends ConsoleInEvent implements Command
{
  public InMessage(Console console, String message)
  {
    super(console,message);
  }
}
