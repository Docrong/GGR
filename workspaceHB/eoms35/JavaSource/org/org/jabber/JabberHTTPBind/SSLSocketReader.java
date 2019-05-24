package org.jabber.JabberHTTPBind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import javax.net.ssl.SSLSocket;

public class SSLSocketReader extends BufferedReader
{
  private SSLSocket sock;

  public SSLSocketReader(SSLSocket sock)
    throws IOException
  {
    super(new InputStreamReader(sock.getInputStream(), "UTF-8"));
    this.sock = sock;
  }

  public boolean ready()
  {
    try
    {
      int oldTimeout = this.sock.getSoTimeout();
      this.sock.setSoTimeout(10);

      mark(1);
      try
      {
        read();
      } catch (SocketTimeoutException e) {
        this.sock.setSoTimeout(oldTimeout);
        return false;
      }

      reset();
      this.sock.setSoTimeout(oldTimeout);

      return true;
    }
    catch (SocketException e1) {
      throw new RuntimeException("SSLSocketReader unable to set socket timeout: \n" + e1); } catch (IOException e) {
    	  throw new RuntimeException("SSLSocketReader unable to access inputstream: \n" + e);
      }
    
  }
}
