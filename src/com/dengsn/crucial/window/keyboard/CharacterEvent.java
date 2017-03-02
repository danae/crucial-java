package com.dengsn.crucial.window.keyboard;

import com.dengsn.crucial.event.Event;

public class CharacterEvent extends Event
{
  // Variables
  private final String text;
  
  // Constructor
  public CharacterEvent(String text)
  {
    this.text = text;
  }
  public CharacterEvent(int codepoint)
  {
    this.text = new String(Character.toChars(codepoint));
  }
  
  // Management
  public String getText()
  {
    return this.text;
  }
}
