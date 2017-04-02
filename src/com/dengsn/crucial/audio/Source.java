package com.dengsn.crucial.audio;

public interface Source
{  
  // Play this source
  public void play(boolean loop);
  
  // Returns if this source is playing
  public boolean isPlaying();

  // Pause this source
  public void pause();
  
  // Returns if this source is paused
  public boolean isPaused();

  // Stop this source
  public void stop();
  
  // Returns if this source is stopped
  public boolean isStopped();

  // Rewind this source
  public void rewind();
  
  // Get the current position in seconds
  public float getPosition();
  
  // Set the position in seconds
  public void setPosition(float position);
}
