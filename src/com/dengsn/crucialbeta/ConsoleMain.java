package com.dengsn.crucialbeta;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.color.Color;
import com.dengsn.crucial.graphics.shape.Circle;
import com.dengsn.crucial.graphics.text.UnicodeFont;
import com.dengsn.crucial.graphics.Texture;
import com.dengsn.crucial.util.Point;
import com.dengsn.crucial.util.Rect;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class ConsoleMain extends ConsoleGame
{
  // Variables
  private final UnicodeFont font;
  private final Texture texture;
  
  // Constructor
  public ConsoleMain() throws GameException, IOException, FontFormatException
  {
    super(1024,768,"Console");
    
    this.getGraphics().setBackground(Color.WHITE);

    this.getConsole().setDefaultColor(Color.BLACK);
    this.getConsole().setInputColor(Color.BLACK);
    this.getConsole().setFixed(true);
    
    // Register commands
    this.getCommands().setPrefix("");
    
    // Resources
    this.font = new UnicodeFont(Font.decode("Verdana").deriveFont(20));
    this.texture = Texture.fromImage(new File("resources/Layers_front.png"));
  }
  
  // Close the resources
  @Override public void close()
  {
    this.texture.close();
  }
  
  // Draw
  @Override public void draw() throws GameException
  {
    this.texture
      .region(new Rect(100,100,200,200))
      .drawAt(new Point(-150,-150));
    
    Circle c = new Circle()
      .withRadius(50)
      .withFillColor(Color.RED)
      .withStrokeColor(Color.BLACK)
      .withStrokeWidth(5);
    this.getGraphics().toViewport()
      .withTranslation(Point.origin().subtract(200,200))
      .draw(() ->
      {
        c.draw();
        c.drawAt(new Point(40,30));
      });
    
    // Draw some text
    this.getGraphics().toViewport().draw(() ->
    {
      this.font
        .withText((int)this.getFPS() + " fps\ncursor at " + this.getMouse().getPosition())
        .withColor(Color.SOFT_BLUE)
        .drawAt(new Point(20,20));
    });

    // Draw parent
    super.draw();
  }
  
  // Main method
  public static void main(String[] args) throws GameException, IOException, FontFormatException
  {
    new ConsoleMain().run();
  }
}
