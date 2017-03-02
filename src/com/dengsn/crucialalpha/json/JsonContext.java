package com.dengsn.crucialalpha.json;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonValue;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JsonContext
{
  // Variables
  private final Map<Class,JsonSerializer> serializers = new HashMap<>();
  private final Map<Class,JsonDeserializer> deserializers = new HashMap<>();
  
  // Register
  public <T> JsonContext serializeWith(Class<T> type, JsonSerializer<T> serializer)
  {
    this.serializers.put(type,serializer);
    return this;
  }
  public <T> JsonContext deserializeWith(Class<T> type, JsonDeserializer<T> deserializer)
  {
    this.deserializers.put(type,deserializer);
    return this;
  }

  // Serialize
  public <T> JsonValue serialize(T object)
  {
    for (Map.Entry<Class,JsonSerializer> e : this.serializers.entrySet())
    {
      if (e.getKey().isAssignableFrom(object.getClass()))
      {
        if (e.getValue() instanceof JsonSerializer)
          return ((JsonSerializer)e.getValue()).serialize(object,this);
        else
          return e.getValue().serialize(object);
      }
    }

    // No serializer found
    return new FieldSerializer().serialize(object,this);
  }
  
  // Serialize to String
  public <T> String serializeToString(T object)
  {
    return this.serialize(object).toString();
  }
  
  // Serialize to ByteBuffer
  public <T> ByteBuffer serializeToByteBuffer(T object) throws JsonException
  {
    String string = this.serializeToString(object);
    return ByteBuffer.wrap(string.getBytes(StandardCharsets.UTF_8));
  }
  
  // Deserialize
  public <T> T deserialize(JsonValue json, Class<T> type) throws JsonException
  {
    for (Map.Entry<Class,JsonDeserializer> e : this.deserializers.entrySet())
    {
      if (e.getKey().isAssignableFrom(type))
      {
        if (e.getValue() instanceof JsonDeserializer)
          return (T)((JsonDeserializer)e.getValue()).deserialize(json,type,this);
        else
          return (T)e.getValue().deserialize(json,type);
      }
    }
    
    // No deserializer found
    return null;
  }
  
  // Deserialize from String
  public <T> T deserializeFromString(String string, Class<T> type) throws JsonException
  {
    return this.deserialize(Json.parse(string),type);
  }
  
  // Deserialize from ByteBuffer
  public <T> T deserializeFromByteBuffer(ByteBuffer buffer, Class<T> type) throws JsonException
  {
    byte[] bytes = new byte[buffer.remaining()];
    buffer.get(bytes);
    return this.deserializeFromString(new String(bytes,StandardCharsets.UTF_8),type);
  }
}
