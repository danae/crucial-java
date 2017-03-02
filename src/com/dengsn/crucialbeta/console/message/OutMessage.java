package com.dengsn.crucialbeta.console.message;

import java.text.SimpleDateFormat;
import com.dengsn.crucial.graphics.color.Color;
import com.dengsn.crucialbeta.console.Console;
import com.dengsn.crucialbeta.console.event.ConsoleOutEvent;

public class OutMessage extends ConsoleOutEvent
{
  // Variables
  private final Color color;
  private boolean visible;

  // Constructor
  public OutMessage(Console console, String message, Color color)
  {
    super(console,message);
    this.color = color;
    this.visible = true;
  }
  public OutMessage(Console console, String message)
  {
    this(console,message,console.getDefaultColor());
  }

  // Management  
  public Color getColor()
  {
    return this.color;
  }
  public boolean isVisible()
  {
    return this.visible;
  }
  public void setVisible(boolean visible)
  {
    this.visible = visible;
  }

  // To string
  @Override public String toString()
  {
    return "[" + new SimpleDateFormat("HH:mm:ss").format(this.getTimestamp()) + "] " + this.getMessage();
  }
}
