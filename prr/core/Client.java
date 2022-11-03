package prr.core;

import java.io.Serializable;
import java.util.*;

import javax.swing.TransferHandler;
import prr.core.exception.StateNotChangedException;


public class Client implements Serializable{
  private String _key;
  private String _name;
  private int _nif;
  private ClientLevel _level;
  private List<Terminal> _terminals;
  private long _payed;
  private long _debt;
  private boolean _notiSet;
  private TariffPlan _plan;

  public Client(String k, String n, int nif, TariffPlan plan){
      _name = n;
      _key = k;
      _nif = nif;
      _terminals = new ArrayList<>();
      _level = ClientLevel.NORMAL;
      _notiSet = true;
      _debt = 0;
      _payed = 0;
      _plan = plan;
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

  public long getPayed(){
      return _payed;
  }

  public long getDebt(){
      return _debt;
  }

  public TariffPlan getPlan() {
    return _plan;
  }

  public void addDebt(double debt) {
    _debt += debt;
  }

  public void addPayed(double payed) {
    _payed += payed;
  }

  public void turnNotiOn() throws StateNotChangedException{
    if (_notiSet)
      throw new StateNotChangedException(_key, _notiSet);
    _notiSet = true;
  }

  public void turnNotiOff() throws StateNotChangedException{
    if (!_notiSet)
      throw new StateNotChangedException(_key, _notiSet);
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
                + "|" + Math.round(_debt) + "\n");                                   //|Debt
    for (Terminal t: _terminals){
      for (Notification n: t.getNotifications()){         //FIXMEPLS getNotifications doesnt exist, nor toString
        client += n.toString() + "\n";
      }
      client += t.getId();
    }
    return client;
  }
}
