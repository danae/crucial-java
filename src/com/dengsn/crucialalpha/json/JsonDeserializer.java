package com.dengsn.crucialalpha.json;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonValue;

@FunctionalInterface
public interface JsonDeserializer<T>
{
  public T deserialize(JsonValue json, Class<T> type, JsonContext context) throws JsonException;
  
  // Deserialize without context
  public default T deserialize(JsonValue json, Class<T> type) throws JsonException
  {
    return this.deserialize(json,type,new JsonContext());
  }
}
