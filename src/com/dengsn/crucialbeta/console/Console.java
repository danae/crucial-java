package com.dengsn.crucialbeta.console;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.Game;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.Updateable;
import com.dengsn.crucial.graphics.color.Color;
import com.dengsn.crucial.graphics.opengl.GL;
import com.dengsn.crucial.graphics.text.Text;
import com.dengsn.crucial.graphics.text.UnicodeFont;
import com.dengsn.crucial.util.Point;
import com.dengsn.crucial.util.Rect;
import com.dengsn.crucialbeta.console.message.OutMessage;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Console implements Drawable, Updateable
{
  // Variables
  private final Game game;
  private final List<OutMessage> messages;
    
  // Graphical variables
  private UnicodeFont font = new UnicodeFont(new Font(Font.MONOSPACED,Font.PLAIN,12));
  private Color color = Color.WHITE;
  private long duration = 10000;

  // Constructor
  public Console(Game game)
  {
    this.game = game;
    this.messages = new CopyOnWriteArrayList<>();
  }
  
  // Returns the game
  protected Game getGame()
  {
    return this.game;
  }

  // List management
  public List<OutMessage> getMessages()
  {
    return this.messages;
  }
  public List<OutMessage> getVisibleMessages()
  {
    List<OutMessage> list = new ArrayList<>();
    this.getMessages().stream()
      .filter(msg -> msg.isVisible())
      .forEach(msg -> list.add(msg));
    return list;
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
    OutMessage msg = new OutMessage(this,message,color);
    this.messages.add(msg);
    this.game.registerEvent(msg);
  }
  public void log(String message)
  {
    this.log(message,this.color);    
  }

  // Draw
  @Override public void draw() throws GameException
  {
    // If no font initialized, don't draw
    if (this.getFont() == null)
      throw new GameException("There is no proper Font initialized for " + this);

    // Draw the text
    int height = this.getFont().getLineHeight();
    int count = this.getVisibleMessages().size();
    for (int i = 0; i < count; i ++)
    {
      OutMessage msg = getVisibleMessages().get(i);
      int y = (int)this.game.getGraphics().getHeight() - 4 - height * (count - i);
      
      this.game.getGraphics().toViewport().draw(() ->
      {
        Text text = this.getFont()
          .withText(msg.toString());
        
        GL.color(this.game.getGraphics().getBackground().blend(0.5));
        GL.rectangle(new Rect(4,y,4 + text.getWidth(),y + height));
        
        text
          .withColor(msg.getColor())
          .drawAt(new Point(4,y));
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
      this.getVisibleMessages().stream()
        .filter(msg -> (now.getTime() - msg.getTimestamp().getTime()) > this.duration)
        .forEach(msg -> msg.setVisible(false));
    }
  }
}
