package com.dengsn.crucialalpha.json;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonValue;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JsonContext
{
  // Variables
  private final Map<Class,JsonSerializer> serializers = new HashMap<>();
  private final Map<Class,JsonDeserializer> deserializers = new HashMap<>();
  
  // Adds a serializer
  public <T> JsonContext registerSerializer(Class<T> type, JsonSerializer<T> serializer)
  {
    this.serializers.put(type,serializer);
    return this;
  }
  
  // Removes a serializer
  public <T> JsonContext unregisterSerializer(Class<T> type)
  {
    this.serializers.remove(type);
    return this;
  }
  
  // Adds a deserializer
  public <T> JsonContext registerDeserializer(Class<T> type, JsonDeserializer<T> deserializer)
  {
    this.deserializers.put(type,deserializer);
    return this;
  }
  
  // Removes a deserializer
  public <T> JsonContext unregisterDeserializer(Class<T> type)
  {
    this.deserializers.remove(type);
    return this;
  }

  // Serialize
  public <T> JsonValue serialize(T object) throws JsonException
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
    throw new JsonException("No serializer present for " + object.getClass().getName());
  }
  
  // Serialize to string
  public <T> String serializeToString(T object) throws JsonException
  {
    return this.serialize(object).toString();
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
    throw new JsonException("No deserializer present for " + type.getName());
  }
  
  // Deserialize from string
  public <T> T deserializeFromString(String string, Class<T> type) throws JsonException
  {
    return this.deserialize(Json.parse(string),type);
  }
  
  // Deserialize from input stream
  public <T> T deserializeFromStream(InputStream in, Class<T> type) throws JsonException
  {
    return this.deserializeFromString(new Scanner(in).useDelimiter("\\A").next(),type);
  }
}
