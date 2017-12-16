import java.io.*;
import java.net.*;

public class StoreClient {
  private static final String GENERATE_HACK = "generate";
  private static final String RETRIEVE_HACK = "retrieve";

  private ServerConnection serverConnection;

  StoreClient(String hostAddress, int hostPort) throws Exception {
    serverConnection = new ServerConnection(hostAddress, hostPort);
  }

  public void run() throws Exception {
    serverConnection.readLine();
    String command = getUserInput();
    serverConnection.writeLine(command);

    //TRY DOING EVErYTHING HERE
    // performRequestResponseCycles(numRequestResponseCycles(command));
    performRequestResponseCycles(command);

    serverConnection.readLine();
    serverConnection.close();
  }

  private String getUserInput() throws Exception {
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    return inFromUser.readLine().trim();
  }

  // private int numRequestResponseCycles(String command) {
  //   switch (command) {
  //   case GENERATE_HACK:
  //     return 2;
  //   case RETRIEVE_HACK:
  //     return 3;
  //   }
  //   return 0;
  // }
  //
  // private void performRequestResponseCycles(int requestResponseCycles) throws Exception {
  //   printList();
  //
  //   for (int i = 0; i < requestResponseCycles; i++) {
  //     String test = serverConnection.readLine(); // 1) Category, 2)Num of Hacks
  //     String userInput = getUserInput();
  //     serverConnection.writeLine(userInput);
  //   }
  // }

  private void performRequestResponseCycles(String command) throws Exception {
    int requestResponseCycles = 2;
    if(command.equalsIgnoreCase(GENERATE_HACK)){
      for (int i = 0; i < requestResponseCycles; i++) {
        if (i == 1) {
          printList();
        }
        String test = serverConnection.readLine();
        String userInput = getUserInput();
        serverConnection.writeLine(userInput);
      }
    }
    else if (command.equalsIgnoreCase(RETRIEVE_HACK)) {
      printList();
      for (int i = 0; i < requestResponseCycles; i++) {
        String test = serverConnection.readLine(); // 1) Category, 2)Num of Hacks, 3) UpDown Votes
        if (i == 1) {
          int testInt = Character.getNumericValue(test.charAt(0));
          requestResponseCycles = (testInt*2) + 1;
        }
        String userInput = getUserInput();
        serverConnection.writeLine(userInput);
      }
    }
    // printList();

    // for (int i = 0; i < requestResponseCycles; i++) {
    //   String test = serverConnection.readLine(); // 1) Category, 2)Num of Hacks
    //   String userInput = getUserInput();
    //   serverConnection.writeLine(userInput);
    // }
  }

// if (userInput.matches("0|1|2|3|4|5")) {
      //   int hBaseIndex = Integer.parseInt(userInput + HACK_INDEX);


      // }


  private void printList() {
    System.out.println("Categories");
    System.out.println("__________");
    System.out.println("0) Academics");
    System.out.println("1) School");
    System.out.println("2) Dining Hall");
    System.out.println("3) Night Life");
    System.out.println("4) Nashville");
    System.out.println("5) Etc.");
    }

}
