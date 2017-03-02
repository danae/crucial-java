package com.dengsn.crucial.audio.decoder;

import com.dengsn.crucial.audio.AudioException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import org.lwjgl.BufferUtils;

public interface Decoder
{
  // Decode a ByteBuffer to PCM
  public DecodedBuffer decode(ByteBuffer buffer) throws AudioException;

  // Decode an InputStream to PCM
  public default DecodedBuffer decode(InputStream in) throws AudioException
  {
    // Create a ByteBuffer of the InputStream
    try
    {
      ByteBuffer buffer = BufferUtils.createByteBuffer(64);

      // Read the stream into the buffer
      try (ReadableByteChannel ch = Channels.newChannel(in))
      {
        while (ch.read(buffer) > -1)
        {
          // If buffer is full, then resize
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
      throw new AudioException("Decoding failed: " + ex.getMessage(), ex);
    }
  }
  
  // Resize a buffer
  public static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) 
  {
    ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
    buffer.flip();
    newBuffer.put(buffer);
    return newBuffer;
  }

  // Decode a file name String to PCM
  public default DecodedBuffer decode(String fileName) throws AudioException, FileNotFoundException
  {
    return this.decode(new FileInputStream(fileName));
  }
}
