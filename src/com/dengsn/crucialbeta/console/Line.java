package com.dengsn.crucialbeta.console;

import java.text.SimpleDateFormat;
import com.dengsn.crucial.graphics.Color;

public class Line extends ConsoleEvent
{
  // Variables
  private final Color color;
  private boolean visible;

  // Constructor
  public Line(Console console, String string, Color color)
  {
    super(console,string);
    this.color = color;
    this.visible = true;
  }
  public Line(Console console, String string)
  {
    this(console,string,console.getDefaultColor());
  }

  // Returns the color  
  public Color getColor()
  {
    return this.color;
  }
  
  // Returns if the line is visible
  public boolean isVisible()
  {
    return this.visible;
  }
  
  // Sets fi the line is visible
  public void setVisible(boolean visible)
  {
    this.visible = visible;
  }

  // Converts to string
  @Override public String toString()
  {
    return "[" + new SimpleDateFormat("HH:mm:ss").format(this.getDate()) + "] " + this.getMessage();
  }
}
