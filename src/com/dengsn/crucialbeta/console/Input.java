package com.dengsn.crucialbeta.console;

public class Input extends ConsoleEvent implements Command
{
  public Input(Console console, String message)
  {
    super(console,message);
  }
}
