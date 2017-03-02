package com.dengsn.crucial.audio;

import com.dengsn.crucial.Resource;
import com.dengsn.crucial.audio.decoder.DecodedBuffer;
import com.dengsn.crucial.audio.decoder.Decoder;
import com.dengsn.crucial.audio.openal.ALBuffer;
import com.dengsn.crucial.audio.openal.ALSource;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.lwjgl.openal.AL11;

public class Sound implements Resource
{
  // Variables
  private final ALBuffer buffer;
  private final ALSource source;

  // Constructor
  public Sound(DecodedBuffer decoded) throws AudioException
  {
    this.buffer = ALBuffer.from(decoded);
    this.source = ALSource.from(this.buffer);
  }

  // Closing the resource
  @Override public void close() throws AudioException
  {
    this.source.close();
    this.buffer.close();
  }

  // Play the sound
  public void play() throws AudioException
  {
    if (this.source.getState() == ALSource.State.PLAYING || this.source.getState() == ALSource.State.LOOPING)
      return;
    this.source.play();
  }

  // Pause the sound
  public void pause() throws AudioException
  {
    if (this.source.getState() == ALSource.State.PAUSED)
      return;
    this.source.pause();
  }

  // Stop the sound
  public void stop() throws AudioException
  {
    if (this.source.getState() == ALSource.State.STOPPED)
      return;
    this.source.stop();
  }

  // Rewind the sound
  public void rewind() throws AudioException
  {
    this.source.rewind();
  }
  
  // Check if the sound is playing
  public boolean isPlaying() throws AudioException
  {
    return this.source.getState() == ALSource.State.PLAYING || this.source.getState() == ALSource.State.LOOPING;     
  }
  
  // Get the current position in secs
  public int getPosition() throws AudioException
  {
    return this.source.getParameter(AL11.AL_SEC_OFFSET);
  }
  
  // Set the position in secs
  public void setPosition(float position) throws AudioException
  {
    this.source.setParameter(AL11.AL_SEC_OFFSET,position);
  }
  
  // Create default sound from a ByteBuffer
  public static Sound create(ByteBuffer buffer, Decoder decoder) throws AudioException
  {
    return new Sound(decoder.decode(buffer));
  }
  
  // Create default sound from an InputStream
  public static Sound create(InputStream in, Decoder decoder) throws AudioException
  {
    return new Sound(decoder.decode(in));
  }
  
  // Create default sound from a file name String
  public static Sound create(String fileName, Decoder decoder) throws AudioException, FileNotFoundException
  {
    return new Sound(decoder.decode(fileName));
  }
}
