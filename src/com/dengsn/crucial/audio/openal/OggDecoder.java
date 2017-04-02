package com.dengsn.crucial.audio.openal;

import com.dengsn.crucial.audio.openal.ALBuffer;
import com.dengsn.crucial.audio.AudioException;
import com.dengsn.crucial.audio.openal.ALFormat;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBVorbis;
import org.lwjgl.stb.STBVorbisInfo;
import static org.lwjgl.system.MemoryUtil.NULL;

public class OggDecoder implements Decoder
{
  // Decodes a buffer to PCM
  @Override public ALBuffer decode(ByteBuffer buffer) throws AudioException
  {
    IntBuffer error = BufferUtils.createIntBuffer(1);
    
    // Open the buffer in memory
    long handle = STBVorbis.stb_vorbis_open_memory(buffer,error,null);
    if (handle == NULL)
      throw new AudioException("Decoding with " + this.getClass().getSimpleName() + " failed: " + error.get(0));
    
    try (STBVorbisInfo info = STBVorbisInfo.malloc()) 
    {
      STBVorbis.stb_vorbis_get_info(handle,info);

      int channels = info.channels();
      int sampleRate = info.sample_rate();
      int sampleCount = STBVorbis.stb_vorbis_stream_length_in_samples(handle);

      ShortBuffer pcm = BufferUtils.createShortBuffer(sampleCount * channels);
    
      STBVorbis.stb_vorbis_get_samples_short_interleaved(handle,channels,pcm);
      STBVorbis.stb_vorbis_close(handle);
      
      return new ALBuffer(pcm,ALFormat.of(channels,16),sampleRate);
    }
  }
}
