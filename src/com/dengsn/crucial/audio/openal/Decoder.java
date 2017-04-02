package com.dengsn.crucial.audio.openal;

import com.dengsn.crucial.audio.openal.ALBuffer;
import com.dengsn.crucial.audio.AudioException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import org.lwjgl.BufferUtils;

public interface Decoder
{
  // Decodes a buffer to PCM
  public ALBuffer decode(ByteBuffer buffer) throws AudioException;

  // Decodes an input stream to PCM
  public default ALBuffer decode(InputStream in) throws AudioException
  {
    try
    {
      ByteBuffer buffer = BufferUtils.createByteBuffer(8192);
      try (ReadableByteChannel ch = Channels.newChannel(in))
      {
        while (ch.read(buffer) > -1)
        {
          if (buffer.remaining() == 0)
            buffer = Decoder.resizeBuffer(buffer,buffer.capacity() * 2);
        }
      }

      // Return the decoded buffer
      buffer.flip();
      return this.decode(buffer);
    }
    catch (IOException ex)
    {
      throw new AudioException("Decoding with " + this.getClass().getSimpleName() + " failed: " + ex.getMessage(), ex);
    }
  }

  // Decodes a file name to PCM
  public default ALBuffer decode(String fileName) throws AudioException, FileNotFoundException
  {
    return this.decode(new FileInputStream(fileName));
  }
  
  // Resize a buffer
  public static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) 
  {
    ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
    buffer.flip();
    newBuffer.put(buffer);
    return newBuffer;
  }
}
