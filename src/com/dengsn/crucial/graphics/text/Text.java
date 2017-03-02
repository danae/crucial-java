package com.dengsn.crucial.graphics.text;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.color.Color;
import com.dengsn.crucial.graphics.color.Colorable;
import com.dengsn.crucial.util.Point;

public class Text implements Drawable, Colorable
{
  // Variables
  private final UnicodeFont font;
  private final CharSequence text;
  private Color color = Color.WHITE;
  private TextAlignment alignment = TextAlignment.ALIGN_LEFT;
  
  // Constructor
  Text(UnicodeFont font, CharSequence text)
  {
    this.font = font;
    this.text = text;
  }
  
  // Management
  public CharSequence getText()
  {
    return this.text;
  }
  public UnicodeFont getFont()
  {
    return this.font;
  }
  @Override public Color getColor()
  {
    return this.color;
  }
  @Override public void setColor(Color color)
  {
    this.color = color;
  }
  @Override public Text withColor(Color color)
  {
    return (Text)Colorable.super.withColor(color);
  }
  public TextAlignment getAlignment()
  {
    return this.alignment;
  }
  public void setAlignment(TextAlignment alignment)
  {
    this.alignment = alignment;
  }
  public Text withAlignment(TextAlignment alignment)
  {
    this.setAlignment(alignment);
    return this;
  }
  
  // Get dimensions
  public Point getDimensions()
  {
    return this.font.getDimensions(this.text);
  }
  public int getLineHeight()
  {
    return this.font.getLineHeight();
  }
  public int getWidth()
  {
    return this.font.getWidth(this.text);
  }
  public int getHeight()
  {
    return this.font.getHeight(this.text);
  }
  
  // Draw
  @Override public void draw() throws GameException
  {
    // Calculate position
    Point position = Point.origin();
    if (this.alignment == TextAlignment.ALIGN_RIGHT)
      position.x -= this.getWidth();
    else if (this.alignment == TextAlignment.ALIGN_CENTER)
      position.x -= (int)(0.5 * this.getWidth());

    // Draw text
    this.font.drawText(this.text,position,this.color);
  }
}
