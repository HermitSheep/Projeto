package prr.core;

import java.io.*;

import java.util.LinkedList;

import prr.core.exception.UnrecognizedEntryException;
import prr.core.exception.UnknownIdentifierException;

  import static prr.core.ClientLevel.NORMAL;
// import more exception core classes if needed

/*
 * A concretização desta classe depende da funcionalidade suportada pelas entidades do core:
 *  - adicionar um cliente e terminal a uma rede de terminais;
 *  - indicar o estado de um terminal
 *  - adicionar um amigo a um dado terminal
 * A forma como estas funcionalidades estão concretizaas terão impacto depois na concretização dos
 * métodos parseClient, parseTerminal e parseFriends
 */

public class Parser {
  private Network _network;

  Parser(Network network) {
    _network = network;
  }

  void parseFile(String filename) throws IOException, UnrecognizedEntryException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);
    }
  }

  private void parseLine(String line) throws UnrecognizedEntryException {
    String[] components = line.split("\\|");

    switch(components[0]) {
      case "CLIENT" -> parseClient(components, line);
      case "BASIC", "FANCY" -> parseTerminal(components, line);
      case "FRIENDS" -> parseFriends(components, line);
      default -> throw new UnrecognizedEntryException("Line with wong type: " + components[0]);
    }
  }

  private void checkComponentsLength(String[] components, int expectedSize, String line) throws UnrecognizedEntryException {
    if (component.length != expectedSize)
      throw new UnrecognizedEntryException("Invalid number of fields in line: " + line);
  }

  // parse a client with format CLIENT|id|nome|taxId
  private void parseClient(String[] components, String line) throws UnrecognizedEntryException {
    checkComponentsLength(components, 4, line);

    try {
      int taxNumber = Integer.parseInt(components[3]);
      _network.registerClient(components[1], components[2], taxNumber);
    } catch (NumberFormatException nfe) {
      throw new UnrecognizedEntryException("Invalid number in line " + line, nfe);
    } catch (OtherException e) {
      throw new UnrecognizedEntryException("Invalid specification in line: " + line, e);
    }
  }

  // parse a line with format terminal-type|idTerminal|idClient|state
  private void parseTerminal(String[] components, String line) throws UnrecognizedEntryException {
    checkComponentsLength(components, 4, line);

    try {
      Terminal terminal = _network.registerTerminal(components[0], components[1], components[2]);
      switch(components[3]) {
        case "SILENCE" -> terminal.setOnSilent();
        case "OFF" -> terminal->turnOff();
        default -> {
          if (!components[3].equals("ON"))
            throw new UnrecognizedEntryException("Invalid specification in line: " + line);
        }
      }
    } catch (SomeOtherException e) {
      throw new UnrecognizedEntryException("Invalid specification: " + line, e);
    }
  }

  //Parse a line with format FRIENDS|idTerminal|idTerminal1,...,idTerminalN
  private void parseFriends(String[] components, String line) throws UnrecognizedEntryException {
    checkComponentsLength(components, 3, line);

    try {
      String terminal = components[1];
      String[] friends = components[2].split(",");

      for (String friend : friends)
        _network.addFriend(terminal, friend);
    } catch (OtherException e) {
      throw new UnrecognizedEntryException("Some message error in line:  " + line, e);
    }
  }

  public static class Client {
    private final String _key;
    private String _name;
    private int _taxNumber;
    private ClientLevel _level;
    private boolean _receiveNotifications;
    LinkedList<Terminal> _terminals;

    public Client(String key, String name, int taxNumber) {
      _key = key;
      _name = name;
      _taxNumber = taxNumber;
      _level = NORMAL;
      _receiveNotifications = false;
      LinkedList<Terminal> _terminals = new LinkedList<Terminal>();
    }


  }