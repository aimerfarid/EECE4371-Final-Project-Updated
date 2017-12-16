import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.reflect.Field;

public class StoreServer {
  private static final String COMMAND_PROMPT = "Generate or Retrieve?";
  private static final String HACK_PROMPT = "WRITE HACK!";
  private static final String CATEGORY_PROMPT = "Which category?";
  private static final String STORE_SUCCESS = "Successfully stored HACK: ";
  private static final String RETRIEVE_PROMPT = "Please enter which HACK categories!";
  private static final String VALUE = "Value: ";
  private static final String KEY_VALUE_SEPERATOR = ": ";
  private static final String INVALID_KEY = "Invalid key!";
  private static final String INVALID_COMMAND = "Invalid command.";
  private static final String UP_DOWN_VOTE = "0) Upvote(u) 1) Downvote(d)";
  private static final String STATUS_VOTE = "Current vote: ";
  private static final String READY_HACKS = " HACKS! Ready? (Yes?)";
  private static final String ENTER_PROCEED = " ____Press ENTER to PROCEED____";
  private static final String END_HACK = " ____HACK ENDED____";

  private static final String GENERATE_HACK = "generate";
  private static final String RETRIEVE_HACK = "retrieve";
  private static final int HACK_INDEX = 0;

  private ServerSocket serverSocket;
  private ClientConnection clientConnection;

  private Hacks[] hCategoryArray = new Hacks[100];

  private HashMap<String, String> dataStore = new HashMap<String, String>();

  StoreServer(int port) throws Exception {
    serverSocket = new ServerSocket(port);
    clientConnection = new ClientConnection();
  }

  public void run() throws Exception {
    while (true) {
      clientConnection.acceptConnection(serverSocket);
      String command = promptUser(COMMAND_PROMPT);
      performCommand(command);
    }
  }

  private void performCommand(String command) throws Exception {
    switch (command) {
    case GENERATE_HACK:
      generate();
      return;
    case RETRIEVE_HACK:
      retrieve();
      return;
    }
    invalidCommand();
  }

  private String promptUser(String prompt) throws Exception {
    clientConnection.writeLine(prompt);
    return clientConnection.readLine();
  }

  private void generate() throws Exception {
    String hTitleHack = promptUser(HACK_PROMPT);
    String hCategoryHack = promptUser(CATEGORY_PROMPT);
    // Place according to each category
    int hCatHack = Integer.parseInt(hCategoryHack);
    int hBaseIndex = Integer.parseInt(hCategoryHack + HACK_INDEX);
    int hVotes = 0;
    String hAuthor = "mohdroef"; //LOGIN INFO
    Date hCreatedAt = new Date();

    // Find an empty spot in the array
    while(hCategoryArray[hBaseIndex] != null){
      hBaseIndex++;
    }
    hCategoryArray[hBaseIndex] = new Hacks(hTitleHack, hCatHack, hVotes, hAuthor, hCreatedAt);
    clientConnection.writeLine(STORE_SUCCESS + hTitleHack);
  }

  private void retrieve() throws Exception {
    Hacks value;
    String updownVotes;
    String keyCategory = promptUser(RETRIEVE_PROMPT);
    int hBaseIndex = Integer.parseInt(keyCategory + HACK_INDEX);
    if (hBaseIndex >= 60 || hBaseIndex < 0) {
      clientConnection.writeLine(INVALID_KEY);
    } else {
      int count = 0;
      for (int i = hBaseIndex; i < (hBaseIndex + 10); i++) {
         if (hCategoryArray[i] != null) {
            count++;
         }
      }
      // Initiate the List of Hacks.
      String readyHacks = promptUser(count + READY_HACKS);

      while(hCategoryArray[hBaseIndex] != null) {
        value = hCategoryArray[hBaseIndex];
        updownVotes = promptUser(value + KEY_VALUE_SEPERATOR + UP_DOWN_VOTE);
        if (updownVotes.equals("u") || updownVotes.equals("U")){
          value.hVotes++;
        } else if (updownVotes.equals("d") || updownVotes.equals("D")) {
          value.hVotes--;
        }
        count--;
        if (count > 0) {
          clientConnection.writeLine(STATUS_VOTE + value.hVotes + ENTER_PROCEED);
        } else {
          clientConnection.writeLine(STATUS_VOTE + value.hVotes + END_HACK);
        }
        hBaseIndex++;
      }
    }
    //System.out.println(value);
    //clientConnection.writeLine("THE END!");
    // if (value == null) {
    //   clientConnection.writeLine(INVALID_KEY);
    // }
    // else {
    //   clientConnection.writeLine(value);
    // }
  }

  private void invalidCommand() throws Exception {
    clientConnection.writeLine(INVALID_COMMAND);
  }
}

//EXTRA INFO

  /**
  * Intended only for debugging.
  *
  * <P>Here, the contents of every field are placed into the result, with
  * one field per line.
  */
  // @Override public String toString() {
  //   StringBuilder result = new StringBuilder();
  //   String NEW_LINE = System.getProperty("line.separator");

  //   result.append(this.getClass().getName() + " Content {" + NEW_LINE);
  //   result.append(" Hack Title: " + hTitleHack + NEW_LINE);
  //   result.append(" Hack Category: " + hCategoryHack + NEW_LINE);
  //   result.append(" Votes: " + hVotes + NEW_LINE );
  //   result.append(" Hack Author: " + hAuthor + NEW_LINE);
  //   result.append(" Date Created: " + hCreatedAt + NEW_LINE);
  //   result.append("}");

  //   return result.toString();
  // }
