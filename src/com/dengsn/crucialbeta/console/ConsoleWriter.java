package com.dengsn.crucialbeta.console;

import com.dengsn.crucial.graphics.Color;
import java.io.IOException;
import java.io.Writer;

public class ConsoleWriter extends Writer
{
  // Variables
  private final Console console;
  private final Color color;
  
  // Constructor
  public ConsoleWriter(Console console, Color color)
  {
    this.console = console;
    this.color = color;
  }
  
  // OutputStream methods
  /*@Override public void write(int b) throws IOException
  {
    if (b == '\n')
    {
      this.console.log(this.buffer.toString(),this.color);
      this.buffer.setLength(0);
    }
    else
      this.buffer.appendCodePoint(b);
  }*/

  @Override public void write(char[] buffer, int offset, int length) throws IOException
  {
    try
    {
      String string = new String(buffer,offset,length);
      this.console.log(string,this.color);
    }
    catch (IndexOutOfBoundsException ex)
    {
      throw new IOException(ex.getMessage(),ex);
    }
  }

  @Override public void flush() throws IOException
  {
    throw new UnsupportedOperationException("The console cannot be flushed");
  }

  @Override public void close() throws IOException
  {
    throw new UnsupportedOperationException("The console cannot be closed");
  }
}
