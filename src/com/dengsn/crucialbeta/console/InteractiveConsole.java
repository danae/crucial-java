package com.dengsn.crucialbeta.console;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.util.Rect;
import com.dengsn.crucial.graphics.GL;
import com.dengsn.crucial.core.Window;
import com.dengsn.crucial.util.Camera;
import com.dengsn.crucial.util.Vector;
import com.dengsn.crucialbeta.command.CommandList;
import java.util.ArrayList;
import java.util.List;

public class InteractiveConsole extends Console
{
  // Variables
  private final CommandList commandManager;
  private final List<Command> history;
  private final InteractiveInput input;
  private Color inputColor = Color.WHITE;
  private boolean fixed = false;
  
  // Constructor
  public InteractiveConsole(Window window, CommandList commandManager)
  {
    super(window);
    
    this.commandManager = commandManager;
    this.history = new ArrayList<>();
    this.input = new InteractiveInput(this);
  }
  
  // Management
  public CommandList getCommandManager()
  {
    return this.commandManager;
  }
  public List<Command> getHistory()
  {
    return this.history;
  }
  public Color getInputColor()
  {
    return this.inputColor;
  }
  public void setInputColor(Color inputColor)
  {
    this.inputColor = inputColor;
  }
  public boolean isFixed()
  {
    return this.fixed;
  }
  public void setFixed(boolean fixed)
  {
    this.fixed = fixed;
    if (this.isFixed())
      this.input.activate();
  }
  
  // Draw
  @Override public void draw() throws GameException
  {
    // Draw the input field, if active
    int height = this.getFont().getLineHeight();
    int offset = 0;
    if (this.input.isActive())
    {
      // Draw it
      String input = this.input.getBuffer() + "_";
      this.getWindow().toCamera().draw(() -> 
      {
        GL.color(this.getWindow().getBackground());
        GL.rectangle(new Rect(4,this.getWindow().getHeight() - 4 - height,this.getWindow().getWidth() - 4,this.getWindow().getHeight() - 4));
        
        this.getFont()
          .withText(input)
          .setColor(this.getInputColor())
          .drawAt(new Vector(4,this.getWindow().getHeight() - 4 - height));
      });
      
      // Set offset
      offset = height + 4;
    }
    
    // Draw the console
    new Camera(new Vector(0,offset).invert()).draw(super::draw);
  }
  
  // Console event
  void in(Input in)
  {
    this.getWindow().registerEvent(in);
    this.getHistory().add(in);
  }
}
