package com.dengsn.crucial.audio.openal;

import com.dengsn.crucial.audio.decoder.DecodedBuffer;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.openal.AL10;

public final class ALBuffer implements AutoCloseable
{
  // Variables
  private final int bufferId;
  
  // Constructor
  protected ALBuffer(Buffer buffer, ALFormat format, int sampleRate) throws ALException
  {
    this.bufferId = AL10.alGenBuffers();
    ALException.check();
    
    // Set the buffer
    if (buffer instanceof ByteBuffer)
      AL10.alBufferData(this.bufferId,format.getALFormat(),(ByteBuffer)buffer,sampleRate);
    else if (buffer instanceof ShortBuffer)
      AL10.alBufferData(this.bufferId,format.getALFormat(),(ShortBuffer)buffer,sampleRate);
    else if (buffer instanceof IntBuffer)
      AL10.alBufferData(this.bufferId,format.getALFormat(),(IntBuffer)buffer,sampleRate);
    ALException.check();
  }
  
  // Closing the resource
  @Override public void close() throws ALException
  {
    AL10.alDeleteBuffers(this.bufferId);
    ALException.check();
  }
  
  // Returns this buffer id
  public int getBufferId()
  {
    return this.bufferId;
  }
  
  // Create an ALBuffer from a DecodedBuffer
  public static ALBuffer from(DecodedBuffer buffer) throws ALException
  {
    return new ALBuffer(buffer.getBuffer(),buffer.getFormat(),buffer.getSampleRate());
  }
}
