package com.dengsn.crucialbeta;

import com.dengsn.crucial.Game;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.audio.AudioException;
import com.dengsn.crucial.audio.openal.OggDecoder;
import com.dengsn.crucial.core.Window;
import com.dengsn.crucial.core.mouse.MouseMoveEvent;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.graphics.shape.Rectangle;
import com.dengsn.crucial.util.Camera;
import com.dengsn.crucial.util.Rect;
import com.dengsn.crucial.util.Vector;
import java.io.FileNotFoundException;

public class Main extends Window
{
  // Variables
  private Vector orientation = Vector.unit();
  
  // Constructor
  public Main() throws GameException
  {
    super(800,600);
    super.setTitle("Crucial 3");
    
    this.registerListener(this::onMouseMove,MouseMoveEvent.class);
    
    // Initialize audio
    try
    {
      new OggDecoder()
        .decode("resources/Caelestis.ogg")
        .newSource()
        .play(true);
    }
    catch (AudioException | FileNotFoundException ex)
    {
      throw new GameException("Audio error: " + ex.getMessage(),ex);
    }
  }
  
  // Draw method
  @Override public void draw() throws GameException
  {   
    // Draw cameras and things
    new Camera(Vector.origin())
      .setOrientation(this.orientation.scale(0.01))
      .draw(() -> {
        new Rectangle()
          .setBounds(new Rect(-50,-50,50,50))
          .setFillColor(Color.YELLOW)
          .setStrokeColor(Color.ICE)
          .setStrokeWidth(2.0)
          .draw();
      });
  }
  
  // Mouse move events
  public void onMouseMove(MouseMoveEvent e)
  {
    this.orientation = e.getPosition().add(this.getWidth() * -0.5,this.getHeight() * -0.5);
  }
  
  // Main method
  public static void main(String[] args) throws GameException
  {
    Game.run(Main.class);
  }
}
