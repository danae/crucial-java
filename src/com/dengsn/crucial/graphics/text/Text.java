package com.dengsn.crucial.graphics.text;

import com.dengsn.crucial.Drawable;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.graphics.Colorable;
import com.dengsn.crucial.util.Vector;

public class Text implements Drawable, Colorable
{
  // Variables
  private final Font font;
  private final CharSequence text;
  private Color color = Color.WHITE;
  private TextAlignment alignment = TextAlignment.ALIGN_LEFT;
  
  // Constructor
  Text(Font font, CharSequence text)
  {
    this.font = font;
    this.text = text;
  }
  
  // Returns the text string of this text
  public CharSequence getText()
  {
    return this.text;
  }
  
  // Returns the font of this text
  public Font getFont()
  {
    return this.font;
  }
  
  // Returns the color of this text
  @Override public Color getColor()
  {
    return this.color;
  }
  
  // Sets the color of this text
  @Override public Text setColor(Color color)
  {
    this.color = color;
    return this;
  }
  
  // Returns the alignment of this text
  public TextAlignment getAlignment()
  {
    return this.alignment;
  }
  
  // Sets the textalignment of this text
  public Text setAlignment(TextAlignment alignment)
  {
    this.alignment = alignment;
    return this;
  }
  
  // Get dimensions
  public Vector getDimensions()
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
  
  // Draws the text
  @Override public void draw() throws GameException
  {
    // Calculate position
    Vector position = Vector.origin();
    if (this.alignment == TextAlignment.ALIGN_RIGHT)
      position.x -= this.getWidth();
    else if (this.alignment == TextAlignment.ALIGN_CENTER)
      position.x -= (int)(0.5 * this.getWidth());

    // Draw text
    this.font.drawText(this.text,position,this.color);
  }
}
