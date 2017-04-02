package com.dengsn.crucial.audio;

import com.dengsn.crucial.GameException;
import org.lwjgl.openal.AL10;

public class AudioException extends GameException
{
  public AudioException(String message) { 
    super(message); 
  }
  public AudioException(String message, Throwable cause) { 
    super(message,cause); 
  }
  
  // Checks if an AL error ocurred and throws it
  public static void checkALError() throws AudioException
  {
    int error = AL10.alGetError();
    if (error != AL10.AL_NO_ERROR)
      throw new AudioException(AL10.alGetString(error));
  }
}
