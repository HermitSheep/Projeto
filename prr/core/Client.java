package core;

import java.util.LinkedList;

import static core.ClientLevel.NORMAL;

public class Client {
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
