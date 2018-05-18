package com.dengsn.crucial.core.event;

import com.dengsn.crucial.GameException;

public class EventException extends GameException
{
  // Constructor
  public EventException(String message) 
  { 
    super(message); 
  }
  public EventException(String message, Throwable cause) 
  {
    super(message,cause); 
  }
}
