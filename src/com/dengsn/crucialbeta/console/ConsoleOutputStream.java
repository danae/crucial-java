package com.dengsn.crucialbeta.console;

import com.dengsn.crucial.graphics.color.Color;
import java.io.IOException;
import java.io.OutputStream;

public class ConsoleOutputStream extends OutputStream
{
  // Variables
  private final Console console;
  private final Color color;
  private final StringBuffer buffer;
  
  // Constructor
  public ConsoleOutputStream(Console console, Color color)
  {
    this.console = console;
    this.color = color;
    this.buffer = new StringBuffer();
  }
  
  // OutputStream methods
  @Override public void write(int b) throws IOException
  {
    if (b == '\n')
    {
      this.console.log(this.buffer.toString(),this.color);
      this.buffer.setLength(0);
    }
    else
      this.buffer.appendCodePoint(b);
  }

  // Writer methods
  public void write(char[] cbuf, int off, int len)
  {
    // Create a string of the desired text
    String string = new String(cbuf).substring(off,off + len);
    
    // Split the string 
    String[] strings = string.split("\n");
    int length = strings.length;
    if (strings[strings.length-1].trim().isEmpty())
      length --;
    
    // Write the strings to the console
    for (int i = 0; i < length; i ++)
      this.console.log(strings[i],this.color);
  }
}
