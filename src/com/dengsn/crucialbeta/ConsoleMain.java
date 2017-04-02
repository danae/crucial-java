package com.dengsn.crucialbeta;

import com.dengsn.crucial.Game;
import com.dengsn.crucial.GameException;
import com.dengsn.crucial.graphics.Color;
import com.dengsn.crucial.graphics.text.UnicodeFont;
import com.dengsn.crucial.core.Window;
import java.util.Random;

public class ConsoleMain extends ConsoleWindow
{
  // Variables
  private final UnicodeFont font;
  
  // Constructor
  public ConsoleMain() throws GameException
  {
    super(1024,768);
    super.setTitle("Console");
    
    this.font = new UnicodeFont(java.awt.Font.decode("Source Code Pro").deriveFont(14));
    
    this.setBackground(Color.WHITE);

    this.getConsole().setDefaultColor(Color.BLACK);
    this.getConsole().setInputColor(Color.BLACK);
    this.getConsole().setFixed(true);
    this.getConsole().setFont(this.font);
    
    // Register commands
    this.getCommands().setPrefix("");
    
    // Add circle windows
    Random r = new Random();
    int l = 30;
    for (int i = 0; i < 3; i ++)
    {
      Window w = new CircleWindow(r.nextInt(200) + 200,r.nextInt(200) + 200)
        .setTitle("Circle")
        .setPosition(l,50);
      l += w.getWidth();
    
      Game.get().addWindow(w);
    }
  }
  
  // Main method
  public static void main(String[] args) throws GameException
  {
    Game.run(ConsoleMain.class);
  }
}
