package com.dengsn.crucial.graphics;

import com.dengsn.crucial.GameException;

public class TextureException extends GameException
{
  public TextureException(String message) { 
    super(message); 
  }
  public TextureException(String message, Throwable cause) { 
    super(message,cause); 
  }
}
