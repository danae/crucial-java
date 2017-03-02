package com.dengsn.crucialbeta.console;

import com.dengsn.crucial.Game;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.color.Color;
import com.dengsn.crucial.util.Viewport;
import com.dengsn.crucial.util.Point;
import com.dengsn.crucial.util.Rect;
import com.dengsn.crucial.graphics.opengl.GL;
import com.dengsn.crucialbeta.command.CommandManager;
import com.dengsn.crucialbeta.console.message.InMessage;
import java.util.ArrayList;
import java.util.List;

public class InteractiveConsole extends Console
{
  // Variables
  private final CommandManager commandManager;
  private final List<Command> history;
  private final InteractiveInput input;
  private Color inputColor = Color.WHITE;
  private boolean fixed = false;
  
  // Constructor
  public InteractiveConsole(Game game, CommandManager commandManager)
  {
    super(game);
    
    this.commandManager = commandManager;
    this.history = new ArrayList<>();
    this.input = new InteractiveInput(this);
  }
  
  // Management
  public CommandManager getCommandManager()
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
      this.getGame().getGraphics().toViewport().draw(() -> 
      {
        GL.color(this.getGame().getGraphics().getBackground());
        GL.rectangle(new Rect(4,this.getGame().getGraphics().getHeight() - 4 - height,this.getGame().getGraphics().getWidth() - 4,this.getGame().getGraphics().getHeight() - 4));
        
        this.getFont()
          .withText(input)
          .withColor(this.getInputColor())
          .drawAt(new Point(4,this.getGame().getGraphics().getHeight() - 4 - height));
      });
      
      // Set offset
      offset = height + 4;
    }
    
    // Draw the console
    new Viewport().withTranslation(new Point(0,offset).invert()).draw(super::draw);
  }
  
  // Console event
  void in(InMessage in)
  {
    this.getGame().registerEvent(in);
    this.getHistory().add(in);
  }
}
