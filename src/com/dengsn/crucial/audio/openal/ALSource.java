package com.dengsn.crucial.audio.openal;

import com.dengsn.crucial.audio.AudioException;
import com.dengsn.crucial.audio.Source;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;

public final class ALSource implements Source, AutoCloseable
{
  // Variables
  final int sourceId;
  
  // Constructor
  ALSource(ALBuffer buffer) throws AudioException
  {
    this.sourceId = AL10.alGenSources();
    ALException.alCheckError();
    
    this.alSourcei(AL10.AL_BUFFER,buffer.bufferId);
  }
  
  // Closing the resource
  @Override public void close() throws AudioException
  {
    AL10.alDeleteSources(this.sourceId);
    AudioException.checkALError();
  }
  
  // Gets a source parameter
  public int alGetSourcei(int parameter)
  {
    int buffer = AL10.alGetSourcei(this.sourceId,parameter);
    
    ALException.alCheckError();
    
    return buffer;
  }
  public float alGetSourcef(int parameter)
  {
    float buffer = AL10.alGetSourcef(this.sourceId,parameter);
    
    ALException.alCheckError();
    
    return buffer;
  }
  public boolean alGetSourceb(int parameter)
  {
    return this.alGetSourcei(parameter) == AL10.AL_TRUE;
  }
  
  // Sets a source parameter
  public void alSourcei(int parameter, int value)
  {
    AL10.alSourcei(this.sourceId,parameter,value);
    ALException.alCheckError();
  }
  public void alSourcef(int parameter, float value)
  {
    AL10.alSourcef(this.sourceId,parameter,value);
    ALException.alCheckError();
  }
  public void alSourceb(int parameter, boolean value)
  {
    this.alSourcei(parameter,value ? AL10.AL_TRUE : AL10.AL_FALSE);
  }

  // Play this source
  @Override public void play(boolean loop)
  {
    this.alSourceb(AL10.AL_LOOPING,loop);
    
    AL10.alSourcePlay(this.sourceId);
    ALException.alCheckError();
  }
  
  // Returns if this source is playing
  @Override public boolean isPlaying()
  {
    return this.alGetSourcei(AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
  }

  // Pause this source
  @Override public void pause()
  {
    AL10.alSourcePause(this.sourceId);
    ALException.alCheckError();
  }

  // Returns if this source is paused
  @Override public boolean isPaused()
  {
    return this.alGetSourcei(AL10.AL_SOURCE_STATE) == AL10.AL_PAUSED;
  }
  
  // Stop this source
  @Override public void stop()
  {
    AL10.alSourceStop(this.sourceId);
    ALException.alCheckError();
  }
  
  // Returns if this source is stopped
  @Override public boolean isStopped()
  {
    return this.alGetSourcei(AL10.AL_SOURCE_STATE) == AL10.AL_STOPPED;
  }

  // Rewind this source
  @Override public void rewind()
  {
    AL10.alSourceRewind(this.sourceId);
    ALException.alCheckError();
  }
  
  // Get the current position in seconds
  @Override public float getPosition()
  {
    return this.alGetSourcef(AL11.AL_SEC_OFFSET);
  }
  
  // Set the position in seconds
  @Override public void setPosition(float position)
  {
    this.alSourcef(AL11.AL_SEC_OFFSET,position);
  }
}
