package com.dengsn.crucial.audio.decoder;

import com.dengsn.crucial.audio.AudioException;
import com.dengsn.crucial.audio.openal.ALFormat;
import java.nio.Buffer;

public class DecodedBuffer
{
  // Variables
  private final Buffer data;
  private final ALFormat format;
  private final int sampleRate;

  // Constructor
  DecodedBuffer(Buffer data, ALFormat format, int sampleRate) throws AudioException
  {
    this.data = data;
    this.format = format;
    this.sampleRate = sampleRate;
  }

  // Get the buffer
  public Buffer getBuffer()
  {
    return this.data;
  }
  
  // Get the format
  public ALFormat getFormat()
  {
    return this.format;
  }

  // Get the sample rate
  public int getSampleRate()
  {
    return this.sampleRate;
  }
}
