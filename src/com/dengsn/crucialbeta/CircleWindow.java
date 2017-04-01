package com.dengsn.crucialbeta;

import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.graphics.shape.Circle;
import com.dengsn.crucial.util.Vector;
import com.dengsn.crucial.core.Window;
import com.dengsn.crucial.core.WindowException;
import com.dengsn.crucial.core.keyboard.KeyboardPressEvent;
import java.util.Random;

public final class CircleWindow extends Window
{
  // Variables
  private final Random random;
  private Circle circle;
  
  // Constructor
  public CircleWindow(int width, int height) throws WindowException
  {
    super(width,height);
    this.registerListener((KeyboardPressEvent e) -> this.change(),KeyboardPressEvent.class);
    
    // Create the randomizer
    this.random = new Random();
    
    // Create the circle
    this.change();
  }
  
  // Change the circle
  public void change()
  {
    this.circle = new Circle()
      .setPosition(Vector.origin())
      .setRadius(this.random.nextInt(70) + 30)
      .setFillColor(Color.randomDefined());
  }
  
  // Draws the window
  @Override public void draw() throws GameException
  {
    this.circle.draw();
  }
}
