package com.dengsn.crucialbeta.command;

import com.dengsn.crucial.core.event.EventQueue;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandList
{
  // Variables
  private EventQueue events;
  private String prefix;
  private final Map<String,Consumer<Command>> commands;
  
  // Constructor
  public CommandList(EventQueue events)
  {
    this.events = events;
    this.prefix = "/";
    this.commands = new LinkedHashMap<>();
  }
  
  // Management
  public String getPrefix()
  {
    return this.prefix;
  }
  public void setPrefix(String prefix)
  {
    // Empty prefix treats all as commands
    this.prefix = prefix;
  }
  
  // Command management
  public void registerCommand(String command, Consumer<Command> consumer, String... aliases)
  {
    this.commands.put(command,consumer);
    for (String alias : aliases)
      this.commands.put(alias,consumer);
  }
  public void registerCommand(String command, Consumer<Command> consumer)
  {
    this.registerCommand(command,consumer,new String[0]);
  }
  public void registerCommand(String command, Runnable runnable, String... aliases)
  {
    this.registerCommand(command,cmd -> runnable.run(),aliases);
  }
  public void registerCommand(String command, Runnable runnable)
  {
    this.registerCommand(command,cmd -> runnable.run());
  }
  public void unregisterCommand(String command)
  {
    this.commands.remove(command);
  }
  
  // Execute command
  public void executeCommand(Command command) throws CommandException
  {
    if (command == null || this.commands.get(command.getName()) == null)
      throw new CommandException("Unknown command, type " + this.getPrefix() + "help for a list of available commands");
    else
    {
      this.commands.get(command.getName()).accept(command);
      this.events.registerEvent(new CommandEvent(command));
    }
  }
  public boolean executeCommand(String string) throws CommandException
  {
    if (!(this.prefix.isEmpty() || string.startsWith(this.prefix)))
      return false;
    
    this.executeCommand(Command.of(string.substring(this.prefix.length())));
    return true;
  }
  
  // To string
  @Override public String toString()
  {
    StringBuilder sb = new StringBuilder("Available commands:");
    for (String name : this.commands.keySet())
      sb.append(" ").append(this.prefix).append(name);
    return sb.toString();
  }
}
