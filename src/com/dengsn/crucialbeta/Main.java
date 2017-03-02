package com.dengsn.crucialbeta;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.color.Color;
import com.dengsn.crucial.graphics.shape.Circle;
import com.dengsn.crucial.graphics.text.UnicodeFont;
import java.awt.Font;

public class Main extends ConsoleGame
{
  // Constructor
  public Main() throws GameException
  {
    super(800,600,"Crucial 3");
    
    this.getCommands().registerCommand("homo",c -> this.getConsole().log(c.getArgsGlued() + " is homo",Color.PINK));
  }
  
  // Draw method
  @Override public void draw() throws GameException
  {   
    new Circle()
      .withRadius(50)
      .withFillColor(Color.random())
      .drawAt(this.getMouse().getViewportPosition(this.getGraphics()));
    new UnicodeFont(Font.decode("Comic Sans MS 20"))
      .withText("Selwin is homo")
      .withColor(Color.PINK)
      .drawAt(this.getMouse().getViewportPosition(this.getGraphics()));
    
    super.draw();
  }
  
  // Main method
  public static void main(String[] args) throws GameException
  {
    new Main().run();
  }
}
