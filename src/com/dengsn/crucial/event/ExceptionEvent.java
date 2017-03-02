package com.dengsn.crucial.event;

public class ExceptionEvent extends Event
{
  // Variables
  private final Throwable throwable;
  
  // Constructor
  public ExceptionEvent(Throwable throwable)
  {
    this.throwable = throwable;
  }
  
  // Management
  public Throwable getThrowable()
  {
    return this.throwable;
  }
}