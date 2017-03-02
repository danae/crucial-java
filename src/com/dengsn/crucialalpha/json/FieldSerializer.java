package com.dengsn.crucialalpha.json;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldSerializer<T> implements JsonSerializer<T>
{
  @Override public JsonValue serialize(T object, JsonContext context)
  {
    // Create a new JSON object
    JsonObject json = new JsonObject();
    
    // Iterate over object fields
    Class c = object.getClass();
    while (!c.equals(Object.class))
    {
      // Loop over fields and add to JSON
      for (Field f : c.getDeclaredFields())
      {
        // If transient, then skip
        if (Modifier.isTransient(f.getModifiers()))
          continue;
         
        // Set the field
        try
        {
          f.setAccessible(true);
          
          Object o = f.get(object);
          if (o instanceof Integer)
            json.add(f.getName(),(Integer)o);
          else if (o instanceof Long)
            json.add(f.getName(),(Long)o);
          else if (o instanceof Float)
            json.add(f.getName(),(Float)o);
          else if (o instanceof Double)
            json.add(f.getName(),(Double)o);
          else if (o instanceof Boolean)
            json.add(f.getName(),(Boolean)o);
          else if (o instanceof String || o instanceof Enum)
            json.add(f.getName(),o.toString());
          else 
            json.add(f.getName(),context.serialize(o));
        }
        catch (SecurityException | IllegalArgumentException | IllegalAccessException ex)
        {
          continue;
        }
      }
      
      // Go one step deeper in the classes
      c = c.getSuperclass();
    }
    
    // Return the JSON object
    return json;
  }
}
