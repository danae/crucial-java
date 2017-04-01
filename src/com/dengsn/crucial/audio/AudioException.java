package com.dengsn.crucial.audio;

import com.dengsn.crucial.GameException;

public class AudioException extends GameException
{
  public AudioException(String message) { 
    super(message); 
  }
  public AudioException(String message, Throwable cause) { 
    super(message,cause); 
  }
}
