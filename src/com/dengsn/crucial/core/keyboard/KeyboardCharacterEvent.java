package com.dengsn.crucial.core.keyboard;

import com.dengsn.crucial.core.event.Event;

public class KeyboardCharacterEvent extends Event
{
  // Variables
  private final String text;
  
  // Constructor
  public KeyboardCharacterEvent(String text)
  {
    this.text = text;
  }
  public KeyboardCharacterEvent(int codepoint)
  {
    this.text = new String(Character.toChars(codepoint));
  }
  
  // Management
  public String getText()
  {
    return this.text;
  }
}
