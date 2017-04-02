package com.dengsn.crucial.audio.openal;

import org.lwjgl.openal.AL10;

public class ALException extends RuntimeException
{
  public ALException(String message) { 
    super(message); 
  }
  public ALException(String message, Throwable cause) { 
    super(message,cause); 
  }
  
  // Checks if an AL error ocurred and throws it
  public static void alCheckError() throws ALException
  {
    int error = AL10.alGetError();
    if (error != AL10.AL_NO_ERROR)
      throw new ALException(AL10.alGetString(error));
  }
}
