package com.dengsn.crucial.audio.decoder;

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
  // Decode ByteBuffer to PCM
  @Override public DecodedBuffer decode(ByteBuffer buffer) throws AudioException
  {
    IntBuffer error = BufferUtils.createIntBuffer(1);
    long handle = STBVorbis.stb_vorbis_open_memory(buffer,error,null);
    if (handle == NULL)
      throw new AudioException("Decoding with " + this.getClass().getName() + " failed: " + error.get(0));
    
    STBVorbisInfo info = STBVorbisInfo.malloc();
    STBVorbis.stb_vorbis_get_info(handle,info);

    int channels = info.channels();
    int samples = STBVorbis.stb_vorbis_stream_length_in_samples(handle);

    ShortBuffer pcm = BufferUtils.createShortBuffer(samples);
    int sampleRate = info.sample_rate();
    
    STBVorbis.stb_vorbis_get_samples_short_interleaved(handle,channels,pcm);
    STBVorbis.stb_vorbis_close(handle);
    info.free();

    return new DecodedBuffer(pcm,ALFormat.of(channels,16),sampleRate);
  }
}
