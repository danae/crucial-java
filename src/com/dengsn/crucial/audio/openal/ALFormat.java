package com.dengsn.crucial.audio.openal;

import java.util.Arrays;
import org.lwjgl.openal.AL10;

public enum ALFormat
{
  // Values
  MONO8(AL10.AL_FORMAT_MONO8),
  MONO16(AL10.AL_FORMAT_MONO16),
  STEREO8(AL10.AL_FORMAT_STEREO8),
  STEREO16(AL10.AL_FORMAT_STEREO16);
  
  // Variables
  private final int alFormat;

  // Constructor
  private ALFormat(int alFormat)
  {
    this.alFormat = alFormat;
  }
  
  // management
  public int getALFormat()
  {
    return this.alFormat;
  }
  
  // AL format to ALFormat
  public static ALFormat of(int alFormat)
  {
    return Arrays.stream(ALFormat.values())
      .filter(f -> f.getALFormat() == alFormat)
      .findFirst()
      .orElse(null);
  }
  
  // Channels and sample rate to ALFormat
  public static ALFormat of(int channels, int sampleSize)
  {
    if (channels == 1)
    {
      if (sampleSize == 8)
        return MONO8;
      else if (sampleSize == 16)
        return MONO16;
    }
    else if (channels == 2)
    {
      if (sampleSize == 8)
        return STEREO8;
      else if (sampleSize == 16)
        return STEREO16;
    }
    
    return null;
  }
}
