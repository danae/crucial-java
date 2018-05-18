package com.dengsn.crucial.audio.openal;

import com.dengsn.crucial.audio.AudioException;
import org.lwjgl.openal.AL10;

public final class ALSource implements AutoCloseable
{
  // State enum
  public static enum State {PLAYING, STOPPED, PAUSED, LOOPING};
  
  // Variables
  private final int sourceId;
  
  // Constructor
  protected ALSource() throws AudioException
  {
    this.sourceId = AL10.alGenSources();
    ALException.check();
  }
  
  // Closing the resource
  @Override public void close() throws AudioException
  {
    AL10.alDeleteSources(this.sourceId);
    ALException.check();
  }
  
  // Returns this source id
  public int getSourceId()
  {
    return this.sourceId;
  }
  
  // Parameter getter and setters
  public int getParameter(int parameter) throws AudioException
  {
    int result = AL10.alGetSourcei(this.sourceId,parameter);
    ALException.check();
    return result;
  }
  public void setParameter(int parameter, int value) throws AudioException
  {
    AL10.alSourcei(this.sourceId,parameter,value);
    ALException.check();
  }
  public void setParameter(int parameter, boolean value) throws AudioException
  {
    this.setParameter(parameter,value ? AL10.AL_TRUE : AL10.AL_FALSE);
  }
  public void setParameter(int parameter, float value) throws AudioException
  {
    AL10.alSourcef(this.sourceId,parameter,value);
    ALException.check();
  }
  public void setParameter(int parameter, float value1, float value2, float value3) throws AudioException
  {
    AL10.alSource3f(this.sourceId,parameter,value1,value2,value3);
    ALException.check();
  }

  // Play this source
  public void play() throws AudioException
  {
    AL10.alSourcePlay(this.sourceId);
    ALException.check();
  }

  // Pause this source
  public void pause() throws AudioException
  {
    AL10.alSourcePause(this.sourceId);
    ALException.check();
  }

  // Stop this source
  public void stop() throws AudioException
  {
    AL10.alSourceStop(this.sourceId);
    ALException.check();
  }

  // Rewind this source
  public void rewind() throws AudioException
  {
    AL10.alSourceRewind(this.sourceId);
    ALException.check();
  }
  
  // Get the state
  public State getState() throws AudioException
  {
    switch (this.getParameter(AL10.AL_SOURCE_STATE))
    {
      case AL10.AL_PLAYING:
        if (this.getParameter(AL10.AL_LOOPING) == AL10.AL_TRUE)
          return State.LOOPING;
        else
          return State.PLAYING;
      case AL10.AL_PAUSED:
        return State.PAUSED;
      default:
        return State.STOPPED;
    }
  }
  
  // Create a ALSource from an ALBuffer
  public static ALSource from(ALBuffer buffer) throws AudioException
  {
    ALSource source = new ALSource();
    source.setParameter(AL10.AL_BUFFER,buffer.getBufferId());
    return source;
  }
}
