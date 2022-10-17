package prr.core;

import java.util.*;

import static prr.core.ClientLevel.NORMAL;

public class Client {
  private final String _key;
  private String _name;
  private int _taxNumber;
  private ClientLevel _level;
  private boolean _receiveNotifications;
  private List<Terminal> _terminals;

  public Client(String key, String name, int taxNumber) {
    _key = key;
    _name = name;
    _taxNumber = taxNumber;
    _level = NORMAL;
    _receiveNotifications = false;
    ArrayList<Terminal> _terminals = new ArrayList<Terminal>();
  }

  private int Getter() {
     return _;
  }
}
