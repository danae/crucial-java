package com.dengsn.crucialbeta.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command
{
  // Variables
  private final String name;
  private final String[] args;
  
  // Constructor
  public Command(String name, String... args)
  {
    this.name = name;
    this.args = args;
  }
  
  // Management
  public String getName()
  {
    return this.name;
  }
  public String[] getArgs()
  {
    return this.args;
  }
  public String getArgsGlued(int beginIndex, int endIndex)
  {
    if (beginIndex >= this.args.length)
      return "";
    StringBuilder sb = new StringBuilder(this.args[beginIndex]);
    for (int i = Math.max(1,beginIndex + 1); i < Math.min(this.args.length,endIndex); i ++)
      sb.append(" ").append(this.args[i]);
    return sb.toString();
  }
  public String getArgsGlued(int beginIndex)
  {
    return this.getArgsGlued(beginIndex,this.args.length);
  }
  public String getArgsGlued()
  {
    return this.getArgsGlued(0);
  }
  
  // From String
  public static Command of(String string)
  {
    if (string == null || string.isEmpty())
      return null;
    
    List<String> args = new ArrayList<>(Arrays.asList(string.split("\\s+")));
    return new Command(args.remove(0),args.toArray(new String[0]));
  }
}
