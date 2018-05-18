package com.dengsn.crucial.audio.openal;

import java.util.Arrays;
import javax.sound.sampled.AudioFormat;
import org.lwjgl.openal.AL10;

public enum ALFormat
{
  // Values
  MONO_8_BIT(AL10.AL_FORMAT_MONO8),
  MONO_16_BIT(AL10.AL_FORMAT_MONO16),
  STEREO_8_BIT(AL10.AL_FORMAT_STEREO8),
  STEREO_16_BIT(AL10.AL_FORMAT_STEREO16);
  
  // Variables
  private final int format;

  // Constructor
  private ALFormat(int format)
  {
    this.format = format;
  }
  
  // management
  public int getALFormat()
  {
    return this.format;
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
        return MONO_8_BIT;
      else if (sampleSize == 16)
        return MONO_16_BIT;
    }
    else if (channels == 2)
    {
      if (sampleSize == 8)
        return STEREO_8_BIT;
      else if (sampleSize == 16)
        return STEREO_16_BIT;
    }
    
    return null;
  }
  
  // AudioFormat to ALFormat
  public static ALFormat of(AudioFormat audioFormat)
  {
    return ALFormat.of(audioFormat.getChannels(),audioFormat.getSampleSizeInBits());
  }
}
