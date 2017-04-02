package com.dengsn.crucial.audio.openal;

import com.dengsn.crucial.audio.AudioException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.openal.AL10;

public final class ALBuffer implements AutoCloseable
{
  // Variables
  final int bufferId;
  
  // Constructor
  ALBuffer(Buffer buffer, ALFormat format, int sampleRate) throws AudioException
  {
    this.bufferId = AL10.alGenBuffers();
    AudioException.checkALError();
    
    // Set the buffer
    if (buffer instanceof ByteBuffer)
      AL10.alBufferData(this.bufferId,format.getALFormat(),(ByteBuffer)buffer,sampleRate);
    else if (buffer instanceof ShortBuffer)
      AL10.alBufferData(this.bufferId,format.getALFormat(),(ShortBuffer)buffer,sampleRate);
    else if (buffer instanceof IntBuffer)
      AL10.alBufferData(this.bufferId,format.getALFormat(),(IntBuffer)buffer,sampleRate);
    else if (buffer instanceof FloatBuffer)
      AL10.alBufferData(this.bufferId,format.getALFormat(),(FloatBuffer)buffer,sampleRate);
    AudioException.checkALError();
  }
  
  // Closes the buffer
  @Override public void close() throws AudioException
  {
    AL10.alDeleteBuffers(this.bufferId);
    AudioException.checkALError();
  }
  
  // Returns a new source object with this buffer
  public ALSource newSource() throws AudioException
  {
    return new ALSource(this);
  }
}
