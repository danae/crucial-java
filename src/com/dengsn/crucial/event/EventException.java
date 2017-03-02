package com.dengsn.crucial.event;

import com.dengsn.crucial.GameException;

public class EventException extends GameException
{
  // Runtime event exception
  public static class Runtime extends RuntimeException
  {
    public Runtime(EventException cause) 
    { 
      super(cause); 
    }
  }
  
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
