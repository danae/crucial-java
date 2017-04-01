package com.dengsn.crucialbeta;

import com.dengsn.crucial.Game;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.graphics.shape.Rectangle;
import com.dengsn.crucial.util.Camera;
import com.dengsn.crucial.util.Rect;
import com.dengsn.crucial.util.Vector;

public class Main extends ConsoleWindow
{
  // Variables
  private double rotation = 0.0;
  
  // Constructor
  public Main() throws GameException
  {
    super(800,600);
    super.setTitle("Crucial 3");
    
    this.getCommands().registerCommand("homo",c -> this.getConsole().log(c.getArgsGlued() + " is homo",Color.PINK));
  }
  
  // Draw method
  @Override public void draw() throws GameException
  {   
    /*new Circle()
      .withRadius(50)
      .withFillColor(Color.random())
      .drawAt(this.getMouse().getViewportPosition(this.getGraphics()));
    new UnicodeFont(Font.decode("Comic Sans MS 20"))
      .withText("Selwin is homo")
      .withColor(Color.PINK)
      .drawAt(this.getMouse().getViewportPosition(this.getGraphics()));*/
    
    // Draw the console
    super.draw();
    
    // Draw cameras and things
    this.rotation += 1.0;
    
    new Camera(Vector.origin())
      .setRotation(this.rotation).draw(() -> {
        new Rectangle()
          .setBounds(new Rect(-50,-50,50,50))
          .setFillColor(Color.YELLOW)
          .setStrokeColor(Color.ICE)
          .setStrokeWidth(2.0)
          .draw();
    });
  }
  
  // Main method
  public static void main(String[] args) throws GameException
  {
    Game.run(Main.class);
  }
}
