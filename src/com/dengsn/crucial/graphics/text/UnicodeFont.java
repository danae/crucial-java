package com.dengsn.crucial.graphics.text;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.graphics.Texture;
import com.dengsn.crucial.graphics.GL;
import com.dengsn.crucial.util.Vector;
import com.dengsn.crucial.util.Rect;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class UnicodeFont
{
  // Variables
  private final Map<Character,Rect> glyphs = new HashMap<>();
  private final Texture texture;
  private int lineHeight;

  // Constructor
  public UnicodeFont(java.awt.Font font, boolean antiAlias)
  {
    this.texture = this.createFontTexture(font,antiAlias);
  }
  public UnicodeFont(java.awt.Font font)
  {
    this(font,true);
  }
  
  // Construct from input stream
  public UnicodeFont(InputStream in, int size, boolean antiAlias) throws FontFormatException, IOException
  {
    this(java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,in).deriveFont(java.awt.Font.PLAIN,size),antiAlias);
  }
  public UnicodeFont(InputStream in, int size) throws FontFormatException, IOException
  {
    this(in,size,true);
  }
  
  // Construct from file
  public UnicodeFont(File file, int size, boolean antiAlias) throws FontFormatException, IOException
  {
    this(new FileInputStream(file),size,antiAlias);
  }
  public UnicodeFont(File file, int size) throws FontFormatException, IOException
  {
    this(file,size,true);
  }

  // Creates a texture from a font
  private Texture createFontTexture(java.awt.Font font, boolean antiAlias)
  {
    // Loop through the characters to get charWidth and charHeight
    int imageWidth = 0;
    int imageHeight = 0;

    // Start at char #32, because ASCII 0 to 31 are just control codes
    for (int i = 32; i < 256; i++)
    {
      if (i == 127)
        continue;

      char c = (char)i;
      BufferedImage ch = this.createCharImage(font,c,antiAlias);
      if (ch == null)
        continue;

      imageWidth += ch.getWidth();
      imageHeight = Math.max(imageHeight, ch.getHeight());
    }

    this.lineHeight = imageHeight;

    // Image for the texture
    BufferedImage image = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = image.createGraphics();

    int x = 0;

    // Create image for the standard chars, again we omit ASCII 0 to 31 because they are just control codes
    for (int i = 32; i < 256; i++)
    {
      if (i == 127)
        continue;

      char c = (char) i;
      BufferedImage charImage = this.createCharImage(font,c,antiAlias);
      if (charImage == null)
        continue;

      int charWidth = charImage.getWidth();
      int charHeight = charImage.getHeight();

      // Create glyph and draw char on image
      Rect glyph = new Rect(x, image.getHeight() - charHeight, x + charWidth, image.getHeight());
      g.drawImage(charImage,x,0,null);
      x += glyph.getWidth();
      this.glyphs.put(c,glyph);
    }

    // Return as texture
    return Texture.fromImage(image);
  }

  // Creates a char image from specified AWT font and char
  private BufferedImage createCharImage(java.awt.Font font, char c, boolean antiAlias)
  {
    // Creating temporary image to extract character size
    BufferedImage image = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = image.createGraphics();
    if (antiAlias)
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    
    g.setFont(font);
    FontMetrics metrics = g.getFontMetrics();
    g.dispose();

    // Get char charWidth and charHeight
    int charWidth = metrics.charWidth(c);
    int charHeight = metrics.getHeight();

    // Check if charWidth is 0
    if (charWidth == 0)
    {
      return null;
    }

    // Create image for holding the char
    image = new BufferedImage(charWidth,charHeight,BufferedImage.TYPE_INT_ARGB);
    g = image.createGraphics();
    if (antiAlias)
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    g.setFont(font);
    g.setPaint(java.awt.Color.WHITE);
    g.drawString(String.valueOf(c),0,metrics.getAscent());
    g.dispose();
    return image;
  }
  
  // management
  public Texture getTexture()
  {
    return this.texture;
  }

  // Dimensions
  public Vector getDimensions(CharSequence text)
  {
    return new Vector(this.getWidth(text),this.getHeight(text));
  }
  public int getLineHeight()
  {
    return this.lineHeight;
  }
  public int getWidth(CharSequence text)
  {
    int width = 0;
    int lineWidth = 0;
    
    for (int i = 0; i < text.length(); i++)
    {
      char c = text.charAt(i);
      if (c == '\n')
      {
        width = Math.max(width,lineWidth);
        lineWidth = 0;
        continue;
      }
      else if (c == '\r')
        continue;
      
      Rect glyph = this.glyphs.get(c);
      lineWidth += glyph.getWidth();
    }
    
    width = Math.max(width, lineWidth);
    return width;
  }
  public int getHeight(CharSequence text)
  {
    int height = 0;
    int lineHeight = 0;
    
    for (int i = 0; i < text.length(); i++)
    {
      char c = text.charAt(i);
      if (c == '\n')
      {
        height += lineHeight;
        lineHeight = 0;
        continue;
      }
      else if (c == '\r')
        continue;
      
      Rect glyph = this.glyphs.get(c);
      lineHeight = Math.max(lineHeight,(int)glyph.getHeight());
    }
    
    height += lineHeight;
    return height;
  }
  
  // Returns a Text object with text
  public Text withText(CharSequence text)
  {
    return new Text(this,text);
  }
    
  // Draw text at the specified position and color
  void drawText(CharSequence text, Vector position, Color color) throws GameException
  {
    int textHeight = getHeight(text);

    double x = position.x;
    double y = position.y;
    if (textHeight > this.lineHeight)
      y += textHeight - this.lineHeight;

    GL.color(color);
    for (int i = 0; i < text.length(); i++)
    {
      char c = text.charAt(i);
      if (c == '\n')
      {
        y -= this.lineHeight;
        x = position.x;
        continue;
      }
      else if (c == '\r')
        continue;
      
      Rect glyph = this.glyphs.get(c);
      this.texture
        .region(glyph)
        .drawAt(new Vector(x,y));
      x += glyph.getWidth();
    }
  }
  void drawText(CharSequence text, Vector position) throws GameException
  {
    drawText(text,position,Color.WHITE);
  }
}
