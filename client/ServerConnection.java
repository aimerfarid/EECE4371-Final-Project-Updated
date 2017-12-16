import java.io.*;
import java.net.*;

public class ServerConnection {
  private Socket socket;
  private BufferedReader inFromServer;
  private DataOutputStream outToServer;

  ServerConnection(String hostAddress, int hostPort) throws Exception {
    socket = new Socket(hostAddress, hostPort);
    outToServer = new DataOutputStream(socket.getOutputStream());
    inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  }

  public void writeLine(String message) throws Exception {
    outToServer.writeBytes(message.trim() + '\n');
  }

  public String readLine() throws Exception {
    String messageFromServer = inFromServer.readLine();
    System.out.println("Message from server: " + messageFromServer);
    return messageFromServer;
  }

  public void close() throws Exception {
    socket.close();
  }
}
