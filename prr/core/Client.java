package prr.core;

import java.io.Serializable;
import java.util.*;


public class Client implements Serializable{
  private String _key;         //FIXME tem de ser uma String, o stor disse
  private String _name;
  private int _nif;
  private ClientLevel _level;
  private List<Terminal> _terminals;
  private int _payed;
  private int _debt;
  private boolean _notiSet;

  public Client(String k, String n, int nif){
      _name = n;
      _key = k;
      _nif = nif;
      _terminals = new ArrayList<>();
      _level = ClientLevel.NORMAL;
      _notiSet = true;
      _debt = 0;
      _payed = 0;
  }

  public String getKey(){
    return _key;
    }

  public String getName(){
    return _name;
  }

  public int getNif() {
    return _nif;
  }

  public ClientLevel getLevel(){
    return _level;
  }

  public boolean getNotiSet() {
     return _notiSet;
  }

  public List<Terminal> getTerminals(){
      return _terminals;
  }

  public int getPayed(){
      return _payed;
  }

  public int getDebt(){
      return _debt;
  }

  public void turnNotiOn() {
    _notiSet = true;
  }

  public void turnNotiOff() {
    _notiSet = false;
  }

  public void addTerminal(Terminal term) {
    _terminals.add(term);
  }

  public List<Notification> getNotifications() {
      List<Notification> noti = new ArrayList<Notification>();
      for (Terminal term : _terminals) {
          if (!term.getNotifications().isEmpty())
          noti.addAll(term.getNotifications());
      }
      return noti;
  }

  public boolean hasNotifications() {
      for (Terminal term : _terminals) {
          if (!term.getNotifications().isEmpty())
          return true;
      }
      return false;
  }

  public int activeTerminals() {
      int res = 0;
      for (Terminal term : _terminals)
          if (term.isActive())
              res++;
      return res;
  }

  public String toString() {
      String client = ("CLIENT|" + _key + "|" + _name + "|" +                         //CLIENT|key|name
                      String.valueOf(_nif) + "|" + _level.name());                    //|Nif|level
      if (_notiSet)
          client += ("|YES");                                                         //|YES
      else
          client += ("|NO");                                                          //|NO
      client += ("|" + String.valueOf(activeTerminals()) + "|" + Math.round(_payed)   //|Active terminals|Payed
                  + "|" + Math.round(_debt));                                         //|Debt
      return client;
  }
  
}
