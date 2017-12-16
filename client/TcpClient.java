import java.io.*;
import java.net.*;

public class TcpClient {
  private static final String HOST_ADDRESS = "127.0.0.1";
  private static final int HOST_PORT = 6789;

  public static void main(String[] args) {
    try {
      StoreClient storeClient = new StoreClient(HOST_ADDRESS, HOST_PORT);
      storeClient.run();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
