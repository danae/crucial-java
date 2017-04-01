package com.dengsn.crucial.graphics;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Color 
{
  // Variables
  public int r;
  public int g;
  public int b;
  public double a;
  
  // Constructor
  public Color(int r, int g, int b, double a)
  {
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = a;
  }
  public Color(int r, int g, int b)
  {
    this(r,g,b,1.0);  
  }
  public Color(int grayscale)
  {
    this(grayscale,grayscale,grayscale,1.0f);    
  }
  public Color(Color color)
  {
    this(color.r,color.g,color.b,color.a);  
  }
  
  // Equality
  @Override public boolean equals(Object o)
  {
    if (!(o instanceof Color))
      return false;
    
    Color clr = (Color)o;
    return this.r == clr.r && this.g == clr.g && this.b == clr.b && this.a == clr.a;
  }
  
  // Sets the red factor
  public Color withRed(int r)
  {
    return new Color(r,this.g,this.b,this.a);
  }
  
  // Sets the green factor
  public Color withGreen(int g)
  {
    return new Color(this.r,g,this.b,this.a);
  }
  
  // Sets the blue factor
  public Color withBlue(int b)
  {
    return new Color(this.r,this.g,b,this.a);
  }
  
  // Sets the alpha factor
  public Color withAlpha(double a)
  {
    return new Color(this.r,this.g,this.b,a);
  }
  
  // Darkens this color
  public Color darken(double fraction)
  {
    return new Color(
      (int)((double)this.r * fraction),
      (int)((double)this.g * fraction),
      (int)((double)this.b * fraction),
      this.a
    );
  }  
  
  // Lightens this color
  public Color lighten(double fraction)
  {
    return new Color(
      (int)(((256.0 - r) * fraction) + r),
      (int)(((256.0 - g) * fraction) + g),
      (int)(((256.0 - b) * fraction) + b),
      this.a
    );
  }
  
  // Mix thiscolor with another color
  public Color mix(Color other, double fraction)
  {
    int r = (int)(this.r + fraction * (other.r - this.r));
    int g = (int)(this.g + fraction * (other.g - this.g));
    int b = (int)(this.b + fraction * (other.b - this.b));
    double a = this.a + fraction * (other.a - this.a);
    return new Color(r,g,b,a);
  }
  public Color mix(Color other)
  {
    return this.mix(other,0.5);
  }
  
  // Returns a totally andom color
  public static Color random()
  {
    Random r = new Random();
    return new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256));
  }
  
  // Returns a random color from the defined list
  public static Color randomDefined()
  {
    List<Color> colors = Arrays.stream(Color.class.getFields())
      .filter(f -> f.getType().equals(Color.class))
      .map(f -> 
      {
        try
        {
          return (Color)f.get(null);
        }
        catch (IllegalArgumentException | IllegalAccessException ex)
        {
          return null;
        }
      })
      .filter(c -> c != null)
      .collect(Collectors.toList());
    Random r = new Random();
    return colors.get(r.nextInt(colors.size()));
  }
  
  // Predefined colors
  public static final Color BLACK = new Color(0);
  public static final Color DARK_GRAY = new Color(64);
  public static final Color GRAY = new Color(128);
  public static final Color LIGHT_GRAY = new Color(192);
  public static final Color WHITE = new Color(255);
  
  public static final Color DARK_RED = new Color(128,0,0);
  public static final Color DARK_ORANGE = new Color(128,64,0);
  public static final Color DARK_YELLOW = new Color(128,128,0);
  public static final Color DARK_LIME = new Color(64,128,0);
  public static final Color DARK_GREEN = new Color(0,128,0);
  public static final Color DARK_TURQUOISE = new Color(0,128,64);
  public static final Color DARK_CYAN = new Color(0,128,128);
  public static final Color DARK_ICE = new Color(0,64,128);
  public static final Color DARK_BLUE = new Color(0,0,128);
  public static final Color DARK_PURPLE = new Color(64,0,128);
  public static final Color DARK_VIOLET = new Color(128,0,128);
  public static final Color DARK_PINK = new Color(128,0,64);
  
  public static final Color RED = new Color(255,0,0);
  public static final Color ORANGE = new Color(255,128,0);
  public static final Color YELLOW = new Color(255,255,0);
  public static final Color LIME = new Color(128,255,0);
  public static final Color GREEN = new Color(0,255,0);
  public static final Color TURQUOISE = new Color(0,255,128);
  public static final Color CYAN = new Color(0,255,255);
  public static final Color ICE = new Color(0,128,255);
  public static final Color BLUE = new Color(0,0,255);
  public static final Color PURPLE = new Color(128,0,255);
  public static final Color VIOLET = new Color(255,0,255);
  public static final Color PINK = new Color(255,0,128);
  
  public static final Color LIGHT_RED = new Color(255,128,128);
  public static final Color LIGHT_ORANGE = new Color(255,192,128);
  public static final Color LIGHT_YELLOW = new Color(255,255,128);
  public static final Color LIGHT_LIME = new Color(192,255,128);
  public static final Color LIGHT_GREEN = new Color(128,255,128);
  public static final Color LIGHT_TURQUOISE = new Color(128,255,192);
  public static final Color LIGHT_CYAN = new Color(128,255,255);
  public static final Color LIGHT_ICE = new Color(128,192,255);
  public static final Color LIGHT_BLUE = new Color(128,128,255);
  public static final Color LIGHT_PURPLE = new Color(192,128,255);
  public static final Color LIGHT_VIOLET = new Color(255,128,255);
  public static final Color LIGHT_PINK = new Color(255,128,192);
                
  public static final Color SOFT_RED = new Color(192,64,64);
  public static final Color SOFT_ORANGE = new Color(192,144,64);
  public static final Color SOFT_YELLOW = new Color(192,192,64);
  public static final Color SOFT_LIME = new Color(144,192,64);
  public static final Color SOFT_GREEN = new Color(64,192,64);
  public static final Color SOFT_TURQUOISE = new Color(64,192,144);
  public static final Color SOFT_CYAN = new Color(64,192,192);
  public static final Color SOFT_ICE = new Color(64,144,192);
  public static final Color SOFT_BLUE = new Color(64,64,192);
  public static final Color SOFT_PURPLE = new Color(144,64,192);
  public static final Color SOFT_VIOLET = new Color(192,64,192);
  public static final Color SOFT_PINK = new Color(192,64,144);
}
