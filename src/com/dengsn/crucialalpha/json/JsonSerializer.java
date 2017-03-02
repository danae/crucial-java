package com.dengsn.crucialalpha.json;

import com.eclipsesource.json.JsonValue;

@FunctionalInterface
public interface JsonSerializer<T>
{
  public JsonValue serialize(T object, JsonContext context);
  
  // Serialize without context
  public default JsonValue serialize(T object)
  {
    return this.serialize(object,new JsonContext());
  }
}
