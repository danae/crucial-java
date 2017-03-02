package com.dengsn.crucialalpha.json;

import com.dengsn.crucial.GameException;

public class JsonException extends GameException
{
  public JsonException(String message) 
  { 
    super(message); 
  }
  public JsonException(String message, Throwable cause) 
  { 
    super(message,cause); 
  }
}
