import java.io.*;
import java.net.*;

public class ClientConnection {
  private Socket connectionSocket;
  private BufferedReader inFromClient;
  private DataOutputStream outToClient;

  public void acceptConnection(ServerSocket serverSocket) throws Exception {
    connectionSocket = serverSocket.accept();
    inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
    outToClient = new DataOutputStream(connectionSocket.getOutputStream());
  }

  public String readLine() throws Exception {
    return inFromClient.readLine();
  }

  public void writeLine(String message) throws Exception {
    outToClient.writeBytes(message.trim() + '\n');
  }
}
