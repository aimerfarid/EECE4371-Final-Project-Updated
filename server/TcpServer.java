import java.io.*;
import java.net.*;

public class TcpServer {
  private static final int PORT = 6789;

  public static void main(String[] args) {
    try {
      StoreServer storeServer = new StoreServer(PORT);
      storeServer.run();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
