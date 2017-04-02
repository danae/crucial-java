package com.dengsn.crucial.audio;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;

public class Audio implements AutoCloseable
{
  // Variables
  private long device;
  private long context;

  // Constructor
  public Audio() throws AudioException
  {
    this.initializeAL();
  }
  
  // Close the resource
  @Override public void close()
  {
    ALC10.alcDestroyContext(this.context);
    ALC10.alcCloseDevice(this.device);
  }

  // OpenAL initialization
  public final void initializeAL() throws AudioException
  {
    // Create an AL device
    String defaultDeviceName = ALC10.alcGetString(0,ALC10.ALC_DEFAULT_DEVICE_SPECIFIER);
    if (defaultDeviceName == null)
      throw new AudioException("No default sound device specified (do you have any sound cards available?)");
    this.device = ALC10.alcOpenDevice(defaultDeviceName);
    
    // Create an AL context
    int[] attributes = {0};
    this.context = ALC10.alcCreateContext(this.device,attributes);
    ALC10.alcMakeContextCurrent(this.context);
    
    // Create capabilities
    ALCCapabilities alcCapabilities = ALC.createCapabilities(this.device);
    ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);
    if (!alCapabilities.OpenAL10)
      throw new AudioException("No OpenAL10 capabilities (do you have any sound cards available?)");
  }
}
