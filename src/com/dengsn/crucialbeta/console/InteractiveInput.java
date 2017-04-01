package com.dengsn.crucialbeta.console;

import com.dengsn.crucial.core.event.ListenerPriority;
import com.dengsn.crucial.core.keyboard.KeyboardPressEvent;
import com.dengsn.crucial.core.keyboard.KeyboardCharacterEvent;
import org.lwjgl.glfw.GLFW;

public final class InteractiveInput
{
  // Variables
  private final InteractiveConsole console;
  private boolean active;
  
  private StringBuilder buffer;
  private StringBuilder original;
  private int history;
  
  // Constructor
  public InteractiveInput(InteractiveConsole console)
  {
    this.console = console;
    this.active = this.console.isFixed();
    this.getConsole().getWindow().registerListener(this::onKeyboard,KeyboardPressEvent.class,ListenerPriority.HIGHEST);
    this.getConsole().getWindow().registerListener(this::onCharacter,KeyboardCharacterEvent.class,ListenerPriority.HIGHEST);
    this.reset();
  }
  
  // Management
  public InteractiveConsole getConsole()
  {
    return this.console;
  }
  public boolean isActive()
  {
    return this.active;
  }
  public void activate()
  {
    this.active = true;
  }
  public void deactivate()
  {
    if (!this.console.isFixed())
      this.active = false;
  }
  public String getBuffer()
  {
    return this.buffer.toString();
  }
  
  // Reset
  public void reset()
  {
    this.buffer = new StringBuilder();
    this.original = null;
    this.history = -1;
  }
  
  // History handlers
  private void historyBack()
  {
    // If not yet in history, then go in history
    if (this.history == -1 && !this.getConsole().getHistory().isEmpty())
    {
      this.original = new StringBuilder(this.buffer);
      this.history = this.getConsole().getHistory().size();
    }
    
    // Go one step back
    if (this.history > 0)
    {
      this.history --;
      this.buffer = new StringBuilder(this.getConsole().getHistory().get(this.history).getMessage());
    }
  }
  private void historyForth()
  {
    // If in history, then go one step forward
    if (this.history != -1)
    {
      // If last, then go back to original
      if (this.history == this.getConsole().getHistory().size() - 1)
      {
        this.history = -1;
        this.buffer = new StringBuilder(this.original);
      }
      
      // Else go one step forward
      else
      {
        this.history ++;
        this.buffer = new StringBuilder(this.getConsole().getHistory().get(this.history).getMessage());
      }
    }
  }
  
  // Keyboard event
  public void onKeyboard(KeyboardPressEvent e)
  {
    // Check if not active and key T pressed
    if (!this.isActive())
    {
      if (e.getKey() == GLFW.GLFW_KEY_T)
      {
        this.activate();
        e.setCancelled(true);
      }
      else if (e.getKey() == GLFW.GLFW_KEY_SLASH)
      {
        this.activate();
        this.buffer.append(this.getConsole().getCommandManager().getPrefix());
        e.setCancelled(true);
      }
    }
    
    // Else handle buffer
    else
    {
      e.setCancelled(true);
      switch (e.getKey())
      {
        // Enter -> Handle command, reset and deactivate
        case GLFW.GLFW_KEY_ENTER:
          Input in = new Input(this.getConsole(),this.getBuffer());
          this.getConsole().in(in);
          
        // Escape -> reset and deactivate
        case GLFW.GLFW_KEY_ESCAPE:
          this.reset();
          this.deactivate();
          break;
          
        // Up arrow -> Go back in history
        case GLFW.GLFW_KEY_UP:
          this.historyBack();
          break;
          
        // Down arrow -> Go forth in history
        case GLFW.GLFW_KEY_DOWN:
          this.historyForth();
          break;
        
        // Backspace -> Erase last character
        case GLFW.GLFW_KEY_BACKSPACE:
          if (this.buffer.length() >= 1)
            this.buffer.deleteCharAt(this.buffer.length() - 1);
          break;
        
        // Otherwise do noting
        default:
          break;  
      }
    }
  }
  
  // Character event
  public void onCharacter(KeyboardCharacterEvent e)
  {
    if (this.isActive())
      this.buffer.append(e.getText());
  }
}
