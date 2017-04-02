package com.dengsn.crucialbeta.console;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.Updateable;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.graphics.GL;
import com.dengsn.crucial.graphics.text.Text;
import com.dengsn.crucial.graphics.text.UnicodeFont;
import com.dengsn.crucial.util.Rect;
import com.dengsn.crucial.core.Window;
import com.dengsn.crucial.util.Vector;
import java.util.Date;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Console implements Drawable, Updateable
{
  // Variables
  private final Window window;
  private final LinkedList<Line> lines;
    
  // Graphical variables
  private UnicodeFont font = new UnicodeFont(new java.awt.Font(java.awt.Font.MONOSPACED,java.awt.Font.PLAIN,12));
  private Color color = Color.WHITE;
  private long duration = 10000;

  // Constructor
  public Console(Window window)
  {
    this.window = window;
    this.lines = new LinkedList<>();
  }
  
  // Returns the game
  protected Window getWindow()
  {
    return this.window;
  }

  // List management
  public LinkedList<Line> getLines()
  {
    return this.lines;
  }
  public LinkedList<Line> getVisibleLines()
  {
    return this.getLines().stream()
      .filter(msg -> msg.isVisible())
      .collect(Collectors.toCollection(LinkedList::new));
  }
  
  // Management
  public UnicodeFont getFont()
  {
    return this.font;
  }
  public void setFont(UnicodeFont font)
  {
    this.font = font;
  }
  public Color getDefaultColor()
  {
    return this.color;
  }
  public void setDefaultColor(Color defaultColor)
  {
    this.color = defaultColor;
  }
  public long getDuration()
  {
    return this.duration;
  }
  public void setDuration(long duration)
  {
    this.duration = duration;
  }

  // PrintStream methods
  public void log(String message, Color color)
  {
    Line msg = new Line(this,message,color);
    this.lines.add(msg);
    this.window.registerEvent(msg);
  }
  public void log(String message)
  {
    this.log(message,this.color);    
  }

  // Draws the console
  @Override public void draw() throws GameException
  {
    // If no font initialized, don't draw
    if (this.getFont() == null)
      throw new GameException("There is no proper Font initialized for " + this);

    // Draw the text
    int height = this.getFont().getLineHeight();
    int count = this.getVisibleLines().size();
    for (int i = 0; i < count; i ++)
    {
      Line msg = getVisibleLines().get(i);
      int y = (int)this.window.getHeight() - 4 - height * (count - i);
      
      this.window.toCamera().draw(() ->
      {
        Text text = this.getFont()
          .withText(msg.toString());
        
        GL.color(this.window.getBackground().withAlpha(0.5));
        GL.rectangle(new Rect(4,y,4 + text.getWidth(),y + height));
        
        text
          .setColor(msg.getColor())
          .drawAt(new Vector(4,y));
      });
    }
  }
  
  // Update
  @Override public void update(long elaspedTime)
  {
     // Remove old messages
    if (this.duration > 0)
    {
      Date now = new Date();
      this.getVisibleLines().stream()
        .filter(msg -> (now.getTime() - msg.getDate().getTime()) > this.duration)
        .forEach(msg -> msg.setVisible(false));
    }
  }
}
