package com.dengsn.crucialbeta.command;

import com.dengsn.crucial.GameException;

public class CommandException extends GameException
{
  public CommandException(String message) { super(message); }
  public CommandException(String message, CommandException cause) { super(message,cause); }
}
