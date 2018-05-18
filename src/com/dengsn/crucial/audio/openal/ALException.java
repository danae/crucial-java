package com.dengsn.crucial.audio.openal;

import com.dengsn.crucial.audio.AudioException;
import org.lwjgl.openal.AL10;

public final class ALException extends AudioException
{
  public ALException(String message) { super(message); }
  public ALException(String message, Throwable cause) { super(message,cause); }
  
  // Check if an AL error ocurred and throw it
  public static void check() throws ALException
  {
    switch (AL10.alGetError())
    {
      case AL10.AL_NO_ERROR:
        break;
      case AL10.AL_INVALID_NAME:
        throw new ALException("OpenAL error: Invalid name parameter passed");
      case AL10.AL_INVALID_ENUM:
        throw new ALException("OpenAL error: Invalid enum value");
      case AL10.AL_INVALID_VALUE:
        throw new ALException("OpenAL error: Invalid parameter value");
      case AL10.AL_INVALID_OPERATION:
        throw new ALException("OpenAl error: Invalid call");
      case AL10.AL_OUT_OF_MEMORY:
        throw new ALException("OpenAL error: Out of memory");
      default:
        throw new ALException("OpenAL error");
    }
  }
}
