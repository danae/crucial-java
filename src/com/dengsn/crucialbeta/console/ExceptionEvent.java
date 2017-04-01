package com.dengsn.crucialbeta.console;

import com.dengsn.crucial.core.event.Event;

public class ExceptionEvent extends Event
{
  // Variables
  private final Throwable exception;
  
  // Constructor
  public ExceptionEvent(Throwable exception)
  {
    this.exception = exception;
  }
  
  // Returns the exception
  public Throwable getException()
  {
    return this.exception;
  }
}
